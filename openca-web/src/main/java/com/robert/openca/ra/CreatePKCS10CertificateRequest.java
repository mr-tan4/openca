package com.robert.openca.ra;

import com.common.cert.CertificateRequestBuilder;
import com.common.utils.Converter;
import com.common.utils.EncryptedPrivateKey;
import com.robert.openca.dao.certificate.CertificateRequestDO;
import com.robert.openca.dao.key.PrivateKeyDO;
import com.robert.openca.dao.key.PublicKeyDO;
import com.robert.openca.service.key.PrivateKeyDao;
import com.robert.openca.service.key.PublicKeyDao;
import org.bouncycastle.pkcs.PKCS10CertificationRequest;
import org.bouncycastle.util.encoders.Base64;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.FileOutputStream;
import java.security.PrivateKey;
import java.security.PublicKey;

/**
 * 制作PKCS10 证书请求文件
 */
public class CreatePKCS10CertificateRequest {
    private static final String REQUEST_FILE_HEADER = "/Users/robert/Request_";
    private static final String REQUEST_FILE_END = ".csr";

    @Autowired
    private PublicKeyDao publicKeyDao;

    @Autowired
    private PrivateKeyDao privateKeyDao;


    /**
     * 写出为PEM格式文件
     *
     * @param certificateRequestDO 请求信息
     * @return 文件存放路径
     */
    public void generatorRequestFromPEM(CertificateRequestDO certificateRequestDO) {
        try {
            PrivateKeyDO privateKeyDO = privateKeyDao.getOne(certificateRequestDO.getPrivateKeyID());
            PublicKeyDO publicKeyDO = publicKeyDao.getOne(certificateRequestDO.getPublicKeyID());
            PKCS10CertificationRequest certificationRequest = new CertificateRequestBuilder()
                    .setSubject(certificateRequestDO.getSubject())
                    .setSignAlgorithm(certificateRequestDO.getSignAlgorithm())
                    .setProvider(certificateRequestDO.getProvider())
                    .setPublicKey(convertPublicKey(publicKeyDO))
                    .setPrivateKey(convertPrivateKey(privateKeyDO))
                    .build();
            Converter.PKCS10CertificationRequest2File(certificationRequest,
                    new FileOutputStream(REQUEST_FILE_HEADER + "test" + REQUEST_FILE_END));
        } catch (Exception e) {

        }
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

    /**
     * 将数据库中保存的私钥字符串转换为私钥对象
     *
     * @param privateKeyDO 私钥实体
     * @return 私钥对象
     */
    private static PrivateKey convertPrivateKey(PrivateKeyDO privateKeyDO) {
        byte[] salt = Base64.decode(privateKeyDO.getSalt());
        String plantText = EncryptedPrivateKey.decryption(privateKeyDO.getPrivateKeyContext(),
                privateKeyDO.getPassword(), salt);
        return Converter.String2PrivateKey(plantText, privateKeyDO.getAlgorithm());
    }

    private static PublicKey convertPublicKey(PublicKeyDO publicKeyDO) {
        return Converter.String2PublicKey(publicKeyDO.getPublicKeyContext(), publicKeyDO.getAlgorithm());
    }

}
