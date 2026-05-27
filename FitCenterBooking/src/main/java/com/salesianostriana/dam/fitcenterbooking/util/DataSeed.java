package com.salesianostriana.dam.fitcenterbooking.util;

import java.time.LocalDateTime;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.salesianostriana.dam.fitcenterbooking.model.Actividad;
import com.salesianostriana.dam.fitcenterbooking.model.ActividadReserva;
import com.salesianostriana.dam.fitcenterbooking.model.Reserva;
import com.salesianostriana.dam.fitcenterbooking.model.Usuario;
import com.salesianostriana.dam.fitcenterbooking.repository.RepositoryActividad;
import com.salesianostriana.dam.fitcenterbooking.repository.RepositoryActividadReserva;
import com.salesianostriana.dam.fitcenterbooking.repository.RepositoryReserva;
import com.salesianostriana.dam.fitcenterbooking.repository.RepositoryUsuario;
import com.salesianostriana.dam.fitcenterbooking.security.RolesUsuario;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;


@Component
@RequiredArgsConstructor
public class DataSeed {
    
    private final RepositoryUsuario usuarioRepo;
    private final RepositoryActividad actividadRepo;
    private final RepositoryReserva reservaRepo;
    private final RepositoryActividadReserva actividadReservaRepo;


    private final PasswordEncoder encoder;
    
    @PostConstruct
    public void init() {
        
        if (usuarioRepo.count() == 0 && actividadRepo.count() == 0 && reservaRepo.count() == 0) {
            
            System.out.println("Metemos datos autogenerados.");
            
            // --------------- Usuarios ---------------
            
            Usuario admin = Usuario.builder()
                    .nombre("admin")
                    .email("admin@admin.com")
                    .password(encoder.encode("admin"))
                    .rol(RolesUsuario.ADMIN)
                    .telefono("600111222")
                    .build();
            
            usuarioRepo.save(admin);
            
            Usuario cliente = Usuario.builder()
                    .nombre("user")
                    .email("user@user.com")
                    .password(encoder.encode("user"))
                    .rol(RolesUsuario.CLIENTE)
                    .telefono("600333444")
                    .build();
            
            usuarioRepo.save(cliente);
            
            // --------------- Actividades ---------------
            
            Actividad yoga = Actividad.builder()
                    .nombre("Yoga")
                    .descripcion("Encuentra tu equilibrio perfecto entre cuerpo y mente. En nuestras sesiones trabajarás la flexibilidad, la fuerza muscular profunda y la corrección postural a través de asanas (posturas) combinadas con el control de la respiración. Una actividad ideal tanto para liberar el estrés acumulado durante el día como para mejorar tu bienestar físico general en un ambiente relajado y guiado por profesionales.")
                    .capacidad(25)
                    .precio(10.00)
                    .imagenUrl("/img/Yoga.png")
                    .build();
            
            actividadRepo.save(yoga);
            
            Actividad crossfit = Actividad.builder()
                    .nombre("Crossfit")
                    .descripcion("Lleva tu rendimiento al siguiente nivel con nuestro entrenamiento de alta intensidad. Esta disciplina combina ejercicios funcionales, levantamiento olímpico, gimnasia y trabajo cardiovascular en sesiones constantemente variadas. Está diseñado para adaptase a cualquier nivel de condición física, desafiando tus límites diarios, quemando calorías y desarrollando una fuerza y resistencia brutales en comunidad.")
                    .capacidad(20)
                    .precio(12.50)
                    .imagenUrl("/img/Crossfit.png")
                    .build();
            
            actividadRepo.save(crossfit);
            
            Actividad natacion = Actividad.builder()
                    .nombre("Natacion")
                    .descripcion("Mejora tu resistencia cardiovascular y tonifica cada grupo muscular sin impacto en las articulaciones dentro de nuestras piscinas climatizadas. Nuestras sesiones están estructuradas para perfeccionar la técnica de los diferentes estilos, aumentar tu capacidad pulmonar y ofrecer un entrenamiento de cuerpo completo de alta eficiencia. Perfecto tanto para nadadores avanzados como para mantenimiento.")
                    .capacidad(20)
                    .precio(15.25)
                    .imagenUrl("/img/Natacion.png")
                    .build();
            
            actividadRepo.save(natacion);
            
            
            // --------------- Reservas ---------------
            
            Reserva reserva1 = Reserva.builder()
            		.fecha(LocalDateTime.of(2026, 6, 02, 19, 30))
                    .precioTotal(15.50)
                    .usuario(cliente)
                    .build();
            
            reservaRepo.save(reserva1);
            
            Reserva reserva2 = Reserva.builder()
            		.fecha(LocalDateTime.of(2026, 6, 10, 19, 30))
                    .precioTotal(55.50)
                    .usuario(cliente)
                    .build();
            
            reservaRepo.save(reserva2);
            
            // --------------- Relación Intermedia ---------------

            ActividadReserva lineaNatacion = ActividadReserva.builder()
                    .reserva(reserva1)
                    .actividad(natacion)
                    .estado("RESERVADA")
                    //.observaciones()
                    .build();
            
            actividadReservaRepo.save(lineaNatacion);
            
            ActividadReserva lineaYoga = ActividadReserva.builder()
                    .reserva(reserva1)
                    .actividad(yoga)
                    .estado("RESERVADA")
                    //.observaciones()
                    .build();
            
            actividadReservaRepo.save(lineaYoga);
            
            
            
            
        }
        
        System.out.println("Datos autogenerados.");
    }
}