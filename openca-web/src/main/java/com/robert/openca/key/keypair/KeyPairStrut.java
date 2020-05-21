package com.robert.openca.key.keypair;

import com.robert.openca.dao.key.PrivateKeyDO;
import com.robert.openca.dao.key.PublicKeyDO;

/**
 * 密钥对结构
 *
 * @author robert
 * @version 1.0
 * @since 2020-05-21
 */
public class KeyPairStrut {

    /**
     * 私钥数据
     */
    private PrivateKeyDO privateKeyDO;

    /**
     * 公钥数据
     */
    private PublicKeyDO publicKeyDO;

    public PrivateKeyDO getPrivateKeyDO() {
        return privateKeyDO;
    }

    public void setPrivateKeyDO(PrivateKeyDO privateKeyDO) {
        this.privateKeyDO = privateKeyDO;
    }

    public PublicKeyDO getPublicKeyDO() {
        return publicKeyDO;
    }

    public void setPublicKeyDO(PublicKeyDO publicKeyDO) {
        this.publicKeyDO = publicKeyDO;
    }
}
