package com.salesianostriana.dam.fitcenterbooking.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.salesianostriana.dam.fitcenterbooking.exception.CapacidadExcedidaException;
import com.salesianostriana.dam.fitcenterbooking.exception.ReservaAjenaException;
import com.salesianostriana.dam.fitcenterbooking.exception.ReservaInvalidaException;
import com.salesianostriana.dam.fitcenterbooking.model.Actividad;
import com.salesianostriana.dam.fitcenterbooking.model.ActividadReserva;
import com.salesianostriana.dam.fitcenterbooking.model.Reserva;
import com.salesianostriana.dam.fitcenterbooking.model.Usuario;
import com.salesianostriana.dam.fitcenterbooking.security.RolesUsuario;
import com.salesianostriana.dam.fitcenterbooking.service.ServiceActividad;
import com.salesianostriana.dam.fitcenterbooking.service.ServiceReserva;
import com.salesianostriana.dam.fitcenterbooking.service.ServiceUsuario;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Controller 
@RequiredArgsConstructor
public class ControllerCarrito {
	
	private final ServiceActividad serviceActividad;
	private final ServiceReserva serviceReserva;
	private final ServiceUsuario serviceUsuario;


	private List<Actividad> obtenerCarrito(HttpSession sesion) {
		@SuppressWarnings("unchecked")
		List<Actividad> carrito = (List<Actividad>) sesion.getAttribute("carrito");
		if (carrito == null) {
			carrito = new ArrayList<>();
			sesion.setAttribute("carrito", carrito);
		}
		return carrito;
	}
	
	private void cargarDatosCarrito(Model model, HttpSession sesion) {
		List<Actividad> actAgregadas = obtenerCarrito(sesion);
		double total = 0.0;
		for(Actividad a : actAgregadas) {
			total += a.getPrecio();
		}
		model.addAttribute("listaCarrito", actAgregadas);		
		model.addAttribute("totalCarrito", total);
	}
	
	@GetMapping("/carrito")
	public String verCarrito (Model model, HttpSession sesion) {

		cargarDatosCarrito(model, sesion);
		
	    return "carrito";
	}
	
	@GetMapping("/carrito/add/{id}")
	public String añadirAlCarrito(@PathVariable("id") Long id, HttpSession sesion) {
		
		List<Actividad> carrito = obtenerCarrito(sesion);
		Actividad a = serviceActividad.buscarPorID(id);
		
		if (a == null) {
	        return "redirect:/";
	    }
	    
		boolean yaExiste = false;
	    
	    for (Actividad actividad : carrito) {
	        if (actividad.getId().equals(id)) {
	            yaExiste = true;
	            break;
	        }
	    }
	    
	    if (!yaExiste) {
	        carrito.add(a);
	    }
		return "redirect:/carrito";
	}
	
	@PostMapping("/carrito/confirmar")
	public String confirmarReserva(@RequestParam("fecha") LocalDateTime fecha, HttpSession sesion, 
			@AuthenticationPrincipal Usuario usuarioLogueado, RedirectAttributes redirect, Model model) {
		
		if (fecha.isBefore(LocalDateTime.now())) {
			cargarDatosCarrito(model, sesion);
			
	        model.addAttribute("errorFecha", "La fecha no puede ser pasada.");
	        model.addAttribute("fecha", fecha); 
	        return "carrito"; 
	    }
		
	    List<Actividad> actividadesCarrito = obtenerCarrito(sesion);
	    
	    if (actividadesCarrito.isEmpty()) {
	        return "redirect:/carrito";
	    }
	    
	    LocalDateTime fechaSeleccionada = fecha;
	    
	    try {	    
		    Reserva nuevaReserva = Reserva.builder()
		            .fecha(fechaSeleccionada)
		            .usuario(usuarioLogueado)
		            .build();
		    
		    for (Actividad act : actividadesCarrito) {
		        
		        int plazasOcupadas = serviceReserva.obtenerPlazasOcupadas(act.getId());
		        
		        if (plazasOcupadas >= act.getCapacidad()) {
		            throw new CapacidadExcedidaException();
		        }
		        
		        ActividadReserva linea = ActividadReserva.builder()
		        		.actividad(act)
		        		.reserva(nuevaReserva)
		        		.build();
		        
		        nuevaReserva.addLinea(linea);
		    }
		            
		    nuevaReserva.setPrecioTotal(nuevaReserva.calcularPrecioTotal());
		    
		    Reserva reservaGuardada = serviceReserva.save(nuevaReserva);
		    
		    sesion.removeAttribute("carrito");
		    
		    return "redirect:/reserva/ticket/" + reservaGuardada.getCodigo();
		    		    
	    }catch (ReservaInvalidaException | CapacidadExcedidaException e) {
	    	
	        redirect.addFlashAttribute("errorCarrito", e.getMessage());
	        return "redirect:/carrito";
	    }    
	}
	
