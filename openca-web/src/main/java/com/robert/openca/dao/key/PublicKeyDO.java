package com.robert.openca.dao.key;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "PUBLIC_KEY_INFO")
public class PublicKeyDO extends KeyDO {

    /**
     * 公钥内容
     */
    @Column(columnDefinition = "text")
    private String publicKeyContext;

    public String getPublicKeyContext() {
        return publicKeyContext;
    }

    public void setPublicKeyContext(String publicKeyContext) {
        this.publicKeyContext = publicKeyContext;
    }
}
