package com.robert.openca.dao.key;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 私钥信息
 */
@Entity
@Table(name = "PRIVATE_KEY_INFO")
public class PrivateKeyDO extends KeyDO {

    /**
     * 私钥加密密码
     */
    @Column(length = 6)
    private String password;

    /**
     * 加密后的私钥密文
     */
    @Column(columnDefinition = "text")
    private String privateKeyContext;

    /**
     * 加密的盐
     */
    @Column(nullable = true)
    private String salt;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPrivateKeyContext() {
        return privateKeyContext;
    }

    public void setPrivateKeyContext(String privateKeyContext) {
        this.privateKeyContext = privateKeyContext;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }
}
