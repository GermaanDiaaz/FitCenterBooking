package com.salesianostriana.dam.fitcenterbooking.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

import com.salesianostriana.dam.fitcenterbooking.model.Usuario;

@Service
public class ServiceUsuario {

	public List<Usuario> getLista() {
		return Arrays.asList(
				new Usuario("Germán", "correoger@gmail.com", "123456789"),
				new Usuario("Manuel", "correomanu@gmail.com", "987654321"),
				new Usuario("Marta", "correomarta@gmail.com", "112233445"),
				new Usuario("Gonzalo", "correogonzalo@gmail.com", "998877665")
				);		
	}
}
