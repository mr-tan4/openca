package com.robert.openca.dao.key;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 对称密钥实体类
 *
 * @author robert
 * @since 2020-05-20
 * @version 1.0
 */
@Entity
@Table(name = "SECURE_KEY_INFO")
public class SecureKeyDO extends KeyDO {

    /**
     * 密钥填充算法
     */
    @Column(length = 32)
    private String padding_algorithm;

    public String getPadding_algorithm() {
        return padding_algorithm;
    }

    public void setPadding_algorithm(String padding_algorithm) {
        this.padding_algorithm = padding_algorithm;
    }
}
