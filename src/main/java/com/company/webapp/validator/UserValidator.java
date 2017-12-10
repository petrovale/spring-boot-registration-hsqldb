package com.company.webapp.validator;

import com.company.webapp.model.User;
import com.company.webapp.service.UserService;
import org.passay.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.util.Arrays;

@Component
public class UserValidator implements Validator {
    @Autowired
    private UserService userService;

    @Override
    public boolean supports(Class<?> aClass) {
        return User.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        User user = (User) o;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "NotEmpty");
        if (user.getUsername().length() < 5 || !isOnlyLettersDigitsPoints(user.getUsername())) {
            errors.rejectValue("username","Invalid.userForm.username");
        }

        if (userService.findByUsername(user.getUsername()) != null) {
            errors.rejectValue("username", "Duplicate.userForm.username");
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "NotEmpty");
        if (!isValidPassword(user.getPassword())) {
            errors.rejectValue("password", "Complexity.userForm.password");
        }

        if (!user.getPasswordConfirm().equals(user.getPassword())) {
            errors.rejectValue("passwordConfirm", "Diff.userForm.passwordConfirm");
        }
    }

    public boolean isOnlyLettersDigitsPoints(String username){
        boolean letter = false, digit = false, point = false;
        for (int i = 0; i < username.length(); i++) {
            char ch = username.charAt(i);
            if (Character.isLetter(ch)) {
                letter = true;
                continue;
            }
            if (ch == '.') {
                point = true;
                continue;
            }
            if (Character.isDigit(ch)) {
                digit = true;
                continue;
            }
            return false;
        }
        return letter && digit && point;
    }

    private boolean isPasswordComplexity(String password) {
        return password.matches(".*[A-Z].*") &&
                password.matches(".*[0-9].*") &&
                password.matches(".*[a-z].*");
    }

    public boolean isValidPassword(final String password) {
        final PasswordValidator validator = new PasswordValidator(Arrays.asList(
                new LengthRule(8, 30),
                new UppercaseCharacterRule(1),
                new DigitCharacterRule(1)));
        final RuleResult result = validator.validate(new PasswordData(password));
        return result.isValid();
    }
}
