package com.salesianostriana.dam.fitcenterbooking.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
public class Actividad {

	private String nombre;
	private int capacidad;
	private double precio;
}
