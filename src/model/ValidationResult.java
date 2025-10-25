package model;

public class ValidationResult {
    final boolean valid;
    final String ruleId;
    final String message;

    public boolean isValid() {
        return valid;
    }

    public String getRuleId() {
        return ruleId;
    }

    public String getMessage() {
        return message;
    }

    ValidationResult(boolean valid, String ruleId, String message) {
        this.valid = valid;
        this.ruleId = ruleId;
        this.message = message;
    }

    public static ValidationResult ok(String ruleId) {
        return new ValidationResult(true, ruleId, "OK");
    }

    public static ValidationResult fail(String ruleId, String message) {
        return new ValidationResult(false, ruleId, message);
    }
}
