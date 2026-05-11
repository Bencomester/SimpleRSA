package rsa;

public class PublicKey {
    private final int n;
    private final int e;

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

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("PublicKey{");
        sb.append("n=").append(n);
        sb.append(", e=").append(e);
        sb.append('}');
        return sb.toString();
    }
}
