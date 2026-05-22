package com.salesianostriana.dam.fitcenterbooking.util;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.salesianostriana.dam.fitcenterbooking.model.Actividad;
import com.salesianostriana.dam.fitcenterbooking.model.Usuario;
import com.salesianostriana.dam.fitcenterbooking.repository.RepositoryActividad;
import com.salesianostriana.dam.fitcenterbooking.repository.RepositoryUsuario;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;


@Component
@RequiredArgsConstructor
public class DataSeed {
    
    private final RepositoryUsuario usuarioRepo;
    private final RepositoryActividad actividadRepo;
    private final PasswordEncoder encoder;
    
    @PostConstruct
    public void init() {
        
        if (usuarioRepo.count() == 0 && actividadRepo.count() == 0) {
            
            System.out.println("Metemos datos autogenerados.");
            
            // --------------- Usuarios ---------------
            
            Usuario admin = Usuario.builder()
                    .nombre("admin")
                    .email("admin@admin.com")
                    .password(encoder.encode("admin"))
                    .rol("ADMIN")
                    .telefono("600111222")
                    .build();
            
            usuarioRepo.save(admin);
            
            Usuario cliente = Usuario.builder()
                    .nombre("user")
                    .email("user@user.com")
                    .password(encoder.encode("user"))
                    .rol("CLIENTE")
                    .telefono("600333444")
                    .build();
            
            usuarioRepo.save(cliente);
            
            // --------------- Actividades ---------------
            
            Actividad yoga = Actividad.builder()
                    .nombre("Yoga")
                    .capacidad(25)
                    .precio(10.00)
                    .build();
            
            actividadRepo.save(yoga);
            
            Actividad crossfit = Actividad.builder()
                    .nombre("Crossfit")
                    .capacidad(20)
                    .precio(12.50)
                    .build();
            
            actividadRepo.save(crossfit);
            
            Actividad natacion = Actividad.builder()
                    .nombre("Natacion")
                    .capacidad(20)
                    .precio(15.25)
                    .build();
            
            actividadRepo.save(natacion);
            
            
        }
        
        System.out.println("Datos autogenerados.");
    }
}