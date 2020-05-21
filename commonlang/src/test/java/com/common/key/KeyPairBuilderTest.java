package com.common.key;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.util.encoders.Base64;
import org.junit.Test;

import java.security.KeyPair;
import java.security.SecureRandom;
import java.security.interfaces.ECPrivateKey;
import java.security.interfaces.ECPublicKey;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;


public class KeyPairBuilderTest {

    @Test
    public void generatorKeyPairWithBuilder(){
        KeyPair keyPair = new KeyPairBuilder()
                .setAlgorithm("ECDSA")
                .setLength(521)
                .setProvider("bc")
                .setSecureRandom(new SecureRandom())
                .build();
        assert keyPair.getPublic() instanceof ECPublicKey;
        assert keyPair.getPrivate() instanceof ECPrivateKey;
        System.out.println(keyPair.getPublic().getAlgorithm());
        System.out.println(keyPair.getPrivate().getAlgorithm());
        System.out.println(new String(Base64.encode(keyPair.getPublic().getEncoded())));
        System.out.println(new String(Base64.encode(keyPair.getPrivate().getEncoded())));
    }

    @Test
    public void generatorKeyPairWithConfiguration(){
        KeyPairBuilder.KeyBuilderConfigure configure = new KeyBuilder.KeyBuilderConfigure();
        configure.setLength(2048);
        configure.setAlgorithm("RSA");
        configure.setProvider("bc");
        configure.setSecureRandom(new SecureRandom());

        KeyPair keyPair = new KeyPairBuilder().setConfiguration(configure).build();

        assert keyPair.getPublic() instanceof RSAPublicKey;
        assert keyPair.getPrivate() instanceof RSAPrivateKey;
        System.out.println(keyPair.getPublic().getAlgorithm());
        System.out.println(keyPair.getPrivate().getAlgorithm());
        System.out.println(new String(Base64.encode(keyPair.getPublic().getEncoded())));
        System.out.println(new String(Base64.encode(keyPair.getPrivate().getEncoded())));
    }
}
