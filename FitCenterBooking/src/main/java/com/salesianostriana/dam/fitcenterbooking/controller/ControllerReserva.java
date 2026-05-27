package com.salesianostriana.dam.fitcenterbooking.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.salesianostriana.dam.fitcenterbooking.service.ServiceReserva;

import lombok.RequiredArgsConstructor;

@Controller 
@RequiredArgsConstructor
public class ControllerReserva {
	
	private final ServiceReserva service;

	@GetMapping("/reservas")
	public String listarReservas (Model model) {
			
		model.addAttribute("listaReservas", service.findAll());		
		return "reservas";
	}
	
	@GetMapping("/reservas/eliminar/{codigo}")
	public String eliminarActividad(@PathVariable("codigo") Long codigo) {
		
		service.deleteById(codigo); 
		
		return "redirect:/reservas";
	}
}
