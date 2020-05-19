package com.common.cert;

import com.common.key.KeyPairBuilder;
import com.common.utils.Converter;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.pkcs.PKCS10CertificationRequest;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.security.KeyPair;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;

public class CertificateBuilderTest {

    @Test
    public void SelfSignCertificateBuilder(){
        KeyPair keyPair = new KeyPairBuilder()
                .setAlgorithm("RSA")
                .setLength(2048)
                .setProvider(new BouncyCastleProvider())
                .setSecureRandom(new SecureRandom())
                .build();
        PKCS10CertificationRequest pkcs10CertificationRequest = new CertificateRequestBuilder()
                .setSubject("C=CN,L=nanjing,O=koal,OU=develop,CN=robert")
                .setPublicKey(keyPair.getPublic())
                .setPrivateKey(keyPair.getPrivate())
                .setProvider(new BouncyCastleProvider())
                .build();
        try {
            X509Certificate signCertificate = Converter.file2Cert(new FileInputStream("/Users/robert/client.crt"));
            X509Certificate x509Certificate = new CertificateBuilder()
                    .setSignCertificate(signCertificate)
                    .setCA(true)
                    .setPathLen(10)
                    .setRequest(pkcs10CertificationRequest)
                    .setSerialNumber(new BigInteger("1"))
                    .setSignKey(keyPair.getPrivate())
                    .build();
            Converter.cert2File(x509Certificate,new FileOutputStream("/Users/robert/test.crt"));
        }catch (IOException e){
            e.printStackTrace();
        }

    }
}
