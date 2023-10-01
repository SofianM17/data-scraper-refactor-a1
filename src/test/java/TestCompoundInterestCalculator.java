import customDataTypes.Money;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestCompoundInterestCalculator extends TestCalculator{
    @Test
    public void testFutureValueConstants(){
        Money expectedFutureValue = new Money(2552.56f);

        // test futureValue with valid arguments
        compoundInterestCalculator_constants.calculateCompoundInterest(CONSTANT_PRINCIPAL);
        assertEquals(expectedFutureValue.toString(), compoundInterestCalculator_constants.getFutureValue().toString());
    }

    @Test
    public void testInterestEarned() {
        Money expectedMaxInterestEarned = new Money(121.55f);

        // test interestEarned with valid arguments
        compoundInterestCalculator_constants.calculateCompoundInterest(CONSTANT_PRINCIPAL);
        assertEquals(expectedMaxInterestEarned.toString(), compoundInterestCalculator_constants.getMaxInterestEarned().toString());
    }
}
