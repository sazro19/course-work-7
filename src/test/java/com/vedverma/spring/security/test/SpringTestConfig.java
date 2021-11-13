package com.vedverma.spring.security.test;

import com.vedverma.spring.security.recaptcha.ReCaptchaService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static org.mockito.Mockito.mock;

@Configuration
public class SpringTestConfig {

    @Bean
    ReCaptchaService reCaptchaService() {
        return mock(ReCaptchaService.class);
    }

}
