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

        ExportResult result = new ExportResult(contentType(), payload.getBytes(StandardCharsets.UTF_8));
        validateResult(result);
        return result;
    }

    protected abstract String contentType();

    protected abstract String encode(ExportRequest req);

    private String normalize(String text) {
        return text == null ? "" : text;
    }

    private void validateResult(ExportResult result) {
        if (result == null) {
            throw new IllegalStateException("ExportResult must not be null");
        }
        if (result.contentType == null || result.contentType.isBlank()) {
            throw new IllegalStateException("contentType must not be blank");
        }
        if (result.bytes == null) {
            throw new IllegalStateException("bytes must not be null");
        }
    }
}
