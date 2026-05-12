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
				new Reserva(1L, LocalDateTime.of(2024, 5, 15, 10, 30), 110),
				new Reserva(2L, LocalDateTime.of(2024, 5, 15, 10, 30), 57.50),
				new Reserva(3L, LocalDateTime.of(2024, 5, 15, 10, 30), 32),
				new Reserva(4L, LocalDateTime.of(2024, 5, 15, 10, 30), 55)
				);		
	}
}
