package com.robert.openca.dao.key;

import javax.persistence.*;


/**
 * 密钥对信息
 *
 * @author robert
 * @version 1.0
 * @since 2020-05-20
 */
@Entity
@Table(name = "KEY_PAIR_INFO",indexes = {@Index(columnList = "ID"),@Index(columnList = "alias")})
public class KeyPairDO implements Cloneable {

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
     * 是否吊销
     */
    @Column
    private boolean revoked;

    /**
     * 是否为备份数据
     */
    @Column
    private boolean backup;

    /**
     * 密钥对别名
     */
    @Column(nullable = false)
    private String alias;

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

    public boolean isRevoked() {
        return revoked;
    }

    public void setRevoked(boolean revoked) {
        this.revoked = revoked;
    }

    public boolean isBackup() {
        return backup;
    }

    public void setBackup(boolean backup) {
        this.backup = backup;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public KeyPairDO clone() throws CloneNotSupportedException {
        return (KeyPairDO) super.clone();
    }
}
