package com.salesianostriana.dam.fitcenterbooking.model;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data  
@AllArgsConstructor 
@NoArgsConstructor
@Builder
@Entity
public class Actividad {

	@Id 
	@GeneratedValue
	private Long id;
	
	private String nombre;
	
	@Lob
	private String descripcion;
	private Integer capacidad;
	private Double precio;
    private String imagenUrl;

	
	@OneToMany(mappedBy = "actividad")
	private List<ActividadReserva> reservas;
}
