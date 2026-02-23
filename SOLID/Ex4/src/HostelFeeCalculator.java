import java.util.*;

public class HostelFeeCalculator {
    private final FakeBookingRepo repo;
    private final AddOnPricingFactory addOnFactory;

    public HostelFeeCalculator(FakeBookingRepo repo) {
        this(repo, new AddOnPricingFactory());
    }

    public HostelFeeCalculator(FakeBookingRepo repo, AddOnPricingFactory addOnFactory) {
        this.repo = repo;
        this.addOnFactory = addOnFactory;
    }

    public void process(BookingRequest req) {
        Money monthly = calculateMonthly(req);
        Money deposit = new Money(5000.00);

        ReceiptPrinter.print(req, monthly, deposit);

        String bookingId = "H-" + (7000 + new Random(1).nextInt(1000)); // deterministic-ish
        repo.save(bookingId, req, monthly, deposit);
    }

    private Money calculateMonthly(BookingRequest req) {
        List<MonthlyContribution> contributions = buildContributions(req);
        double total = 0.0;
        for (MonthlyContribution contrib : contributions) {
            total += contrib.contribute().amount;
        }
        return new Money(total);
    }

    private List<MonthlyContribution> buildContributions(BookingRequest req) {
        List<MonthlyContribution> list = new ArrayList<>();
        list.add(new RoomPricing(req.roomType));
        for (AddOn addon : req.addOns) {
            list.add(addOnFactory.getPricing(addon));
        }
        
        return list;
    }
}
