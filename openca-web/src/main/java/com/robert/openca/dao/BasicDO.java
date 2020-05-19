package com.robert.openca.dao;

import javax.persistence.Column;
import javax.persistence.Id;

/**
 * 基本信息 会被复用
 * @author robert
 * @since 2020-05-19
 * @version 1.0
 */
public class BasicDO {

    /**
     * 主键
     */
    @Id
    protected String ID;

    /**
     * 国家代码
     */
    @Column(length = 2)
    protected String CountyCode;

    /**
     * 州/省
     */
    @Column(length = 255)
    protected String State;

    /**
     * 城市
     */
    @Column(length = 255)
    protected String Location;

    /**
     * 组织/机构
     */
    @Column(length = 255)
    protected String Organization;

    /**
     * 单位/部门
     */
    @Column(length = 255)
    protected String OrganizationUnit;

    /**
     * 通用名称/域名
     */
    @Column(length = 255,nullable = false)
    protected String CommonName;

    /**
     * 签名算法
     */
    @Column(length = 255,nullable = false)
    protected String SignAlgorithm = "SHA256WithRSA";

    /**
     * 公钥ID
     */
    @Column(length = 16)
    protected String PublicKeyID;

    /**
     * 私钥ID
     */
    @Column(length = 16)
    protected String PrivateKeyID;

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getCountyCode() {
        return CountyCode;
    }

    public void setCountyCode(String countyCode) {
        CountyCode = countyCode;
    }

    public String getState() {
        return State;
    }

    public void setState(String state) {
        State = state;
    }

    public String getLocation() {
        return Location;
    }

    public void setLocation(String location) {
        Location = location;
    }

    public String getOrganization() {
        return Organization;
    }

    public void setOrganization(String organization) {
        Organization = organization;
    }

    public String getOrganizationUnit() {
        return OrganizationUnit;
    }

    public void setOrganizationUnit(String organizationUnit) {
        OrganizationUnit = organizationUnit;
    }

    public String getCommonName() {
        return CommonName;
    }

    public void setCommonName(String commonName) {
        CommonName = commonName;
    }

    public String getSignAlgorithm() {
        return SignAlgorithm;
    }

    public void setSignAlgorithm(String signAlgorithm) {
        SignAlgorithm = signAlgorithm;
    }

    public String getPublicKeyID() {
        return PublicKeyID;
    }

    public void setPublicKeyID(String publicKeyID) {
        PublicKeyID = publicKeyID;
    }

    public String getPrivateKeyID() {
        return PrivateKeyID;
    }

    public void setPrivateKeyID(String privateKeyID) {
        PrivateKeyID = privateKeyID;
    }
}
