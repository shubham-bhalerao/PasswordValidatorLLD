package rules;

import model.Context;
import model.ValidationResult;

public class MaxLengthRule implements PasswordRule {
    private final int max;

    public MaxLengthRule(int max) {
        this.max = max;
    }

    @Override
    public ValidationResult validate(String password, Context ctx) {
        if (password.length() <= max) return ValidationResult.ok(id());
        return ValidationResult.fail(id(), "Maximum length is " + max);
    }

    @Override
    public String id() {
        return "MAX_LENGTH";
    }
}