	@GetMapping("/reserva/ticket/{codigo}")
    public String verTicket(@PathVariable("codigo") Long codigo, Model model, 
                            @AuthenticationPrincipal Usuario usuarioLogueado) {
        
        Reserva r = serviceReserva.buscarPorID(codigo);
        
        if (r == null) {
            return "redirect:/carrito";
        }
        
        if (usuarioLogueado.getRol() != RolesUsuario.ADMIN && !r.getUsuario().getId().equals(usuarioLogueado.getId())) {
            return "redirect:/misReservas";
        }
        
        model.addAttribute("reserva", r);
        return "ticket";
    }
	
	@GetMapping("/misReservas")
	public String verMisReservas (Model model, @AuthenticationPrincipal Usuario usuarioLogueado) {
		List<Reserva> reservasUsuario = serviceReserva.listarReservasUsuario(usuarioLogueado.getId());
		
		model.addAttribute("listaReservas", reservasUsuario);
		return "misReservas";
	}
	
	
	@GetMapping("/misReservas/editar/{codigo}")
	public String editarActividad(@PathVariable("codigo") Long codigo, Model model, 
			@AuthenticationPrincipal Usuario usuarioLogueado) {
		
		Reserva r = serviceReserva.buscarPorID(codigo);
		
		if (r == null) {
	        return "redirect:/misReservas";
	    }
		
		if (!r.getUsuario().getId().equals(usuarioLogueado.getId())) {
	        return "redirect:/misReservas";
	    }
				
        model.addAttribute("reserva", r);
        model.addAttribute("usuarios", serviceUsuario.findAll());
		
		return "formReserva";
	}
	
	@PostMapping("/misReservas/editar/{codigo}")
	public String procesarEdicion(@PathVariable("codigo") Long codigo, 
	        @Valid @ModelAttribute("reserva") Reserva reservaEditada,
	        BindingResult bindingResult,
	        @RequestParam(value = "usuarioId", required = false) Long idUsuario, 
	        @AuthenticationPrincipal Usuario usuarioLogueado, Model model) {
	    
		if (reservaEditada.getFecha() != null && reservaEditada.getFecha().isBefore(LocalDateTime.now()) && !bindingResult.hasFieldErrors("fecha")) {
	        bindingResult.rejectValue("fecha", "error.reserva", "La fecha de la reserva no puede ser pasada.");
	    }

	    if (bindingResult.hasErrors()) {
	        Reserva resOriginal = serviceReserva.buscarPorID(codigo);
	        
	        reservaEditada.setPrecioTotal(resOriginal.getPrecioTotal());
	        
	        if (idUsuario != null) {
	            reservaEditada.setUsuario(serviceUsuario.buscarPorID(idUsuario));
	        } else {
	            reservaEditada.setUsuario(resOriginal.getUsuario());
	        }

	        model.addAttribute("usuarios", serviceUsuario.findAll());
	        return "formReserva";
	    }
	    
	    Reserva resOriginal = serviceReserva.buscarPorID(codigo);
	    
	    if (usuarioLogueado.getRol() != RolesUsuario.ADMIN && !resOriginal.getUsuario().getId().equals(usuarioLogueado.getId())) {
	        throw new ReservaAjenaException();
	    }
	            
	    resOriginal.setFecha(reservaEditada.getFecha());
	    
	    if (idUsuario != null) {
	        Usuario user = serviceUsuario.buscarPorID(idUsuario);
	        resOriginal.setUsuario(user);
	    }
	    
	    serviceReserva.save(resOriginal); 
	    
	    return "redirect:/misReservas";
	}
	
	@GetMapping("/carrito/eliminar/{id}")
	public String eliminarDelCarrito(@PathVariable("id") Long id, HttpSession sesion) {
	    
	    List<Actividad> carrito = obtenerCarrito(sesion);
	    
	    boolean eliminado = carrito.removeIf(actividad -> actividad.getId().equals(id));
	    
	    if (!eliminado) {
	        return "redirect:/carrito";
	    }
	    	    
	    return "redirect:/carrito";
	}
	
	
	
	
	
}
