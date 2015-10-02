package ar.edu.uces.progweb2.tp1.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import ar.edu.uces.progweb2.tp1.bo.JuegoBO;
import ar.edu.uces.progweb2.tp1.dto.FormJuegoDTO;
import ar.edu.uces.progweb2.tp1.dto.FormJugadorDTO;
import ar.edu.uces.progweb2.tp1.dto.ResulDTO;
import ar.edu.uces.progweb2.tp1.helper.ApplicationHelper;
import ar.edu.uces.progweb2.tp1.helper.CookieHelper;
import ar.edu.uces.progweb2.tp1.helper.SessionHelper;
import ar.edu.uces.progweb2.tp1.model.Jugador;
import ar.edu.uces.progweb2.tp1.validator.FormJuegoValidator;

@SessionAttributes("jugador")
@Controller
public class JuegoController {

	private JuegoBO juegoBO;
	private FormJuegoValidator formJuegoValidator;
	private CookieHelper helperCookie;
	private SessionHelper helperSesion;
	private ApplicationHelper helperApplication;
	
	@Autowired
	public void setHelperApplication(ApplicationHelper helperApplication) {
		this.helperApplication = helperApplication;
	}

	@Autowired
	public void setJuegoBO(JuegoBO juegoBO) {
		this.juegoBO = juegoBO;
	}

	@Autowired
	public void setHelperCookie(CookieHelper helperCookie) {
		this.helperCookie = helperCookie;
	}

	@Autowired
	public void setHelperSesion(SessionHelper helperSesion) {
		this.helperSesion = helperSesion;
	}

	@Autowired
	public void setFormJuegoValidator(FormJuegoValidator formJuegoValidator) {
		this.formJuegoValidator = formJuegoValidator;
	}

	@RequestMapping(value = "/login/procesarFormJugada")
	public String procesarFormJugada(ModelMap model, @ModelAttribute("formJuego") FormJuegoDTO formJuego,
		BindingResult result, @CookieValue(value = "cookieMaquina", required = false) String cookieMaquina,
		HttpServletRequest request, HttpServletResponse response) {
		
		Jugador jugador = (Jugador) model.get("jugador");
		// valido form
		this.formJuegoValidator.validate(formJuego, result);
		if (result.hasErrors()) {
			return "/jsp/formJuego.jsp";
		}
		// procesar jugada
		ResulDTO resultDTO = this.juegoBO.procesarJugada(jugador, formJuego);
		this.helperSesion.actualizarRacha(jugador);
		this.helperApplication.actualizarScopeApplication(request, jugador);
		model.addAttribute("cookieMaquina", this.helperCookie.actualizarCookieMaquina(response, cookieMaquina, jugador));
		model.addAttribute("cookieJugador", this.helperCookie.actualizarCookieJugador(request, response, jugador));
		model.addAttribute("resultDTO", resultDTO);
		model.addAttribute("formJuego", new FormJuegoDTO());

		return "/jsp/formJuego.jsp";
	}

	@RequestMapping(value = "/login/reiniciarJuego")
	public String reiniciarJuego(ModelMap model, @CookieValue(value = "cookieMaquina", required = false) String cookieMaquina, HttpServletRequest request) {
		Jugador jugador = (Jugador) model.get("jugador");
		// reiniciar el juego
		this.juegoBO.reiniciarJuego(jugador);
		model.addAttribute("formJuego", new FormJuegoDTO());
		Cookie cookieJugador = this.helperCookie.buscarCookieJugador(request, jugador.getNombre());
		model.addAttribute("cookieJugador", cookieJugador.getValue());
		model.addAttribute("cookieMaquina", cookieMaquina);
		return "/jsp/formJuego.jsp";
	}

	@RequestMapping(value = "/login/salir")
	public String reiniciarFormJugador(ModelMap model, SessionStatus session) {
		session.setComplete();
		model.addAttribute("formJugador", new FormJugadorDTO());
		return "/jsp/formUsuario.jsp";
	}

}
