import customDataTypes.InterestRate;
import customDataTypes.Money;

public class CompoundInterestCalculator {
    private InterestRate interestRate;
    private int term;
    private Money futureValue;
    private Money maxInterestEarned;

    public InterestRate getInterestRate() {
        return interestRate;
    }

    public int getTerm() {
        return term;
    }

    public Money getMaxInterestEarned() {
        return maxInterestEarned;
    }

    public Money getFutureValue() {
        return futureValue;
    }

    public CompoundInterestCalculator(InterestRate interestRate, int term) {
        if (term < 0) {
            throw new IllegalArgumentException("A value of 0 or greater must be provided for the term");
        }

        if (interestRate.getAmount() < 0f || interestRate.getAmount() >= 0.3f) {
            throw new IllegalArgumentException("A value of 0 or greater but less than 0.3 must be provided for the term. " +
                    "Negative interest rates or rates above 0.3 go against historical context in Canada.");
        }

        this.interestRate = interestRate;
        this.term = term;
    }

    public void calculateCompoundInterest(Money principal) {
        if(principal.getAmount() < 0) {
            throw new IllegalArgumentException("A positive principal must be provided");
        }

        InterestRate interestRate = getInterestRate();
        int term = getTerm();
        futureValue = principal;
        System.out.println("--- Compounded Interest Projection Over " + term + " Years ---");

        for (int year = 1; year <= term; year++) {
            maxInterestEarned = futureValue.multiply(interestRate.getAmount());
            futureValue = futureValue.add(maxInterestEarned);

            System.out.println("Year " + year + " | Interest Rate: " + interestRate + " | Interest Earned: " + maxInterestEarned.toString());
        }

        System.out.println("Your total balance after " + term + " years with an initial principal of " + principal +
                " may be " + futureValue.toString());
    }
}
