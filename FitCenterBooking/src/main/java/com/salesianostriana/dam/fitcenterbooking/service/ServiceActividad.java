package com.salesianostriana.dam.fitcenterbooking.service;

import org.springframework.stereotype.Service;

import com.salesianostriana.dam.fitcenterbooking.exception.ActividadNotFoundException;
import com.salesianostriana.dam.fitcenterbooking.model.Actividad;
import com.salesianostriana.dam.fitcenterbooking.repository.RepositoryActividad;
import com.salesianostriana.dam.fitcenterbooking.service.base.BaseServiceImpl;

@Service
public class ServiceActividad extends BaseServiceImpl<Actividad, Long, RepositoryActividad>{

	public Actividad buscarPorID(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ActividadNotFoundException(id));
    }
}
