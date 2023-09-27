import java.util.ArrayList;

public class InterestGrowthCalculator {
    private float interestRate;
    private int term;

    public InterestGrowthCalculator(float interestRate, int term) {
        this.interestRate = interestRate;
        this.term = term;
    }

    public float getInterestRate() {
        return interestRate;
    }

    public int getTerm() {
        return term;
    }

    public void growthCalc(float principal) {
        float interestRate = getInterestRate();
        int term = getTerm();
        float total = principal;
        System.out.println("--- Uncompounded Growth Projection Over " + term + " Years ---");
        for (int year = 1; year <= term; year++) {
            float estimatedGrowth = principal * interestRate;
            total += estimatedGrowth;

            System.out.println("Year " + year + " | Interest Rate: " + (interestRate * 100) + "% | Interest Earned: $" + estimatedGrowth);
        }
        System.out.println("Your total balance after " + term + " years with an initial principal of $" + principal +
                " may be $" + total);
    }
}