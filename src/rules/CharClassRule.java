package rules;

import model.Context;
import model.ValidationResult;

public class CharClassRule implements PasswordRule {
    private final int minUpper, minLower, minDigits, minSpecial;

    public CharClassRule(int u, int l, int d, int s) {
        minUpper = u;
        minLower = l;
        minDigits = d;
        minSpecial = s;
    }

    @Override
    public ValidationResult validate(String password, Context ctx) {
        int up = 0, low = 0, dig = 0, spec = 0;
        for (char c : password.toCharArray()) {
            if (Character.isUpperCase(c)) up++;
            else if (Character.isLowerCase(c)) low++;
            else if (Character.isDigit(c)) dig++;
            else spec++;
        }
        if (up < minUpper) return ValidationResult.fail(id(), "Need at least " + minUpper + " uppercase");
        if (low < minLower) return ValidationResult.fail(id(), "Need at least " + minLower + " lowercase");
        if (dig < minDigits) return ValidationResult.fail(id(), "Need at least " + minDigits + " digits");
        if (spec < minSpecial) return ValidationResult.fail(id(), "Need at least " + minSpecial + " special");
        return ValidationResult.ok(id());
    }

    @Override
    public String id() {
        return "CHAR_CLASS";
    }
}
