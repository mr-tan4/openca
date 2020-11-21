package com.robert.openca.controller;

import com.robert.openca.constant.UUIDUtil;
import com.robert.openca.dao.key.KeyPairDO;
import com.robert.openca.dao.key.PrivateKeyDO;
import com.robert.openca.dao.key.PublicKeyDO;
import com.robert.openca.dao.key.SecretKeyDO;
import com.robert.openca.key.keypair.KeyPairStrut;
import com.robert.openca.key.soft.KeyPairGenerator;
import com.robert.openca.key.soft.SecretKeyGenerator;
import com.robert.openca.service.key.KeyPairDao;
import com.robert.openca.service.key.PrivateKeyDao;
import com.robert.openca.service.key.PublicKeyDao;
import com.robert.openca.service.key.SecretKeyDao;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;


/**
 * 密钥控制器
 *
 * @author robert
 * @version 1.0
 * @since 2020-05-21
 */

@Api()
@RestController()
@Slf4j
public class KeyController {

    @Autowired
    private KeyPairDao keyPairDao;

    @Autowired
    private PublicKeyDao publicKeyDao;

    @Autowired
    private PrivateKeyDao privateKeyDao;

    @Autowired
    private SecretKeyDao secretKeyDao;

    /**
     * 创建密钥对
     *
     * @param keyPairStrut json格式的请求文件
     */
    @ApiOperation(value = "生成密钥对", notes = "生成密钥对")
    @PostMapping("/create/KeyPair")
    public void createKeyPair(@RequestBody @NotNull KeyPairStrut keyPairStrut) throws Exception {
        PrivateKeyDO privateKeyDO = keyPairStrut.getPrivateKeyDO();
        PublicKeyDO publicKeyDO = keyPairStrut.getPublicKeyDO();
        try {
            new KeyPairGenerator().generatorKeyPair(keyPairStrut);
            privateKeyDO.setId(UUIDUtil.get());
            publicKeyDO.setId(UUIDUtil.get());
            privateKeyDao.save(privateKeyDO);
            publicKeyDao.save(publicKeyDO);
            KeyPairDO keyPairDO = new KeyPairDO();
            keyPairDO.setID(UUIDUtil.get());
            keyPairDO.setPublic_key_id(publicKeyDO.getId());
            keyPairDO.setPrivate_key_id(privateKeyDO.getId());
            keyPairDO.setBackup(false);
            keyPairDO.setAlias(keyPairStrut.getAlias());
            keyPairDao.save(keyPairDO);
            log.info("创建密钥对成功!");
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    /**
     * 创建对称密钥
     *
     * @param secretKeyDO
     */
    @PostMapping("/create/SecureKey")
    public void createSecureKey(@RequestBody @NotNull SecretKeyDO secretKeyDO) {
        try {
            new SecretKeyGenerator().generatorSecretKey(secretKeyDO);
            secretKeyDO.setId(UUIDUtil.get());
            secretKeyDO.setBackup(false);
            secretKeyDao.save(secretKeyDO);
            log.error("生成密钥成功!");
        } catch (Exception e) {
            e.printStackTrace();
            log.error("生成密钥失败!");
        }
    }

    /**
     * 吊销密钥对
     *
     * @param id 密钥对ID
     */
    @GetMapping("/revoke/KeyPair")
    public void revokeKeyPair(@RequestParam("id") @NotNull String id) {
        KeyPairDO keyPairDO = keyPairDao.getOne(id);
        PublicKeyDO publicKeyDO = publicKeyDao.getOne(keyPairDO.getPublic_key_id());
        PrivateKeyDO privateKeyDO = privateKeyDao.getOne(keyPairDO.getPrivate_key_id());
        publicKeyDO.setRevoked(true);
        privateKeyDO.setRevoked(true);
        keyPairDO.setRevoked(false);
        publicKeyDao.save(publicKeyDO);
        privateKeyDao.save(privateKeyDO);
        keyPairDao.save(keyPairDO);
    }

    /**
     * 吊销对称密钥
     *
     * @param id 密钥ID
     */
    @GetMapping("/revoke/SecureKey")
    public void revokeSecureKey(@RequestParam("id") @NotNull String id) {
        SecretKeyDO secretKeyDO = secretKeyDao.getOne(id);
        secretKeyDO.setRevoked(true);
        secretKeyDao.save(secretKeyDO);
    }

    /**
     * 安全销毁密钥对
     *
     * @param id
     */
    @GetMapping("/destroy/KeyPair")
    public void destroyKeyPair(@RequestParam("id") @NotNull String id) {
        KeyPairDO keyPairDO = keyPairDao.getOne(id);
        publicKeyDao.deleteById(keyPairDO.getPublic_key_id());
        privateKeyDao.deleteById(keyPairDO.getPrivate_key_id());
        keyPairDao.deleteById(id);
    }

    /**
     * 安全销毁密钥
     */

    @GetMapping("/destroy/SecureKey")
    public void destroySecureKey(@RequestParam("id") @NotNull String id) {
        secretKeyDao.deleteById(id);
    }

    /**
     * 修改密钥对中的密钥用途
     *
     * @param keyPairDO
     */
    @PostMapping("/modify/KeyPair/keyUse")
    public void modifyKeyPairKeyUse(@RequestBody @NotNull KeyPairDO keyPairDO) {
        keyPairDao.save(keyPairDO);
    }

    /**
     * 修改密钥对中的扩展密钥用户
     *
     * @param keyPairDO
     */
    @PostMapping("/modify/KeyPair/extKeyUse")
    public void modifyKeyPairExtKeyUse(@RequestBody @NotNull KeyPairDO keyPairDO) {
        keyPairDao.save(keyPairDO);
    }

    /**
     * 备份密钥对
     *
     * @param id
     */
    @GetMapping("backup/KeyPair")
    public void backUpKeyPair(@RequestParam("id") @NotNull String id) throws CloneNotSupportedException {
        KeyPairDO keyPairDO = keyPairDao.getOne(id);
        KeyPairDO var = keyPairDO.clone();
        var.setID(UUIDUtil.get());
        var.setBackup(true);
        keyPairDao.save(var);
    }

    /**
     * 备份密钥
     *
     * @param id
     */
    @GetMapping("backup/SecureKey")
    public void backUpSecureKey(@RequestParam("id") @NotNull String id) {
        SecretKeyDO secretKeyDO = secretKeyDao.getOne(id);
        secretKeyDO.setId(UUIDUtil.get());
        secretKeyDO.setBackup(true);
        secretKeyDao.save(secretKeyDO);
    }
}
