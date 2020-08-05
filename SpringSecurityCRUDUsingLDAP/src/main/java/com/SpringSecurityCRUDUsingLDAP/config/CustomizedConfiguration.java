package com.SpringSecurityCRUDUsingLDAP.config;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.ldap.DefaultSpringSecurityContextSource;

@EnableWebSecurity
public class CustomizedConfiguration extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.ldapAuthentication().userDnPatterns("uid={0},ou=people").userSearchBase("ou=people")
				.userSearchFilter("uid={0}").groupSearchBase("ou=groups").groupSearchFilter("uniqueMember={0}")
				.contextSource(contextSource()).passwordCompare().passwordEncoder(new BCryptPasswordEncoder())
				.passwordAttribute("userPassword");

	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.csrf().disable().authorizeRequests().antMatchers("/saveDept").hasRole("MANAGERS").antMatchers("/**")
				.hasRole("MANAGERS")

				.anyRequest().fullyAuthenticated().and().formLogin();
	}

	@Bean
	public DefaultSpringSecurityContextSource contextSource() {
		return new DefaultSpringSecurityContextSource(Arrays.asList("ldap://localhost:8389/"),
				"dc=springframework,dc=org");
	}

}
