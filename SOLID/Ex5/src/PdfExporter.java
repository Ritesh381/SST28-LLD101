public class PdfExporter extends Exporter {
    @Override
    protected String contentType() {
        return "application/pdf";
    }

    @Override
    protected String encode(ExportRequest req) {
        return "PDF(" + req.title + "):" + req.body;
    }

    @Override
    protected void validate(ExportRequest req) {
        if(req.body.length() > 20){
            throw new IllegalArgumentException("ERROR: PDF cannot handle content > 20 chars");
        }
    }
}
