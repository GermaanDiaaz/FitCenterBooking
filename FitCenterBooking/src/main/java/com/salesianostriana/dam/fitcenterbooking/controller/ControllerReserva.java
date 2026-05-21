package com.salesianostriana.dam.fitcenterbooking.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.salesianostriana.dam.fitcenterbooking.service.ServiceUsuario;

import lombok.RequiredArgsConstructor;

@Controller @RequiredArgsConstructor
public class ControllerReserva {
	
	private final ServiceUsuario service;

	@GetMapping("/reservas")
	public String listarReservas (Model model) {
			
		model.addAttribute("listaReservas", service.findAll());		
		return "ListaReservas";
	}
}
