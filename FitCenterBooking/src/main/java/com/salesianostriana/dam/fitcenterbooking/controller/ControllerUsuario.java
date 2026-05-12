package com.salesianostriana.dam.fitcenterbooking.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.salesianostriana.dam.fitcenterbooking.service.ServiceUsuario;

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
}
