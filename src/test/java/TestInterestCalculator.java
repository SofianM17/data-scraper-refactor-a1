import customDataTypes.InterestRate;
import customDataTypes.Money;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestInterestCalculator {
    TestData testData = new TestData();
    InterestRate CONSTANT_RATE = testData.getConstantRate();
    int CONSTANT_TERM = testData.getConstantTerm();
    Money CONSTANT_PRINCIPAL = testData.getConstantPrincipal();

    boolean COMPOUNDED = testData.isCompounded();
    boolean GROWTH = testData.isGrowth();

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

    InterestCalculator interestCalculator_constants;
    InterestCalculator interestCalculator_zeroRate;
    InterestCalculator interestCalculator_zeroTerm;

    @BeforeEach
    public void initCalculators() {
        interestCalculator_constants = new InterestCalculator(CONSTANT_RATE, CONSTANT_TERM);
        interestCalculator_zeroRate = new InterestCalculator(zeroRate, CONSTANT_TERM);
        interestCalculator_zeroTerm =  new InterestCalculator(CONSTANT_RATE, zeroTerm);
    }

    @Test
    public void testCalcTerm() {

        // test for a valid term
        assertEquals(validTerm,
                new InterestCalculator(CONSTANT_RATE, validTerm).getTerm(),
                "Expected term to be initialized with the valid term of " + validTerm);

        // test for a negative term - an exception should be thrown
        assertThrows(IllegalArgumentException.class, () -> new InterestCalculator(CONSTANT_RATE, invalidTerm),
                "With term = " + invalidTerm + ", expected IllegalArgumentException to be thrown");
    }

    @Test
    public void testCalcRate() {
        // test for a valid rate
        assertEquals(validRate,
                new InterestCalculator(validRate, CONSTANT_TERM).getInterestRate(),
                "Expected rate to be initialized with the valid rate of " + validRate);

        // test for a negative invalid rate
        assertThrows(IllegalArgumentException.class, () -> new InterestCalculator(negativeInvalidRate, CONSTANT_TERM),
                "With negative rate = " + negativeInvalidRate + ", expected IllegalArgumentException to be thrown");

        // test for an unrealistically large invalid rate
        assertThrows(IllegalArgumentException.class, () -> new InterestCalculator(largeInvalidRate, CONSTANT_TERM),
                "With unrealistically large rate = " + largeInvalidRate + ", expected IllegalArgumentException to be thrown");
    }

    @Test
    public void testCalcPrincipal() {
        float uninitializedFloatVal = 0.0f;

        // test for invalid (negative) principal
        assertThrows(IllegalArgumentException.class, () -> interestCalculator_constants.calculateInterest(invalidPrincipal, false),
                "with negative principal = " + invalidPrincipal + ", expected IllegalArgumentException to be thrown");

        // if principal is valid, future value should not be the default of 0.0f
        interestCalculator_constants.calculateInterest(validPrincipal, GROWTH);
        assertNotEquals(uninitializedFloatVal, interestCalculator_constants.getFutureValue());
    }

    @Test
    public void testFutureValueArgConditions() {

        // test futureValue with interest rate of 0
        interestCalculator_zeroRate.calculateInterest(CONSTANT_PRINCIPAL, GROWTH);
        assertEquals(CONSTANT_PRINCIPAL.toString(), interestCalculator_zeroRate.getFutureValue().toString(),
                "with an interest rate of 0, futureValue is expected to match principal of " + CONSTANT_PRINCIPAL);

        //  test futureValue with a principal of 0
        interestCalculator_constants.calculateInterest(zeroPrincipal, GROWTH);
        assertEquals(zeroPrincipal.toString(), interestCalculator_constants.getFutureValue().toString(),
                "with a principal of 0, futureValue is expected to match principal of 0");

        // test futureValue with a term of 0
        interestCalculator_zeroTerm.calculateInterest(CONSTANT_PRINCIPAL, GROWTH);
        assertEquals(CONSTANT_PRINCIPAL.toString(), interestCalculator_zeroTerm.getFutureValue().toString(),
                "with a term of 0, futureValue is expected to match principal of " + CONSTANT_PRINCIPAL);
    }

    @Test
    public void testInterestEarnedArgConditions() {
        // test interestEarned with interest rate of 0
        interestCalculator_zeroRate.calculateInterest(CONSTANT_PRINCIPAL, GROWTH);
        assertEquals(new Money(0).toString(), interestCalculator_zeroRate.getInterestEarned().toString(),
                "with an interest rate of 0, interestEarned is expected to be 0");

        //  test interestEarned with a principal of 0
        interestCalculator_constants.calculateInterest(zeroPrincipal, GROWTH);
        assertEquals(new Money(0).toString(), interestCalculator_constants.getInterestEarned().toString(),
                "with a principal of 0, interestEarned is expected to be 0");

        // test interestEarned with a term of 0
        interestCalculator_zeroTerm.calculateInterest(CONSTANT_PRINCIPAL, GROWTH);
        assertEquals(new Money(0).toString(), interestCalculator_constants.getInterestEarned().toString(),
                "with a term of 0, interestEarned is expected to be 0");
    }

    @Test
    public void testFutureValueConstants(){
        Money expectedFutureValueGrowth = new Money (2500f);
        Money expectedFutureCompounded= new Money(2552.56f);

        // test futureValue with valid arguments for growth calculation
        interestCalculator_constants.calculateInterest(CONSTANT_PRINCIPAL, GROWTH);
        assertEquals(expectedFutureValueGrowth.toString(), interestCalculator_constants.getFutureValue().toString());

        // test futureValue with valid arguments for compounded calculation
        interestCalculator_constants.calculateInterest(CONSTANT_PRINCIPAL, COMPOUNDED);
        assertEquals(expectedFutureCompounded.toString(), interestCalculator_constants.getFutureValue().toString());
    }

    @Test
    public void testInterestEarned() {
        Money expectedInterestEarnedGrowth = CONSTANT_PRINCIPAL.multiply(CONSTANT_RATE.getAmount());
        Money expectedInterestEarnedCompounded = new Money(121.55f);

        // test interestEarned with valid arguments for growth calculation
        interestCalculator_constants.calculateInterest(CONSTANT_PRINCIPAL, GROWTH);
        assertEquals(expectedInterestEarnedGrowth.toString(), interestCalculator_constants.getInterestEarned().toString());

        // test interestEarned with valid arguments
        interestCalculator_constants.calculateInterest(CONSTANT_PRINCIPAL, COMPOUNDED);
        assertEquals(expectedInterestEarnedCompounded.toString(), interestCalculator_constants.getInterestEarned().toString());
    }
}
