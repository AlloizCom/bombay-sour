package com.bombaysour.bombaysour.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@ComponentScan("com.bombaysour.bombaysour")
@EnableWebSecurity
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable().authorizeRequests()
//                .formLogin()
//                .loginPage("/login")
//                .usernameParameter("name")
//                .passwordParameter("password")
//                .defaultSuccessUrl("/admin", true)
//                .failureUrl("/")
//                .permitAll()
//                .and()
//                .logout()
//                .logoutUrl("/logout")
//                .logoutSuccessUrl("/").permitAll()
//                .and()
//                .authorizeRequests()
//                .antMatchers("/admin").access("hasRole('ADMIN')")
//                .antMatchers("/admin/**").access("hasRole('ADMIN')")
//                .antMatchers("/login").permitAll()
//                .antMatchers("/").permitAll()
                .anyRequest().permitAll()
        .and().headers().cacheControl().disable().and().cors();
    }

    @Autowired
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("admin").password("admin").roles("ADMIN");
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("resources/**");
    }
}