package test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import rsa.SimpleRSA;

import java.util.Arrays;

public class UnitTest {

    @Test
    public void testBinarySplitting() {
        SimpleRSA rsa = new SimpleRSA();
        Assertions.assertEquals(Arrays.asList(8, 16, 32, 64), rsa.splitIntoBinaryExponents(120));

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
}
