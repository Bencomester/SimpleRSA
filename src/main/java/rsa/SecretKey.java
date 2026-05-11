package rsa;

public class SecretKey {
    private final int d;
    private final int p;
    private final int q;

    public SecretKey(int d, int p, int q) {
        this.d = d;
        this.p = p;
        this.q = q;
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

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("SecretKey{");
        sb.append("d=").append(d);
        sb.append(", p=").append(p);
        sb.append(", q=").append(q);
        sb.append(", fn=").append((p - 1) * (q - 1));
        sb.append('}');
        return sb.toString();
    }
}
