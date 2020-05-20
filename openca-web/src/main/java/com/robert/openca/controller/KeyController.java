package com.robert.openca.controller;

import com.robert.openca.dao.key.KeyPairDO;
import com.robert.openca.dao.key.SecureKeyDO;
import com.robert.openca.service.key.KeyPairDao;
import com.robert.openca.service.key.PrivateKeyDao;
import com.robert.openca.service.key.PublicKeyDao;
import com.robert.openca.service.key.SecureKeyDao;
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
@RestController
@RequestMapping("/")
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
     * @param keyPairDO json格式的请求文件
     */
    @PostMapping("/create/KeyPair")
    public void createKeyPair(@RequestBody @NotNull KeyPairDO keyPairDO) {
        keyPairDao.save(keyPairDO);
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
