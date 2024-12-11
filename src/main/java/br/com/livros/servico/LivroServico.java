package br.com.livros.servico;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.livros.dto.LivroDTO;
import br.com.livros.entidade.Livro;
import br.com.livros.repositorio.LivroRepositorio;

@Service
public class LivroServico {
	
	@Autowired
	private LivroRepositorio livroRepositorio;
	
	public Livro cadastrarLivro(LivroDTO livroDTO) {
		var cadastrar = new Livro(livroDTO);
		return livroRepositorio.save(cadastrar);
	}

	public List<Livro>listarLivros(){
		return livroRepositorio.findAll();
	}
	
	public Livro buscarPorId(Long id) {
		Optional<Livro>buscar = livroRepositorio.findById(id);
		return buscar.get();
	}
	
	public Livro AtualizarLivro(LivroDTO livroDTO) {
		var atualizar = new Livro(livroDTO);		
		return livroRepositorio.save(atualizar);
	}
	
	public void excluirLivro(Long id) {
		livroRepositorio.deleteById(id);
	}
}
