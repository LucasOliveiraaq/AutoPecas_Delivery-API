package com.pecasDelivery.AutoPecas_Delivery_API.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

import com.pecasDelivery.AutoPecas_Delivery_API.security.authentication.UserAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
	
	@Autowired
    private UserAuthenticationFilter UserAuthenticationFilter;

	public static final String[] ENDPOINTS_WITH_AUTHENTICATION_NOT_REQUIRED = {
            "/users/login",
            "/users"
    };
	
	public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
		return httpSecurity
				.csrf(csrf -> csrf.disable())
				.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) //STATELESS -> Não armazena a sessão, só passa o token
				.authorizeHttpRequests(authorize -> authorize
										.requestMatchers(HttpMethod.POST, "/produto").hasRole("ADMIN")
										.anyRequest().authenticated() //para o restante das requisições precisa só está autenticado.
				)
				.build();
	}
}
