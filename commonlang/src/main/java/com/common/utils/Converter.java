package com.common.utils;

import org.bouncycastle.asn1.pkcs.PKCSObjectIdentifiers;
import org.bouncycastle.cert.X509CertificateHolder;
import org.bouncycastle.cert.jcajce.JcaX509CertificateConverter;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.openssl.PEMParser;
import org.bouncycastle.openssl.jcajce.JcaPEMWriter;
import org.bouncycastle.operator.OperatorCreationException;
import org.bouncycastle.pkcs.PKCS10CertificationRequest;
import org.bouncycastle.pkcs.PKCS8EncryptedPrivateKeyInfo;
import org.bouncycastle.pkcs.PKCS8EncryptedPrivateKeyInfoBuilder;
import org.bouncycastle.pkcs.jcajce.JcaPKCS8EncryptedPrivateKeyInfoBuilder;
import org.bouncycastle.pkcs.jcajce.JcePKCSPBEOutputEncryptorBuilder;
import org.bouncycastle.util.encoders.Base64;
import org.bouncycastle.util.io.pem.PemObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.*;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.*;
import java.security.*;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

/**
 * 转换工具
 * 支持将PEM格式、DER格式文件转换为对象且可以反转
 */
public class Converter {

    private static final Logger LOGGER = LoggerFactory.getLogger(Converter.class);
    /**
     * 公钥开头
     */
    private static final String BEGIN_PUBLIC_KEY = "-----BEGIN PUBLIC KEY-----\n";
    /**
     * 公钥结尾
     */
    private static final String END_PUBLIC_KEY = "-----END PUBLIC KEY-----";

