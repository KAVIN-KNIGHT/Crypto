import java.math.BigInteger;

public class DiffieHellman {

    private BigInteger q;
    private BigInteger alpha;

    public DiffieHellman(BigInteger q, BigInteger alpha) {
        this.q = q;
        this.alpha = alpha;
    }

    // Calculate public key
    public BigInteger generatePublicKey(BigInteger privateKey) {
        return alpha.modPow(privateKey, q);
    }

    // Calculate shared secret key
    public BigInteger generateSharedKey(
            BigInteger receivedPublicKey,
            BigInteger privateKey) {

        return receivedPublicKey.modPow(privateKey, q);
    }

    public BigInteger getQ() {
        return q;
    }

    public BigInteger getAlpha() {
        return alpha;
    }
}