package rules;

import model.Context;
import model.ValidationResult;

public class MinLengthRule implements PasswordRule {
    private final int min;

    public MinLengthRule(int min) {
        this.min = min;
    }

    @Override
    public ValidationResult validate(String password, Context ctx) {
        if (password == null) return ValidationResult.fail(id(), "Password is null");
        return password.length() >= min ? ValidationResult.ok(id())
                : ValidationResult.fail(id(), "Minimum length is " + min);
    }

    @Override
    public String id() {
        return "MIN_LENGTH";
    }
}
