public class LaundryPricing implements MonthlyContribution {
    @Override
    public Money contribute() {
        return new Money(500.0);
    }
}
