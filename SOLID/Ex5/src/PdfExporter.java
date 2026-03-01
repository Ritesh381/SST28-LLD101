public class PdfExporter extends Exporter {
    @Override
    protected String contentType() {
        return "application/pdf";
    }

    @Override
    protected String encode(ExportRequest req) {
        return "PDF(" + req.title + "):" + req.body;
    }
}
