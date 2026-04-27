package rsa;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.Random;

public class SimpleRSA {

    public Map.Entry<SecretKey, PublicKey> initializeAndGenerateKeys() {

        final int maxPQ = (int) Math.sqrt(Integer.MAX_VALUE);
        final int minPQ = 10000;
        Random rand = new Random();
        int p = rand.nextInt(minPQ,maxPQ);
        while (!millerRabinTest(p)) p = rand.nextInt(minPQ, maxPQ);
        int q = rand.nextInt(minPQ, maxPQ);
        while (!millerRabinTest(q)) q = rand.nextInt(minPQ, maxPQ);
        int n = p * q;
        int fn = (p - 1) * (q - 1);

        int e = rand.nextInt(fn);
        ArrayList<Integer> inv = extendedEuclideanAlgorithm(e, fn);
        while (inv.isEmpty()) {
            e = rand.nextInt(fn);
            inv = extendedEuclideanAlgorithm(e, fn);
        }

        int d = inv.get(0);
        if (d < 0) d += fn;

        SecretKey sk = new SecretKey(d, p, q);
        PublicKey pk = new PublicKey(n, e);
        return Map.entry(sk, pk);
    }

    public int encrypt(int m, PublicKey pk) {
        return fastExponentiation(m, pk.getE(), pk.getN());
    }

    public int decrypt(int c, SecretKey sk) {
        return CRT(c, sk.getD(), sk.getP(), sk.getQ());
    }

    public boolean millerRabinTest(int n) {
        return millerRabinTest(n, 3);
    }

    public boolean millerRabinTest(int n, int a) {
        int d = n - 1;
        int s = 0;
        while(d % 2 == 0) {
            d >>= 1;
            s++;
        }

        int ad = fastExponentiation(a, d, n);
        if (ad == 1) return true;


        for (int i = 0; i < s; i++) {
            if (ad == n - 1) return true;
            ad = (int) ((long) ad * ad) % n;
        }
        return false;
    }

    public int fastExponentiation(int a, int n, int mod) {
        ArrayList<Integer> nums = new ArrayList<>();
        ArrayList<Long> exps = splitIntoBinaryExponents(n);
        int num = a;
        for (long exp = 1; exp <= exps.getLast(); exp *= 2) {
            if (exps.contains(exp)) nums.add(num);
            num = (int) ((long) num * num % mod);
        }

        int prod = 1;
        for (int i : nums) {
            prod = (int) ((long) prod * i % mod);
        }
        return prod;
    }

    public ArrayList<Long> splitIntoBinaryExponents(int n) {
        long exp = 1;
        ArrayList<Long> list = new ArrayList<>();
        while(n > 0) {
            if (n % 2 == 1) list.add(exp);
            n = n / 2;
            exp *= 2;
        }
        return list;
    }

    //Returns empty lists if not relative primes
    public ArrayList<Integer> extendedEuclideanAlgorithm(int r1, int r2) {
        int k = 1;
        int q1 = 0;
        int q2 = r1 / r2;
        long x1 = 1;
        long x2 = 0;
        long y1 = 0;
        long y2 = 1;
        while (r1 % r2 > 0) {
            k++;

            int tempR = r1;
            r1 = r2;
            r2 = tempR % r1;

            q1 = q2;
            q2 = r1 / r2;

            long tempX = x1;
            x1 = x2;
            x2 = x1 * q1 + tempX;

            long tempY = y1;
            y1 = y2;
            y2 = y1 * q1 + tempY;
        }

        if (r2 != 1) return new ArrayList<>();
        x2 = k % 2 == 0 ? x2 : -x2;
        y2 = k % 2 == 0 ? -y2 : y2;
        return new ArrayList<>(Arrays.asList((int) x2, (int) y2));
    }

    public int CRT(int m, int d, int p, int q) {
        int r1 = fastExponentiation(m, (d % (q - 1)), q);
        int r2 = fastExponentiation(m, (d % (p - 1)), p);

        ArrayList<Integer> inv = extendedEuclideanAlgorithm(p, q);
        int y1 = (int) (inv.get(0) % q);
        if (y1 < 0) y1 += q;
        int y2 = (int) (inv.get(1) % p);
        if (y2 < 0) y2 += p;

        int n = p * q;

        return (int) ((((long) r1 * p) % n * y1) % n + (((long) r2 * q) % n * y2) % n) % n;
    }
}
