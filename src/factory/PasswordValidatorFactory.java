package factory;

import model.PasswordPolicy;
import model.Role;
import rules.*;
import service.BreachService;
import service.PasswordValidator;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PasswordValidatorFactory {
    private final Map<Role, PasswordPolicy> policies;
    private final BreachService breachService;

    public PasswordValidatorFactory(Map<Role, PasswordPolicy> p, BreachService svc) {
        this.policies = p;
        this.breachService = svc;
    }

    public PasswordValidator create(Role role) {
        PasswordPolicy p = policies.get(role);
        List<PasswordRule> rules = new ArrayList<>();
        rules.add(new MinLengthRule(p.getMinLength()));
        rules.add(new MaxLengthRule(p.getMaxLength()));
        rules.add(new CharClassRule(p.getMinUpperCase(), p.getMinLowerCase(), p.getMinDigits(), p.getMinSpecial()));
        if (p.isDisallowUsername()) rules.add(new NoUsernameRule());
        if (!p.getBlacklistPatterns().isEmpty()) rules.add(new RegexBlacklistRule(p.getBlacklistPatterns()));
        if (p.isCheckBreach()) rules.add(new BreachRule(breachService));
        return new PasswordValidator(rules);
    }
}
