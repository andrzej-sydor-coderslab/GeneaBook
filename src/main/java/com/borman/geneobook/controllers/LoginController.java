package com.borman.geneobook.controllers;

import com.borman.geneobook.entity.LoggedUser;
import com.borman.geneobook.entity.pojo.LoginUser;
import com.borman.geneobook.repository.EmailRepository;
import com.borman.geneobook.repository.RandomDataRepositories;
import org.springframework.boot.web.servlet.server.Session;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/login")
@SessionAttributes({"email"})
public class LoginController {

    private final RandomDataRepositories randomDataRepositories;
    private final EmailRepository emailRepository;

    public LoginController(RandomDataRepositories randomDataRepositories, EmailRepository emailRepository) {
        this.randomDataRepositories = randomDataRepositories;
        this.emailRepository = emailRepository;
    }

    @GetMapping
    public String loginForm(Model model) { //,
//                            @CookieValue(name = "Email", value = "Value", required = false) Session.Cookie cookie) {
//        if (rCookie != null) {
//        }
//        model.addAttribute("loginUser", new LoginUser());
        model.addAttribute("email");
        LoginUser loginUser = new LoginUser();

        model.addAttribute("loginUser", loginUser);

        return "login/user-logging";
    }

    @PostMapping
    public String loginSubmit() {
        return "geneo";
    }

    @GetMapping("/forgot")
    public String forgotPassSend(Model model, LoggedUser loggedUser) {

        model.addAttribute("sendForgotPass", true);

        String token = randomDataRepositories.getRandomPass();

//        LoggedUser loggedUser = (LoggedUser) model.getAttribute("loginUser");
        System.out.println(model.getAttribute(loggedUser.getEmail()));

//        emailRepository.SendEmail(
//                loginUser.getEmail(),
//                "New password",
//                "Password: " + token);

        return "registration/login-sendEmail";
    }

    @GetMapping("/forgot/resend")
    public String resendPass(Model model, LoggedUser loggedUser) {
        forgotPassSend(model, loggedUser);
        return "registration/login-sendEmail";
    }

    @GetMapping("/forgot/{token}")
    public String forgotPassForm(Model model, @PathVariable String token) {

        if (token == null) {
            return "redirect:/login";
        } else {
            model.addAttribute("validate", true);
        }

        return "registration/forgot-pass-form";
    }

    @PostMapping("/forgot/{token}")
    public String forgotPassSubmit(Model model) {

        boolean validate = Boolean.parseBoolean((String) model.getAttribute("validate"));

        if (validate) {
            String email = (String) model.getAttribute("email");

            String token = randomDataRepositories.getRandomPass();

        } else {
            model.addAttribute("message", "Email does not exist");
        }

        return "registration/forgot-pass-form";
    }

}
