package com.robert.openca.dao.certificate;

import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.math.BigInteger;
import java.util.Date;

/**
 * 证书基本信息
 *
 * @author robert
 * @version 1.0
 * @since 2020.05.19
 */
@Entity
@Table(name = "CERTIFICATE_INFO", indexes = {@Index(columnList = "CN")})
public class CertificateInfoDO extends BasicDO {

    /**
     * 是否为CA
     */
    @Column
    private boolean IsCa;

    /**
     * 限制长度
     */
    @Column(length = 11)
    private int pathLen;

    /**
     * 证书内容
     */
    @Column(columnDefinition = "mediumtext")
    private String certificate_context;

    /**
     * 序列号
     * 默认缺省序列号为65537 为内部保存序列号，无效！
     */
    @Column(length = 16, unique = true, nullable = false)
    private BigInteger SerialNumber = new BigInteger("65537");

    /**
     * 证书在 。。。 之前过期
     * 默认为 0000-00-00 00:00:00
     */
    @CreatedDate
    @Column(nullable = false)
    private Date noBefore;

    /**
     * 证书在 ... 之后过期
     * 默认为 0000-00-00 00:00:00
     */
    @CreatedDate
    @Column(nullable = false)
    private Date noAfter;

    public boolean isCa() {
        return IsCa;
    }

    public void setCa(boolean ca) {
        IsCa = ca;
    }

    public int getPathLen() {
        return pathLen;
    }

    public void setPathLen(int pathLen) {
        this.pathLen = pathLen;
    }

    public BigInteger getSerialNumber() {
        return SerialNumber;
    }

    public void setSerialNumber(BigInteger serialNumber) {
        SerialNumber = serialNumber;
    }

    public Date getNoBefore() {
        return noBefore;
    }

    public void setNoBefore(Date noBefore) {
        this.noBefore = noBefore;
    }

    public Date getNoAfter() {
        return noAfter;
    }

    public void setNoAfter(Date noAfter) {
        this.noAfter = noAfter;
    }

    public String getCertificate_context() {
        return certificate_context;
    }

    public void setCertificate_context(String certificate_context) {
        this.certificate_context = certificate_context;
    }
}
