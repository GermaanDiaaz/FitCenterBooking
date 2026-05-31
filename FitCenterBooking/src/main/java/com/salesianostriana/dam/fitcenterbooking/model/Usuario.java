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
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
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

	private static final long serialVersionUID = 1L;

	@Id 
	@GeneratedValue
	private Long id;
	
	@NotBlank(message = "El nombre no puede estar vacío")
	@Size(min = 1, max = 20, message = "El nombre debe tener entre 1 y 20 caracteres")
	private String nombre;	
	
	@NotBlank(message = "El correo electrónico es obligatorio")
	@Email(message = "Debe introducir un formato de correo válido")
	private String email;
	
	@NotBlank(message = "El teléfono es obligatorio")
	@Pattern(regexp = "^[0-9]{9}$", message = "El teléfono debe tener 9 dígitos")
	private String telefono;
	
	@NotBlank(message = "La contraseña no puede estar vacía")
	@Size(min = 4, message = "La contraseña debe tener al menos 4 caracteres")
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
		return this.email;
	}
}
