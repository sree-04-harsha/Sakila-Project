package com.cg.sakila.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.cg.sakila.security.JwtAuthenticationEntryPoint;
import com.cg.sakila.security.JwtAuthenticationFilter;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration {

	private final JwtAuthenticationEntryPoint point;

	private final JwtAuthenticationFilter filter;

	private final AuthenticationProvider authProvider;

	@Autowired
	public SecurityConfiguration(JwtAuthenticationEntryPoint point, JwtAuthenticationFilter filter,
			AuthenticationProvider authProvider) {
		super();
		this.point = point;
		this.filter = filter;
		this.authProvider = authProvider;
	}

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

		http.cors().and().csrf(csrf -> csrf.disable())
				.authorizeHttpRequests(authorize -> authorize.requestMatchers("/swagger-ui/**").permitAll()
						.requestMatchers("/v3/api-docs/**").permitAll() // swagger configuration
						.requestMatchers("/api/v1/**").permitAll().anyRequest().authenticated())
				.authenticationProvider(authProvider)
				.sessionManagement(mng -> mng.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

		http.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);

		return http.build();
	}

}
