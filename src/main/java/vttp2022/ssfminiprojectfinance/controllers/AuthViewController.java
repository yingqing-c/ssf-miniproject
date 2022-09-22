package vttp2022.ssfminiprojectfinance.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import vttp2022.ssfminiprojectfinance.models.User;
import vttp2022.ssfminiprojectfinance.services.UserRedis;

@Controller
@RequestMapping
public class AuthViewController {

    @Autowired
    private UserRedis service;

    @GetMapping("/signup")
    public String get(Model model) {
        model.addAttribute("user", new User());
        return "signup";
    }
    
    @PostMapping("/signup")
    public ModelAndView get(@ModelAttribute("user") User user, BindingResult result) {
        ModelAndView mav = new ModelAndView();

        User u = service.findByUsername(user.getUsername());
        if (u != null) {
            // model.addAttribute("error", "This email already exists");
            mav.addObject("error", "This email already exists");
            mav.setViewName("signup");
            return mav;
        }
        int x = service.save(user);
        if (x != 0) {
            mav.addObject("error", "Sign up failed for unknown reasons, please try again.");
            // model.addAttribute("error", "Internal Server Error - Failed to sign up");
            mav.setViewName("signup");
            return mav;
        }

        // model.addAttribute("success", "Successfully signed up");
        // model.addAttribute("name", user.getUsername());
        mav.addObject("name", user.getUsername());
        mav.setViewName("redirect:/index");
        return mav;
    }

    @GetMapping(path = {"/", "/login"})
    public String login(Model model) {
        model.addAttribute("user", new User());
        return "login";
    }

    @PostMapping("/login")
    public ModelAndView login (@ModelAttribute("user") User user, BindingResult result) {
        ModelAndView mav = new ModelAndView();
        if (result.hasErrors()) {
            mav.addObject("error", result.getAllErrors().get(0).toString());
            mav.setViewName("login");
            return mav;
        }
        User u = service.findByUsername(user.getUsername());
        if (u == null) {
            mav.addObject("error", "Invalid username");
            mav.setViewName("login");
            return mav;
        }
        // model.addAttribute("name", u.getUsername());
        mav.addObject("name", u.getUsername());
        mav.setViewName("redirect:/index");
        return mav;
    }

    @GetMapping("/logout")
    public ModelAndView logout() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("redirect:/login");
        return mav;
    }

}
