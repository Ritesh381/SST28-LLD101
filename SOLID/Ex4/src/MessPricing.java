public class MessPricing implements MonthlyContribution {
    @Override
    public Money contribute() {
        return new Money(1000.0);
    }
}
