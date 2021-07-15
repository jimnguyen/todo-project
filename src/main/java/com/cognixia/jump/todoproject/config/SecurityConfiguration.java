package com.cognixia.jump.todoproject.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final UserDetailsService userDetailsService;

    @Autowired
    public SecurityConfiguration(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
    	
    	/*
    	 * Everyone has the permission to Get and Post "/api/user"
    	 * 
    	 * Only Roles USER and ADMIN have access to update their user profile and CRUD operations on TO-DOs
    	 */
    	
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers(HttpMethod.GET, "/api/user")
                    .permitAll()
                .antMatchers(HttpMethod.POST,"/api/user")
                    .permitAll()
                .antMatchers(HttpMethod.PUT,"/api/user/**")
                    .hasAnyRole("USER", "ADMIN")
                .antMatchers(HttpMethod.DELETE, "/api/user/delete/**")
                    .hasRole("ADMIN")
                .antMatchers(HttpMethod.GET,"/api/todo")
                    .hasAnyRole("USER", "ADMIN")
                .antMatchers(HttpMethod.POST,"/api/todo")
                    .hasAnyRole("USER", "ADMIN")
                .antMatchers(HttpMethod.PUT,"/api/todo/update/**")
                    .hasAnyRole("USER", "ADMIN")
                .antMatchers(HttpMethod.DELETE,"/api/todo/delete/**")
                    .hasAnyRole("USER", "ADMIN")
                .and().httpBasic();
    }
}
