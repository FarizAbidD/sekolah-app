package com.sekolah.app.controller;

import com.sekolah.app.dto.UserDto;
import com.sekolah.app.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class WebController {

    private UserService userService;

    @Autowired
    public WebController(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    private HttpSession httpSession;

    @GetMapping("/")
    public String redirectToLogin() {
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @PostMapping("/login")
    public ModelAndView login(@RequestParam String email, @RequestParam String password) {
        UserDto user = userService.authenticate(email, password);
        if (user != null) {
            httpSession.setAttribute("user", user); // Store user in session
            ModelAndView mav = new ModelAndView("home");
            mav.addObject("user", user);
            System.out.println("User: " + user.getName() + ", Email: " + user.getEmail());
            return mav;
        } else {
            ModelAndView mav = new ModelAndView("login");
            mav.addObject("error", "Invalid email or password");
            return mav;
        }
    }

    @GetMapping("/home")
    public String home(Model model) {
        UserDto user = (UserDto) httpSession.getAttribute("user");
        if (user != null) {
            model.addAttribute("user", user);
            return "home";
        }
        return "redirect:/login";
    }

    @GetMapping("/subject")
    public String subjects(Model model) {
        UserDto user = (UserDto) httpSession.getAttribute("user");
        if (user != null) {
            model.addAttribute("user", user);
            return "subject";
        }
        return "redirect:/login";
    }

    @GetMapping("/students")
    public String students(Model model) {
        UserDto user = (UserDto) httpSession.getAttribute("user");
        if (user != null) {
            model.addAttribute("user", user);
            return "students";
        }
        return "redirect:/login";
    }

    @GetMapping("/profile")
    public String profile(Model model) {
        UserDto user = (UserDto) httpSession.getAttribute("user");
        if (user != null) {
            model.addAttribute("user", user);
            return "profile";
        }
        return "redirect:/login";
    }

    @GetMapping("/register")
    public String register() {
        return "register";
    }
}