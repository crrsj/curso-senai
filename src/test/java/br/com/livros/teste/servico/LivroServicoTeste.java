package br.com.livros.teste.servico;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.livros.dto.LivroDTO;
import br.com.livros.entidade.Livro;
import br.com.livros.repositorio.LivroRepositorio;
import br.com.livros.servico.LivroServico;

@SpringBootTest
public class LivroServicoTeste {

	private Livro livro;
	private Optional<Livro>optionalLivro;
	private LivroDTO livroDTO;
	
	@Mock
	private LivroRepositorio livroRepositorio;
	
	@InjectMocks
	private LivroServico livroServico;
	
	@BeforeEach
	void config() {
		MockitoAnnotations.openMocks(this);
		 iniciar();
	}

	
	
	private void iniciar() {
		livro = new Livro(1L,"Código limpo","Robert Cecil Martin",2024);
		livroDTO = new LivroDTO(1L,"Código limpo","Robert Cecil Martin",2024);
		optionalLivro = Optional.of(new Livro(1L,"Código limpo","Robert Cecil Martin",2024));
		
	}



	@Nested
	class TesteNoMetodoCadastrarLivro{
		
		@Test
		@DisplayName("Quando cadastrar livros retorne sucesso.")
		void quandocadastrarLivrosRetorneSucesso() {
			
			when(livroRepositorio.save(livro)).thenReturn(livro);
			var resposta = livroServico.cadastrarLivro(livroDTO);
			assertNotNull(resposta);
			assertEquals(Livro.class, resposta.getClass());
			assertEquals(1L, resposta.getId());
			assertEquals("Código limpo", resposta.getTitulo());
			assertEquals("Robert Cecil Martin", resposta.getAutor());
			assertEquals(2024, resposta.getAnoPublicacao());

		}
	
		@Test
		@DisplayName("Erro ao cadastrar livro")
	    void erroAoCadastrarLivro() {
		   
			doThrow(new RuntimeException()).when(livroRepositorio).save(livro);
			assertThrows(RuntimeException.class, ()->livroServico.cadastrarLivro(livroDTO));
		   
	   }
		
		@Nested
		class TesteNoMetodoBuscarPorId{
			
			@Test
			@DisplayName("Teste no método buscar por id")
			void quandoBuscarPorIdRetorneSucesso() {
				when(livroRepositorio.findById(anyLong())).thenReturn(optionalLivro);
				var resposta = livroServico.buscarPorId(1L);
				assertNotNull(resposta);
				assertEquals(Livro.class, resposta.getClass());
				assertEquals(1L, resposta.getId());
				assertEquals("Código limpo", resposta.getTitulo());
				assertEquals("Robert Cecil Martin", resposta.getAutor());
				assertEquals(2024, resposta.getAnoPublicacao());
			}
		}
		
		@Test
		@DisplayName("Falha ao buscar por id.")
		void falhaAoBuscarPorId() {
			try {
				when(livroRepositorio.findById(anyLong())).thenThrow(new NoSuchElementException("Objeto não encontrado !"));
			} catch (Exception e) {
				assertEquals(NoSuchElementException.class, e.getClass());
				assertEquals("Objeto não encontrado !", e.getMessage());
			}
		}
		
		@Nested
		class TesteAoAtualizarLivro{
			
			@Test
			@DisplayName("Sucesso ao atualizar livre ")
			void testeNoMetodoAtualizarLivro() {
				
				when(livroRepositorio.save(livro)).thenReturn(livro);
				var resposta = livroServico.AtualizarLivro(livroDTO);
				assertNotNull(resposta);
				assertEquals(Livro.class, resposta.getClass());
				assertEquals(1L, resposta.getId());
				assertEquals("Código limpo", resposta.getTitulo());
				assertEquals("Robert Cecil Martin", resposta.getAutor());
				assertEquals(2024, resposta.getAnoPublicacao());
			}
			
			
			@Test
			@DisplayName("Erro ao atualizar livro")
		    void erroAoAtualizarLivro() {
			   
				doThrow(new RuntimeException()).when(livroRepositorio).save(livro);
				assertThrows(RuntimeException.class, ()->livroServico.AtualizarLivro(livroDTO));			   
			
	     	 }		
			
		 	
		
		}
		
		@Nested
		class TesteNoMetodoExcluirLivro{
			
			@Test
			@DisplayName("Sucesso ao excluir")
			void sucessoAoExcluir() {
				doNothing().when(livroRepositorio).deleteById(anyLong());
				livroServico.excluirLivro(1L);
				verify(livroRepositorio,times(1)).deleteById(1L);
			}
		}
		
		@Nested
	    class ListarTodosOsLivros{
			
			@Test
			@DisplayName("Sucesso ao listar livros")
			void sucessoAoListarLivros() {
				when(livroRepositorio.findAll()).thenReturn(List.of(livro));
				List<Livro>resposta = livroServico.listarLivros();
				assertEquals(1L, resposta.size());
				assertNotNull(resposta);
				assertEquals(Livro.class, resposta.get(0).getClass());
				assertEquals(1L, resposta.get(0).getId());
				assertEquals("Código limpo", resposta.get(0).getTitulo());
				assertEquals("Robert Cecil Martin", resposta.get(0).getAutor());
				assertEquals(2024, resposta.get(0).getAnoPublicacao());
				
				
			}
	    	
	    }
  }
}
