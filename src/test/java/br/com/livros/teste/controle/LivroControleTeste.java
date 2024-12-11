package br.com.livros.teste.controle;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import br.com.livros.controle.LivroControle;
import br.com.livros.dto.LivroDTO;
import br.com.livros.entidade.Livro;
import br.com.livros.servico.LivroServico;

@SpringBootTest
public class LivroControleTeste {
	
	private Livro livro;
	
	private LivroDTO livroDTO;
	
	@Mock
	private LivroServico livroServico;
	
	@InjectMocks
	private LivroControle livroControle;
	
	@BeforeEach
	void config() {
		MockitoAnnotations.openMocks(this);
		iniciar();
	}	
	
	
	private void iniciar() {
		livro = new Livro(1L,"Código limpo","Robert Cecil Martin",2024);
		livroDTO = new LivroDTO(1L,"Código limpo","Robert Cecil Martin",2024);
		
		
	}
	
	@Nested
	class TesteNoMetodoCadastrarLivro{
		@Test
		@DisplayName("Sucesso ao cadastrar livro")
		void sucessoAoCadstrarLivro() {			
			when(livroServico.cadastrarLivro(livroDTO)).thenReturn(livro);
			ResponseEntity<LivroDTO>resposta = livroControle.cadastroDeLivros(livroDTO);
			assertNotNull(resposta);
			assertEquals(ResponseEntity.class, resposta.getClass());
			assertEquals(HttpStatus.CREATED, resposta.getStatusCode());
			assertEquals(1L, resposta.getBody().id());
			assertEquals("Código limpo", resposta.getBody().titulo());
			assertEquals("Robert Cecil Martin", resposta.getBody().autor());
			assertEquals(2024, resposta.getBody().anoPublicacao());
			
		}
		
	}
	
	@Nested
	class TesteNoMetodoBuscarPorId {
		
		@Test
		@DisplayName("Sucesso ao buscar por id")
		void sucessoAoBuscarPorId(){
			when(livroServico.buscarPorId(anyLong())).thenReturn(livro);
			ResponseEntity<LivroDTO>resposta = livroControle.buscarPorId(1L);
			assertNotNull(resposta);
			assertEquals(ResponseEntity.class, resposta.getClass());
			assertEquals(HttpStatus.CREATED, resposta.getStatusCode());
			assertEquals(1L, resposta.getBody().id());
			assertEquals("Código limpo", resposta.getBody().titulo());
			assertEquals("Robert Cecil Martin", resposta.getBody().autor());
			assertEquals(2024, resposta.getBody().anoPublicacao());
		}
	}

	@Nested
	class TesteNoMetodoAtualizar{
		@Test
		@DisplayName("Sucesso ao cadastrar livro")
		void sucessoAoAtualizarLivro() {			
			when(livroServico.AtualizarLivro(livroDTO)).thenReturn(livro);
			ResponseEntity<LivroDTO>resposta = livroControle.atualizarLivro(livroDTO);
			assertNotNull(resposta);
			assertEquals(ResponseEntity.class, resposta.getClass());
			assertEquals(HttpStatus.OK, resposta.getStatusCode());
			assertEquals(1L, resposta.getBody().id());
			assertEquals("Código limpo", resposta.getBody().titulo());
			assertEquals("Robert Cecil Martin", resposta.getBody().autor());
			assertEquals(2024, resposta.getBody().anoPublicacao());
			
		}
		
	}
	
	@Nested
	class TesteNoMetodoListarTodos {
		
		@Test
		@DisplayName("Sucesso ao listar Todos os livros")
		void sucessoAoListarLivros() {
			when(livroServico.listarLivros()).thenReturn(List.of(livro));
			ResponseEntity<List<LivroDTO>>resposta = livroControle.listarLivros();
			assertEquals(HttpStatus.OK, resposta.getStatusCode());
			assertEquals(1L,resposta.getBody().get(0).id());
			assertEquals("Código limpo", resposta.getBody().get(0).titulo());
			assertEquals("Robert Cecil Martin", resposta.getBody().get(0).autor());
			assertEquals(2024, resposta.getBody().get(0).anoPublicacao());
		}
	}
	
	@Nested
	class TesteNoMetodoExcluir {
		
	 @Test
	 @DisplayName("Sucesso ao excluir livro")
	 void sucessoAoExcluirLivro() {
		 doNothing().when(livroServico).excluirLivro(anyLong());
		 ResponseEntity<Void>resposta = livroControle.excluirLivro(1L);
		 assertNotNull(resposta);
		 verify(livroServico,times(1)).excluirLivro(anyLong());
		 assertEquals(HttpStatus.NO_CONTENT, resposta.getStatusCode());
	 }
	}
}
