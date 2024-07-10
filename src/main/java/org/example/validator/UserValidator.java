package org.example.validator;

import org.example.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserValidator {
    public boolean isNotValid(User user) {
        return user.getEmail() == null || user.getEmail().isEmpty()
                || user.getLogin() == null || user.getLogin().isEmpty()
                || user.getEmail().length() > 25 || user.getLogin().length() > 20;
    }
}
