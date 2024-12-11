package br.com.livros.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.livros.entidade.Livro;

public interface LivroRepositorio extends JpaRepository<Livro, Long> {

}
