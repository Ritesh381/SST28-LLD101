public class WhatsAppSender extends NotificationSender {
    public WhatsAppSender(AuditLog audit) { super(audit); }

    @Override
    protected String ServiceType(){
        return "WA";
    }

    @Override
    protected void ExtraValidation(Notification n) {
        if (n.phone == null || !n.phone.startsWith("+")) {
            throw new IllegalArgumentException("phone must start with + and country code");
        }
    }

    @Override
    protected String render(Notification n) {
        return "WA -> to=" + n.phone + " body=" + n.body;
    }
}
