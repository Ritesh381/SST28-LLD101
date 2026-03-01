public class JsonExporter extends Exporter {
    @Override
    protected String contentType() {
        return "application/json";
    }

    @Override
    protected String encode(ExportRequest req) {
        return "{\"title\":\"" + escape(req.title) + "\",\"body\":\"" + escape(req.body) + "\"}";
    }

    private String escape(String s) {
        String escaped = s
                .replace("\\", "\\\\")
                .replace("\"", "\\\"")
                .replace("\n", "\\n")
                .replace("\r", "\\r")
                .replace("\t", "\\t");
        return escaped;
    }
}
