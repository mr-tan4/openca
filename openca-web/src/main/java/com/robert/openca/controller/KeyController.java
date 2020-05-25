package com.robert.openca.controller;

import com.robert.openca.constant.UUIDUtil;
import com.robert.openca.dao.key.KeyPairDO;
import com.robert.openca.dao.key.PrivateKeyDO;
import com.robert.openca.dao.key.PublicKeyDO;
import com.robert.openca.dao.key.SecureKeyDO;
import com.robert.openca.key.keypair.KeyPairStrut;
import com.robert.openca.key.soft.KeyPairGenerator;
import com.robert.openca.service.key.KeyPairDao;
import com.robert.openca.service.key.PrivateKeyDao;
import com.robert.openca.service.key.PublicKeyDao;
import com.robert.openca.service.key.SecureKeyDao;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
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
    private SecureKeyDao secureKeyDao;

    /**
     * 创建密钥对
     *
     * @param keyPairStrut json格式的请求文件
     */
    @ApiOperation(value = "生成密钥对", notes="生成密钥对")
    @PostMapping("/create/KeyPair")
    public void createKeyPair(@RequestBody @NotNull KeyPairStrut keyPairStrut) {
        PrivateKeyDO privateKeyDO = keyPairStrut.getPrivateKeyDO();
        PublicKeyDO publicKeyDO = keyPairStrut.getPublicKeyDO();
        try {
            new KeyPairGenerator().generatorKeyPair(keyPairStrut);
            privateKeyDO.setId(UUIDUtil.get());
            publicKeyDO.setId(UUIDUtil.get());
            System.out.println(UUIDUtil.get().length());
            privateKeyDao.save(privateKeyDO);
            publicKeyDao.save(publicKeyDO);
            KeyPairDO keyPairDO = new KeyPairDO();
            keyPairDO.setID(UUIDUtil.get());
            keyPairDO.setPublic_key_id(publicKeyDO.getId());
            keyPairDO.setPrivate_key_id(privateKeyDO.getId());
            keyPairDO.setCertificate_id(UUIDUtil.get());
            keyPairDO.setCertificate_request_id(UUIDUtil.get());
            keyPairDao.save(keyPairDO);
            log.info("创建密钥对成功!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 创建对称密钥
     *
     * @param secureKeyDO
     */
    @PostMapping("/create/SecureKey")
    public void createSecureKey(@RequestBody @NotNull SecureKeyDO secureKeyDO) {
        secureKeyDao.save(secureKeyDO);
    }

    /**
     * 吊销密钥对
     *
     * @param id 密钥对ID
     */
    @GetMapping("/revoke/KeyPair")
    public void revokeKeyPair(@RequestParam("id") @NotNull String id) {
        keyPairDao.deleteById(null);
    }

    /**
     * 吊销对称密钥
     *
     * @param id 密钥ID
     */
    @GetMapping("/revoke/SecureKey")
    public void revokeSecureKey(@RequestParam("id") @NotNull String id) {
        secureKeyDao.save(null);
    }

    /**
     * 安全销毁密钥对
     *
     * @param id
     */
    @GetMapping("/destroy/KeyPair")
    public void destroyKeyPair(@RequestParam("id") @NotNull String id) {
        keyPairDao.deleteById(id);
    }

    /**
     * 安全销毁密钥
     */

    @GetMapping("/destroy/SecureKey")
    public void destroySecureKey(@RequestParam("id") @NotNull String id) {
        secureKeyDao.deleteById(id);
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
    public void backUpKeyPair(@RequestParam("id") @NotNull String id) {
        keyPairDao.save(null);
    }

    /**
     * 备份密钥
     *
     * @param id
     */
    @GetMapping("backup/SecureKey")
    public void backUpSecureKey(@RequestParam("id") @NotNull String id) {
        secureKeyDao.save(null);
    }
}
