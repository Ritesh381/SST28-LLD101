public class SmsSender extends NotificationSender {
    public SmsSender(AuditLog audit) { super(audit); }

    @Override
    protected String ServiceType(){
        return "SMS";
    }

    @Override
    protected String render(Notification n) {
        return "SMS -> to=" + n.phone + " body=" + n.body;
    }
}
