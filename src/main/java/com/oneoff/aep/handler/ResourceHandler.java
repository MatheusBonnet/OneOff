package com.oneoff.aep.handler;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.oneoff.aep.exception.PontoException;
import com.oneoff.aep.exception.UserException;
import com.oneoff.aep.model.Response;

@ControllerAdvice
public class ResourceHandler {

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Response<Map<String, String>>> handlerMethodArgumentNotValidException(MethodArgumentNotValidException m) {

		Map<String, String> erros = new HashMap<>();
		m.getBindingResult().getAllErrors().forEach(erro -> {
			String campo = ((FieldError) erro).getField();
			String mensagem = erro.getDefaultMessage();
			erros.put(campo, mensagem);
		});
	
		Response<Map<String, String>> response = new Response<>();
		response.setStatusCode(HttpStatus.BAD_REQUEST.value());
		response.setData(erros);

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);

	}

	@ExceptionHandler(PontoException.class)
	public ResponseEntity<Response<String>> handlerDoacaoException(PontoException pontoException) {
		Response<String> response = new Response<String>();
		response.setStatusCode(pontoException.getHttpStatus().value());
		response.setData(pontoException.getMessage());
		return ResponseEntity.status(pontoException.getHttpStatus()).body(response);
	}
	
	@ExceptionHandler(UserException.class)
	public ResponseEntity<Response<String>> handlerDoadorException(UserException user) {
		Response<String> response = new Response<String>();
		response.setStatusCode(user.getHttpStatus().value());
		response.setData(user.getMessage());
		return ResponseEntity.status(user.getHttpStatus()).body(response);
	}
}
