import customDataTypes.Money;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestInterestGrowthCalculator extends TestCalculator {
    @Test
    public void testFutureValueConstants(){
        Money expectedFutureValue = new Money (2500f);

        // test futureValue with valid arguments
        interestGrowthCalculator_constants.growthCalc(CONSTANT_PRINCIPAL);
        assertEquals(expectedFutureValue.toString(), interestGrowthCalculator_constants.getFutureValue().toString());
    }

    @Test
    public void testInterestEarned() {
        Money expectedInterestEarned = CONSTANT_PRINCIPAL.multiply(CONSTANT_RATE.getAmount());

        // test interestEarned with valid arguments
        interestGrowthCalculator_constants.growthCalc(CONSTANT_PRINCIPAL);
        assertEquals(expectedInterestEarned.toString(), interestGrowthCalculator_constants.getInterestEarned().toString());
    }
}
