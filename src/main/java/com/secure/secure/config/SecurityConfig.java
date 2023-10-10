package com.secure.secure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .authorizeHttpRequests(auth->
                        auth.requestMatchers("/user/**").hasRole("USER")
                                .requestMatchers("/admin/**").hasRole("ADMIN")
                                .requestMatchers("/").permitAll()
                                .anyRequest().authenticated()
        ).formLogin(Customizer.withDefaults());
          return httpSecurity.build();
    }


    @Bean
    PasswordEncoder getPasswordEncoder(){

        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationProvider getAuthenticationProvider(){

        DaoAuthenticationProvider authProvider =new DaoAuthenticationProvider();

        authProvider.setUserDetailsService(getUserDetails());
        authProvider.setPasswordEncoder(new BCryptPasswordEncoder());

        return authProvider;
    }

    @Bean
    public UserDetailsService getUserDetails(){

       UserDetails user1=
            User.builder()
            .username("admin")
            .password(getPasswordEncoder().encode("admin"))
            .roles("ADMIN")
            .build();

        UserDetails user2=
            User.builder()
            .username("customer")
            .password(getPasswordEncoder().encode("customer"))
            .roles("USER")
            .build();

        return new  InMemoryUserDetailsManager(user1, user2);
    }
}
