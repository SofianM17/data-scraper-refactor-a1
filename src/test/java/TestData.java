import customDataTypes.InterestRate;
import customDataTypes.Money;

public class TestData {
    private final InterestRate CONSTANT_RATE = new InterestRate(0.05f);
    private final Money CONSTANT_PRINCIPAL = new Money(2000);
    private final int CONSTANT_TERM = 5;

    private final boolean COMPOUNDED = true;
    private final boolean GROWTH = false;

    private int validTerm = 1;
    private int negativeTerm = -1;
    private int zeroTerm = 0;

    private InterestRate validRate = new InterestRate(0.01f);
    private InterestRate negativeRate = new InterestRate(-0.01f);
    private InterestRate largeInvalidRate = new InterestRate(0.3f);

    private InterestRate zeroRate = new InterestRate(0f);

    private Money validPrincipal = new Money(10000);
    private Money invalidPrincipal = new Money(-1);
    private Money zeroPrincipal = new Money(0);

    public InterestRate getConstantRate() {
        return CONSTANT_RATE;
    }

    public Money getConstantPrincipal() {
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

    public InterestRate getValidRate() {
        return validRate;
    }

    public void setValidRate(InterestRate validRate) {
        this.validRate = validRate;
    }

    public InterestRate getNegativeRate() {
        return negativeRate;
    }

    public void setNegativeRate(InterestRate negativeRate) {
        this.negativeRate = negativeRate;
    }

    public InterestRate getLargeInvalidRate() {
        return largeInvalidRate;
    }

    public void setLargeInvalidRate(InterestRate largeInvalidRate) {
        this.largeInvalidRate = largeInvalidRate;
    }

    public Money getValidPrincipal() {
        return validPrincipal;
    }

    public void setValidPrincipal(Money validPrincipal) {
        this.validPrincipal = validPrincipal;
    }

    public Money getInvalidPrincipal() {
        return invalidPrincipal;
    }

    public void setInvalidPrincipal(Money invalidPrincipal) {
        this.invalidPrincipal = invalidPrincipal;
    }

    public InterestRate getZeroRate() {
        return zeroRate;
    }

    public Money getZeroPrincipal() {
        return zeroPrincipal;
    }

    public boolean isCompounded() {
        return COMPOUNDED;
    }

    public boolean isGrowth() {
        return GROWTH;
    }
}
