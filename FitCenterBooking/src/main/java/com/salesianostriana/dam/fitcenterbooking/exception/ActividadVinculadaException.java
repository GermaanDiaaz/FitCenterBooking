package com.salesianostriana.dam.fitcenterbooking.exception;

public class ActividadVinculadaException extends RuntimeException {
    
	private static final long serialVersionUID = 1L;

	public ActividadVinculadaException(Long id) {
        super("No se puede eliminar la actividad con ID " + id + " porque tiene reservas asociadas.");
    }
}