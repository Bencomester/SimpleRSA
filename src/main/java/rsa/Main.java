package rsa;

import java.util.Arrays;
import java.util.Map;

public class Main {
    static void main(String[] args) {
        SimpleRSA rsa = new SimpleRSA();
        Map.Entry<SecretKey, PublicKey> keys = rsa.initializeAndGenerateKeys();

        PublicKey pk = keys.getValue();
        SecretKey sk = keys.getKey();
        System.out.println(pk);
        System.out.println(sk);

        int m1 = 356;
        String m2 = "Message to encrypt";

        int c1 = rsa.encrypt(m1, pk);
        int[] c2 = rsa.encrypt(m2, pk);

        System.out.println(c1);
        System.out.println(rsa.decrypt(c1, sk));

        System.out.println(Arrays.toString(c2));
        System.out.println(rsa.decrypt(c2, sk));
    }
}
