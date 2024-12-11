package br.com.livros.infra;

import java.util.NoSuchElementException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import br.com.livros.dto.MensagemDeErro;
import br.com.livros.dto.ValidandoCampos;

@RestControllerAdvice
public class TratamentoDeErros {
	
	@ExceptionHandler(NoSuchElementException.class)
	public ResponseEntity<MensagemDeErro>objetoNaoEncontrado(){
		var erros = new MensagemDeErro(HttpStatus.NOT_FOUND, "Objeto não encontrado !");
		return new ResponseEntity<>(erros,HttpStatus.NOT_FOUND);
	}
	
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<?>validarCampos(MethodArgumentNotValidException ex){
		var erros = ex.getFieldErrors();
		return ResponseEntity.badRequest().body(erros.stream().map(ValidandoCampos::new ).toList());
	}
}
