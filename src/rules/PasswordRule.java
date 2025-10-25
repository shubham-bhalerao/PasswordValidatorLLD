package rules;

import model.Context;
import model.ValidationResult;

public interface PasswordRule {
    ValidationResult validate(String password, Context ctx);

    String id();
}
