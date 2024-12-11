package br.com.livros.entidade;

import br.com.livros.dto.LivroDTO;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tb_livros")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Livro {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String titulo;
	private String autor;
	private Integer anoPublicacao;
	
	public Livro(LivroDTO livroDTO) {
		this.id = livroDTO.id();
		this.titulo = livroDTO.titulo();
		this.autor = livroDTO.autor();
		this.anoPublicacao = livroDTO.anoPublicacao();
	}

}
