public class InvoiceFormatter {
    public String format(String invoiceId, PricingCalculator.InvoiceSnapshot snapshot) {
        StringBuilder out = new StringBuilder();
        out.append("Invoice# ").append(invoiceId).append("\n");

        for (PricingCalculator.InvoiceLine line : snapshot.lines) {
            out.append(String.format("- %s x%d = %.2f\n", line.itemName, line.qty, line.lineTotal));
        }

        out.append(String.format("Subtotal: %.2f\n", snapshot.subtotal));
        out.append(String.format("Tax(%.0f%%): %.2f\n", snapshot.taxPct, snapshot.tax));
        out.append(String.format("Discount: -%.2f\n", snapshot.discount));
        out.append(String.format("TOTAL: %.2f\n", snapshot.total));
        return out.toString();
    }
}
