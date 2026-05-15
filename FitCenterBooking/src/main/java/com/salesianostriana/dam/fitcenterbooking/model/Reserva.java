package com.salesianostriana.dam.fitcenterbooking.model;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data 
@AllArgsConstructor 
@NoArgsConstructor
@Entity
public class Reserva {

	@Id 
	@GeneratedValue
	private long codigo;
	
	private LocalDateTime fecha;
	private double precioTotal;
	
	@Id
	@OneToMany
	private Actividad actividad;
	
	@Id
	@OneToOne
	private Usuario usuario;
}
