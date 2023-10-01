import customDataTypes.InterestRate;
import customDataTypes.Money;

public class InterestGrowthCalculator extends Calculator{
    public InterestGrowthCalculator(InterestRate interestRate, int term) {
        super(interestRate, term);
    }

    public void calculateGrowth(Money principal) {
        calculateInterest(principal, false);
    }
}