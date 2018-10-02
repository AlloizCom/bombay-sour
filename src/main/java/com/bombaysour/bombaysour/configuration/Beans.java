package com.bombaysour.bombaysour.configuration;

import com.bombaysour.bombaysour.service.utils.FileBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@EnableJpaRepositories(basePackages = "com.bombaysour.bombaysour.repository")
@Component
public class Beans {

    @Bean
    FileBuilder fileBuilder() {
        return new FileBuilder();
    }

}
