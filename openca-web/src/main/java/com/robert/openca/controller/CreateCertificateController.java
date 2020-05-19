package com.robert.openca.controller;


import com.robert.openca.dao.certificate.CertificateInfoDO;
import com.robert.openca.service.CertificateDao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;


@RestController
@RequestMapping("/")
public class CreateCertificateController {

    @Autowired
    private CertificateDao certificateDao;

    @PostMapping("create/certificate")
    public void create(@RequestBody @NotNull CertificateInfoDO certificateInfoDO) {
        certificateDao.save(certificateInfoDO);
    }
}
