package com.common.cert;

import com.common.algorithm.BcSupportAlgorithm;
import org.bouncycastle.asn1.pkcs.PKCSObjectIdentifiers;
import org.bouncycastle.asn1.x500.X500Name;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.asn1.x509.X509Extensions;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.operator.ContentSigner;
import org.bouncycastle.operator.OperatorCreationException;
import org.bouncycastle.operator.jcajce.JcaContentSignerBuilder;
import org.bouncycastle.pkcs.PKCS10CertificationRequest;
import org.bouncycastle.pkcs.jcajce.JcaPKCS10CertificationRequestBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.bind.DatatypeConverter;
import java.io.IOException;
import java.security.*;

/**
 * 证书请求文件生成类
 *
 * @author robert
 */
public class CertificateRequestBuilder {

    private static final Logger LOGGER = LoggerFactory.getLogger(CertificateRequestBuilder.class);

    /**
     * 证书签名算法
     * 默认为SHA256WithRSA
     */
    private String signAlgorithm = BcSupportAlgorithm.RSA_SHA256.getValue();

    /**
     * 算法提供者
     * 默认为 BC
     */
    private Provider provider = new BouncyCastleProvider();

    /**
     * 证书使用对象
     */
    private String subject;

    /**
     * 证书公钥
     */
    private PublicKey publicKey;

    /**
     * 证书私钥
     */
    private PrivateKey privateKey;

    /**
     * 随机数产生器
     */
    private SecureRandom secureRandom = new SecureRandom();

    public CertificateRequestBuilder setSignAlgorithm(String signAlgorithm) {
        this.signAlgorithm = signAlgorithm;
        return this;
    }

    public CertificateRequestBuilder setProvider(Provider provider) {
        this.provider = provider;
        return this;
    }

    public CertificateRequestBuilder setSubject(String subject) {
        this.subject = subject;
        return this;
    }

    public CertificateRequestBuilder setPublicKey(PublicKey publicKey) {
        this.publicKey = publicKey;
        return this;
    }

    public CertificateRequestBuilder setPrivateKey(PrivateKey privateKey) {
        this.privateKey = privateKey;
        return this;
    }

    public CertificateRequestBuilder setSecureRandom(SecureRandom secureRandom) {
        this.secureRandom = secureRandom;
        return this;
    }

    private X500Name generatorX500Name() {
        return new X500Name(subject);
    }

    /**
     * 生成器
     *
     * @return 标准的Certificate Sign Request 对象
     */
    public PKCS10CertificationRequest build() {
        try {
            LOGGER.info("签名算法: {}",signAlgorithm);
            ContentSigner contentSigner = new JcaContentSignerBuilder(signAlgorithm)
                    .setProvider(provider)
                    .setSecureRandom(secureRandom)
                    .build(privateKey);
            LOGGER.info("创建签名器 算法为: {},算法提供者为: {},随机数产生器算法为: {},随机数产生器算法提供者为: {}",
                    signAlgorithm, provider.getName(), secureRandom.getAlgorithm(), secureRandom.getProvider().getName());
            PKCS10CertificationRequest pkcs10CertificationRequest = new JcaPKCS10CertificationRequestBuilder(
                    generatorX500Name(), publicKey)
                    .build(contentSigner);
            LOGGER.info("生成证书请求对象成功!");
            outputPKCS10CertificateRequestInfo(pkcs10CertificationRequest);
            return pkcs10CertificationRequest;
        } catch (OperatorCreationException e) {
            LOGGER.error("生成证书请求对象失败!", e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 将证书请求文件内容结构化的输出到日志中去
     *
     * @param request
     */
    private void outputPKCS10CertificateRequestInfo(PKCS10CertificationRequest request) {
        LOGGER.info("证书使用者: {}", request.getSubject().toString());
        LOGGER.info("证书公钥算法: {}", request.getSubjectPublicKeyInfo().getAlgorithmId().getAlgorithm().getId());
        LOGGER.info("证书签名算法: {}", request.getSignatureAlgorithm().getAlgorithm().getId());
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-1", new BouncyCastleProvider());
            byte[] der = request.getEncoded();
            md.update(der);
            byte[] digest = md.digest();
            String digestHex = DatatypeConverter.printHexBinary(digest);
            LOGGER.info("证书指纹为: {}", digestHex.toLowerCase());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
