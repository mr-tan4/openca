package com.robert.openca.dao.key;

import org.springframework.data.annotation.CreatedDate;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.util.Date;

/**
 * 定义密钥的基本信息
 *
 * @author robert
 * @version 1.0
 * @since 2020-05-19
 */
@MappedSuperclass
public class KeyDO {

    /**
     * 主键
     */
    @Id
    @Column(length = 16)
    protected String Id;

    /**
     * 密钥用途
     * 默认为加密
     */
    @Column(length = 255, nullable = false)
    protected String KeyUse = "encrypt";

    /**
     * 扩展密钥用途
     */
    @Column(length = 255)
    protected String ExtKeyUse;

    /**
     * 密钥长度
     */
    @Column(length = 4, nullable = false)
    protected int Length = 2048;

    /**
     * 密钥算法
     */
    @Column(length = 10)
    protected String Algorithm;

    /**
     * 是否为非对称密钥对
     * 默认为 true
     */
    @Column(nullable = false)
    protected boolean isASymmetric = true;


    /**
     * 密钥在 ... 之前过期
     * 默认为 0000-00-00 00:00:00
     */
    @CreatedDate
    @Column(nullable = false)
    protected Date not_before;

    /**
     * 密钥在 ... 之后过期
     * 默认为 0000-00-00 00:00:00
     */
    @CreatedDate
    @Column(nullable = false)
    protected Date not_after;

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getKeyUse() {
        return KeyUse;
    }

    public void setKeyUse(String keyUse) {
        KeyUse = keyUse;
    }

    public String getExtKeyUse() {
        return ExtKeyUse;
    }

    public void setExtKeyUse(String extKeyUse) {
        ExtKeyUse = extKeyUse;
    }

    public int getLength() {
        return Length;
    }

    public void setLength(int length) {
        Length = length;
    }

    public String getAlgorithm() {
        return Algorithm;
    }

    public void setAlgorithm(String algorithm) {
        Algorithm = algorithm;
    }

    public boolean isASymmetric() {
        return isASymmetric;
    }

    public void setASymmetric(boolean ASymmetric) {
        isASymmetric = ASymmetric;
    }

    public Date getNot_before() {
        return not_before;
    }

    public void setNot_before(Date not_before) {
        this.not_before = not_before;
    }

    public Date getNot_after() {
        return not_after;
    }

    public void setNot_after(Date not_after) {
        this.not_after = not_after;
    }
}
