package rsa;

public class SecretKey {
    int d;
    int p;
    int q;

    public SecretKey(int d, int p, int q) {
        this.d = d;
        this.p = p;
        this.q = q;
    }

    public int fn(int p, int q) {
        return (p - 1) * (q - 1);
    }
}
