package com.salesianostriana.dam.fitcenterbooking.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.salesianostriana.dam.fitcenterbooking.model.Actividad;
import com.salesianostriana.dam.fitcenterbooking.model.ActividadReserva;
import com.salesianostriana.dam.fitcenterbooking.model.Reserva;
import com.salesianostriana.dam.fitcenterbooking.model.Usuario;
import com.salesianostriana.dam.fitcenterbooking.service.ServiceActividad;
import com.salesianostriana.dam.fitcenterbooking.service.ServiceReserva;
import com.salesianostriana.dam.fitcenterbooking.service.ServiceUsuario;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@Controller 
@RequiredArgsConstructor
public class ControllerCarrito {
	
	private final ServiceActividad serviceActividad;
	private final ServiceReserva serviceReserva;
	private final ServiceUsuario serviceUsuario;


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
		Actividad activ = serviceActividad.buscarPorID(id);
	    
	    if (!carrito.contains(activ)) {
	        carrito.add(activ);
	    }
		return "redirect:/carrito";
	}
	
	@PostMapping("/carrito/confirmar")
	public String confirmarReserva(@RequestParam("fecha") String fecha, HttpSession sesion) {
	    
	    List<Actividad> actividadesCarrito = obtenerCarrito(sesion);
	    
	    LocalDateTime fechaSeleccionada = LocalDateTime.parse(fecha);
	    
	    Usuario usuarioCliente = serviceUsuario.buscarPorID(2L); 
	    
	    Reserva nuevaReserva = Reserva.builder()
	            .fecha(fechaSeleccionada)
	            .usuario(usuarioCliente)
	            .build();
	            
	    nuevaReserva = serviceReserva.save(nuevaReserva);
	    
	    for (Actividad act : actividadesCarrito) {
	        ActividadReserva linea = ActividadReserva.builder()
	                .reserva(nuevaReserva)
	                .actividad(act)
	                .build();
	        
	        nuevaReserva.addLinea(linea);
	    }
	    
	    nuevaReserva.setPrecioTotal(nuevaReserva.calcularPrecioTotal());
	    
	    serviceReserva.save(nuevaReserva);
	    
	    sesion.removeAttribute("carrito");
	    
	    return "redirect:/misReservas";
	}
	
	@GetMapping("/misReservas")
	public String verMisReservas (Model model) {
		List<Reserva> reservasUsuario = serviceReserva.listarReservasUsuario(2L);
		
		model.addAttribute("listaReservas", reservasUsuario);
		return "misReservas";
	}
	
	
	@GetMapping("/misReservas/editar/{codigo}")
	public String editarActividad(@PathVariable("codigo") Long codigo, Model model) {
		
		Reserva r = serviceReserva.buscarPorID(codigo);
		
        model.addAttribute("reserva", r);
        model.addAttribute("usuarios", serviceUsuario.findAll());
		
		return "formReserva";
	}
	
	@PostMapping("/misReservas/editar/{codigo}")
	public String procesarEdicion(@PathVariable("codigo") Long codigo, @ModelAttribute("reserva") Reserva reservaEditada,
			@RequestParam("usuarioId") Long idUsuario) {
		
		Reserva resOriginal = serviceReserva.buscarPorID(codigo);
		Usuario user = serviceUsuario.buscarPorID(idUsuario);
		
		resOriginal.setFecha(reservaEditada.getFecha());
		resOriginal.setUsuario(user);
		
		double precioCalculado = resOriginal.calcularPrecioTotal(); 
		resOriginal.setPrecioTotal(precioCalculado);

		serviceReserva.save(resOriginal); 
		
		return "redirect:/reservas";
	}
	
	
}
