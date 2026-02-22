import java.util.Optional;

public class CgrRule implements EligibilityRule {
    @Override
    public Optional<String> reasonIfNotEligible(StudentProfile student) {
        if (student.cgr < 8.0) {
            return Optional.of("CGR below " + String.format("%.1f", 8.0));
        }
        return Optional.empty();
    }
}
