package goit.springhomework.validator;

import goit.springhomework.model.User;
import goit.springhomework.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;

public class UserValidator implements org.springframework.validation.Validator {
    @Autowired
    UserService userService;

    @Override
    public boolean supports(Class<?> aClass) {
        return User.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        User user = (User) o;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "NotEmpty");
            if (user.getUserName().length() < 6 || user.getUserName().length() > 32){
                errors.rejectValue("username", "Size.userForm.username");
            }

            if(userService.findByUserName(user.getUserName())!=null){
                errors.rejectValue("username", "Duplicate.userForm.username");
            }

            ValidationUtils.rejectIfEmptyOrWhitespace(errors,"password", "NotEmpty");
            if (user.getPassword().length() < 6 || user.getPassword().length()>32){
                errors.rejectValue("password", "Size.userForm.password");
            }

            if (!user.getPasswordConfirm().equals(user.getPassword())){
                errors.rejectValue("passwordConfirm", "Diff.userForm.passwordConfirm");
            }
    }
}
