package demo;

import factory.PasswordValidatorFactory;
import model.Context;
import model.PasswordPolicy;
import model.Role;
import service.NoOpBreachService;
import service.PasswordValidator;

import java.util.Map;

public class PasswordValidatorDemo {
    public static void main(String[] args) {
        PasswordPolicy regular = new PasswordPolicy.Builder()
                .minLength(8).minLowerCase(1).minDigits(1)
                .addBlacklistRegex("password|123456|qwerty").build();

        PasswordPolicy admin = new PasswordPolicy.Builder()
                .minLength(12).minUpperCase(1).minLowerCase(1)
                .minDigits(1).minSpecial(1).disallowUsername(true)
                .requireEntropyCheck(true).minEntropyBits(60)
                .addBlacklistRegex("password|admin|letmein").build();

        Map<Role, PasswordPolicy> map = Map.of(
                Role.REGULAR_USER, regular,
                Role.ADMIN, admin
        );
        PasswordValidatorFactory factory = new PasswordValidatorFactory(map, new NoOpBreachService());
        PasswordValidator adminVal = factory.create(Role.ADMIN);

        Context ctx = new Context("rootAdmin", Role.ADMIN);
        String[] pwds = {"password123", "S3cure!Pass", "Very$trongPassword123!"};
        for (String p : pwds) {
            System.out.println("Password: " + p);
            System.out.println(adminVal.validate(p, ctx));
        }
    }
}
