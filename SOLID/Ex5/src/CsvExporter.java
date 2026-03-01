public class CsvExporter extends Exporter {
    @Override
    protected String contentType() {
        return "text/csv";
    }

    @Override
    protected String encode(ExportRequest req) {
        return "title,body\n" + escape(req.title) + "," + escape(req.body) + "\n";
    }

    private String escape(String value) {
        String escaped = value.replace("\"", "\"\"");
        if (escaped.contains(",") || escaped.contains("\n") || escaped.contains("\r") || escaped.contains("\"")) {
            return "\"" + escaped + "\"";
        }
        return escaped;
    }
}
