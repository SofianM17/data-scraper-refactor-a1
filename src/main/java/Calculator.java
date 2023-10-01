import customDataTypes.InterestRate;
import customDataTypes.Money;

public class Calculator {
    private InterestRate interestRate;
    private Money initialPrincipal;
    private Money futureValue;
    private int term;
    private Money interestEarned;

    public Calculator(InterestRate interestRate, int term) {
        if (term < 0) {
            throw new IllegalArgumentException("A value of 0 or greater must be provided for the term");
        }

        if (interestRate.getAmount() < 0f || interestRate.getAmount() >= 0.3f) {
            throw new IllegalArgumentException("A value of 0 or greater but less than 0.3 must be provided for the term. " +
                    "Negative interest rates or rates above 0.3 go against historical context in Canada.");
        }

        setInterestRate(interestRate);
        setTerm(term);
    }

    public InterestRate getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(InterestRate interestRate) {
        this.interestRate = interestRate;
    }

    public Money getFutureValue() {
        return futureValue;
    }

    public void setFutureValue(Money futureValue) {
        this.futureValue = futureValue;
    }

    public int getTerm() {
        return term;
    }

    public void setTerm(int term) {
        this.term = term;
    }

    public Money getInitialPrincipal() {
        return initialPrincipal;
    }

    public void setInitialPrincipal(Money initialPrincipal) {
        this.initialPrincipal = initialPrincipal;
    }

    public Money getInterestEarned() {
        return interestEarned;
    }

    public void setInterestEarned(Money interestEarned) {
        this.interestEarned = interestEarned;
    }

    public void calculateInterest (Money principal, boolean isCompounded) {
        if (principal.getAmount() < 0) {
            throw new IllegalArgumentException("A positive principal must be provided");
        }

        setInitialPrincipal(principal);

        InterestRate interestRate = getInterestRate();
        int term = getTerm();
        futureValue = principal;

        String projectionType = isCompounded ? "Compunded Interest Projection" : "Uncompounded Growth Projection";
        System.out.println("--- " + projectionType + " Over " + term + " Years ---");

        for (int year = 1; year <= term; year++) {
            setInterestEarned(isCompounded ? futureValue.multiply(interestRate.getAmount()) : getInitialPrincipal().multiply(interestRate.getAmount()));
            futureValue = futureValue.add(interestEarned);

            System.out.println("Year " + year + " | Interest Rate: " + interestRate + " | Interest Earned: " + interestEarned);
        }
        setFutureValue(futureValue);

        System.out.println("Your total balance after " + term + " years with an initial principal of " + getInitialPrincipal() +
                " may be " + getFutureValue());
    }
}
