package model;

import java.util.Collections;
import java.util.List;

public class PasswordValidationReport {
    private final boolean valid;
    private final List<ValidationResult> results;

    public PasswordValidationReport(boolean valid, List<ValidationResult> results) {
        this.valid = valid;
        this.results = Collections.unmodifiableList(results);
    }

    public boolean isValid() {
        return valid;
    }

    public List<ValidationResult> getResults() {
        return results;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("PasswordValidationReport: ").append(valid ? "VALID" : "INVALID").append("\n");
        for (ValidationResult r : results) {
            sb.append(" - ").append(r.ruleId).append(": ").append(r.valid ? "OK" : "FAIL")
                    .append(" -> ").append(r.message).append("\n");
        }
        return sb.toString();
    }
}
