public class GymPricing implements MonthlyContribution {
    @Override
    public Money contribute() {
        return new Money(300.0);
    }
}
