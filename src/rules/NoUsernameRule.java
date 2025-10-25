package rules;

import model.Context;
import model.ValidationResult;

public class NoUsernameRule implements PasswordRule {
    @Override
    public String id() {
        return "NO_USERNAME_SUBSTRING";
    }

    @Override
    public ValidationResult validate(String password, Context ctx) {
        if (ctx.getUsername() == null) return ValidationResult.ok(id());
        if (password.toLowerCase().contains(ctx.getUsername().toLowerCase()))
            return ValidationResult.fail(id(), "Password must not contain username");
        return ValidationResult.ok(id());
    }
}
