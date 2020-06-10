package com.robert.openca.dao.certificate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;

/**
 * 证书请求文件实体
 *
 * @author robert
 * @version 1.0
 * @since 2020-05-19
 */
@Entity
@Table(name = "CERTIFICATE_REQUEST_INFO", indexes = {@Index(columnList = "CN")})
public class CertificateRequestDO extends BasicDO {

    /**
     * 证书请求文件内容
     */
    @Column(columnDefinition = "mediumtext")
    private String certificate_request_context;

    public String getCertificate_request_context() {
        return certificate_request_context;
    }

    public void setCertificate_request_context(String certificate_request_context) {
        this.certificate_request_context = certificate_request_context;
    }
}
