package com.salesianostriana.dam.fitcenterbooking.controller;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.salesianostriana.dam.fitcenterbooking.model.Actividad;
import com.salesianostriana.dam.fitcenterbooking.model.Reserva;
import com.salesianostriana.dam.fitcenterbooking.model.Usuario;
import com.salesianostriana.dam.fitcenterbooking.security.RolesUsuario;
import com.salesianostriana.dam.fitcenterbooking.service.ServiceActividad;
import com.salesianostriana.dam.fitcenterbooking.service.ServiceReserva;
import com.salesianostriana.dam.fitcenterbooking.service.ServiceUsuario;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Controller 
@RequiredArgsConstructor
public class ControllerUsuario {

	private final ServiceUsuario service;
	private final ServiceReserva serviceReserva;
	private final ServiceActividad serviceActividad;


    private final PasswordEncoder encoder;


	@GetMapping("/usuarios")
	public String listarUsuarios (Model model) {
		
		model.addAttribute("listaUsuarios", service.listarSoloActivos());
		return "usuarios";
	}
	
	@GetMapping("/login")
	public String verLogin (Model model) {
		
		return "login";
	}
	
	@GetMapping("/registro")
	public String verRegistro (Model model) {
		
		Usuario nuevoUsuario = new Usuario();
        
        model.addAttribute("usuario", nuevoUsuario);
		return "registro";
	}
	
	@PostMapping("/registro")
	public String procesarRegistro(@Valid @ModelAttribute("usuario") Usuario newUsuario, 
			BindingResult bindingResult) {
		
		if (service.existsByEmail(newUsuario.getEmail())) {
			bindingResult.rejectValue("email", "error.newUsuario", "Este correo electrónico ya está registrado.");
		}
		
		if (bindingResult.hasErrors()) {
	        return "registro";
	    }
		
		newUsuario.setRol(RolesUsuario.CLIENTE);
		newUsuario.setPassword(encoder.encode(newUsuario.getPassword()));
		service.save(newUsuario);
		
        return "redirect:/login"; 
    }
	
	@GetMapping("/usuarios/eliminar/{id}")
	public String eliminarUsuario(@PathVariable("id") Long id, RedirectAttributes redirect) {
		
		Usuario usuarioABorrar = service.buscarPorID(id);
		
		if (usuarioABorrar.getRol() == RolesUsuario.ADMIN) {
		    redirect.addFlashAttribute("error", "No se puede eliminar al administrador del sistema");
		    return "redirect:/usuarios";
		}
		
		List<Reserva> susReservas = serviceReserva.listarReservasUsuario(id);
	    
	    boolean tieneReservasActivas = false;
	            
	    for(Reserva r : susReservas) {
	    	if(r.getFecha().isAfter(LocalDateTime.now())) {
	    		tieneReservasActivas = true;
	    	}
	    }
	    if (tieneReservasActivas) {
	        redirect.addFlashAttribute("errorUsuario", "No se puede eliminar al usuario porque tiene reservas pendientes.");
	        return "redirect:/usuarios";
	    }
	    
	    usuarioABorrar.setActivo(false);
	    service.edit(usuarioABorrar);
	    		
		return "redirect:/usuarios";
	}
	
	@GetMapping("/usuarios/editar/{id}")
	public String editarUsuario(@PathVariable("id") Long id, Model model) {
		
		Usuario u = service.buscarPorID(id);
        model.addAttribute("usuario", u);
        model.addAttribute("action", "/usuarios/editar/{id}");
		
		return "formUsuario";
	}
	
