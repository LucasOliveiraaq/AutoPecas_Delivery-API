package com.pecasDelivery.AutoPecas_Delivery_API.dto;

import java.util.List;

public record RegisterDTO( String login, String senha, String email, List<String> roles) { }
