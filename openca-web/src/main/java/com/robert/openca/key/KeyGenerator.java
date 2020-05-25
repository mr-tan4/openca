package com.robert.openca.key;

import com.robert.openca.key.keypair.KeyPairStrut;
import net.lingala.zip4j.ZipFile;
import net.lingala.zip4j.exception.ZipException;
import net.lingala.zip4j.model.ZipParameters;
import net.lingala.zip4j.model.enums.AesKeyStrength;
import net.lingala.zip4j.model.enums.CompressionLevel;
import net.lingala.zip4j.model.enums.CompressionMethod;
import net.lingala.zip4j.model.enums.EncryptionMethod;

import javax.crypto.SecretKey;
import java.io.File;
import java.security.Key;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * 密钥生成
 *
 * @author robert
 * @version 1.0
 * @since 2020-05-21
 */
public abstract class KeyGenerator {

    /**
     * 公钥文件开头
     */
    private static final String PUBLIC_KEY_FILE_HEADER = "/Users/robert/PublicKey_";
    /**
     * 私钥文件开头
     */
    private static final String PRIVATE_KEY_FILE_HEADER = "/Users/robert/PrivateKey_";
    /**
     * 公钥文件结尾
     */
    private static final String PUBLIC_KEY_FILE_END = ".pub";
    /**
     * 私钥文件结尾
     */
    private static final String PRIVATE_KEY_FILE_END = ".pri";

    /**
     * zip文件开头
     */
    private static final String ZIP_FILE_HEADER = "/Users/robert/" + SimpleDateFormat.getDateInstance().format(new Date());

    /**
     * zip文件结尾
     */
    private static final String ZIP_FILE_END = ".zip";

    /**
     * 对称密钥文件开头
     */
    private static final String KEY_FILE_HEADER = "/Users/robert/Key_";

    /**
     * 对称密钥文件结尾
     */
    private static final String KEY_FILE_END = ".key";

    /**
     * 密钥对生成器
     */
    protected abstract void generatorKeyPair(KeyPairStrut keyPairStrut);

    /**
     * 对称密钥生成器
     */
    protected abstract void generatorSecureKey();

    protected File storeFileName(Key key) {
        if (key instanceof PublicKey) {
            return new File(PUBLIC_KEY_FILE_HEADER + "test" + PUBLIC_KEY_FILE_END);
        } else if (key instanceof PrivateKey) {
            return new File(PRIVATE_KEY_FILE_HEADER + "test" + PRIVATE_KEY_FILE_END);
        } else if (key instanceof SecretKey) {
            return new File(KEY_FILE_HEADER + "test" + KEY_FILE_END);
        }
        return null;
    }

    /**
     * 创建zip压缩文件
     */
    protected void createZipFile(List<File> files, String password) {
        ZipFile zipFile = new ZipFile(new File(ZIP_FILE_HEADER + "test" + ZIP_FILE_END), password.toCharArray());
        ZipParameters parameters = new ZipParameters();
        parameters.setCompressionMethod(CompressionMethod.DEFLATE);
        parameters.setCompressionLevel(CompressionLevel.NORMAL);
        parameters.setEncryptFiles(true);
        parameters.setEncryptionMethod(EncryptionMethod.AES);
        parameters.setAesKeyStrength(AesKeyStrength.KEY_STRENGTH_256);
        try {
            zipFile.addFiles(files, parameters);
        } catch (ZipException e) {
            e.printStackTrace();
        }
    }
}
