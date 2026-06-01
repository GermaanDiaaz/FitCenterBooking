package com.salesianostriana.dam.fitcenterbooking.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.salesianostriana.dam.fitcenterbooking.model.Reserva;

public interface RepositoryReserva extends JpaRepository<Reserva, Long>{
	
	@Query("SELECT r FROM Reserva r WHERE r.usuario.id = :codigo")
	List<Reserva> listarPorID(@Param("codigo") Long codigo);
	
	
	@Query("SELECT COUNT(r) > 0 FROM Reserva r JOIN r.actividades linea WHERE linea.actividad.id = :id")
	boolean existeEstaActividadEnAlgunaReserva(Long id);
	
	boolean existsByUsuarioId(Long usuarioId);
	
	@Query("SELECT COUNT(ar) FROM ActividadReserva ar WHERE ar.actividad.id = :actividadId")
    int contarPlazasReservadas(@Param("actividadId") Long actividadId);
	
	
	@Query("SELECT r FROM Reserva r ORDER BY r.fecha DESC")
	List<Reserva> findReservasOrdenadasPorFecha();
}
