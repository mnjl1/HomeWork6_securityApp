package goit.springhomework.controller;

import goit.springhomework.model.User;
import goit.springhomework.service.SecurityService;
import goit.springhomework.service.UserService;
import goit.springhomework.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private UserValidator userValidator;

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String registration(Model model){
        model.addAttribute("userForm", new User());
        return "registration";
    }

    public String registration(@ModelAttribute("userForm") User userForm,
                               BindingResult bindingResult, Model model){

        userValidator.validate(userForm, bindingResult);
        if (bindingResult.hasErrors()){
            return "registration";
        }

        userService.save(userForm);
        securityService.autoLogging(userForm.getUserName(), userForm.getPasswordConfirm());

        return "redirect:/welcome";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public  String login (Model model, String error, String logout){
        if ((error!=null)){
            model.addAttribute("error", "Invalid password");
        }
        if(logout!=null){
            model.addAttribute("message", "Success logged out.");
        }
        return "login";
    }

    @RequestMapping(value = {"/", "/welcome"}, method = RequestMethod.GET)
    public String welcome(Model model){

        return "welcome";
    }
}
