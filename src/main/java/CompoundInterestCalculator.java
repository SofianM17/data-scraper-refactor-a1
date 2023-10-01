import customDataTypes.InterestRate;
import customDataTypes.Money;

public class CompoundInterestCalculator extends Calculator {
    public CompoundInterestCalculator(InterestRate interestRate, int term) {
        super(interestRate, term);
    }

    public void calculateCompoundInterest(Money principal) {
        calculateInterest(principal, true);
    }
}
