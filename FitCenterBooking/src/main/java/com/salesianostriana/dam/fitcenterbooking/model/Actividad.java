package com.salesianostriana.dam.fitcenterbooking.model;

import java.util.List;

import org.hibernate.validator.constraints.URL;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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
	
	@NotBlank(message = "El nombre de la actividad es obligatorio.")
    @Size(min = 1, max = 25, message = "El nombre debe tener entre 1 y 25 caracteres.")
    private String nombre;	
	
	
	@Lob
	@NotBlank(message = "Añade una pequeña descripción de la actividad.")
	private String descripcion;
	
	
	
	@NotNull(message = "El aforo es obligatorio.")
    @Min(value = 1, message = "El aforo mínimo debe ser de 1 persona.")
    private Integer capacidad;
	
	
	@NotNull(message = "El precio es obligatorio.")
    @DecimalMin(value = "0", message = "El precio no puede ser negativo.")
    private Double precio;
	
	@URL(message = "Debe ser una URL válida (ej: http://...)")
	private String imagenUrl;

	
	@OneToMany(mappedBy = "actividad")
	private List<ActividadReserva> reservas;
}
