package com.common.utils;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.util.encoders.Hex;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.Security;

public class test {

    public static void main(String[] args) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        Security.addProvider(new BouncyCastleProvider());
        String str = "utf-8";
        MessageDigest md = MessageDigest.getInstance("MD2");
        for (int i = 0; i < 100000000; i++) {
            byte[] digest = md.digest(str.getBytes("UTF-8"));
            str = new String(Hex.encode(digest));
        }
        System.out.println(str);
    }
}
