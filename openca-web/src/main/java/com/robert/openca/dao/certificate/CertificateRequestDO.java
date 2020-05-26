package com.robert.openca.dao.certificate;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 证书请求文件实体
 *
 * @author robert
 * @version 1.0
 * @since 2020-05-19
 */
@Entity
@Table(name = "CERTIFICATE_REQUEST_INFO")
public class CertificateRequestDO extends BasicDO {

    public String getSubject() {
        return new StringBuilder()
                .append("C=")
                .append(CountyCode)
                .append(",ST=")
                .append(State)
                .append(",L=")
                .append(Location)
                .append(",O=")
                .append(Organization)
                .append(",OU=")
                .append(OrganizationUnit)
                .append("CN=")
                .append(CommonName)
                .toString();

    }

}
