package com.common.cert;

import com.common.key.KeyPairBuilder;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.pkcs.PKCS10CertificationRequest;
import org.junit.Test;

import java.security.KeyPair;
import java.security.SecureRandom;

public class CertificateRequestBuilderTest {

    @Test
    public void test(){
        // 构建密钥对
        KeyPair keyPair = new KeyPairBuilder()
                .setAlgorithm("RSA")
                .setLength(2048)
                .setProvider("bc")
                .setSecureRandom(new SecureRandom())
                .build();
        PKCS10CertificationRequest pkcs10CertificationRequest = new CertificateRequestBuilder()
                .setSubject("C=CN,O=nanjing,OU=koal,CN=robert")
                .setPublicKey(keyPair.getPublic())
                .setPrivateKey(keyPair.getPrivate())
                .setProvider(new BouncyCastleProvider())
                .build();
    }
}
