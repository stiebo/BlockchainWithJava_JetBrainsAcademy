package blockchain.trader;

import java.security.*;

public class KeyGen {
    public static KeyPair generateKeyPair(int keyLength) {
        KeyPairGenerator keyGen;
        try {
            keyGen = KeyPairGenerator.getInstance("RSA");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        keyGen.initialize(keyLength);
        return keyGen.generateKeyPair();
    }
}
