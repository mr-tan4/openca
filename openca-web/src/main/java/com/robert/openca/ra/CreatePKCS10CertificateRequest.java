package com.robert.openca.ra;

import com.robert.openca.dao.certificate.CertificateRequestDO;

/**
 * 制作PKCS10 证书请求文件
 */
public class CreatePKCS10CertificateRequest {

    /**
     * 写出为PEM格式文件
     *
     * @param certificateRequestDO 请求信息
     * @return 文件存放路径
     */
    public static String generatorRequestFromPEM(CertificateRequestDO certificateRequestDO) {

        return null;
    }

    /**
     * 写出为DER格式文件
     *
     * @param certificateRequestDO 请求文件信息
     * @return 文件存放路径
     */
    public static String generatorRequestFromDER(CertificateRequestDO certificateRequestDO) {
        return null;
    }

}
