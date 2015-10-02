package ar.edu.uces.progweb2.tp1.dto;

public class FormJugadorDTO {
	
	private String nombre;
	private String idioma;
	
	// los get y set son obligatorios para el modelo de vista
	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setIdioma(String idioma) {
		this.idioma = idioma;
	}

	public String getIdioma() {
		return this.idioma;
	}
	
	public FormJugadorDTO() {
		
	}
	
}
