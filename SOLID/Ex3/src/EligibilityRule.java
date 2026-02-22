import java.util.Optional;

public interface EligibilityRule {
    Optional<String> reasonIfNotEligible(StudentProfile student);
}
