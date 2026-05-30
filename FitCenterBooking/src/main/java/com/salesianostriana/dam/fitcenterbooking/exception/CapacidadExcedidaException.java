package com.salesianostriana.dam.fitcenterbooking.exception;

public class CapacidadExcedidaException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public CapacidadExcedidaException() {
        super("No se puede reservar porque esta actividad cuenta con el aforo máximo permitido");
    }
}