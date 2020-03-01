package com.producthop.softuni.config;

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
                .antMatchers("/", "/user/login", "/user/register").anonymous()
                .antMatchers("/user/profile", "/product/home", "/logout", "/product/api/category/*").hasAnyAuthority("USER", "MODERATOR", "ADMIN", "ROOT")
                .antMatchers("/category/all", "/category/create", "/category/edit/*", "/category/delete/*",
                                        "/product/create", "/product/all", "/product/details/*", "/product/edit/*", "/product/delete/*").hasAnyAuthority("MODERATOR", "ADMIN", "ROOT")
                .antMatchers("/user/all", "/user/*/role/*").hasAnyAuthority("ADMIN", "ROOT")
                .antMatchers("/js/**", "/css/**").permitAll()
                .anyRequest().authenticated()
            .and()
                .formLogin()
                .loginPage("/user/login")
                .usernameParameter("username")
                .passwordParameter("password")
                .defaultSuccessUrl("/product/home")
            .and()
                .logout()
                .logoutSuccessUrl("/user/login");
    }

    private CsrfTokenRepository csrfTokenRepository() {
        HttpSessionCsrfTokenRepository repository =
                new HttpSessionCsrfTokenRepository();
        repository.setSessionAttributeName("_csrf");
        return repository;
    }
}
