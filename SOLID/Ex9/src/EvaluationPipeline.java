import java.util.Objects;

public class EvaluationPipeline {
    private final PlagiarismCheckService plagiarismChecker;
    private final CodeGradingService codeGrader;
    private final ReportWritingService reportWriter;
    private final Rubric rubric;

    public EvaluationPipeline(
            PlagiarismCheckService plagiarismChecker,
            CodeGradingService codeGrader,
            ReportWritingService reportWriter,
            Rubric rubric
    ) {
        this.plagiarismChecker = Objects.requireNonNull(plagiarismChecker, "plagiarismChecker");
        this.codeGrader = Objects.requireNonNull(codeGrader, "codeGrader");
        this.reportWriter = Objects.requireNonNull(reportWriter, "reportWriter");
        this.rubric = Objects.requireNonNull(rubric, "rubric");
    }

    public void evaluate(Submission sub) {
        int plag = plagiarismChecker.check(sub);
        System.out.println("PlagiarismScore=" + plag);

        int code = codeGrader.grade(sub, rubric);
        System.out.println("CodeScore=" + code);

        String reportName = reportWriter.write(sub, plag, code);
        System.out.println("Report written: " + reportName);

        int total = plag + code;
        String result = (total >= 90) ? "PASS" : "FAIL";
        System.out.println("FINAL: " + result + " (total=" + total + ")");
    }
}
