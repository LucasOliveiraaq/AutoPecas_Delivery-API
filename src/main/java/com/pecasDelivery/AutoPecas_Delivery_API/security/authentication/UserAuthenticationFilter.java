package com.pecasDelivery.AutoPecas_Delivery_API.security.authentication;

import java.io.IOException;
import java.util.Arrays;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.pecasDelivery.AutoPecas_Delivery_API.model.Usuario;
import com.pecasDelivery.AutoPecas_Delivery_API.repositories.UsuarioRepository;
import com.pecasDelivery.AutoPecas_Delivery_API.security.config.SecurityConfiguration;
import com.pecasDelivery.AutoPecas_Delivery_API.security.details.UserDetailsImpl;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class UserAuthenticationFilter extends OncePerRequestFilter{
	
	@Autowired
	private JwtTokenService jwtTokenService;
	
	@Autowired
	private UsuarioRepository usuarioRepository;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
		if(verificaEndPointIsNotPublic(request)) {
			String token = recuperarToken(request);
			if(token != null) {
				String subject = jwtTokenService.getSubjectFromToken(token);
				Optional<Usuario> optionalUser = usuarioRepository.findByEmail(subject);
				if(optionalUser.isPresent()) {
					Usuario user  = optionalUser.get();
					UserDetailsImpl userDetails = new UserDetailsImpl(user);
                    UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                    System.out.println("Authentication successful for user: " + user.getEmail());
                    
				} else {
					System.out.println("User not found for subject: " + subject); 
				}
			} else {
				System.out.println("Token is missing in the request.");
			}
		}
		filterChain.doFilter(request, response);
	}
	
	private boolean verificaEndPointIsNotPublic(HttpServletRequest request)  {
		String requestURI = request.getRequestURI();
		return !Arrays.asList(SecurityConfiguration.ENDPOINTS_WITH_AUTHENTICATION_NOT_REQUIRED).contains(requestURI);
	}
	
	private String recuperarToken(HttpServletRequest request) {
		String authorizationHeader = request.getHeader("Authorization");
		if(authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
			return authorizationHeader.replace("Bearer ", "").trim();
		}
		return null;
	}

}
