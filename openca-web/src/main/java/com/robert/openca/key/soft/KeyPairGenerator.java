package com.robert.openca.key.soft;

import com.common.key.KeyPairBuilder;
import com.common.utils.Converter;

import com.common.utils.EncryptedPrivateKey;
import com.robert.openca.dao.key.PrivateKeyDO;
import com.robert.openca.dao.key.PublicKeyDO;
import com.robert.openca.dao.key.SecretKeyDO;
import com.robert.openca.key.KeyGenerator;
import com.robert.openca.key.keypair.KeyPairStrut;
import lombok.extern.slf4j.Slf4j;
import org.bouncycastle.util.encoders.Base64;

import java.io.File;
import java.io.FileOutputStream;
import java.security.*;
import java.util.ArrayList;
import java.util.List;


@Slf4j
public class KeyPairGenerator extends KeyGenerator {

    @Override
    public void generatorKeyPair(KeyPairStrut keyPairStrut) {
        try {
            KeyPair keyPair = new KeyPairBuilder()
                    .setAlgorithm(keyPairStrut.getPublicKeyDO().getAlgorithm())
                    .setLength(keyPairStrut.getPublicKeyDO().getLength())
                    .setProvider(keyPairStrut.getPublicKeyDO().getProvider())
                    .build();
            //写出到文件
            Converter.PublicKey2File(new FileOutputStream(storeFileName(keyPair.getPublic())), keyPair.getPublic());
            Converter.PrivateKey2File(new FileOutputStream(storeFileName(keyPair.getPrivate())), keyPair.getPrivate());
            PublicKeyDO publicKeyDO = keyPairStrut.getPublicKeyDO();
            PrivateKeyDO privateKeyDO = keyPairStrut.getPrivateKeyDO();
            publicKeyDO.setPublicKeyContext(Converter.PublicKey2String(keyPair.getPublic()));
            byte[] salt = EncryptedPrivateKey.init();
            String encryptedPrivateKey = EncryptedPrivateKey
                    .encryption(Converter.PrivateKey2String(keyPair.getPrivate()), privateKeyDO.getPassword(), salt);
            privateKeyDO.setPrivateKeyContext(encryptedPrivateKey);
            privateKeyDO.setSalt(Base64.toBase64String(salt));
            keyPairStrut.setPublicKeyDO(publicKeyDO);
            keyPairStrut.setPrivateKeyDO(privateKeyDO);
            List<File> files = new ArrayList<>();
            files.add(storeFileName(keyPair.getPublic()));
            files.add(storeFileName(keyPair.getPrivate()));
            createZipFile(files, keyPairStrut.getPrivateKeyDO().getPassword());
            log.info("生成密钥对成功!");
        } catch (Exception e) {
            log.error("生成密钥对失败!  失败原因: {}", e.getMessage());
        }
    }

    @Override
    public void generatorSecretKey(SecretKeyDO secretKeyDO) {
    }


}
