import customDataTypes.InterestRate;
import customDataTypes.Money;

public class InterestGrowthCalculator {
    private InterestRate interestRate;
    private int term;
    private Money interestEarned;
    private Money futureValue;

    public InterestGrowthCalculator(InterestRate interestRate, int term) {
        if (term < 0) {
            throw new IllegalArgumentException("A value of 0 or greater must be provided for the term");
        }

        if (interestRate.getAmount() < 0f || interestRate.getAmount() >= 0.3f) {
            throw new IllegalArgumentException("A value of 0 or greater but less than 0.3 must be provided for the interest rate. " +
                    "Negative interest rates or rates above 0.3 go against historical context in Canada.");
        }

        this.interestRate = interestRate;
        this.term = term;
    }

    public InterestRate getInterestRate() {
        return interestRate;
    }

    public int getTerm() {
        return term;
    }

    public Money getInterestEarned() {
        return interestEarned;
    }

    public Money getFutureValue() {
        return futureValue;
    }

    public void growthCalc(Money principal) {
        if(principal.getAmount() < 0) {
            throw new IllegalArgumentException("A positive principal must be provided");
        }

        InterestRate interestRate = getInterestRate();
        int term = getTerm();
        futureValue = principal;

        System.out.println("--- Uncompounded Growth Projection Over " + term + " Years ---");

        for (int year = 1; year <= term; year++) {
            interestEarned = principal.multiply(interestRate.getAmount());
            futureValue = futureValue.add(interestEarned);

            System.out.println("Year " + year + " | Interest Rate: " + interestRate + " | Interest Earned: " + interestEarned.toString());
        }

        System.out.println("Your total balance after " + term + " years with an initial principal of " + principal +
                " may be " + futureValue.toString());
    }
}