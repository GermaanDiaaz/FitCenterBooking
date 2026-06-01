package com.salesianostriana.dam.fitcenterbooking.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.salesianostriana.dam.fitcenterbooking.model.Actividad;

public interface RepositoryActividad extends JpaRepository<Actividad, Long>{

	@Query("SELECT ar.actividad FROM ActividadReserva ar GROUP BY ar.actividad ORDER BY COUNT(ar.actividad) DESC LIMIT 3")
	List<Actividad> findActividadesMasPopulares();
}
