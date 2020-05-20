package com.robert.openca.dao.key;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * 密钥对信息
 *
 * @author robert
 * @version 1.0
 * @since 2020-05-20
 */
@Entity
@Table(name = "KEY_PAIR_INFO")
public class KeyPairDO {

    /**
     * 主键
     */
    @Id
    private String ID;

    /**
     * 公钥ID
     */
    @Column(nullable = false)
    private String public_key_id;

    /**
     * 私钥ID
     */
    @Column(nullable = false)
    private String private_key_id;

    /**
     * 使用该密钥对的证书的ID
     */
    @Column(nullable = false)
    private String certificate_id;

    /**
     * 使用该密钥对的证书请求的ID
     */
    @Column(nullable = false)
    private String certificate_request_id;

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getPublic_key_id() {
        return public_key_id;
    }

    public void setPublic_key_id(String public_key_id) {
        this.public_key_id = public_key_id;
    }

    public String getPrivate_key_id() {
        return private_key_id;
    }

    public void setPrivate_key_id(String private_key_id) {
        this.private_key_id = private_key_id;
    }

    public String getCertificate_id() {
        return certificate_id;
    }

    public void setCertificate_id(String certificate_id) {
        this.certificate_id = certificate_id;
    }

    public String getCertificate_request_id() {
        return certificate_request_id;
    }

    public void setCertificate_request_id(String certificate_request_id) {
        this.certificate_request_id = certificate_request_id;
    }
}
