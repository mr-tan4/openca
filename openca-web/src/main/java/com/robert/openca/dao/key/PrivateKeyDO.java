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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
