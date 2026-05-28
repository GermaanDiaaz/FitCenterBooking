package com.salesianostriana.dam.fitcenterbooking.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ActividadNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public ActividadNotFoundException(Long id) {
        super("No se ha podido encontrar la reserva con el ID: " + id);
    }
}
