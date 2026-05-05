package com.salesianostriana.dam.fitcenterbooking.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ControllerReserva {

	@GetMapping ("/MisReservas")
	public String reservas(Model model) {
		model.addAttribute("codigo", "Fuchibol");
		model.addAttribute("fecha", 14);
		model.addAttribute("precioTotal", 15);

		return "Reservas";
	}
}
