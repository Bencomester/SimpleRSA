package rsa;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class SimpleRSA {

    public void initializeAndGenerateKeys() {
        Random rand = new Random();
        int p = rand.nextInt(2,Integer.MAX_VALUE);
        while (!millerRabinTest(p)) p = rand.nextInt(2, Integer.MAX_VALUE);
        int q = rand.nextInt(2, Integer.MAX_VALUE);
        while (!millerRabinTest(q)) q = rand.nextInt(2, Integer.MAX_VALUE);
        long n = (long) p * q;
        long fn = (long) (p - 1) * (q - 1);

        long e = rand.nextLong(fn);
        ArrayList<Long> inv = extendedEuclideanAlgorithm(e, fn);
        while (inv.isEmpty()) {
            e = rand.nextLong(fn);
            inv = extendedEuclideanAlgorithm(e, fn);
        }

        long d = inv.get(0);
        if (d < 0) d += fn;

        SecretKey sk = new SecretKey(d, p, q);
        PublicKey pk = new PublicKey(n, e);
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
        ArrayList<Integer> exps = splitIntoBinaryExponents(n);
        int num = a;
        for (int exp = 1; exp <= exps.getLast(); exp *= 2) {
            if (exps.contains(exp)) nums.add(num);
            num = (int) ((long) num * num) % mod;
        }

        int prod = 1;
        for (int i : nums) {
            prod = (int) ((long) prod * i) % mod;
        }
        return prod;
    }

    public ArrayList<Integer> splitIntoBinaryExponents(int n) {
        int exp = 1;
        ArrayList<Integer> list = new ArrayList<>();
        while(n > 0) {
            if (n % 2 == 1) list.add(exp);
            n = n / 2;
            exp *= 2;
        }
        return list;
    }

    //Returns empty lists if not relative primes
    public ArrayList<Long> extendedEuclideanAlgorithm(long r1, long r2) {
        long k = 1;
        long q1 = 0;
        long q2 = r1 / r2;
        long x1 = 1;
        long x2 = 0;
        long y1 = 0;
        long y2 = 1;
        while (r1 % r2 > 0) {
            k++;

            long temp = r1;
            r1 = r2;
            r2 = temp % r1;

            q1 = q2;
            q2 = r1 / r2;

            temp = x1;
            x1 = x2;
            x2 = x1 * q1 + temp;

            temp = y1;
            y1 = y2;
            y2 = y1 * q1 + temp;
        }

        if (r2 != 1) return new ArrayList<>();
        x2 = k % 2 == 0 ? x2 : -x2;
        y2 = k % 2 == 0 ? -y2 : y2;
        return new ArrayList<>(Arrays.asList(x2, y2));
    }
}
