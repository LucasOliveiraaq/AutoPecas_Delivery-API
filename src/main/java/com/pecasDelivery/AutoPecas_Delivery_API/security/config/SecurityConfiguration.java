package com.pecasDelivery.AutoPecas_Delivery_API.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import com.pecasDelivery.AutoPecas_Delivery_API.security.authentication.UsuarioAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
	
	@Autowired
    private UsuarioAuthenticationFilter usuarioAuthenticationFilter;

	public static final String[] ENDPOINTS_WITH_AUTHENTICATION_NOT_REQUIRED = {
            "/users/login",
            "/users"
    };
}
