package com.salesianostriana.dam.fitcenterbooking.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data  
@AllArgsConstructor 
@NoArgsConstructor
@Entity
public class Actividad {

	@Id 
	@GeneratedValue
	private Long id;
	
	
	private String nombre;
	private int capacidad;
	private double precio;
	
	/*@Id
	@ManyToOne
	private Reserva reserva;*/
}
