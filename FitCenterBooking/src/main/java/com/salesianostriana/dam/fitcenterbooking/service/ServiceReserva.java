package com.salesianostriana.dam.fitcenterbooking.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.salesianostriana.dam.fitcenterbooking.exception.ReservaNotFoundException;
import com.salesianostriana.dam.fitcenterbooking.model.Reserva;
import com.salesianostriana.dam.fitcenterbooking.repository.RepositoryReserva;
import com.salesianostriana.dam.fitcenterbooking.service.base.BaseServiceImpl;

@Service
public class ServiceReserva extends BaseServiceImpl<Reserva, Long, RepositoryReserva>{

	public Reserva buscarPorID(Long codigo) {
		return repository.findById(codigo)
        .orElseThrow(() -> new ReservaNotFoundException(codigo));		
	}
	
	
	public List<Reserva> listarReservasUsuario(Long codigo) {
		return repository.listarPorID(codigo);
	}
	
	
	public boolean tieneReservaAsignada(Long idActividad) {
		
	    return repository.existeEstaActividadEnAlgunaReserva(idActividad);
	}
	
	
	public boolean tieneReservasAsignadas(Long idUsuario) {
	    return repository.existsByUsuarioId(idUsuario);
	}
	
	
	public int obtenerPlazasOcupadas(Long actividadId) {
	    return repository.contarPlazasReservadas(actividadId);
	}
	
	public List<Reserva> ordenarPorFecha(){
		return repository.findReservasOrdenadasPorFecha();
	}
}
