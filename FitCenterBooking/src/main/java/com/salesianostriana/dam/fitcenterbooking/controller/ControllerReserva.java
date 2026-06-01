package com.salesianostriana.dam.fitcenterbooking.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.salesianostriana.dam.fitcenterbooking.model.Reserva;
import com.salesianostriana.dam.fitcenterbooking.model.Usuario;
import com.salesianostriana.dam.fitcenterbooking.service.ServiceReserva;
import com.salesianostriana.dam.fitcenterbooking.service.ServiceUsuario;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Controller 
@RequiredArgsConstructor
public class ControllerReserva {
	
	private final ServiceReserva service;
	private final ServiceUsuario serviceUsuario;

	@GetMapping("/reservas")
	public String listarReservas (Model model) {
			
		model.addAttribute("listaReservas", service.findAll());		
		return "reservas";
	}
	
	@GetMapping("/reservas/eliminar/{codigo}")
	public String eliminarActividad(@PathVariable("codigo") Long codigo) {
		
		Reserva r = service.buscarPorID(codigo);
		
		if (r == null) {
	        return "redirect:/reservas";
	    }
		
		service.deleteById(codigo); 
		
		return "redirect:/reservas";
	}
	
	@GetMapping("/reservas/editar/{codigo}")
	public String editarActividad(@PathVariable("codigo") Long codigo, Model model) {
		
		Reserva r = service.buscarPorID(codigo);
		
		if (r == null) {
	        return "redirect:/reservas";
	    }
		
        model.addAttribute("reserva", r);
        model.addAttribute("usuarios", serviceUsuario.findAll());
		
		return "formReserva";
	}
	
	@PostMapping("/reservas/editar/{codigo}")
	public String procesarEdicion(@PathVariable("codigo") Long codigo, 
			@Valid @ModelAttribute("reserva") Reserva reservaEditada,
			BindingResult bindingResult,
			@RequestParam("usuarioId") Long idUsuario, Model model) {
		
		if (bindingResult.hasErrors()) {
			model.addAttribute("usuarios", serviceUsuario.findAll());
			return "formReserva";
		}
		
		Reserva resOriginal = service.buscarPorID(codigo);
		Usuario user = serviceUsuario.buscarPorID(idUsuario);
		
		resOriginal.setFecha(reservaEditada.getFecha());
		resOriginal.setUsuario(user);
		
		double precioCalculado = resOriginal.calcularPrecioTotal(); 
		resOriginal.setPrecioTotal(precioCalculado);

		service.save(resOriginal); 
		
		return "redirect:/reservas";
	}
}
