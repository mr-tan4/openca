package com.robert.openca.dao.certificate;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * 基本信息 会被复用
 *
 * @author robert
 * @version 1.0
 * @since 2020-05-19
 */

@MappedSuperclass
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
    @Column(length = 255, nullable = false)
    protected String CommonName;

    /**
     * 签名算法
     */
    @Column(length = 255, nullable = false)
    protected String SignAlgorithm = "SHA256WithRSA";

    /**
     * 密钥对ID
     */
    @Column(length = 40)
    protected String KeyPairID;

    /**
     * 算法提供者
     */
    @Column
    protected String Provider;

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

    public String getKeyPairID() {
        return KeyPairID;
    }

    public void setKeyPairID(String keyPairID) {
        KeyPairID = keyPairID;
    }

    public String getProvider() {
        return Provider;
    }

    public void setProvider(String provider) {
        Provider = provider;
    }
}
