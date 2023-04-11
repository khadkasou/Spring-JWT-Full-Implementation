package com.souraj.demo3.validations;

import com.souraj.demo3.model.User;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Service
public class UserValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return User.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {

        User user = new User();

        if(user.getPassword().length()<=8){
            errors.rejectValue("password","length","password must be at least 8 characters");
        }
    }
}
