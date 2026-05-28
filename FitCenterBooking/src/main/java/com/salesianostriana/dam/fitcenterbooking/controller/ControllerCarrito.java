package com.salesianostriana.dam.fitcenterbooking.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.salesianostriana.dam.fitcenterbooking.model.Actividad;
import com.salesianostriana.dam.fitcenterbooking.service.ServiceActividad;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@Controller 
@RequiredArgsConstructor
public class ControllerCarrito {
	
	private final ServiceActividad service;

	@SuppressWarnings("unchecked")
	private List<Actividad> obtenerCarrito(HttpSession sesion) {
		List<Actividad> carrito = (List<Actividad>) sesion.getAttribute("carrito");
		if (carrito == null) {
			carrito = new ArrayList<>();
			sesion.setAttribute("carrito", carrito);
		}
		return carrito;
	}
	
	@GetMapping("/carrito")
	public String verCarrito (Model model, HttpSession sesion) {
			
		List<Actividad> actAgregadas = obtenerCarrito(sesion);
	    
	    double total = 0.0;
	    for(Actividad a : actAgregadas) {
	        total += a.getPrecio();
	    }
	    
	    model.addAttribute("listaCarrito", actAgregadas);		
	    model.addAttribute("totalCarrito", total);
	    return "carrito";
	}
	
	@GetMapping("/carrito/add/{id}")
	public String añadirAlCarrito(@PathVariable("id") Long id, HttpSession sesion) {
		List<Actividad> carrito = obtenerCarrito(sesion);
		Actividad a = service.findById(id).orElse(null);
		
		if (a != null && !carrito.contains(a)) {
			carrito.add(a);
		}
		return "redirect:/carrito";
	}
	
	@PostMapping("/carrito/confirmar")
	public String confirmarReserva(@RequestParam("fechaFormulario") String fecha, HttpSession sesion) {
		
		
		sesion.removeAttribute("carrito");
	    return "redirect:/misReservas";
	}
	
	@GetMapping("/misReservas")
	public String verMisReservas (Model model) {
			
		model.addAttribute("listaCarrito", service.findAll());		
		return "misReservas";
	}
}
