package ar.edu.uces.progweb2.tp1.boImpl;

import java.util.Random;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.SessionAttributes;

import ar.edu.uces.progweb2.tp1.bo.JuegoBO;
import ar.edu.uces.progweb2.tp1.dto.FormJuegoDTO;
import ar.edu.uces.progweb2.tp1.dto.FormJugadorDTO;
import ar.edu.uces.progweb2.tp1.dto.ResulDTO;
import ar.edu.uces.progweb2.tp1.model.Jugador;

@SessionAttributes("jugador")
@Component
public class JuegoBOImpl implements JuegoBO{
	
	private static final int oportunidades = 10;
	
	@Override
	public Jugador getJugador(FormJugadorDTO form ){
		Jugador jugador = new Jugador();
		jugador.setNombre(form.getNombre());
		jugador.setIdioma(form.getIdioma());
		//genero el primer numero aleatorio
		jugador.setNumeroAleatorio(this.generarNumeroAleatorio());
		//inicializa racha en 0 en la primera session
		jugador.getRacha().setMejorRacha(0);
		jugador.getRacha().setRachaActual(0);
		
		return jugador;
	}
	
	private int generarNumeroAleatorio(){
		Random random = new Random();
		return random.nextInt(99)+1;
	}
	
	//proceso la jugada y seteo los mensajes en cada caso(ganador, perdedor y nueva oportunidad)
	@Override
	public ResulDTO procesarJugada(Jugador jugador, FormJuegoDTO form) {
		int valor = Integer.parseInt(form.getNumero());
		ResulDTO result = new ResulDTO();
		
		if (jugador.getNumeroAleatorio() == valor) {
			// metodo de jugador que incrementa la racha actual
			result.setGanador(true);
			jugador.setNumeroAleatorio(this.generarNumeroAleatorio());
			jugador.setNumeroIntentos(0);
			jugador.getNumIncorrectos().clear();
			jugador.getRacha().setRachaActual(jugador.getRacha().getRachaActual() + 1);
			
		} else {
			if(jugador.getNumeroIntentos() < oportunidades){
				jugador.setNumeroIntentos(jugador.getNumeroIntentos() + 1);
				jugador.getNumIncorrectos().add(valor);
			} else {
				result.setPerdedor(true);
				jugador.setNumeroAleatorio(this.generarNumeroAleatorio());
				jugador.setNumeroIntentos(0);
				jugador.getNumIncorrectos().clear();
				jugador.getRacha().setRachaActual(0);
			}
		}
		
		return result;
	}
	
	// metodo publico que reinicia el jugador en caso de reinciar el juego, cabe aclarar que a la racha actual se setea en 0, 
	@Override
	public void reiniciarJuego(Jugador jugador){
		//se crea nuevo nuevo aleatorio
		jugador.setNumeroAleatorio(this.generarNumeroAleatorio());
		jugador.setNumeroIntentos(0);
		jugador.getNumIncorrectos().clear();
		jugador.getRacha().setRachaActual(0);
	}
	
}
