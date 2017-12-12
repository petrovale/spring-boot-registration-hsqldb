package com.company.webapp.web;

import org.slf4j.Logger;
import com.company.webapp.AuthorizedUser;
import com.company.webapp.service.SecurityService;
import com.company.webapp.model.User;
import com.company.webapp.service.UserService;
import com.company.webapp.util.UserUtil;
import com.company.webapp.validator.UserValidator;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.concurrent.atomic.AtomicInteger;

@Controller
@RequestMapping(value = "/junior-3")
public class UserController {
    private final Logger log = LoggerFactory.getLogger(UserController.class);
    @Autowired
    private UserService userService;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private UserValidator userValidator;

    @Autowired
    private HttpServletRequest request;

    private AtomicInteger countViewsGreetings = new AtomicInteger(0);

    @RequestMapping(value = "/sign-up", method = RequestMethod.GET)
    public String registration(Model model) {
        if (AuthorizedUser.safeGet() != null) {
            return "redirect:/junior-3/greetings";
        }

        model.addAttribute("userForm", new User());
        return "sign-up";
    }

    @RequestMapping(value = "/sign-up", method = RequestMethod.POST)
    public String registration(@ModelAttribute("userForm") User userForm, BindingResult bindingResult, Model model) {
        log.info("Start sign-up");
        UserUtil.prepareToSave(userForm, request);
        userValidator.validate(userForm, bindingResult);

        if (bindingResult.hasErrors()) {
            log.error("Processing failed {}", bindingResult.getModel());
            return "sign-up";
        }
        userService.save(userForm);
        log.info("create {}", userForm);

        securityService.autologin(userForm.getUsername(), userForm.getPasswordConfirm());

        return "redirect:/junior-3/greetings";
    }

    @RequestMapping(value = "/sign-in", method = RequestMethod.GET)
    public String login(Model model, String error, String logout) {
        log.info("sign-in");
        if (AuthorizedUser.safeGet() != null) {
            log.info("redirect:/junior-3/greetings");
            return "redirect:/junior-3/greetings";
        }

        if (error != null) {
            log.error("Username and password are not suitable.");
            model.addAttribute("error", "Имя пользователя и пароль не подходят.");
        }

        if (logout != null) {
            log.info("You have successfully quit the application.");
            model.addAttribute("message", "Вы успешно вышли из приложения.");
        }

        return "sign-in";
    }

    @RequestMapping(value = {"/greetings"}, method = RequestMethod.GET)
    public String greetings(Model model) {
        log.info("greetings");
        model.addAttribute("lastLoginDate", AuthorizedUser.getLastLoginDate());
        model.addAttribute("clientIP", AuthorizedUser.getLastClientIpAddress());
        model.addAttribute("countViewsGreetings", countViewsGreetings.incrementAndGet());
        return "greetings";
    }

    @RequestMapping(value = {"/"}, method = RequestMethod.GET)
    public String root(Model model) {
        log.info("redirect to greetings");
        return "redirect:/junior-3/greetings";
    }
}
