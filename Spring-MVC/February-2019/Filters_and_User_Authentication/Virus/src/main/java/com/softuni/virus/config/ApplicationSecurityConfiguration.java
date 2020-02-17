package com.softuni.virus.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class ApplicationSecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf()
                .csrfTokenRepository(this.csrfTokenRepository())
            .and()
                .authorizeRequests()
                .antMatchers("/user/login", "/user/register").anonymous()
                .antMatchers("/virus/all", "/logout").hasAnyAuthority("USER", "MODERATOR", "ADMIN", "ROOT")
                .antMatchers("/virus/add", "/virus/edit/*", "/virus/delete/*").hasAnyAuthority("MODERATOR", "ADMIN", "ROOT")
                .antMatchers("/user/all", "/user/authorize/**").hasAnyAuthority("ADMIN", "ROOT")
                .antMatchers("/", "/js/**", "/css/**").permitAll()
                .anyRequest().authenticated()
            .and()
                .formLogin()
                .loginPage("/user/login")
                .usernameParameter("username")
                .passwordParameter("password")
                .defaultSuccessUrl("/")
            .and()
                .logout()
                .logoutSuccessUrl("/user/login")
            .and()
                .exceptionHandling()
                .accessDeniedPage("/unauthorized");
    }

    private CsrfTokenRepository csrfTokenRepository() {
        HttpSessionCsrfTokenRepository repository =
                new HttpSessionCsrfTokenRepository();
        repository.setSessionAttributeName("_csrf");
        return repository;
    }
}
