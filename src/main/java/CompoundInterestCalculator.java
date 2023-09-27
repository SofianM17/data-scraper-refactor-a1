public class CompoundInterestCalculator {
    private float interestRate;
    private int term;

    public float getInterestRate() {
        return interestRate;
    }

    public int getTerm() {
        return term;
    }

    public CompoundInterestCalculator(float interestRate, int term) {
        this.interestRate = interestRate;
        this.term = term;
    }

    public void calculateCompoundInterest(float principal) {
        float interestRate = getInterestRate();
        int term = getTerm();
        float total = principal;
        System.out.println("--- Compounded Interest Projection Over " + term + " Years ---");

        for (int year = 1; year <= term; year++) {
            double interest = total * interestRate;
            total += interest;

            System.out.println("Year " + year + " | Interest Rate: " + (interestRate * 100) + "%" + " | Interest Earned: $" + interest);
        }

        System.out.println("Your total balance after " + term + " years with an initial principal of $" + principal +
                " may be $" + total);
    }
}
