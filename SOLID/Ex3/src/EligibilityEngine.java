import java.util.*;

public class EligibilityEngine {
    private final FakeEligibilityStore store;
    private final List<EligibilityRule> rules;

    public EligibilityEngine(FakeEligibilityStore store) {
        this(store, defaultRules(new RuleInput()));
    }

    public EligibilityEngine(FakeEligibilityStore store, List<EligibilityRule> rules) {
        this.store = store;
        this.rules = List.copyOf(rules);
    }

    public void runAndPrint(StudentProfile s) {
        ReportPrinter p = new ReportPrinter();
        EligibilityEngineResult r = evaluate(s); // giant conditional inside
        p.print(s, r);
        store.save(s.rollNo, r.status);
    }

    public EligibilityEngineResult evaluate(StudentProfile s) {
        List<String> reasons = new ArrayList<>();

        for (EligibilityRule rule : rules) {
            Optional<String> reason = rule.reasonIfNotEligible(s);
            if (reason.isPresent()) {
                reasons.add(reason.get());
                return new EligibilityEngineResult("NOT_ELIGIBLE", reasons);
            }
        }

        return new EligibilityEngineResult("ELIGIBLE", reasons);
    }

    private static List<EligibilityRule> defaultRules(RuleInput input) {
        return List.of(
                new DisciplinaryFlagRule(),
                new CgrRule(),
                new AttendanceRule(),
                new CreditsRule()
        );
    }
}

class EligibilityEngineResult {
    public final String status;
    public final List<String> reasons;
    public EligibilityEngineResult(String status, List<String> reasons) {
        this.status = status;
        this.reasons = reasons;
    }
}
