package com.salesianostriana.dam.fitcenterbooking.exception;

public class ReservaInvalidaException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ReservaInvalidaException() {
        super("No se puede realizar una reserva para una fecha pasada");
    }
}