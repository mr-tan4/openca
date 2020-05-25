package com.robert.openca.dao.key;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 对称密钥实体类
 *
 * @author robert
 * @version 1.0
 * @since 2020-05-20
 */
@Entity
@Table(name = "SECRET_KEY_INFO")
public class SecretKeyDO extends KeyDO {

    /**
     * 密钥填充算法
     */
    @Column(length = 32)
    private String padding_algorithm;

    /**
     * 是否为备份
     */
    @Column
    private boolean backup;

    /**
     * 对称密钥密码
     */
    @Column(length = 6)
    private String password;

    public String getPadding_algorithm() {
        return padding_algorithm;
    }

    public void setPadding_algorithm(String padding_algorithm) {
        this.padding_algorithm = padding_algorithm;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isBackup() {
        return backup;
    }

    public void setBackup(boolean backup) {
        this.backup = backup;
    }
}
