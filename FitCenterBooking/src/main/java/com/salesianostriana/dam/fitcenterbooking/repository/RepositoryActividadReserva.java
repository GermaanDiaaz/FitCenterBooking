package com.salesianostriana.dam.fitcenterbooking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.salesianostriana.dam.fitcenterbooking.model.ActividadReserva;

@Repository
public interface RepositoryActividadReserva extends JpaRepository<ActividadReserva, Long>{

}
