package br.com.livros.dto;

import org.springframework.http.HttpStatus;

public record MensagemDeErro(HttpStatus status, String mensagem) {

}
