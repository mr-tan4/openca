package com.common.key;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.NoSuchAlgorithmException;
import java.security.Provider;
import java.security.SecureRandom;

/**
 * 对称密钥生成算法
 *
 * @author robert
 * @version 1.0
 * @since 2020-05-25
 */
public class SecureKeyBuilder {

    private static final Logger LOGGER = LoggerFactory.getLogger(SecureKeyBuilder.class);

    /**
     * 密钥算法
     * 默认为aes算法
     */
    private String algorithm = "AES";
    /**
     * 密钥长度
     */
    private int length;

    /**
     * 算法提供者
     * 默认为 BC
     */
    private Provider provider = new BouncyCastleProvider();

    /**
     * 随机数产生器
     * 默认为 sun
     */
    private SecureRandom secureRandom = new SecureRandom();

    public SecureKeyBuilder setAlgorithm(String algorithm) {
        this.algorithm = algorithm;
        return this;
    }

    public SecureKeyBuilder setLength(int length) {
        this.length = length;
        return this;
    }

    public SecureKeyBuilder setProvider(Provider provider) {
        this.provider = provider;
        return this;
    }

    public SecureKeyBuilder setSecureRandom(SecureRandom secureRandom) {
        this.secureRandom = secureRandom;
        return this;
    }

    public SecureKeyBuilder setConfiguration(SecureKeyBuilderConfiguration secureKeyBuilderConfiguration) {
        this.algorithm = secureKeyBuilderConfiguration.getAlgorithm();
        this.length = secureKeyBuilderConfiguration.getLength();
        this.provider = secureKeyBuilderConfiguration.getProvider();
        this.secureRandom = secureKeyBuilderConfiguration.getSecureRandom();
        return this;
    }

    public SecretKey build() {
        try {
            KeyGenerator keyGenerator = KeyGenerator.getInstance(algorithm, provider);
            keyGenerator.init(length, secureRandom);
            LOGGER.info("创建对称密钥");
            LOGGER.info("密钥算法为: {},算法提供者为: {}", algorithm, provider.getName());
            LOGGER.info("密钥长度为: {}", length);
            return keyGenerator.generateKey();
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error("密钥生产失败! 原因是 {}", e.getMessage());
            return null;
        }

    }


    class SecureKeyBuilderConfiguration {
        /**
         * 密钥算法
         */
        private String algorithm = "AES";
        /**
         * 密钥长度
         */
        private int length;

        /**
         * 算法提供者
         */
        private Provider provider = new BouncyCastleProvider();
        /**
         * 随机数产生器
         */
        private SecureRandom secureRandom = new SecureRandom();

        public String getAlgorithm() {
            return algorithm;
        }

        public void setAlgorithm(String algorithm) {
            this.algorithm = algorithm;
        }

        public int getLength() {
            return length;
        }

        public void setLength(int length) {
            this.length = length;
        }

        public Provider getProvider() {
            return provider;
        }

        public void setProvider(Provider provider) {
            this.provider = provider;
        }

        public SecureRandom getSecureRandom() {
            return secureRandom;
        }

        public void setSecureRandom(SecureRandom secureRandom) {
            this.secureRandom = secureRandom;
        }
    }
}
