package com.robert.openca.controller;


import com.robert.openca.dao.certificate.CertificateInfoDO;
import com.robert.openca.dao.certificate.CertificateRequestDO;
import com.robert.openca.ra.CreatePKCS10CertificateRequest;
import com.robert.openca.service.certificate.CertificateDao;

import com.robert.openca.service.certificate.CertificateRequestDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.validation.constraints.NotNull;
import java.math.BigInteger;
import java.util.Optional;

/**
 * 证书控制器
 *
 * @author robert
 * @version 1.0
 * @since 2020-05-21
 */
@RestController
@RequestMapping("/")
@EnableSwagger2
public class CertificateController {

    @Autowired
    private CertificateDao certificateDao;

    @Autowired
    private CertificateRequestDao certificateRequestDao;

    /**
     * 创建证书接口
     *
     * @param certificateInfoDO
     */
    @PostMapping("create/certificate")
    public void create(@RequestBody @NotNull CertificateInfoDO certificateInfoDO) {
        certificateDao.save(certificateInfoDO);
    }

    /**
     * 更新证书接口
     */
    @PostMapping("update/certificate")
    public void update(@RequestBody @NotNull CertificateInfoDO certificateInfoDO) {
        certificateDao.save(certificateInfoDO);
    }

    /**
     * 吊销证书接口
     *
     * @param serialNumber
     */
    @GetMapping("revoke/certificate")
    public void revoke(@RequestParam("serialNumber") BigInteger serialNumber) {
        Optional<CertificateInfoDO> certificateInfoDO = certificateDao.findOne(null);
        certificateDao.save(certificateInfoDO.get());
    }

    /**
     * 证书签名接口
     *
     * @param certificateInfoDO
     */
    @PostMapping("sign/certificate")
    public void sign(@RequestBody @NotNull CertificateInfoDO certificateInfoDO) {
        certificateDao.save(certificateInfoDO);
    }

    /**
     * 生成证书请求
     */
    @PostMapping("create/certificate/request")
    public void create(@RequestBody @NotNull CertificateRequestDO certificateRequestDO) {
        new CreatePKCS10CertificateRequest().generatorRequestFromPEM(certificateRequestDO);
        certificateRequestDao.save(certificateRequestDO);
    }
}
