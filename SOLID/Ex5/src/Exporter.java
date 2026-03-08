import java.nio.charset.StandardCharsets;

public abstract class Exporter {
    public final ExportResult export(ExportRequest req) {
        if (req == null) {
            throw new IllegalArgumentException("ExportRequest must not be null");
        }

        ExportRequest normalized = new ExportRequest(normalize(req.title), normalize(req.body));
        String payload = encode(normalized);
        if (payload == null) {
            throw new IllegalStateException("Encoded payload must not be null");
        }
        validate(req);

        ExportResult result = new ExportResult(contentType(), payload.getBytes(StandardCharsets.UTF_8));
        return result;
    }

    protected abstract String contentType();

    protected abstract String encode(ExportRequest req);
    
    protected void validate(ExportRequest req) {}

    private String normalize(String text) {
        return text == null ? "" : text;
    }

}
