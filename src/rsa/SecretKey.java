package rsa;

public class SecretKey {
    private int d;
    private int p;
    private int q;

    public SecretKey(int d, int p, int q) {
        this.d = d;
        this.p = p;
        this.q = q;
    }

    public int fn(int p, int q) {
        return (p - 1) * (q - 1);
    }

    public int getD() {
        return d;
    }

    public int getP() {
        return p;
    }

    public int getQ() {
        return q;
    }
}
