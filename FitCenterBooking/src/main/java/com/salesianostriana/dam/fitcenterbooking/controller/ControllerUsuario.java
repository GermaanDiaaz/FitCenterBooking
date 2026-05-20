package com.salesianostriana.dam.fitcenterbooking.controller;

import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.salesianostriana.dam.fitcenterbooking.model.Usuario;
import com.salesianostriana.dam.fitcenterbooking.service.ServiceUsuario;

import lombok.RequiredArgsConstructor;

@Controller @RequiredArgsConstructor
public class ControllerUsuario {

	private final ServiceUsuario service;

	/*@GetMapping("/usuarios")
	public String listarUsuarios (Model model) {
		
		model.addAttribute("listaUsuarios", service.getLista());		
		return "ListaUsuarios";
	}*/
	
	@GetMapping("/home")
	public String index (Model model) {
		
		return "index";
	}
	
	
	@GetMapping("/login")
	public String login (Model model) {
		
		return "login";
	}
	
	@PostMapping("/login")
    public String procesarLogin(@RequestParam("email") String email,
    							@RequestParam("password") String password,
    							Model model) {
        
		Optional<Usuario> user = service.findByEmail(email);

		if(user.isPresent()) {
			Usuario usuario = user.get();
			if(usuario.getPassword().equals(password)) {
				
				return "redirect:/home";
			}
		}
		model.addAttribute("errorLogin", "El correo o la contraseña no son correctos.");	
        return "login"; 
    }
	
	
	@GetMapping("/registro")
	public String registro (Model model) {
		
		Usuario nuevoUsuario = new Usuario();
        
        model.addAttribute("usuario", nuevoUsuario);
		return "registro";
	}
	
	@PostMapping("/registro")
    public String procesarRegistro(@ModelAttribute("usuario") Usuario newUsuario) {
        
		newUsuario.setRol("CLIENTE");
		service.save(newUsuario);
		
        return "redirect:/login"; 
    }
	
}
