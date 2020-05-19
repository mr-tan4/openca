package com.robert.openca.service;

import com.robert.openca.dao.certificate.CertificateInfoDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * 证书数据服务
 */
@Repository
public interface CertificateDao extends JpaRepository<CertificateInfoDO,Integer> {
}
