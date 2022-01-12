package silveira.caio.validation.constraint;

import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import silveira.caio.validation.NotEmptyList;

@SuppressWarnings("rawtypes")
public class NotEmptyListValidator implements ConstraintValidator<NotEmptyList, List> {

	@Override
	public boolean isValid(List lista, ConstraintValidatorContext context) {
		
		return lista != null && !lista.isEmpty();
	}

	@Override
	public void initialize(NotEmptyList constraintAnnotation) {
		//Se precisar fazer algo quando disparar a validação
		
	}
	
	

}
