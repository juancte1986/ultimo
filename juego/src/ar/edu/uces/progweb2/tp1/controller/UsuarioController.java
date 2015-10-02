package ar.edu.uces.progweb2.tp1.controller;

import java.util.Locale;
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
import org.springframework.web.servlet.LocaleResolver;
import ar.edu.uces.progweb2.tp1.bo.JuegoBO;
import ar.edu.uces.progweb2.tp1.dto.FormJuegoDTO;
import ar.edu.uces.progweb2.tp1.dto.FormJugadorDTO;
import ar.edu.uces.progweb2.tp1.helper.ApplicationHelper;
import ar.edu.uces.progweb2.tp1.helper.CookieHelper;
import ar.edu.uces.progweb2.tp1.model.Jugador;
import ar.edu.uces.progweb2.tp1.validator.FormJugadorValidator;

@SessionAttributes("jugador")
@Controller
public class UsuarioController {

	private JuegoBO juegoBO;
	private FormJugadorValidator formJugadorValidator;
	private CookieHelper helperCookie;
	private ApplicationHelper helperApplication;
	private LocaleResolver localeResolver;
	
	@Autowired
	public void setSessionLocaleResolver(LocaleResolver localeResolver) {
		this.localeResolver = localeResolver;
	}
	
	@Autowired
	public void setHelperApplication(ApplicationHelper helperApplication) {
		this.helperApplication = helperApplication;
	}

	@Autowired
	public void setJuegoBO(JuegoBO juegoBO) {
		this.juegoBO = juegoBO;
	}

	@Autowired
	public void setFormJugadorValidator(
			FormJugadorValidator formJugadorValidator) {
		this.formJugadorValidator = formJugadorValidator;
	}

	@Autowired
	public void setHelperCookie(CookieHelper helperCookie) {
		this.helperCookie = helperCookie;
	}

	@RequestMapping(value = "/inicializarFormJugador")
	public String inicializarFormJugador(ModelMap model) {
		model.addAttribute("formJugador", new FormJugadorDTO());
		return "/jsp/formUsuario.jsp";
	}

	@RequestMapping(value = "/procesarFormUsuario", method = RequestMethod.POST)
	public String procesarFormJugador( ModelMap model, @ModelAttribute("formUsuario") FormJugadorDTO formJugador,
		BindingResult result, @CookieValue(value = "cookieMaquina", required = false) String cookieMaquina,
		HttpServletRequest request, HttpServletResponse response) {
		// validar formulario
		this.formJugadorValidator.validate(formJugador, result);
		if (result.hasErrors()) {
			return "/jsp/formUsuario.jsp";
		}
		// me setea el jugador para comenzar el juego
		Jugador jugador = this.juegoBO.getJugador(formJugador);
		//setLocale
		this.localeResolver.setLocale(request, response, new Locale(jugador.getIdioma()));
		// cookie maquina
		model.addAttribute("cookieMaquina", this.helperCookie.crearCookieMaquina(cookieMaquina, response));
		// cookie jugador
		model.addAttribute("cookieJugador", this.helperCookie.crearCookieJugador(request, response, jugador));
		// creo el scopeApplication
		this.helperApplication.crearScopeApplication(request, jugador);
		// guardo el jugador en session
		model.addAttribute("jugador", jugador);
		model.addAttribute("formJuego", new FormJuegoDTO());
		return "/jsp/formJuego.jsp";
	}

}
