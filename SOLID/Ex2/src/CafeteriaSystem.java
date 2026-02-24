import java.util.*;

public class CafeteriaSystem {
    private final Map<String, MenuItem> menu = new LinkedHashMap<>();
    private final PricingCalculator pricingCalculator;
    private final InvoiceFormatter invoiceFormatter;
    private final InvoiceStore store;
    private int invoiceSeq = 1000;

    public CafeteriaSystem(PricingCalculator pricingCalculator, InvoiceFormatter invoiceFormatter, InvoiceStore store) {
        this.pricingCalculator = pricingCalculator;
        this.invoiceFormatter = invoiceFormatter;
        this.store = store;
    }

    public void addToMenu(MenuItem i) { menu.put(i.id, i); }

    public void checkout(String customerType, List<OrderLine> lines) {
        String invId = "INV-" + (++invoiceSeq);
        List<MenuItem> menuItems = new ArrayList<>();
        for (OrderLine l : lines) {
            MenuItem item = menu.get(l.itemId);
            if (item == null) {
                throw new IllegalArgumentException("Unknown menu item id: " + l.itemId);
            }
            menuItems.add(item);
        }

        PricingCalculator.InvoiceSnapshot snapshot = pricingCalculator.calculate(customerType, menuItems, lines);
        String printable = invoiceFormatter.format(invId, snapshot);
        System.out.print(printable);

        store.save(invId, printable);
        System.out.println("Saved invoice: " + invId + " (lines=" + store.countLines(invId) + ")");
    }
}
