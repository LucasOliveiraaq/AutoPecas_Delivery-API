package com.pecasDelivery.AutoPecas_Delivery_API.security.authentication;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.pecasDelivery.AutoPecas_Delivery_API.security.details.UserDetailsImpl;

@Service
public class JwtTokenService {

	@Value("${api.security.token.secret}")
	private String secretKey; //variavel de ambiente
	
	private static final String ISSUER = "AutoPecas_Delivery_API"; //Emissor do token
	
	//gera o token
	public String generateToken(UserDetailsImpl user) {
		try {
			Algorithm algorithm = Algorithm.HMAC256(secretKey);
			return JWT.create()
					.withIssuer(ISSUER)
					.withIssuedAt(creationDate())
					.withExpiresAt(expirationDate())
					.withSubject(user.getUsername())
					.sign(algorithm);
		} catch (JWTCreationException e) {
			throw new JWTCreationException("Erro ao gerar token.", e);
		}
	}
	
	public String getSubjectFromToken(String token) {
		try {
			Algorithm algorithm = Algorithm.HMAC256(secretKey);
			return JWT.require(algorithm)
						.withIssuer(ISSUER)
						.build()
						.verify(token)
						.getSubject();
		} catch (JWTVerificationException  e) {
			throw new JWTVerificationException("Token inválido ou expirado.");
		}
	}
	
	private Instant creationDate() {
		return ZonedDateTime.now(ZoneId.of("America/Sao_Paulo")).toInstant();
	}
	
	private Instant expirationDate() {
		//token vai expira em 4 horas.
		return ZonedDateTime.now(ZoneId.of("America/Sao_Paulo")).plusHours(4).toInstant();
	}
}
