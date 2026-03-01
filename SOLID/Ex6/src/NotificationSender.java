public abstract class NotificationSender {
    protected final AuditLog audit;
    protected NotificationSender(AuditLog audit) { this.audit = audit; }

    public final void send(Notification n) {
        if(n==null) throw new IllegalArgumentException("notification must not be null");
        ExtraValidation(n);
        System.out.println(render(n));
        audit.add(auditMessage());
    }

    protected void ExtraValidation(Notification n) {}

    protected String auditMessage() {
        return ServiceType().toLowerCase() + " sent";
    }

    protected abstract String render(Notification n);
    
    protected abstract String ServiceType();
}
