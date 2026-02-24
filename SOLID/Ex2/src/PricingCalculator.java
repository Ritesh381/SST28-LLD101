import java.util.*;

public class PricingCalculator {
    private final TaxPolicy taxPolicy;
    private final DiscountPolicy discountPolicy;

    public PricingCalculator(TaxPolicy taxPolicy, DiscountPolicy discountPolicy) {
        this.taxPolicy = taxPolicy;
        this.discountPolicy = discountPolicy;
    }

    public InvoiceSnapshot calculate(String customerType, List<MenuItem> menuItems, List<OrderLine> lines) {
        List<InvoiceLine> invoiceLines = new ArrayList<>();
        double subtotal = 0.0;

        for (int i = 0; i < lines.size(); i++) {
            OrderLine line = lines.get(i);
            MenuItem item = menuItems.get(i);
            double lineTotal = item.price * line.qty;
            subtotal += lineTotal;
            invoiceLines.add(new InvoiceLine(item.name, line.qty, lineTotal));
        }

        double taxPct = taxPolicy.taxPercent(customerType);
        double tax = subtotal * (taxPct / 100.0);
        double discount = discountPolicy.discountAmount(customerType, subtotal, lines.size());
        double total = subtotal + tax - discount;

        return new InvoiceSnapshot(invoiceLines, subtotal, taxPct, tax, discount, total);
    }

    public static class InvoiceLine {
        public final String itemName;
        public final int qty;
        public final double lineTotal;

        public InvoiceLine(String itemName, int qty, double lineTotal) {
            this.itemName = itemName;
            this.qty = qty;
            this.lineTotal = lineTotal;
        }
    }

    public static class InvoiceSnapshot {
        public final List<InvoiceLine> lines;
        public final double subtotal;
        public final double taxPct;
        public final double tax;
        public final double discount;
        public final double total;

        public InvoiceSnapshot(List<InvoiceLine> lines, double subtotal, double taxPct, double tax, double discount, double total) {
            this.lines = lines;
            this.subtotal = subtotal;
            this.taxPct = taxPct;
            this.tax = tax;
            this.discount = discount;
            this.total = total;
        }
    }
}
