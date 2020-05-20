package com.robert.openca.service.certificate;

import com.robert.openca.dao.certificate.CertificateRequestDO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CertificateRequestDao extends JpaRepository<CertificateRequestDO,String> {
}
