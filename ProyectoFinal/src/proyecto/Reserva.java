package proyecto;

import java.time.LocalDateTime;

public class Reserva {

	private String codigo;
	private LocalDateTime fecha;
	private double precioTotal;
	
	
	public Reserva(String codigo, LocalDateTime fecha, double precioTotal) {
		super();
		this.codigo = codigo;
		this.fecha = fecha;
		this.precioTotal = precioTotal;
	}
	
	
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public LocalDateTime getFecha() {
		return fecha;
	}
	public void setFecha(LocalDateTime fecha) {
		this.fecha = fecha;
	}
	public double getPrecioTotal() {
		return precioTotal;
	}
	public void setPrecioTotal(double precioTotal) {
		this.precioTotal = precioTotal;
	}
	
	
	@Override
	public String toString() {
		return "Reserva [codigo=" + codigo + ", fecha=" + fecha + ", precioTotal=" + precioTotal + "]";
	}

	
}
