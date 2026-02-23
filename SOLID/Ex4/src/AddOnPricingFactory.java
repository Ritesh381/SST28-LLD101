public class AddOnPricingFactory {
    public MonthlyContribution getPricing(AddOn addon) {
        return switch (addon) {
            case MESS -> new MessPricing();
            case LAUNDRY -> new LaundryPricing();
            case GYM -> new GymPricing();
        };
    }
}
