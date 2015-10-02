package ar.edu.uces.progweb2.tp1.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import ar.edu.uces.progweb2.tp1.dto.FormJuegoDTO;

@Component
public class FormJuegoValidator implements Validator{

	@Override
	public boolean supports(Class<?> clazz) {
		return FormJuegoDTO.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object obj, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "numero", "errors.juego.number.empty");
		FormJuegoDTO formJuego = (FormJuegoDTO) obj;
		int valor = 0;
		
		if(!errors.hasFieldErrors("numero")){
			try{
				valor = Integer.parseInt(formJuego.getNumero());
			} catch(NumberFormatException e){
				errors.rejectValue("numero", "errors.juego.number.format");
			}
		}
		
		if(!errors.hasFieldErrors("numero")){
			
			if(valor < 1 || valor > 100){
				errors.rejectValue("numero", "errors.juego.number.range");
			}

		}
	}

}
