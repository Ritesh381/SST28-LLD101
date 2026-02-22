import java.util.*;

public class CalcTotal {
    private double subTotal;
    private final Map<String, MenuItem> menu;
    
    CalcTotal(Map<String, MenuItem> menu){
        this.subTotal = 0;
        this.menu =  menu;
    }

    public double calculate(List<OrderLine> lines, String customerType, StringBuilder out){
        double subtotal = 0.0;
        for (OrderLine l : lines) {
            MenuItem item = menu.get(l.itemId);
            double lineTotal = item.price * l.qty;
            subtotal += lineTotal;
            out.append(String.format("- %s x%d = %.2f\n", item.name, l.qty, lineTotal));
        }

        double taxPct = TaxRules.taxPercent(customerType);
        double tax = subtotal * (taxPct / 100.0);

        double discount = DiscountRules.discountAmount(customerType, subtotal, lines.size());

        double total = subtotal + tax - discount;
        return total;
    }
}
