package com.common.key;

import com.common.algorithm.BcSupportAlgorithm;
import com.common.provider.Providers;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

import java.security.*;

/**
 * 生成密钥对
 */
public abstract class KeyBuilder {
    /**
     * 密钥长度
     * 默认为 1024
     */
    protected int length = 1024;

    /**
     * 算法提供者
     * 默认为 bc
     */
    protected String provider = Providers.BC.name();

    /**
     * 算法名称
     * 默认为 RSA
     */
    protected String algorithm = "RSA";

    protected SecureRandom secureRandom = new SecureRandom();

    /**
     * 构建密钥的方法
     */
    public abstract KeyPair build() throws NoSuchAlgorithmException;

    /**
     * 密钥对配置参数类
     */
    static class KeyBuilderConfigure {
        protected int length = 1024;
        protected String provider;
        protected String algorithm = "RSA";
        protected SecureRandom secureRandom = new SecureRandom();


        public KeyBuilderConfigure() {

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

        public String getAlgorithm() {
            return algorithm;
        }

        public void setAlgorithm(String algorithm) {
            this.algorithm = algorithm;
        }

        public SecureRandom getSecureRandom() {
            return secureRandom;
        }

        public void setSecureRandom(SecureRandom secureRandom) {
            this.secureRandom = secureRandom;
        }
    }

}
