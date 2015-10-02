package ar.edu.uces.progweb2.tp1.bo;

import ar.edu.uces.progweb2.tp1.dto.FormJuegoDTO;
import ar.edu.uces.progweb2.tp1.dto.FormJugadorDTO;
import ar.edu.uces.progweb2.tp1.dto.ResulDTO;
import ar.edu.uces.progweb2.tp1.model.Jugador;

public interface JuegoBO {

	Jugador getJugador(FormJugadorDTO formJugador);

	ResulDTO procesarJugada(Jugador jugador, FormJuegoDTO formJugada);

	void reiniciarJuego(Jugador jugador);
	
}