    /**
     * 证书对象写出到文件 PEM格式
     *
     * @param x509Certificate  证书对象
     * @param fileOutputStream 文件对象
     */
    public static void cert2File(X509Certificate x509Certificate, FileOutputStream fileOutputStream) {
        JcaPEMWriter pemWriter = new JcaPEMWriter(new OutputStreamWriter(fileOutputStream));
        try {
            pemWriter.writeObject(x509Certificate);
            pemWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                pemWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 证书文件转换为对象 PEM格式
     *
     * @param stream 文件流
     * @return 证书对象
     */
    public static X509Certificate file2Cert(FileInputStream stream) {
        PEMParser pemParser = new PEMParser(new InputStreamReader(stream));
        try {
            PemObject pemObject = pemParser.readPemObject();
            X509CertificateHolder x509CertificateHolder = new X509CertificateHolder(pemObject.getContent());
            JcaX509CertificateConverter jcaX509CertificateConverter = new JcaX509CertificateConverter();
            pemParser.close();
            return jcaX509CertificateConverter.getCertificate(x509CertificateHolder);
        } catch (IOException | CertificateException e) {
            e.printStackTrace();
        } finally {
            try {
                pemParser.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * 公钥写出到文件 PEM格式
     *
     * @param publicKey
     * @param stream
     */
    public static void PublicKey2File(FileOutputStream stream, PublicKey publicKey) {
        JcaPEMWriter jcaPEMWriter = new JcaPEMWriter(new OutputStreamWriter(stream));
        try {
            jcaPEMWriter.writeObject(publicKey);
            jcaPEMWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                jcaPEMWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 文件转换成公钥 PEM格式
     *
     * @pamar stream
     */

    public static PublicKey File2PublicKey(FileInputStream stream, String algorithm) {
        PEMParser pemParser = new PEMParser(new InputStreamReader(stream));
        try {
            PemObject pemObject = pemParser.readPemObject();
            X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(pemObject.getContent());
            KeyFactory keyFactory = KeyFactory.getInstance(algorithm, new BouncyCastleProvider());
            pemParser.close();
            return keyFactory.generatePublic(x509EncodedKeySpec);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        } finally {
            try {
                pemParser.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * 私钥写出到文件
     *
     * @param stream
     * @param privateKey
     */
    public static void PrivateKey2File(FileOutputStream stream, PrivateKey privateKey) {
        JcaPEMWriter writer = new JcaPEMWriter(new OutputStreamWriter(stream));
        try {
            writer.writeObject(privateKey);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    /**
     * 文件转到私钥对象
     *
     * @param stream
     * @param algorithm
     * @return
     */
    public static PrivateKey File2PrivateKey(FileInputStream stream, String algorithm) {
        PEMParser pemParser = new PEMParser(new InputStreamReader(stream));
        try {
            PemObject pemObject = pemParser.readPemObject();
            PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(pemObject.getContent());
            KeyFactory factory = KeyFactory.getInstance(algorithm, new BouncyCastleProvider());
            pemParser.close();
            return factory.generatePrivate(pkcs8EncodedKeySpec);
        } catch (IOException | NoSuchAlgorithmException | InvalidKeySpecException e) {
            e.printStackTrace();
        } finally {
            try {
                pemParser.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * csr 文件转换为PKCS10对象
     *
     * @param stream
     * @return
     * @throws IOException
     */
    public static PKCS10CertificationRequest File2PKCS10CertificateRequest(FileInputStream stream) throws IOException {
        PEMParser pemParser = new PEMParser(new InputStreamReader(stream));
        try {
            PemObject pemObject = pemParser.readPemObject();
            PKCS10CertificationRequest pkcs10CertificationRequest = new PKCS10CertificationRequest(
                    pemObject.getContent());
            pemParser.close();
            return pkcs10CertificationRequest;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            pemParser.close();
        }
        return null;
    }

    /**
     * 将 PKCS10转换为文件
     *
     * @param request
     * @param stream
     */
    public static void PKCS10CertificationRequest2File(PKCS10CertificationRequest request, FileOutputStream stream) {
        JcaPEMWriter jcaPEMWriter = new JcaPEMWriter(new OutputStreamWriter(stream));
        try {
            jcaPEMWriter.writeObject(request);
            jcaPEMWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                jcaPEMWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 将加密的私钥文件转换为私钥对象
     *
     * @param stream
     * @param algorithm
     * @param password
     * @return
     */
    public static PrivateKey EncryptionPrivateKeyFile2PrivateKey(FileInputStream stream, String algorithm,
                                                                 String password) {
        PEMParser pemParser = new PEMParser(new InputStreamReader(stream));
        try {
            PemObject pemObject = pemParser.readPemObject();
            EncryptedPrivateKeyInfo encryptedPrivateKeyInfo = new EncryptedPrivateKeyInfo(pemObject.getContent());
            PBEKeySpec keySpec = new PBEKeySpec(password.toCharArray());
            SecretKeyFactory factory = SecretKeyFactory.getInstance(encryptedPrivateKeyInfo.getAlgName(),
                    new BouncyCastleProvider());
            Cipher cipher = Cipher.getInstance(encryptedPrivateKeyInfo.getAlgName(), new BouncyCastleProvider());
            cipher.init(Cipher.DECRYPT_MODE, factory.generateSecret(keySpec),
                    encryptedPrivateKeyInfo.getAlgParameters());
            KeyFactory keyFactory = KeyFactory.getInstance(algorithm);
            pemParser.close();
            return keyFactory.generatePrivate(encryptedPrivateKeyInfo.getKeySpec(cipher));
        } catch (IOException | InvalidKeySpecException | NoSuchAlgorithmException | InvalidKeyException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidAlgorithmParameterException e) {
            e.printStackTrace();
        } finally {
            try {
                pemParser.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * 将私钥对象加入加密的文件中 PEM格式
     *
     * @param stream
     * @param password
     * @param privateKey
     */
    public static void PrivateKey2EncryptionFile(FileOutputStream stream, String password, PrivateKey privateKey) {
        JcaPEMWriter jcaPEMWriter = new JcaPEMWriter(new OutputStreamWriter(stream));
        try {
            PKCS8EncryptedPrivateKeyInfoBuilder pkcs8EncryptedPrivateKeyInfoBuilder =
                    new JcaPKCS8EncryptedPrivateKeyInfoBuilder(privateKey);
            PKCS8EncryptedPrivateKeyInfo pkcs8EncryptedPrivateKeyInfo = pkcs8EncryptedPrivateKeyInfoBuilder
                    .build(new JcePKCSPBEOutputEncryptorBuilder(PKCSObjectIdentifiers.pbeWithSHA1AndDES_CBC)
                            .setProvider(new BouncyCastleProvider())
                            .build(password.toCharArray()));
            jcaPEMWriter.writeObject(pkcs8EncryptedPrivateKeyInfo);
            jcaPEMWriter.close();
        } catch (OperatorCreationException | IOException e) {
            e.printStackTrace();
        } finally {
            try {
                jcaPEMWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 将对称密钥文件转换为对象
     *
     * @param stream    文件流
     * @param algorithm 算法
     */
    public static SecretKey SecureKeyFile2SecureKey(FileInputStream stream, String algorithm, Provider provider) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            byte[] bytes = new byte[2048];

            int length;
            while ((length = stream.read(bytes)) != -1) {
                byteArrayOutputStream.write(bytes, 0, length);
            }
            return Object2SecureKey(Base64.decode(byteArrayOutputStream.toString()), algorithm, provider);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                stream.close();
                byteArrayOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }


    /**
     * 将对称密钥写出到文件
     *
     * @param secretKey        密钥对象
     * @param fileOutputStream 文件输出流
     */
    public static void SecretKey2File(SecretKey secretKey, FileOutputStream fileOutputStream) {
        try {
            byte[] bytes = Base64.encode(secretKey.getEncoded());
            fileOutputStream.write(bytes);
            fileOutputStream.flush();
            fileOutputStream.close();
            LOGGER.info("写出密钥文件成功!");
        } catch (IOException e) {
            e.printStackTrace();
            LOGGER.error("写出密钥文件失败! 原因是 {}", e.getMessage());
        } finally {
            try {
                fileOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 将字符串转化为密钥对象
     *
     * @param var1      字符串
     * @param algorithm 算法
     * @return
     */
    public static SecretKey String2SecureKey(String var1, String algorithm, Provider provider) {
        byte[] bytes = Base64.decode(var1);
        return Object2SecureKey(bytes, algorithm, provider);
    }

    /**
     * 将二进制数据转换为密钥对象
     *
     * @param var1
     * @param algorithm
     * @param provider
     * @return
     */
    private static SecretKey Object2SecureKey(byte[] var1, String algorithm, Provider provider) {
        try {
            SecretKeySpec secretKeySpec = new SecretKeySpec(var1, algorithm);
            SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance(algorithm, provider);
            return secretKeyFactory.generateSecret(secretKeySpec);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 将公钥转换为String
     *
     * @param publicKey 公钥对象
     * @return 字符串
     */
    public static String PublicKey2String(PublicKey publicKey) {
        String text = Base64.toBase64String(publicKey.getEncoded());
        int count = text.length() % 64;
        if (count != 0) {
            count = (text.length() / 64) + 1;
        } else {
            count = text.length() / 64;
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(BEGIN_PUBLIC_KEY);
        for (int i = 0; i < count; i++) {
            if (i == count - 1) {
                stringBuilder.append(text.substring(i * 64, text.length() - 1));
                stringBuilder.append("\n");
            } else {
                stringBuilder.append(text.substring(i * 64, (i + 1) * 64));
                stringBuilder.append("\n");
            }
        }
        stringBuilder.append(END_PUBLIC_KEY);
        return stringBuilder.toString();
    }

    /**
     * 将私钥转换成String
     *
     * @param privateKey 私钥对象
     * @return 字符串
     */
    public static String PrivateKey2String(PrivateKey privateKey) {
        String text = Base64.toBase64String(privateKey.getEncoded());
        int count = text.length() % 64;
        if (count != 0) {
            count += 1;
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("-----BEGIN ");
        stringBuilder.append(privateKey.getAlgorithm());
        stringBuilder.append(" PRIVATE KEY-----\n");
        for (int i = 0; i < count; i++) {
            if (i == count - 1) {
                stringBuilder.append(text.substring(i * 64, text.length() - 1));
            } else {
                stringBuilder.append(text.substring(i * 64, (i + 1) * 64));
                stringBuilder.append("\n");
            }
        }
        stringBuilder.append("-----END ");
        stringBuilder.append(privateKey.getAlgorithm());
        stringBuilder.append(" PRIVATE KEY-----");
        return stringBuilder.toString();
    }


    public static PublicKey String2PublicKey(String var, String algorithm) {
        PEMParser pemParser = new PEMParser(new StringReader(var));
        try {
            PemObject pemObject = pemParser.readPemObject();
            X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(pemObject.getContent());
            KeyFactory keyFactory = KeyFactory.getInstance(algorithm, new BouncyCastleProvider());
            pemParser.close();
            return keyFactory.generatePublic(x509EncodedKeySpec);
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } finally {
            try {
                pemParser.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public static PrivateKey String2PrivateKey(String var, String algorithm) {
        PEMParser pemParser = new PEMParser(new StringReader(var));
        try {
            PemObject pemObject = pemParser.readPemObject();
            PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(pemObject.getContent());
            KeyFactory factory = KeyFactory.getInstance(algorithm, new BouncyCastleProvider());
            pemParser.close();
            return factory.generatePrivate(pkcs8EncodedKeySpec);
        } catch (IOException | NoSuchAlgorithmException | InvalidKeySpecException e) {
            e.printStackTrace();
        } finally {
            try {
                pemParser.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

}
