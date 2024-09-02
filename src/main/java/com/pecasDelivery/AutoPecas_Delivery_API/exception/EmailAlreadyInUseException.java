package com.pecasDelivery.AutoPecas_Delivery_API.exception;

public class EmailAlreadyInUseException extends RuntimeException {

	public EmailAlreadyInUseException(String message) {
		super(message);
	}
}
