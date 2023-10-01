import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestInterestGrowthCalculator extends TestCalculator {
    @Test
    public void testFutureValueConstants(){
        float expectedFutureValue = 2500f;

        // test futureValue with valid arguments
        interestGrowthCalculator_constants.growthCalc(CONSTANT_PRINCIPAL);
        assertEquals(expectedFutureValue, interestGrowthCalculator_constants.getFutureValue());
    }

    @Test
    public void testInterestEarned() {
        float expectedInterestEarned = CONSTANT_PRINCIPAL * CONSTANT_RATE;

        // test interestEarned with valid arguments
        interestGrowthCalculator_constants.growthCalc(CONSTANT_PRINCIPAL);
        assertEquals(expectedInterestEarned, interestGrowthCalculator_constants.getInterestEarned());
    }
}
