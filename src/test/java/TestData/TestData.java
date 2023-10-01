package TestData;

public class TestData {
    private final float CONSTANT_RATE = 0.05f;
    private final int CONSTANT_PRINCIPAL = 2000;
    private final int CONSTANT_TERM = 5;

    private int validTerm = 1;
    private int negativeTerm = -1;
    private int zeroTerm = 0;

    private float validRate = 0.01f;
    private float negativeRate = -0.01f;
    private float largeInvalidRate = 0.3f;

    private float zeroRate = 0f;

    private int validPrincipal = 10000;
    private int invalidPrincipal = -1;
    private int zeroPrincipal = 0;

    public float getConstantRate() {
        return CONSTANT_RATE;
    }

    public int getConstantPrincpal() {
        return CONSTANT_PRINCIPAL;
    }

    public int getConstantTerm() {
        return CONSTANT_TERM;
    }


    public int getNegativeTerm() {
        return negativeTerm;
    }

    public void setNegativeTerm(int negativeTerm) {
        this.negativeTerm = negativeTerm;
    }

    public int getValidTerm() {
        return validTerm;
    }

    public void setValidTerm(int validTerm) {
        this.validTerm = validTerm;
    }

    public int getZeroTerm() {
        return zeroTerm;
    }

    public float getValidRate() {
        return validRate;
    }

    public void setValidRate(float validRate) {
        this.validRate = validRate;
    }

    public float getNegativeRate() {
        return negativeRate;
    }

    public void setNegativeRate(float negativeRate) {
        this.negativeRate = negativeRate;
    }

    public float getLargeInvalidRate() {
        return largeInvalidRate;
    }

    public void setLargeInvalidRate(float largeInvalidRate) {
        this.largeInvalidRate = largeInvalidRate;
    }

    public int getValidPrincipal() {
        return validPrincipal;
    }

    public void setValidPrincipal(int validPrincipal) {
        this.validPrincipal = validPrincipal;
    }

    public int getInvalidPrincipal() {
        return invalidPrincipal;
    }

    public void setInvalidPrincipal(int invalidPrincipal) {
        this.invalidPrincipal = invalidPrincipal;
    }

    public float getZeroRate() {
        return zeroRate;
    }

    public int getZeroPrincipal() {
        return zeroPrincipal;
    }
}
