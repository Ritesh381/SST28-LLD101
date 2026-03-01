public class EmailSender extends NotificationSender {
    public EmailSender(AuditLog audit) { super(audit); }

    @Override
    protected String ServiceType(){
        return "EMAIL";
    }

    @Override
    protected String render(Notification n) {
        return "EMAIL -> to=" + n.email + " subject=" + n.subject + " body=" + n.body;
    }
}
