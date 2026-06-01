package com.salesianostriana.dam.fitcenterbooking.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.salesianostriana.dam.fitcenterbooking.model.Usuario;

public interface RepositoryUsuario extends JpaRepository<Usuario, Long>{

	Optional<Usuario> findByEmail(String email);
	
	boolean existsByEmail(String email);
	
	List<Usuario> findByActivoTrue();
	
	
	int countByActivoTrue();
}
