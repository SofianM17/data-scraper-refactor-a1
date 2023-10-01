package customDataTypeTests;
import customDataTypes.InterestRate;
import customDataTypes.Money;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
public class TestMoney {
    @Test
    public void testConstructor() {
        Money money = new Money(10.50f);
        assertEquals(10.50f, money.getAmount());
    }

    @Test
    public void testAdd() {
        Money money1 = new Money(10.50f);
        Money money2 = new Money(5.25f);
        Money result = money1.add(money2);
        assertEquals(15.75f, result.getAmount());
    }

    @Test
    public void testMultiply() {
        Money money = new Money(10.50f);
        Money result = money.multiply(2.0f);
        assertEquals(21.00f, result.getAmount());
    }

    @Test
    public void testRoundFloat() {
        Money money = new Money(10.555f);
        // Rounded to two decimal places
        assertEquals(10.56f, money.getAmount());
    }

    @Test
    public void testToString() {
        Money money = new Money(25.75f);
        InterestRate interestRate = new InterestRate(0.05f);
        assertEquals("$25.75", money.toString());
        assertEquals("5.0%", interestRate.toString());
    }
}
