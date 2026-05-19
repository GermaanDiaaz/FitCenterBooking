package com.salesianostriana.dam.fitcenterbooking.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.salesianostriana.dam.fitcenterbooking.service.ServiceUsuario;
import com.salesianostriana.dam.proyectoej06formularios1.model.Empleado;

import lombok.RequiredArgsConstructor;

@Controller @RequiredArgsConstructor
public class ControllerUsuario {

	private final ServiceUsuario service;

	@GetMapping("/usuarios")
	public String listarUsuarios (Model model) {
		
		model.addAttribute("listaUsuarios", service.getLista());		
		return "ListaUsuarios";
	}
	
	@GetMapping("/home")
	public String index (Model model) {
		
		return "index";
	}
	
	@GetMapping("/login")
	public String login (Model model) {
		
		return "login";
	}
	
	@GetMapping("/registro")
	public String registro (Model model) {
		
		return "registro";
	}
	
}
