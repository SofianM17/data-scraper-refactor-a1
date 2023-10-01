package customDataTypes;

public class InterestRate extends Money{
    public InterestRate(float amount) {
        super(amount);
    }

    public String toString() {
        return getAmount() * 100 + "%";
    }
}
