package com.salesianostriana.dam.fitcenterbooking.model;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.salesianostriana.dam.fitcenterbooking.security.RolesUsuario;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data 
@AllArgsConstructor 
@NoArgsConstructor
@Builder
@Entity
public class Usuario implements UserDetails{


	@Id 
	@GeneratedValue
	private Long id;
	
	private String nombre;
	private String email;
	private String telefono;
	private String password;
	
	@Enumerated(EnumType.STRING)
	private RolesUsuario rol;


	@OneToMany(mappedBy = "usuario", cascade = CascadeType.REMOVE)
	private List<Reserva> reservas;
	
	@Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
		
        return List.of(new SimpleGrantedAuthority("ROLE_" + this.rol));
    }

	@Override
	public String getUsername() {
		return this.nombre;
	}
}