	@PostMapping("/usuarios/editar/{id}")
	public String procesarUsuario(@Valid @ModelAttribute("usuario") Usuario newUser, 
	        BindingResult bindingResult,
	        @RequestParam(value = "passwordActual", required = false) String passwordActual,
	        @RequestParam(value = "passwordNueva", required = false) String passwordNueva) {
	    
	    Optional<Usuario> mismoEmail = service.findByEmail(newUser.getEmail());
	    if (mismoEmail.isPresent() && !mismoEmail.get().getId().equals(newUser.getId())) {
	        bindingResult.rejectValue("email", "error.newUser", "Este correo electrónico ya está asignado a otro usuario.");
	    }
	    
	    boolean tieneActual = passwordActual != null && !passwordActual.isBlank();
	    boolean tieneNueva = passwordNueva != null && !passwordNueva.isBlank();

	    if (tieneActual || tieneNueva) {
	        if (!tieneActual || !tieneNueva) {
	            bindingResult.rejectValue("password", "error.newUser", "Para cambiar la contraseña debes rellenar tanto la actual como la nueva.");
	        } else if (!encoder.matches(passwordActual, newUser.getPassword())) {
	            bindingResult.rejectValue("password", "error.newUser", "La contraseña actual no es correcta.");
	        } else {
	            newUser.setPassword(encoder.encode(passwordNueva));
	        }
	    }
	    
	    if (bindingResult.hasErrors()) {
	        return "formUsuario";
	    }
	    
	    service.edit(newUser);
	    
	    return "redirect:/usuarios";
	}
	
	@GetMapping("/editar/perfil")
	public String verMiPerfil(Model model) {
	    
	    String emailLogueado = SecurityContextHolder.getContext().getAuthentication().getName(); 
	    
	    Usuario u = service.findByEmail(emailLogueado)
	                       .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
	    
	    model.addAttribute("usuario", u);
	    model.addAttribute("action", "/editar/perfil");
	    
	    return "formUsuario";
	}

	@PostMapping("/editar/perfil")
	public String procesarMiPerfil(@Valid @ModelAttribute("usuario") Usuario miUser, 
	        BindingResult bindingResult,
	        @RequestParam(value = "passwordActual", required = false) String passwordActual,
	        @RequestParam(value = "passwordNueva", required = false) String passwordNueva,
	        Model model) {
	    
	    Optional<Usuario> mismoEmail = service.findByEmail(miUser.getEmail());
	    if (mismoEmail.isPresent() && !mismoEmail.get().getId().equals(miUser.getId())) {
	        bindingResult.rejectValue("email", "error.newUser", "Este correo electrónico ya está asignado a otro usuario.");
	    }
	    
	    boolean tieneActual = passwordActual != null && !passwordActual.isBlank();
	    boolean tieneNueva = passwordNueva != null && !passwordNueva.isBlank();

	    if (tieneActual || tieneNueva) {
	        if (!tieneActual || !tieneNueva) {
	            bindingResult.rejectValue("password", "error.newUser", "Para cambiar la contraseña debes rellenar ambos formularios.");
	        } else if (!encoder.matches(passwordActual, miUser.getPassword())) {
	            bindingResult.rejectValue("password", "error.newUser", "La contraseña actual no es correcta.");
	        } else {
	            miUser.setPassword(encoder.encode(passwordNueva));
	        }
	    }
	    
	    if (bindingResult.hasErrors()) {
	        model.addAttribute("action", "/editar/perfil");
	        return "formUsuario"; 
	    }
	    
	    service.edit(miUser);
	    return "redirect:/"; 
	}
	
	
	@GetMapping("/estadisticas")
	public String verEstadisticas(Model model) {
		
	    model.addAttribute("usuariosActivos", service.contarActivos()); 
	    model.addAttribute("reservasOrdenadas", serviceReserva.ordenarPorFecha());
	    
	    List<Actividad> populares = serviceActividad.buscarPopulares();
	    model.addAttribute("actividadesPopulares", populares);
	    
	    Map<Long, Integer> plazasOcupadas = new HashMap<>();
	    
	    for (Actividad act : populares) {
	        int plazas = serviceReserva.obtenerPlazasOcupadas(act.getId()); 
	        plazasOcupadas.put(act.getId(), plazas);
	    }
	    
	    model.addAttribute("plazasOcupadas", plazasOcupadas);
	    
	    return "estadisticas";
	}
	
	
	
}
