package com.park.spring.security.oauth.client.securityoauthclient.config;

import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
//@EnableWebSecurity
@EnableOAuth2Sso
public class OauthConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.antMatcher("/**")
                .authorizeRequests()
                .antMatchers("/", "/login**").permitAll()
                .antMatchers("/rest/home").authenticated()
                .antMatchers("/rest/user").hasRole("USER")
                .antMatchers("/rest/admin").hasRole("ADMIN")
                .anyRequest()
                .authenticated();
    }
}
