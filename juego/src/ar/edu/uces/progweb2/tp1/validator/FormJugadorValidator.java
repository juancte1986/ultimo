package ar.edu.uces.progweb2.tp1.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import ar.edu.uces.progweb2.tp1.dto.FormJugadorDTO;

@Component
public class FormJugadorValidator implements Validator{

	@Override
	public boolean supports(Class<?> clazz) {
		return FormJugadorDTO.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object arg0, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "nombre", "errors.user.name.empty");
	}
		
	
}
