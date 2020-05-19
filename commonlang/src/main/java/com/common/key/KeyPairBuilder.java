package com.common.key;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.security.*;

/**
 * 该类为密钥对产生的实现类
 */
public class KeyPairBuilder extends KeyBuilder {

    private static final Logger LOGGER = LoggerFactory.getLogger(KeyPairBuilder.class);

    public KeyPairBuilder setLength(int length) {
        this.length = length;
        return this;
    }

    public KeyPairBuilder setSecureRandom(SecureRandom secureRandom) {
        this.secureRandom = secureRandom;
        return this;
    }

    public KeyPairBuilder setAlgorithm(String algorithm) {
        this.algorithm = algorithm;
        return this;
    }

    public KeyPairBuilder setProvider(Provider provider) {
        this.provider = provider;
        return this;
    }

    public KeyPairBuilder setConfiguration(KeyBuilderConfigure configuration) {
        this.length = configuration.getLength();
        this.provider = configuration.getProvider();
        this.algorithm = configuration.getAlgorithm();
        this.secureRandom = configuration.getSecureRandom();
        return this;
    }

    public KeyPair build() {
        try {
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(algorithm, provider);
            LOGGER.info("创建密钥对产生器  算法为: {} ,算法提供者为: {}", algorithm, provider.getName());
            keyPairGenerator.initialize(length, secureRandom);
            LOGGER.info("初始化密钥对产生器 密钥长度为: {}, 随机数产生器算法为: {}, 随机数产生器算法提供者为: {}", length,
                    secureRandom.getAlgorithm(), secureRandom.getProvider().getName());
            KeyPair keyPair = keyPairGenerator.genKeyPair();
            LOGGER.info("密钥对生产成功!");
            LOGGER.info("密钥对信息: ");
            LOGGER.info("密钥算法: {}",algorithm);
            LOGGER.info("密钥长度: {}",length);
            return keyPair;
        } catch (NoSuchAlgorithmException e) {
            LOGGER.error("密钥对生成失败! 失败原因: {}",e.getMessage());
            e.printStackTrace();
        }catch (InvalidParameterException e){
            LOGGER.error("密钥对生成失败! 失败原因: {}",e.getMessage());
            e.printStackTrace();
        }
        return null;
    }
}
