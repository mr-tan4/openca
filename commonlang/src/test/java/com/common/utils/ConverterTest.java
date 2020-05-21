package com.common.utils;

import com.common.cert.CertificateRequestBuilder;
import com.common.key.KeyPairBuilder;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.pkcs.PKCS10CertificationRequest;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;

public class ConverterTest {

    public KeyPair generatorKeyPair() {
        KeyPair keyPair = new KeyPairBuilder()
                .setAlgorithm("RSA")
                .setLength(2048)
                .setProvider("bc")
                .setSecureRandom(new SecureRandom())
                .build();
        return keyPair;
    }

    public PKCS10CertificationRequest generatorPKCS10CertificationRequest() {
        KeyPair keyPair = generatorKeyPair();
        PKCS10CertificationRequest pkcs10CertificationRequest = new CertificateRequestBuilder()
                .setSubject("C=CN,O=nanjing,OU=koal,CN=robert")
                .setPublicKey(keyPair.getPublic())
                .setPrivateKey(keyPair.getPrivate())
                .setProvider(new BouncyCastleProvider())
                .build();
        return pkcs10CertificationRequest;
    }

    @Test
    public void privateKey2File() throws FileNotFoundException {
        KeyPair keyPair = generatorKeyPair();
        Converter.PrivateKey2File(new FileOutputStream("/Users/robert/test.pri"), keyPair.getPrivate());
    }

    @Test
    public void file2PrivateKey() throws FileNotFoundException {
        PrivateKey privateKey = Converter.File2PrivateKey(new FileInputStream("/Users/robert/test.pri"),
                "RSA");
        System.out.println(privateKey.getAlgorithm());
    }

    @Test
    public void publicKey2File() throws FileNotFoundException {
        KeyPair keyPair = generatorKeyPair();
        Converter.PublicKey2File(new FileOutputStream("/Users/robert/test.pub"), keyPair.getPublic());
    }

    @Test
    public void file2PublicKey() throws FileNotFoundException {
        PublicKey publicKey = Converter.File2PublicKey(new FileInputStream("/Users/robert/test.pub"),
                "RSA");
        System.out.println(publicKey);
    }

    @Test
    public void encryptionFile2PrivateKey() throws FileNotFoundException {
        PrivateKey privateKey = Converter.EncryptionPrivateKeyFile2PrivateKey(new FileInputStream(
                "/Users/robert/encryption.pri"),
                "RSA", "189213");
        System.out.println(privateKey);

    }

    @Test
    public void privateKey2EncryptionFile() throws FileNotFoundException {
        KeyPair keyPair = generatorKeyPair();
        Converter.PrivateKey2EncryptionFile(new FileOutputStream("/Users/robert/encryption.pri"),
                "189213", keyPair.getPrivate());
    }

    @Test
    public void CSR2File() throws FileNotFoundException {
        PKCS10CertificationRequest pkcs10CertificationRequest = generatorPKCS10CertificationRequest();
        Converter.PKCS10CertificationRequest2File(pkcs10CertificationRequest, new
                FileOutputStream("/Users/robert/test.csr"));
    }

    @Test
    public void File2CSR() throws IOException {
        PKCS10CertificationRequest pkcs10CertificationRequest = Converter.File2PKCS10CertificateRequest(
                new FileInputStream("/Users/robert/test.csr"));
        System.out.println(pkcs10CertificationRequest.getSubject().toString());

    }
}

