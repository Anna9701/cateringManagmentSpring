package com.annawyrwal.configuration;

import com.annawyrwal.service.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	@Autowired
	private MyUserDetailsService userDetailsService;

	@Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
			.antMatchers("/register", "/", "/index").permitAll()
		    .antMatchers("/catering/**").hasAnyAuthority("ADMIN", "USER")
            .anyRequest().authenticated()
            .and().formLogin()
                .loginPage("/login")
                .permitAll()
                .loginProcessingUrl("/app-login")
                .defaultSuccessUrl("/index")
                .failureUrl("/login-error")
            .and().logout().logoutUrl("/logout")
                .logoutSuccessUrl("/index")
            .and().exceptionHandling()
                .accessDeniedPage("/errors/403");
	}

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider
                = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(encoder());
        return authProvider;
    }

    @Bean
    public PasswordEncoder encoder() {
        return bCryptPasswordEncoder;
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
    }
}

/**
 https://www.concretepage.com/spring-boot/spring-boot-mvc-security-custom-login-and-logout-thymeleaf-csrf-mysql-database-jpa-hibernate-example
 *
 * 					.antMatchers("/admin/**").hasAnyRole("ADMIN")
 .antMatchers("/user/**").hasAnyRole("USER")
 .anyRequest().authenticated()
 * */