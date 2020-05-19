package com.robert.openca.dao.key;

import javax.persistence.Column;
import javax.persistence.Id;

/**
 * 定义密钥的基本信息
 *
 * @author robert
 * @version 1.0
 * @since 2020-05-19
 */
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

}
