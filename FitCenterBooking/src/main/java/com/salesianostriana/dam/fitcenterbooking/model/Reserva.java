package com.salesianostriana.dam.fitcenterbooking.model;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
public class Reserva {

	@Id 
	@GeneratedValue
	private Long codigo;
	
	@DateTimeFormat(pattern = "dd-MM-yyyy'T'HH:mm")
	private LocalDateTime fecha;
	private double precioTotal;
	
	@ManyToOne
	@JoinColumn(name = "usuario_id")
	private Usuario usuario;

	@OneToMany(mappedBy = "reserva", cascade = CascadeType.ALL)
	private List<ActividadReserva> actividades;
	
	
	
	public void addLinea(ActividadReserva linea) {
	    if (this.actividades == null) {
	        this.actividades = new java.util.ArrayList<>();
	    }
	    this.actividades.add(linea);
	    linea.setReserva(this);
	}
	
	
	public double calcularPrecioTotal() {
	    if (this.actividades == null || this.actividades.isEmpty()) {
	        return 0.00;
	        
	    }else {
	    	double suma = 0.0;
	        
	        for (ActividadReserva linea : this.actividades) {
	            suma += linea.getActividad().getPrecio();
	        }
	        
	        return suma;
	    }
	    
	    
	}
}


