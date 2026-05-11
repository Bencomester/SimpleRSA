import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import rsa.PublicKey;
import rsa.SecretKey;
import rsa.SimpleRSA;

import java.util.Arrays;
import java.util.Map;
import java.util.Random;

public class UnitTest {

    @Test
    public void testBinarySplitting() {
        SimpleRSA rsa = new SimpleRSA();
        Assertions.assertEquals(Arrays.asList(8L, 16L, 32L, 64L), rsa.splitIntoBinaryExponents(120));

    }

    @Test
    public void fastExponentiationTest() {
        SimpleRSA rsa = new SimpleRSA();
        Assertions.assertEquals(9025, rsa.fastExponentiation(43, 120, 10403));
    }

    @Test
    public void millerRabinTestTest() {
        SimpleRSA rsa = new SimpleRSA();
        Assertions.assertTrue(rsa.millerRabinTest(13));
        Assertions.assertTrue(rsa.millerRabinTest(17));
        Assertions.assertFalse(rsa.millerRabinTest(20));
    }

    @Test
    public void extendedEuclideanTest() {
        SimpleRSA rsa = new SimpleRSA();
        Assertions.assertEquals(Arrays.asList(7, -142), rsa.extendedEuclideanAlgorithm(345, 17));
    }

    @Test
    public void CRTTest() {
        SimpleRSA rsa = new SimpleRSA();
        Assertions.assertEquals(10, rsa.CRT(3117, 5171, 107, 103));
    }

    @Test
    public void encryptionDecryptionTest() {
        SimpleRSA rsa = new SimpleRSA();
        Map.Entry<SecretKey, PublicKey> keys = rsa.initializeAndGenerateKeys();
        SecretKey sk = keys.getKey();
        PublicKey pk = keys.getValue();
        int m = 8726;
        Assertions.assertEquals(m, rsa.decrypt(rsa.encrypt(m,pk), sk));
        Assertions.assertEquals(2, rsa.decrypt(rsa.encrypt(2,pk), sk));
        Assertions.assertEquals(3, rsa.decrypt(rsa.encrypt(3,pk), sk));
        m = pk.getN() - 1;
        Assertions.assertEquals(m, rsa.decrypt(rsa.encrypt(m,pk), sk));
    }

    @Test
    public void massEncryptionDecryptionTest() {
        SimpleRSA rsa = new SimpleRSA();
        Random random = new Random();

        for (int i = 0; i < 10000; i++) {
            Map.Entry<SecretKey, PublicKey> keys = rsa.initializeAndGenerateKeys();
            SecretKey sk = keys.getKey();
            PublicKey pk = keys.getValue();

            int m = random.nextInt(1000, pk.getN());
            Assertions.assertEquals(m, rsa.decrypt(rsa.encrypt(m,pk), sk), "Failed on iteration " + i + ", with p=" + sk.getP() + " q=" + sk.getQ() + " e=" + pk.getE() + " d=" + sk.getD());
        }
    }
}
