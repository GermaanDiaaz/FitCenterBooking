package com.salesianostriana.dam.fitcenterbooking.controller;

import java.util.NoSuchElementException;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.salesianostriana.dam.fitcenterbooking.model.Actividad;
import com.salesianostriana.dam.fitcenterbooking.service.ServiceActividad;

import lombok.RequiredArgsConstructor;

@Controller 
@RequiredArgsConstructor
public class ControllerActividad {
	
	private final ServiceActividad service;

	@GetMapping({"/"})
	public String index (Model model) {
		
		model.addAttribute("listaActividades", service.findAll());		
		return "index";
	}
	
	@GetMapping({"/actividades"})
	public String listarActividades (Model model) {
		
		model.addAttribute("listaActividades", service.findAll());		
		return "actividades";
	}
	
	@GetMapping("/actividad/{id}")
	public String mostrarActividad (@PathVariable("id") Long id,Model model) {
		
		Actividad a = service.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Actividad no encontrada con el ID: " + id));
        
		model.addAttribute("actividad", a);
		
		return "actividad";
	}
	
	@GetMapping("/actividades/nueva")
    public String mostrarFormularioNuevaActividad(Model model) {
        model.addAttribute("actividad", new Actividad());
        return "formActividad";
    }
	
	@PostMapping("/actividades/nueva")
    public String procesarRegistro(@ModelAttribute("actividad") Actividad newActividad) {
        
		service.save(newActividad);
		
        return "redirect:/actividades"; 
    }
	
	
	@GetMapping("/actividades/eliminar/{id}")
	public String eliminarActividad(@PathVariable("id") Long id) {
		
		service.deleteById(id); 
		
		return "redirect:/actividades";
	}
	
	@GetMapping("/actividades/editar/{id}")
	public String editarActividad(@PathVariable("id") Long id, Model model) {
		
		Actividad a = service.findById(id).get();
        model.addAttribute("actividad", a);
		
		return "formActividad";
	}
	
}
