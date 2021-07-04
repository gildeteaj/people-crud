package com.stefanini.selecao.peoplews.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class AuthorizationConfiguration extends WebSecurityConfigurerAdapter {
	@Value("${app.basic.user}")
	private String user;

	@Value("${app.basic.secretKey}")
	private String secretKey;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		   
		http.authorizeRequests() 
			    .antMatchers("/*").permitAll()
			    .antMatchers("/swagger-ui/**").permitAll()
				.and().csrf().disable().authorizeRequests()
				.antMatchers("/**").hasRole("USER").anyRequest().authenticated().and().httpBasic();
	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
		auth.inMemoryAuthentication().withUser(user).password(encoder.encode(secretKey)).roles("USER");
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/**.html", "/**.html/*", "/v2/api-docs", "/webjars/**", "/configuration/**",
				"/swagger-resources/**");
	}
	
	

}
