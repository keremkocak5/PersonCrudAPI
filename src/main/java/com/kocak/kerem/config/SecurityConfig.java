package com.kocak.kerem.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .anyRequest()
                .authenticated()
                .and().csrf().disable()
                .httpBasic();
    }

    /**
     * Enable this block and disable the one above to access H2 console on your local environment.
     *
     * @Override protected void configure(HttpSecurity httpSecurity) throws Exception {
     * httpSecurity.authorizeRequests().antMatchers("/").permitAll().and()
     * .authorizeRequests().antMatchers("/console/**").permitAll();
     * httpSecurity.csrf().disable();
     * httpSecurity.headers().frameOptions().disable();
     * }
     */

    @Override
    public void configure(WebSecurity web) {
        web.ignoring()
                .antMatchers(HttpMethod.OPTIONS);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("user1").password("{noop}1234").roles("role1")
                .and()
                .withUser("user2").password("{noop}1234").roles("role1");
    }

}