package com.salesianostriana.dam.fitcenterbooking.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ControllerActividad {

	@GetMapping ("/Fuchibol")
	public String actividad(Model model) {
		model.addAttribute("nombre", "Fuchibol");
		model.addAttribute("capacidad", 14);
		model.addAttribute("precio", 15);

		//model.addAttribute("url", "https://event.supercell.com/brawlstars/es");

		return "ActividadFuchivol";
	}
}
