package rsa;

public class PublicKey {
    long n;
    long e;

    public PublicKey(long n, long e) {
        this.n = n;
        this.e = e;
    }
}
