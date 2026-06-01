package com.salesianostriana.dam.fitcenterbooking.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.salesianostriana.dam.fitcenterbooking.exception.UsuarioNotFoundException;
import com.salesianostriana.dam.fitcenterbooking.model.Usuario;
import com.salesianostriana.dam.fitcenterbooking.repository.RepositoryUsuario;
import com.salesianostriana.dam.fitcenterbooking.service.base.BaseServiceImpl;

@Service
public class ServiceUsuario extends BaseServiceImpl<Usuario, Long, RepositoryUsuario>{
		
	public Optional<Usuario> findByEmail(String email){
		return repository.findByEmail(email);
	}
	
	public Usuario buscarPorID(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new UsuarioNotFoundException(id));
    }
	
	public boolean existsByEmail(String email) {
        return repository.existsByEmail(email);
    }
	
	public List<Usuario> listarSoloActivos() {
	    return repository.findByActivoTrue();
	}
	
	public int contarActivos() {
		return repository.countByActivoTrue();
	}
	
}
