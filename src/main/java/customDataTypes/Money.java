package customDataTypes;

public class Money {
    private float amount;

    public Money(float amount) {
        this.amount = amount;
    }

    public float getAmount() {
        return roundFloat(amount);
    }

    public Money add(Money amountToAdd) {
        return new Money(this.amount + amountToAdd.amount);
    }
    public Money multiply(float multiplier) {
        return new Money(this.amount * multiplier);
    }

    private float roundFloat(float f) {
        return Math.round(f * 100.0f) / 100.0f;
    }

    public String toString() {
        return "$" + getAmount();
    }
}
