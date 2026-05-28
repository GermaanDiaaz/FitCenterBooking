package com.salesianostriana.dam.fitcenterbooking.model;

import java.time.LocalDateTime;

import com.salesianostriana.dam.fitcenterbooking.security.EstadosReserva;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class ActividadReserva {

	@Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name = "reserva_id")
    private Reserva reserva;

    @ManyToOne
    @JoinColumn(name = "actividad_id")
    private Actividad actividad;

    @Enumerated(EnumType.STRING)
    private EstadosReserva estado;
    
    private String observaciones;
    
    
    public EstadosReserva getEstado() {
        if (this.reserva == null || this.reserva.getFecha() == null) {
            return EstadosReserva.RESERVADA;
        }
        
        if (this.reserva.getFecha().isBefore(LocalDateTime.now())) {
            return EstadosReserva.REALIZADA;
        } else {
            return EstadosReserva.RESERVADA;
        }
    }
}
