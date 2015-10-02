package ar.edu.uces.progweb2.tp1.helper;

import org.springframework.stereotype.Component;

import ar.edu.uces.progweb2.tp1.model.Jugador;

@Component
public class SessionHelper {
	
	//metodo que actualiza si corresponde la mejor racha de la session
	public void actualizarRacha(Jugador jugador){
		if(jugador.getRacha().getRachaActual() > jugador.getRacha().getMejorRacha()){
			jugador.getRacha().setMejorRacha(jugador.getRacha().getRachaActual());
		}
	}
}
