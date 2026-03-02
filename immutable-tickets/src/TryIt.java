import com.example.tickets.IncidentTicket;
import com.example.tickets.TicketService;

import java.util.List;

/**
 * Starter demo that shows why mutability is risky.
 *
 * After refactor:
 * - direct mutation should not compile (no setters)
 * - external modifications to tags should not affect the ticket
 * - service "updates" should return a NEW ticket instance
 */
public class TryIt {

    public static void main(String[] args) {
        TicketService service = new TicketService();

        IncidentTicket ticket = service.createTicket("TCK-1001", "reporter@example.com", "Payment failing on checkout");
        System.out.println("Created: " + ticket);

        IncidentTicket assigned = service.assign(ticket, "agent@example.com");
        IncidentTicket escalated = service.escalateToCritical(assigned);
        System.out.println("\nAfter service updates (new instance): " + escalated);
        System.out.println("Original unchanged: " + ticket);

        List<String> tags = escalated.getTags();
        try {
            tags.add("HACKED_FROM_OUTSIDE");
            System.out.println("\nUnexpected: external mutation succeeded.");
        } catch (UnsupportedOperationException ex) {
            System.out.println("\nExternal tag mutation blocked as expected.");
        }
        System.out.println("After external attempt: " + escalated);
    }
}
