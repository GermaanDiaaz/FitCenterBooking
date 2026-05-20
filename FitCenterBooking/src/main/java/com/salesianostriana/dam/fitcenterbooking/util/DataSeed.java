package com.salesianostriana.dam.fitcenterbooking.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import com.salesianostriana.dam.fitcenterbooking.model.Usuario;
import com.salesianostriana.dam.fitcenterbooking.repository.RepositoryActividad;
import com.salesianostriana.dam.fitcenterbooking.repository.RepositoryReserva;
import com.salesianostriana.dam.fitcenterbooking.repository.RepositoryUsuario;


@Component
public class DataSeed implements ApplicationListener<ContextRefreshedEvent>{
	
	@Autowired
    private RepositoryUsuario usuarioRepo;

	@Autowired
    private RepositoryActividad actividadRepo;

	@Autowired
    private RepositoryReserva reservaRepo;

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		
		if (usuarioRepo.count() == 0 && actividadRepo.count() == 0) {
        	
        	System.out.println("Metemos datos autogenerados.");
        	
			Usuario admin = new Usuario();
			admin.setNombre("admin");
			admin.setEmail("admin@admin.com");
			admin.setPassword("admin");
			admin.setRol("ADMIN");
			admin.setTelefono("600111222");
			usuarioRepo.save(admin);

			Usuario cliente = new Usuario();
			cliente.setNombre("user");
			cliente.setEmail("user@user.com");
			cliente.setPassword("user");
			cliente.setRol("CLIENTE");
			cliente.setTelefono("600333444");
			usuarioRepo.save(cliente);
        }
        
        System.out.println("Datos autogenerados.");
		
	}
}
