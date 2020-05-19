package com.common.cert;


import org.bouncycastle.asn1.x500.X500Name;
import org.bouncycastle.asn1.x509.BasicConstraints;
import org.bouncycastle.asn1.x509.Extension;
import org.bouncycastle.cert.CertIOException;
import org.bouncycastle.cert.X509CertificateHolder;
import org.bouncycastle.cert.X509v3CertificateBuilder;
import org.bouncycastle.cert.jcajce.JcaX509CertificateConverter;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.operator.ContentSigner;
import org.bouncycastle.operator.OperatorCreationException;
import org.bouncycastle.operator.jcajce.JcaContentSignerBuilder;
import org.bouncycastle.pkcs.PKCS10CertificationRequest;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigInteger;
import java.security.PrivateKey;
import java.security.Provider;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * 证书生成类
 * @author robert
 * @since 2020-05-19
 */
public class CertificateBuilder {

    private static final Logger LOGGER = LoggerFactory.getLogger(CertificateRequestBuilder.class);
    /**
     * 默认的过期时长
     */
    private static final int MONTH = 3;

    /**
     * 证书签名请求对象
     */
    @NotNull
    private PKCS10CertificationRequest request;
    /**
     * 证书序列号
     */
    @NotNull
    private BigInteger serialNumber;
    /**
     * 证书在 ... 之前失效
     * 默认为当前时间
     */
    private Date noBefore = new Date();
    /**
     * 证书在 ... 之后失效
     * 默认为3个月的有效期
     */
    private Date noAfter = defaultNoAfter(noBefore);
    /**
     * 签名证书
     */
    @NotNull
    private X509Certificate signCertificate;
    /**
     * 算法提供者
     * 默认为 BC
     */
    private Provider provider = new BouncyCastleProvider();
    /**
     * 随机数方法
     * 默认为 SUN
     */
    private SecureRandom secureRandom = new SecureRandom();

    /**
     * 签名密钥
     */
    @NotNull
    private PrivateKey signKey;

    /**
     * 是否为CA证书
     * 默认为不是
     */
    private boolean isCA = false;

    /**
     * 如果该证书为CA证书
     * 控制证书长度
     * 默认不控制
     */
    private int pathLen = 0;

    /**
     * 证书扩展列表
     */
    private List<Extension> extensions = new ArrayList<>();

    public CertificateBuilder setRequest(PKCS10CertificationRequest request) {
        this.request = request;
        return this;
    }

    public CertificateBuilder setSerialNumber(BigInteger serialNumber) {
        this.serialNumber = serialNumber;
        return this;
    }

    public CertificateBuilder setNoBefore(Date noBefore) {
        this.noBefore = noBefore;
        return this;
    }

    public CertificateBuilder setNoAfter(Date noAfter) {
        this.noAfter = noAfter;
        return this;
    }

    public CertificateBuilder setSignCertificate(X509Certificate signCertificate) {
        this.signCertificate = signCertificate;
        return this;
    }

    public CertificateBuilder setProvider(Provider provider) {
        this.provider = provider;
        return this;
    }

    public CertificateBuilder setSecureRandom(SecureRandom secureRandom) {
        this.secureRandom = secureRandom;
        return this;
    }

    public CertificateBuilder setSignKey(PrivateKey signKey) {
        this.signKey = signKey;
        return this;
    }

    public CertificateBuilder setCA(boolean isCA) {
        this.isCA = isCA;
        return this;
    }

    public CertificateBuilder setPathLen(int pathLen) {
        this.pathLen = pathLen;
        return this;
    }

    public CertificateBuilder setExtensions(List<Extension> extensions) {
        this.extensions = extensions;
        return this;
    }

    /**
     * 证书生成
     *
     * @return 标准X509证书对象
     */
    public X509Certificate build() {
        try {
            X509v3CertificateBuilder builder = new X509v3CertificateBuilder(
                    new X500Name(signCertificate.getSubjectX500Principal().getName()),
                    serialNumber, noBefore, noAfter, request.getSubject(), request.getSubjectPublicKeyInfo());
            LOGGER.info("构建证书生成器");
            LOGGER.info("签发者为: {}", new X500Name(signCertificate.getSubjectX500Principal().getName()).toString());
            LOGGER.info("序列号为: {}", serialNumber);
            LOGGER.info("在 {} 之前过期", noBefore);
            LOGGER.info("在 {} 之后过期", noAfter);
            LOGGER.info("使用者为: {}", request.getSubject().toString());
            LOGGER.info("该证书{}一张CA证书", isCA ? "是" : "不是");
            LOGGER.info(pathLen == 0 ? "该证书不限制证书长度" : "该证书限制证书长度,证书长度为{}", pathLen);
            if (isCA) {
                if (pathLen > 0) {
                    builder.addExtension(Extension.basicConstraints, true, new BasicConstraints(pathLen));
                }else {
                    builder.addExtension(Extension.basicConstraints, true, new BasicConstraints(true));
                }
            } else {
                builder.addExtension(Extension.basicConstraints, true, new BasicConstraints(false));
            }
            if (!extensions.isEmpty()) {
                extensions.forEach(extension -> {
                    try {
                        builder.addExtension(extension);
                    } catch (CertIOException e) {
                        e.printStackTrace();
                    }
                });
            }
            ContentSigner contentSigner = new JcaContentSignerBuilder(signCertificate.getSigAlgName())
                    .setSecureRandom(secureRandom)
                    .setProvider(provider)
                    .build(signKey);
            LOGGER.info("构建签名器");
            LOGGER.info("签名算法为: {}", signCertificate.getSigAlgName());
            LOGGER.info("算法提供者为: {}", provider.getName());
            LOGGER.info("随机数产生算法为: {},算法提供者为: {}", secureRandom.getAlgorithm(),
                    secureRandom.getProvider().getName());
            X509CertificateHolder holder = builder.build(contentSigner);
            JcaX509CertificateConverter jcaX509CertificateConverter = new JcaX509CertificateConverter();
            return jcaX509CertificateConverter.getCertificate(holder);
        } catch (CertIOException | OperatorCreationException | CertificateException e) {
            LOGGER.info("签发证书失败! 原因是: {}", e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 将证书的过期时间默认向后推延3个月
     *
     * @param source 开始时间
     * @return 结束时间
     */
    private Date defaultNoAfter(Date source) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(source);
        calendar.add(Calendar.MONTH, MONTH);
        return calendar.getTime();
    }
}
