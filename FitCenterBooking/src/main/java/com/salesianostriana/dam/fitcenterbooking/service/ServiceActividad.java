package com.salesianostriana.dam.fitcenterbooking.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

import com.salesianostriana.dam.fitcenterbooking.model.Actividad;

@Service
public class ServiceActividad {

	public List<Actividad> getLista() {
		return Arrays.asList(
				new Actividad("Fútbol", 21, 10),
				new Actividad("Basket", 14, 11.50),
				new Actividad("Tenis", 21, 15.50),
				new Actividad("Voley", 21, 11)
				);		
	}
}
