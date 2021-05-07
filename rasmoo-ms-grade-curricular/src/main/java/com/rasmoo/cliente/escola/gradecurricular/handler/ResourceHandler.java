package com.rasmoo.cliente.escola.gradecurricular.handler;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.rasmoo.cliente.escola.gradecurricular.exceptions.MateriaException;
import com.rasmoo.cliente.escola.gradecurricular.model.ErrorMapResponse;
import com.rasmoo.cliente.escola.gradecurricular.model.ErrorMapResponse.ErrorMapResponseBuilder;
import com.rasmoo.cliente.escola.gradecurricular.model.ErrorResponse;
import com.rasmoo.cliente.escola.gradecurricular.model.ErrorResponse.ErrorResponseBuilder;

@ControllerAdvice
public class ResourceHandler {
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ErrorMapResponse> handlerMethodArgumentNotValidException(MethodArgumentNotValidException m) {
		 Map<String, String> erros = new HashMap<>();
		 m.getBindingResult().getAllErrors().forEach(erro -> {
			 String campo = ((FieldError)erro).getField();
			 String mensagem = erro.getDefaultMessage();
			 erros.put(campo, mensagem);
		 });
		 ErrorMapResponseBuilder erroMap = ErrorMapResponse.builder();
		 erroMap.erros(erros).httpStatus(HttpStatus.BAD_REQUEST.value()).timeStamp(System.currentTimeMillis());
		 return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erroMap.build());
	}
	
	@ExceptionHandler(MateriaException.class)
	public ResponseEntity<ErrorResponse> handlerMateriaException(MateriaException m) {
		 ErrorResponseBuilder erro = ErrorResponse.builder();
		 erro.httpStatus(m.getHttpStatus().value());
		 erro.mensagem(m.getMessage());
		 erro.timeStamp(System.currentTimeMillis());
		 return ResponseEntity.status(m.getHttpStatus()).body(erro.build());
	}

}
