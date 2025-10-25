package service;

import model.Context;
import model.PasswordValidationReport;
import model.ValidationResult;
import rules.PasswordRule;

import java.util.ArrayList;
import java.util.List;

public class PasswordValidator {
    private final List<PasswordRule> rules;

    public PasswordValidator(List<PasswordRule> rules) {
        this.rules = rules;
    }

    public PasswordValidationReport validate(String password, Context ctx) {
        List<ValidationResult> res = new ArrayList<>();
        boolean all = true;
        for (PasswordRule r : rules) {
            ValidationResult vr = r.validate(password, ctx);
            res.add(vr);
            if (!vr.isValid()) all = false;
        }
        return new PasswordValidationReport(all, res);
    }
}
