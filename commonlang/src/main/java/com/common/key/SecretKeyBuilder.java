package com.common.key;

import com.common.provider.Providers;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.Provider;
import java.security.SecureRandom;

/**
 * 对称密钥生成算法
 *
 * @author robert
 * @version 1.0
 * @since 2020-05-25
 */
public class SecretKeyBuilder {

    private static final Logger LOGGER = LoggerFactory.getLogger(SecretKeyBuilder.class);

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
    private String provider = "BC";

    /**
     * 随机数产生器
     * 默认为 sun
     */
    private SecureRandom secureRandom = new SecureRandom();

    public SecretKeyBuilder setAlgorithm(String algorithm) {
        this.algorithm = algorithm;
        return this;
    }

    public SecretKeyBuilder setLength(int length) {
        this.length = length;
        return this;
    }

    public SecretKeyBuilder setProvider(String provider) {
        this.provider = provider;
        return this;
    }

    public SecretKeyBuilder setSecureRandom(SecureRandom secureRandom) {
        this.secureRandom = secureRandom;
        return this;
    }

    public SecretKeyBuilder setConfiguration(SecureKeyBuilderConfiguration secureKeyBuilderConfiguration) {
        this.algorithm = secureKeyBuilderConfiguration.getAlgorithm();
        this.length = secureKeyBuilderConfiguration.getLength();
        this.provider = secureKeyBuilderConfiguration.getProvider();
        this.secureRandom = secureKeyBuilderConfiguration.getSecureRandom();
        return this;
    }

    public SecretKey build() {
        try {
            KeyGenerator keyGenerator = KeyGenerator.getInstance(algorithm, Providers.getProviderOfName(provider));
            keyGenerator.init(length, secureRandom);
            LOGGER.info("创建对称密钥");
            LOGGER.info("密钥算法为: {},算法提供者为: {}", algorithm, provider);
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
        private String provider = "BC";
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

        public String getProvider() {
            return provider;
        }

        public void setProvider(String provider) {
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
