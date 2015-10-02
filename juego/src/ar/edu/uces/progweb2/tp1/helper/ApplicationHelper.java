package ar.edu.uces.progweb2.tp1.helper;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;

import ar.edu.uces.progweb2.tp1.model.Jugador;

@Component
public class ApplicationHelper {

	public void actualizarScopeApplication(HttpServletRequest request,
			Jugador jugador) {
		ServletContext context = request.getSession().getServletContext();
		Integer racha = (Integer) context.getAttribute("racha");

		if (jugador.getRacha().getMejorRacha() > racha) {
			context.setAttribute("jugador", jugador.getNombre());
			context.setAttribute("racha", jugador.getRacha().getMejorRacha());
		}
	}

	public void crearScopeApplication(HttpServletRequest request,
			Jugador jugador) {
		ServletContext context = request.getSession().getServletContext();
		Integer racha = (Integer) context.getAttribute("racha");
		if (racha == null) {
			context.setAttribute("racha", new Integer(0));
		}
	}
}
