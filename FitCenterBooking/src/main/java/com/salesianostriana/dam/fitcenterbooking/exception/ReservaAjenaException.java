package com.salesianostriana.dam.fitcenterbooking.exception;

public class ReservaAjenaException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ReservaAjenaException() {
        super("No tienes permiso para modificar una reserva ajena.");
    }
}
