package rsa;

public class PublicKey {
    private int n;
    private int e;

    public PublicKey(int n, int e) {
        this.n = n;
        this.e = e;
    }

    public int getN() {
        return n;
    }

    public int getE() {
        return e;
    }
}
