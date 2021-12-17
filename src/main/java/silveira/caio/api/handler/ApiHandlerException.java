package silveira.caio.api.handler;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import silveira.caio.api.handler.exception.ExceptionComum;

@ControllerAdvice
public class ApiHandlerException extends ResponseEntityExceptionHandler {
	
	@Autowired
	private MessageSource msgSource;

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		List<ExceptionComum.Campo> campos = new ArrayList<>();
		
		for(ObjectError ob : ex.getBindingResult().getAllErrors()) {
			String nome = ((FieldError) ob).getField();
			String msg = msgSource.getMessage(ob, LocaleContextHolder.getLocale());
			
			campos.add(new ExceptionComum.Campo(nome, msg));
		}
		
		ExceptionComum ec = new ExceptionComum();
		ec.setStatus(status.value());
		ec.setDataHora(OffsetDateTime.now());
		ec.setTitulo("Campo(s) inválido(s)... Preencha corretamente e tente novamente");
		ec.setCampos(campos);
		
		return super.handleExceptionInternal(ex, ec, headers, status, request);
	}
	
	
}
