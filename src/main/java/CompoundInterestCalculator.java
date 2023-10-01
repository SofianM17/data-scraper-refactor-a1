public class CompoundInterestCalculator {
    private float interestRate;
    private int term;
    private float futureValue;
    private float maxInterestEarned;

    public float getInterestRate() {
        return roundFloat(interestRate);
    }

    public int getTerm() {
        return term;
    }

    public float getEstimatedGrowth() {
        return roundFloat(interestRate);
    }

    public float getMaxInterestEarned() {
        return roundFloat(maxInterestEarned);
    }

    public float getFutureValue() {
        return roundFloat(futureValue);
    }

    public CompoundInterestCalculator(float interestRate, int term) {
        if (term < 0) {
            throw new IllegalArgumentException("A value of 0 or greater must be provided for the term");
        }

        if (interestRate < 0f || interestRate >= 0.3f) {
            throw new IllegalArgumentException("A value of 0 or greater but less than 0.3 must be provided for the term. " +
                    "Negative interest rates or rates above 0.3 go against historical context in Canada.");
        }

        this.interestRate = interestRate;
        this.term = term;
    }

    public float roundFloat(float f) {
        return Math.round(f * 100.0f) / 100.0f;
    }

    public void calculateCompoundInterest(float principal) {
        if(principal < 0) {
            throw new IllegalArgumentException("A positive principal must be provided");
        }

        float interestRate = getInterestRate();
        int term = getTerm();
        futureValue = principal;
        System.out.println("--- Compounded Interest Projection Over " + term + " Years ---");

        for (int year = 1; year <= term; year++) {
            maxInterestEarned = futureValue * interestRate;
            futureValue += maxInterestEarned;

            System.out.println("Year " + year + " | Interest Rate: " + (interestRate * 100) + "%" + " | Interest Earned: $" + maxInterestEarned);
        }

        System.out.println("Your total balance after " + term + " years with an initial principal of $" + principal +
                " may be $" + futureValue);
    }
}
