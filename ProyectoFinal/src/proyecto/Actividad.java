package proyecto;

public class Actividad {

	private String nombre;
	private int capacidad;
	private double precio;
	
	
	public Actividad(String nombre, int capacidad, double precio) {
		super();
		this.nombre = nombre;
		this.capacidad = capacidad;
		this.precio = precio;
	}
	
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public int getCapacidad() {
		return capacidad;
	}
	public void setCapacidad(int capacidad) {
		this.capacidad = capacidad;
	}
	public double getPrecio() {
		return precio;
	}
	public void setPrecio(double precio) {
		this.precio = precio;
	}
	
	
	@Override
	public String toString() {
		return "Actividad [nombre=" + nombre + ", capacidad=" + capacidad + ", precio=" + precio + "]";
	}

	
}
