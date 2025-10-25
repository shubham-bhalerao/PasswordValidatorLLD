package rules;

import model.Context;
import model.ValidationResult;

import java.util.List;
import java.util.regex.Pattern;

public class RegexBlacklistRule implements PasswordRule {
    private final List<Pattern> patterns;

    public RegexBlacklistRule(List<Pattern> patterns) {
        this.patterns = patterns;
    }

    @Override
    public String id() {
        return "REGEX_BLACKLIST";
    }

    @Override
    public ValidationResult validate(String password, Context ctx) {
        for (Pattern p : patterns) {
            if (p.matcher(password).find())
                return ValidationResult.fail(id(), "Disallowed pattern: " + p.pattern());
        }
        return ValidationResult.ok(id());
    }
}
