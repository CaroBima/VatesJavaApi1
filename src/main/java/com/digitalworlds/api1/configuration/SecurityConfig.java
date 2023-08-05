package com.digitalworlds.api1.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http
                .csrf().disable()
                .cors()
                .and()
                .authorizeRequests()
                .antMatchers(HttpMethod.GET, "/clima", "/clima/{id}").authenticated()
                .antMatchers(HttpMethod.GET, "/clima/data").hasRole("ADMIN")
                .antMatchers(HttpMethod.POST, "/clima/savedata","/clima/borrardata", "/clima/putdata").hasRole("ADMIN")
                .antMatchers(HttpMethod.DELETE, "/clima/borrardata", "/clima/putdata").hasRole("ADMIN")
                .antMatchers(HttpMethod.PUT,  "/clima/putdata").hasRole("ADMIN")
                //.antMatchers(HttpMethod.GET, "/realtime/history").permitAll()
                .anyRequest()
                //.permitAll()
                .authenticated()
                .and()
                .httpBasic();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    @Bean
    @Override
    protected UserDetailsService userDetailsService(){

        UserDetails usuario = User.builder()
                .username("usuario")
                .password(passwordEncoder().encode("12345"))
                .roles("USER")
                .build();

        UserDetails admin = User.builder()
                .username("admin")
                .password(passwordEncoder().encode("12345"))
                .roles("ADMIN")
                .build();

        InMemoryUserDetailsManager userDetailsManager = new InMemoryUserDetailsManager(usuario, admin);

        return userDetailsManager;
    }
}
