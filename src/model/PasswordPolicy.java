package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Pattern;

public class PasswordPolicy {
    final int minLength;
    final int maxLength;
    final int minUpperCase;
    final int minLowerCase;
    final int minDigits;
    final int minSpecial;
    final boolean disallowUsername;
    final boolean requireEntropyCheck;
    final int minEntropyBits;
    final List<Pattern> blacklistPatterns;
    final boolean checkBreach;

    private PasswordPolicy(Builder b) {
        this.minLength = b.minLength;
        this.maxLength = b.maxLength;
        this.minUpperCase = b.minUpperCase;
        this.minLowerCase = b.minLowerCase;
        this.minDigits = b.minDigits;
        this.minSpecial = b.minSpecial;
        this.disallowUsername = b.disallowUsername;
        this.requireEntropyCheck = b.requireEntropyCheck;
        this.minEntropyBits = b.minEntropyBits;
        this.blacklistPatterns = Collections.unmodifiableList(b.blacklistPatterns);
        this.checkBreach = b.checkBreach;
    }

    public int getMinLength() {
        return minLength;
    }

    public int getMaxLength() {
        return maxLength;
    }

    public int getMinUpperCase() {
        return minUpperCase;
    }

    public int getMinLowerCase() {
        return minLowerCase;
    }

    public int getMinDigits() {
        return minDigits;
    }

    public int getMinSpecial() {
        return minSpecial;
    }

    public boolean isDisallowUsername() {
        return disallowUsername;
    }

    public boolean isRequireEntropyCheck() {
        return requireEntropyCheck;
    }

    public int getMinEntropyBits() {
        return minEntropyBits;
    }

    public List<Pattern> getBlacklistPatterns() {
        return blacklistPatterns;
    }

    public boolean isCheckBreach() {
        return checkBreach;
    }

    public static class Builder {
        private int minLength = 8;
        private int maxLength = 128;
        private int minUpperCase = 0;
        private int minLowerCase = 0;
        private int minDigits = 0;
        private int minSpecial = 0;
        private boolean disallowUsername = false;
        private boolean requireEntropyCheck = false;
        private int minEntropyBits = 40;
        private List<Pattern> blacklistPatterns = new ArrayList<>();
        private boolean checkBreach = false;

        public Builder minLength(int m) {
            this.minLength = m;
            return this;
        }

        public Builder maxLength(int m) {
            this.maxLength = m;
            return this;
        }

        public Builder minUpperCase(int v) {
            this.minUpperCase = v;
            return this;
        }

        public Builder minLowerCase(int v) {
            this.minLowerCase = v;
            return this;
        }

        public Builder minDigits(int v) {
            this.minDigits = v;
            return this;
        }

        public Builder minSpecial(int v) {
            this.minSpecial = v;
            return this;
        }

        public Builder disallowUsername(boolean v) {
            this.disallowUsername = v;
            return this;
        }

        public Builder requireEntropyCheck(boolean v) {
            this.requireEntropyCheck = v;
            return this;
        }

        public Builder minEntropyBits(int bits) {
            this.minEntropyBits = bits;
            return this;
        }

        public Builder addBlacklistRegex(String regex) {
            this.blacklistPatterns.add(Pattern.compile(regex));
            return this;
        }

        public Builder checkBreach(boolean v) {
            this.checkBreach = v;
            return this;
        }

        public PasswordPolicy build() {
            return new PasswordPolicy(this);
        }
    }
}
