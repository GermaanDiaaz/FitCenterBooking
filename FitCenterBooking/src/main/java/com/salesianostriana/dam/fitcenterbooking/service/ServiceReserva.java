package com.salesianostriana.dam.fitcenterbooking.service;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

import com.salesianostriana.dam.fitcenterbooking.model.Reserva;

@Service
public class ServiceReserva {

	public List<Reserva> getLista() {
		return Arrays.asList(
				new Reserva("001@A", LocalDateTime.of(2024, 5, 15, 10, 30), 110),
				new Reserva("Basket", LocalDateTime.of(2024, 5, 15, 10, 30), 57.50),
				new Reserva("Tenis", LocalDateTime.of(2024, 5, 15, 10, 30), 32),
				new Reserva("Voley", LocalDateTime.of(2024, 5, 15, 10, 30), 55)
				);		
	}
}
