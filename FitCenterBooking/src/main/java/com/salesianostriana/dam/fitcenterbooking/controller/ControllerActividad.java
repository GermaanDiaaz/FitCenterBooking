package com.salesianostriana.dam.fitcenterbooking.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.salesianostriana.dam.fitcenterbooking.service.ServiceActividad;

import lombok.RequiredArgsConstructor;

@Controller @RequiredArgsConstructor
public class ControllerActividad {
	
	private final ServiceActividad service;

	@GetMapping({"/"})
	public String listarActividades (Model model) {
		
		model.addAttribute("listaActividades", service.findAll());		
		return "index";
	}
	
	@GetMapping("/actividad")
	public String mostrarActividad (Model model) {
		
		return "actividad";
	}
	
}
