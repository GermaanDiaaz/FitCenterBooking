package com.salesianostriana.dam.fitcenterbooking.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data 
@AllArgsConstructor 
@NoArgsConstructor
@Entity
public class Usuario {

	@Id 
	@GeneratedValue
	private long id;
	
	private String nombre;
	private String email;
	private String telefono;
	
	@Id
	@OneToOne
	private Reserva reserva;
}
