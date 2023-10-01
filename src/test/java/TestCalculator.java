import customDataTypes.InterestRate;
import customDataTypes.Money;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestCalculator {
    TestData testData = new TestData();
    InterestRate CONSTANT_RATE = testData.getConstantRate();
    int CONSTANT_TERM = testData.getConstantTerm();
    Money CONSTANT_PRINCIPAL = testData.getConstantPrincipal();

    int validTerm = testData.getValidTerm();
    int invalidTerm = testData.getNegativeTerm();
    int zeroTerm = testData.getZeroTerm();

    InterestRate validRate = testData.getValidRate();
    InterestRate negativeInvalidRate = testData.getNegativeRate();
    InterestRate largeInvalidRate = testData.getLargeInvalidRate();
    InterestRate zeroRate = testData.getZeroRate();

    Money invalidPrincipal = testData.getInvalidPrincipal();
    Money validPrincipal = testData.getValidPrincipal();
    Money zeroPrincipal = testData.getZeroPrincipal();

    InterestGrowthCalculator interestGrowthCalculator_constants;
    InterestGrowthCalculator interestGrowthCalculator_zeroRate;
    InterestGrowthCalculator interestGrowthCalculator_zeroTerm;

    CompoundInterestCalculator compoundInterestCalculator_constants;
    CompoundInterestCalculator compoundInterestCalculator_zeroRates;
    CompoundInterestCalculator compoundInterestCalculator_zeroTerm;

    @BeforeEach
    public void initCalculators() {
        interestGrowthCalculator_constants = new InterestGrowthCalculator(CONSTANT_RATE, CONSTANT_TERM);
        interestGrowthCalculator_zeroRate = new InterestGrowthCalculator(zeroRate, CONSTANT_TERM);
        interestGrowthCalculator_zeroTerm =  new InterestGrowthCalculator(CONSTANT_RATE, zeroTerm);

        compoundInterestCalculator_constants = new CompoundInterestCalculator(CONSTANT_RATE, CONSTANT_TERM);
        compoundInterestCalculator_zeroRates = new CompoundInterestCalculator(zeroRate, CONSTANT_TERM);
        compoundInterestCalculator_zeroTerm =  new CompoundInterestCalculator(CONSTANT_RATE, zeroTerm);
    }

    @Test
    public void testCalcTerm() {

        // test for a valid term
        assertEquals(validTerm,
                new InterestGrowthCalculator(CONSTANT_RATE, validTerm).getTerm(),
                "Expected term to be initialized with the valid term of " + validTerm);

        // test for a negative term - an exception should be thrown
        assertThrows(IllegalArgumentException.class, () -> new InterestGrowthCalculator(CONSTANT_RATE, invalidTerm),
                "With term = " + invalidTerm + ", expected IllegalArgumentException to be thrown");
    }

    @Test
    public void testCalcRate() {
        // test for a valid rate
        assertEquals(validRate,
                new InterestGrowthCalculator(validRate, CONSTANT_TERM).getInterestRate(),
                "Expected rate to be initialized with the valid rate of " + validRate);

        // test for a negative invalid rate
        assertThrows(IllegalArgumentException.class, () -> new InterestGrowthCalculator(negativeInvalidRate, CONSTANT_TERM),
                "With negative rate = " + negativeInvalidRate + ", expected IllegalArgumentException to be thrown");

        // test for an unrealistically large invalid rate
        assertThrows(IllegalArgumentException.class, () -> new InterestGrowthCalculator(largeInvalidRate, CONSTANT_TERM),
                "With unrealistically large rate = " + largeInvalidRate + ", expected IllegalArgumentException to be thrown");
    }

    @Test
    public void testCalcPrincipal() {
        float uninitializedFloatVal = 0.0f;

        // test for invalid (negative) principal
        assertThrows(IllegalArgumentException.class, () -> interestGrowthCalculator_constants.growthCalc(invalidPrincipal),
                "with negative principal = " + invalidPrincipal + ", expected IllegalArgumentException to be thrown");

        // if principal is valid, future value should not be the default of 0.0f
        interestGrowthCalculator_constants.growthCalc(validPrincipal);
        assertNotEquals(uninitializedFloatVal, interestGrowthCalculator_constants.getFutureValue());
    }

    @Test
    public void testFutureValueArgConditions() {

        // test futureValue with interest rate of 0
        interestGrowthCalculator_zeroRate.growthCalc(CONSTANT_PRINCIPAL);
        assertEquals(CONSTANT_PRINCIPAL.toString(), interestGrowthCalculator_zeroRate.getFutureValue().toString(),
                "with an interest rate of 0, futureValue is expected to match principal of " + CONSTANT_PRINCIPAL);

        //  test futureValue with a principal of 0
        interestGrowthCalculator_constants.growthCalc(zeroPrincipal);
        assertEquals(zeroPrincipal.toString(), interestGrowthCalculator_constants.getFutureValue().toString(),
                "with a principal of 0, futureValue is expected to match principal of 0");

        // test futureValue with a term of 0
        interestGrowthCalculator_zeroTerm.growthCalc(CONSTANT_PRINCIPAL);
        assertEquals(CONSTANT_PRINCIPAL.toString(), interestGrowthCalculator_zeroTerm.getFutureValue().toString(),
                "with a term of 0, futureValue is expected to match principal of " + CONSTANT_PRINCIPAL);
    }

    @Test
    public void testInterestEarnedArgConditions() {
        // test interestEarned with interest rate of 0
        interestGrowthCalculator_zeroRate.growthCalc(CONSTANT_PRINCIPAL);
        assertEquals(new Money(0).toString(), interestGrowthCalculator_zeroRate.getInterestEarned().toString(),
                "with an interest rate of 0, interestEarned is expected to be 0");

        //  test interestEarned with a principal of 0
        interestGrowthCalculator_constants.growthCalc(zeroPrincipal);
        assertEquals(new Money(0).toString(), interestGrowthCalculator_constants.getInterestEarned().toString(),
                "with a principal of 0, interestEarned is expected to be 0");

        // test interestEarned with a term of 0
        interestGrowthCalculator_zeroTerm.growthCalc(CONSTANT_PRINCIPAL);
        assertEquals(new Money(0).toString(), interestGrowthCalculator_constants.getInterestEarned().toString(),
                "with a term of 0, interestEarned is expected to be 0");
    }
}
