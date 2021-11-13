package com.vedverma.spring.security.web;

import com.vedverma.spring.security.web.dto.ForgotPasswordForm;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
@RequestMapping("/forgot-password")
public class ForgotPasswordController {

    @ModelAttribute("forgotPasswordForm")
    public ForgotPasswordForm forgotPasswordForm() {
        return new ForgotPasswordForm();
    }

    @GetMapping
    public String showForgotPassword(Model model) {
        return "forgot-password";
    }

    @PostMapping
    public String handleForgotPassword(@ModelAttribute("forgotPasswordForm") @Valid ForgotPasswordForm form,
                                       BindingResult result, Model model, HttpServletRequest request){

        if (result.hasErrors()){
            return "forgot-password";
        }

        return "redirect:/forgot-password?success";
    }

}
