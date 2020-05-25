package com.robert.openca.key.soft;

import com.common.key.SecretKeyBuilder;
import com.common.utils.Converter;
import com.robert.openca.dao.key.SecretKeyDO;

import com.robert.openca.key.KeyGenerator;
import com.robert.openca.key.keypair.KeyPairStrut;
import lombok.extern.slf4j.Slf4j;

import javax.crypto.SecretKey;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class SecretKeyGenerator extends KeyGenerator {

    @Override
    protected void generatorKeyPair(KeyPairStrut keyPairStrut) {
        return;
    }

    @Override
    public void generatorSecretKey(SecretKeyDO secretKeyDO) {
        SecretKey secretKey = new SecretKeyBuilder()
                .setAlgorithm(secretKeyDO.getAlgorithm())
                .setLength(secretKeyDO.getLength())
                .setProvider(secretKeyDO.getProvider())
                .build();
        try {
            Converter.SecretKey2File(secretKey, new FileOutputStream(storeFileName(secretKey)));
            List<File> files = new ArrayList<>();
            files.add(storeFileName(secretKey));
            createZipFile(files, secretKeyDO.getPassword());
            log.info("生成密钥成功!");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            log.error("生成密钥对失败!  失败原因: {}", e.getMessage());
        }
    }
}
