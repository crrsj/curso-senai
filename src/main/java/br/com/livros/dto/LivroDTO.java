package br.com.livros.dto;

import br.com.livros.entidade.Livro;
import jakarta.validation.constraints.NotBlank;

public record LivroDTO(
		
		Long id,
		@NotBlank(message = "não pode estar em branco !")
		String titulo,
		@NotBlank(message = "não pode estar em branco !")
		String autor,
		Integer anoPublicacao) {

	public LivroDTO(Livro cadastrar) {
	 this(
			 cadastrar.getId(),
			 cadastrar.getTitulo(),
			 cadastrar.getAutor(),
			 cadastrar.getAnoPublicacao());
	}

}
