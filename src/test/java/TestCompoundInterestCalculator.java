import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestCompoundInterestCalculator extends TestCalculator{
    @Test
    public void testFutureValueConstants(){
        float expectedFutureValue = 2552.56f;

        // test futureValue with valid arguments
        compoundInterestCalculator_constants.calculateCompoundInterest(CONSTANT_PRINCIPAL);
        assertEquals(expectedFutureValue, compoundInterestCalculator_constants.getFutureValue());
    }

    @Test
    public void testInterestEarned() {
        float expectedMaxInterestEarned = 121.55f;

        // test interestEarned with valid arguments
        compoundInterestCalculator_constants.calculateCompoundInterest(CONSTANT_PRINCIPAL);
        assertEquals(expectedMaxInterestEarned, compoundInterestCalculator_constants.getMaxInterestEarned());
    }
}
