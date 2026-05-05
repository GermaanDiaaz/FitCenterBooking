package com.salesianostriana.dam.fitcenterbooking.model;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
public class Reserva {

	private String codigo;
	private LocalDateTime fecha;
	private double precioTotal;
}
