package rsa;

import java.util.Map;

public class Main {
    static void main(String[] args) {
        SimpleRSA rsa = new SimpleRSA();
        Map.Entry<SecretKey, PublicKey> keys = rsa.initializeAndGenerateKeys();
        PublicKey pk = keys.getValue();
        SecretKey sk = keys.getKey();

        int m = 356;
        int c = rsa.encrypt(m, pk);
        System.out.println(c);
        System.out.println(rsa.decrypt(c, sk));
    }
}
