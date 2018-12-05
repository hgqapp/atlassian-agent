package io.zhile.crack.atlassian.utils;

import java.security.*;

/**
 * @author pengzhile
 * @link https://zhile.io
 * @version 1.0
 */
public class DSA {
    public static void genKeyPair() throws NoSuchAlgorithmException {
        KeyPairGenerator dsa = KeyPairGenerator.getInstance("DSA");
        SecureRandom random = new SecureRandom();
        dsa.initialize(1024, random);
        KeyPair keypair = dsa.generateKeyPair();
        PrivateKey privateKey = keypair.getPrivate();
        PublicKey publicKey = keypair.getPublic();
        System.out.println(Base64.encode(privateKey.getEncoded()));
        System.out.println(Base64.encode(publicKey.getEncoded()));
    }
}
