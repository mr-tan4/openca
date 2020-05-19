package com.robert.openca.dao;

import lombok.Data;

import java.math.BigInteger;

/**
 * 证书基本信息
 * @author robert
 */
@Data
public class CertificateInfoDao {

    /**
     * 主键
     */
    private String ID;

    /**
     * 国家代码
     */
    private String CountyCode;

    /**
     * 州/省
     */
    private String State;

    /**
     * 城市
     */
    private String Location;

    /**
     * 组织/机构
     */
    private String Organization;

    /**
     * 单位/部门
     */
    private String OrganizationUnit;

    /**
     * 通用名称/域名
     */
    private String CommonName;

    /**
     * 是否为CA
     */
    private int IsCa;

    /**
     * 限制长度
     */
    private int pathLen;

    /**
     * 序列号
     */
    private BigInteger SerialNumber;

}
