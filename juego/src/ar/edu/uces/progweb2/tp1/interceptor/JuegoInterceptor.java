package ar.edu.uces.progweb2.tp1.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import ar.edu.uces.progweb2.tp1.model.Jugador;

public class JuegoInterceptor implements HandlerInterceptor{

	@Override
	public void afterCompletion(HttpServletRequest arg0,
			HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		
	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1,
			Object arg2, ModelAndView arg3) throws Exception {
		
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
			Object arg2) throws Exception {
		
		HttpSession session = request.getSession();
		Jugador jugador = (Jugador) session.getAttribute("jugador"); 
		if(jugador != null){
			return true;
		} else {
			//forward
			request.getServletContext().getRequestDispatcher("/inicializarFormJugador.htm").forward(request, response);;
			return false;
		}
	}

}
