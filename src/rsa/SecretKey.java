package rsa;

public class SecretKey {
    long d;
    long p;
    long q;

    public SecretKey(long d, long p, long q) {
        this.d = d;
        this.p = p;
        this.q = q;
    }

    public long fn(long p, long q) {
        return (p - 1) * (q - 1);
    }
}
