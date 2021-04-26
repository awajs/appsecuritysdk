package com.scenera.nicesecurityapplib.Encryption;

import org.spongycastle.util.encoders.Base64;

import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Security;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

/**
 * Created by Ekta Bhatt on 4/22/2020.
 */
public class Crypto {
    private static KeyFactory keyFactory, keyFactoryRSA;
    private static Crypto instance;

    static {
        Security.insertProviderAt(new org.spongycastle.jce.provider.BouncyCastleProvider(), 1);
    }

    public static synchronized Crypto getInstance() {
        if (instance == null) {
            instance = new Crypto();
        }

        return instance;
    }
    public Crypto() {
        try {
            keyFactory = KeyFactory.getInstance("ECDSA", "SC");
            keyFactoryRSA = KeyFactory.getInstance("RSA", "SC");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchProviderException e) {
            e.printStackTrace();
        }
    }

    public static synchronized KeyPair readEcKeyPair(String pubKeyStr, String privKeyStr)
            throws Exception {
        return new KeyPair(getEcPubKey(pubKeyStr), getEcPriKey(privKeyStr));
    }
    public static synchronized PublicKey getEcPubKey(String keyStr) throws Exception {
        X509EncodedKeySpec x509ks = new X509EncodedKeySpec(
                Base64.decode(keyStr));
        return keyFactory.generatePublic(x509ks);


    }

    public static synchronized PrivateKey getEcPriKey(String keyStr) throws Exception {
        PKCS8EncodedKeySpec p8ks = new PKCS8EncodedKeySpec(
                Base64.decode(keyStr));

        return keyFactory.generatePrivate(p8ks);
    }

    public static synchronized KeyPair readRSAKeyPair(String pubKeyStr, String privKeyStr)
            throws Exception {
        return new KeyPair(getRSAPubKey(pubKeyStr), getRSAPriKey(privKeyStr));
    }
    public static synchronized PublicKey getRSAPubKey(String keyStr) throws Exception {
        X509EncodedKeySpec x509ks = new X509EncodedKeySpec(
                Base64.decode(keyStr));
        return keyFactoryRSA.generatePublic(x509ks);


    }

    public static synchronized PrivateKey getRSAPriKey(String keyStr) throws Exception {
        PKCS8EncodedKeySpec p8ks = new PKCS8EncodedKeySpec(
                Base64.decode(keyStr));

        return keyFactoryRSA.generatePrivate(p8ks);
    }
}
