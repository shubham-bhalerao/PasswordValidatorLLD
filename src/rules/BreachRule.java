package rules;

import model.Context;
import model.ValidationResult;
import service.BreachService;

import java.security.MessageDigest;

public class BreachRule implements PasswordRule {
    private final BreachService svc;

    public BreachRule(BreachService s) {
        this.svc = s;
    }

    @Override
    public String id() {
        return "BREACH_CHECK";
    }

    @Override
    public ValidationResult validate(String password, Context ctx) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            StringBuilder hex = new StringBuilder();
            for (byte b : md.digest(password.getBytes()))
                hex.append(String.format("%02x", b & 0xff));
            String prefix = hex.toString().substring(0, 5);
            if (svc.isBreached(prefix))
                return ValidationResult.fail(id(), "Password found in breach DB");
            return ValidationResult.ok(id());
        } catch (Exception e) {
            return ValidationResult.fail(id(), e.getMessage());
        }
    }
}
