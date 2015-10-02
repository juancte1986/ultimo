package ar.edu.uces.progweb2.tp1.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Jugador {

	private String nombre;
	private String idioma;
	private int numeroAleatorio;
	private List<Integer> numIncorrectos = new ArrayList<Integer>();
	private int numeroIntentos = 0;
	private boolean ganador = false;
	private boolean perdedor = false;
	private Racha racha = new Racha();
	
	public void setRacha(Racha racha) {
		this.racha = racha;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getIdioma() {
		return idioma;
	}
	public void setIdioma(String idioma) {
		this.idioma = idioma;
	}
	public List<Integer> getNumIncorrectos() {
		return numIncorrectos;
	}
	public void setNumIncorrectos(List<Integer> numIncorrectos) {
		this.numIncorrectos = numIncorrectos;
	}
	public int getNumeroAleatorio() {
		return numeroAleatorio;
	}
	public void setNumeroAleatorio(int numeroAleatorio) {
		this.numeroAleatorio = numeroAleatorio;
	}
	public Racha getRacha() {
		return racha;
	}
	public int getNumeroIntentos() {
		return numeroIntentos;
	}
	public void setNumeroIntentos(int numeroIntentos) {
		this.numeroIntentos = numeroIntentos;
	}
	public boolean getGanador() {
		return ganador;
	}
	public void setGanador(boolean ganador) {
		this.ganador = ganador;
	}
	public boolean getPerdedor() {
		return perdedor;
	}
	public void setPerdedor(boolean perdedor) {
		this.perdedor = perdedor;
	}
}
