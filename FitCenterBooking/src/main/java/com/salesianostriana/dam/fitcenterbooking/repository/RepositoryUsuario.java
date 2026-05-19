package com.salesianostriana.dam.fitcenterbooking.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.salesianostriana.dam.fitcenterbooking.model.Usuario;

public interface RepositoryUsuario extends JpaRepository<Usuario, Long>{

	/*@Query("select all from Usuario u")
	public List<Usuario> findAll();*/
}
