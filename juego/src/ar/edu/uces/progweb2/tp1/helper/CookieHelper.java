package ar.edu.uces.progweb2.tp1.helper;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;

import ar.edu.uces.progweb2.tp1.model.Jugador;

@Component
public class CookieHelper {

	private String crearCookie(HttpServletResponse response, String name, String valor) {
		Cookie cookie = new Cookie(name, valor);
		cookie.setMaxAge(31536000);
		cookie.setPath("/juego");
		response.addCookie(cookie);
		return cookie.getValue();
	}
	
	public String crearCookieMaquina(String cookieMaquina, HttpServletResponse response) {
		if (cookieMaquina != null) {
			// si existe la envio por el scopeRequest
			return cookieMaquina;
		} else {
			this.crearCookie(response, "cookieMaquina", "N/C 0");
			return this.crearCookie(response, "cookieMaquina", "N/C 0");
		}
	}

	public String crearCookieJugador(HttpServletRequest request, HttpServletResponse response, 
		Jugador jugador) {
		Cookie cookieJugador = this.buscarCookieJugador(request, jugador.getNombre());
		if (cookieJugador != null) {
			return cookieJugador.getValue();
		} else {
			return this.crearCookie(response, jugador.getNombre(), "0");
		}
	}

	public String actualizarCookieMaquina(HttpServletResponse response, String cookieMaquina, Jugador jugador) {
		String arg[] = cookieMaquina.split(" ");
		if (jugador.getRacha().getMejorRacha() > Integer
				.parseInt(arg[1])) {
			return this.crearCookie(response, "cookieMaquina", jugador.getNombre() + " " + jugador
					.getRacha().getMejorRacha().toString());
		} else {
			return cookieMaquina;
		}
	}

	public String actualizarCookieJugador(HttpServletRequest request, HttpServletResponse response, Jugador jugador) {
		Cookie cookieJugador = this.buscarCookieJugador(request,
				jugador.getNombre());
		if (cookieJugador != null) {
			Integer valor = Integer.parseInt(cookieJugador.getValue());
			if (jugador.getRacha().getMejorRacha() > valor) {
				return this.crearCookie(response, jugador.getNombre(),
						jugador.getRacha().getMejorRacha().toString());
			}
		}

		return cookieJugador.getValue();
	}

	public Cookie buscarCookieJugador(HttpServletRequest request, String name) {
		Cookie[] cookies = request.getCookies();
		for (int i = 0; i < cookies.length; i++) {
			if (cookies[i].getName().equals(name)) {
				return cookies[i];
			}
		}

		return null;
	}

}
