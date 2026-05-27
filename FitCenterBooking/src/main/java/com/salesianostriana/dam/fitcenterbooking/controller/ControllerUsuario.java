package com.salesianostriana.dam.fitcenterbooking.controller;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.salesianostriana.dam.fitcenterbooking.model.Usuario;
import com.salesianostriana.dam.fitcenterbooking.security.RolesUsuario;
import com.salesianostriana.dam.fitcenterbooking.service.ServiceUsuario;

import lombok.RequiredArgsConstructor;

@Controller 
@RequiredArgsConstructor
public class ControllerUsuario {

	private final ServiceUsuario service;
    private final PasswordEncoder encoder;


	@GetMapping("/usuarios")
	public String listarUsuarios (Model model) {
		
		model.addAttribute("listaUsuarios", service.findAll());		
		return "usuarios";
	}
	
	@GetMapping("/login")
	public String login (Model model) {
		
		//model.addAttribute("errorLogin", "El correo o la contraseña no son correctos.");

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
        
		newUsuario.setRol(RolesUsuario.CLIENTE);
		newUsuario.setPassword(encoder.encode(newUsuario.getPassword()));
		service.save(newUsuario);
		
        return "redirect:/login"; 
    }
	
	@GetMapping("/usuarios/eliminar/{id}")
	public String eliminarActividad(@PathVariable("id") Long id) {
		
		service.deleteById(id); 
		
		return "redirect:/usuarios";
	}
	
	@GetMapping("/usuarios/editar/{id}")
	public String editarActividad(@PathVariable("id") Long id, Model model) {
		
		Usuario u = service.findById(id).get();
        model.addAttribute("usuario", u);
		
		return "formUsuario";
	}
	
	@PostMapping("/usuarios/editar")
	public String procesarUsuario(@ModelAttribute("usuario") Usuario newUser) {
		
		service.edit(newUser);
		
		return "redirect:/usuarios";
	}
}
