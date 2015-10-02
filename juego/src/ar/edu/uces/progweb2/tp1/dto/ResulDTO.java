package ar.edu.uces.progweb2.tp1.dto;

public class ResulDTO {

	private boolean ganador = false;
	private boolean perdedor = false;

	public void setGanador(boolean ganador) {
		this.ganador = ganador;
	}

	public boolean getPerdedor() {
		return perdedor;
	}

	public void setPerdedor(boolean perdedor) {
		this.perdedor = perdedor;
	}

	public boolean getGanador() {
		return ganador;
	}

}
