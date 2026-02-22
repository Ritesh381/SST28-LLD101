import java.util.Optional;

public class CreditsRule implements EligibilityRule {
    @Override
    public Optional<String> reasonIfNotEligible(StudentProfile student) {
        if (student.earnedCredits < 20) {
            return Optional.of("credits below " + 20);
        }
        return Optional.empty();
    }
}
