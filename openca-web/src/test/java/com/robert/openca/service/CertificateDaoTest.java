package com.robert.openca.service;

import com.robert.openca.dao.certificate.CertificateInfoDO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.event.annotation.BeforeTestExecution;

import java.math.BigInteger;

@SpringBootTest
public class CertificateDaoTest {

    @Autowired
    private CertificateDao certificateDao;

    @BeforeTestExecution
    public void before(){
        CertificateInfoDO certificateInfoDO  = new CertificateInfoDO();
        certificateInfoDO.setID("1111");
        certificateInfoDO.setCountyCode("CN");
        certificateInfoDO.setState("江苏");
        certificateInfoDO.setLocation("南京");
        certificateInfoDO.setOrganization("格尔");
        certificateInfoDO.setOrganizationUnit("产品部");
        certificateInfoDO.setCommonName("谈一敏");
        certificateInfoDO.setCa(false);
        certificateInfoDO.setPathLen(0);
        certificateInfoDO.setSerialNumber(new BigInteger("1"));
        certificateDao.save(certificateInfoDO);
    }

    @Test
    public void addTest(){
        CertificateInfoDO certificateInfoDO  = new CertificateInfoDO();
        certificateInfoDO.setID("1111");
        certificateInfoDO.setCountyCode("CN");
        certificateInfoDO.setState("江苏");
        certificateInfoDO.setLocation("南京");
        certificateInfoDO.setOrganization("格尔");
        certificateInfoDO.setOrganizationUnit("产品部");
        certificateInfoDO.setCommonName("谈一敏");
        certificateInfoDO.setCa(false);
        certificateInfoDO.setPathLen(0);
        certificateInfoDO.setSerialNumber(new BigInteger("1"));
        certificateDao.save(certificateInfoDO);
    }


}
