package com.vedverma.spring.security.web.dto;

import com.vedverma.spring.security.recaptcha.ValidReCaptcha;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

public class ForgotPasswordForm {

    @Email
    @NotEmpty
    private String email;

    @NotEmpty
    @ValidReCaptcha
    private String reCaptchaResponse;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getReCaptchaResponse() {
        return reCaptchaResponse;
    }

    public void setReCaptchaResponse(String reCaptchaResponse) {
        this.reCaptchaResponse = reCaptchaResponse;
    }
}
