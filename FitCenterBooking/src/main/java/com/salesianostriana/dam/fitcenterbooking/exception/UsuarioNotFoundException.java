package com.salesianostriana.dam.fitcenterbooking.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class UsuarioNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public UsuarioNotFoundException(Long id) {
        super("No se ha podido encontrar el usuario con el ID: " + id);
    }
}