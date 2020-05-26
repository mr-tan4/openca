package com.common.utils;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.util.encoders.Base64;
import org.jetbrains.annotations.NotNull;

import javax.crypto.*;
import javax.crypto.spec.PBEKeySpec;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.Security;
import java.security.spec.InvalidKeySpecException;

/**
 * 私钥加密工具
 *
 * @author robert
 * @version 1.0
 * @since 2020-05-26
 */
public final class EncryptedPrivateKey {

    /**
     * 类加载时加入算法提供者
     */
    static {
        Security.addProvider(new BouncyCastleProvider());
    }

    /**
     * 密钥生成算法
     */
    private static final String ALGORITHM = "PBEWithSHA1AndDESede";
    /**
     * 填充方式
     */
    private static final String PADDING_ALGORITHM = "/CBC/PKCS5Padding";

    /**
     * 密钥产生器
     *
     * @param password 密码
     * @return 密钥对象
     */
    private static SecretKey generatorKey(@NotNull String password) {
        try {
            PBEKeySpec pbeKeySpec = new PBEKeySpec(password.toCharArray());
            SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance(ALGORITHM, "BC");
            return secretKeyFactory.generateSecret(pbeKeySpec);
        } catch (NoSuchAlgorithmException | InvalidKeySpecException | NoSuchProviderException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 加密方法
     *
     * @param plaintext 明文
     * @param password  密码
     * @return 密文
     */
    public static String encryption(@NotNull String plaintext, @NotNull String password) {
        try {
            SecretKey secretKey = generatorKey(password);
            Cipher cipher = Cipher.getInstance(ALGORITHM + PADDING_ALGORITHM, "BC");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            byte[] bytes = cipher.doFinal(Base64.decode(plaintext));
            return Base64.toBase64String(bytes);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (NoSuchProviderException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 解密方法
     *
     * @param ciphertext 密文
     * @param password   密码
     * @return 明文
     */
    public static String decryption(@NotNull String ciphertext, @NotNull String password) {
        try {
            SecretKey secretKey = generatorKey(password);
            Cipher cipher = Cipher.getInstance(ALGORITHM + PADDING_ALGORITHM, "BC");
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            byte[] bytes = cipher.doFinal(Base64.decode(ciphertext));
            return Base64.toBase64String(bytes);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (NoSuchProviderException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        }
        return null;
    }
}
