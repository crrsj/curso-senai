package br.com.livros.controle;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.livros.dto.LivroDTO;
import br.com.livros.servico.LivroServico;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;

@RestController
@RequestMapping("api/livros")
public class LivroControle {
	
	@Autowired
	private LivroServico livroServico;
	
	@PostMapping
	@Operation(summary = "Endpoint responsável por cadastrar livros.") 
    @ApiResponse(responseCode = "201",description = " sucesso",content = {
   	@Content(mediaType = "application.json",schema = @Schema(implementation = ResponseEntity.class))
    })   
	public ResponseEntity<LivroDTO>cadastroDeLivros(@RequestBody @Valid LivroDTO livroDTO){
		var cadastrar = livroServico.cadastrarLivro(livroDTO);
		var uri = ServletUriComponentsBuilder.fromCurrentRequest().path("{id}").
		buildAndExpand(cadastrar.getId()).toUri();
		return ResponseEntity.created(uri).body(new LivroDTO(cadastrar));
	}
	
	
	@GetMapping
	@Operation(summary = "Endpoint responsável pela busca de todos os livros.") 
    @ApiResponse(responseCode = "200",description = " sucesso",content = {
   	@Content(mediaType = "application.json",schema = @Schema(implementation = ResponseEntity.class))
    })           
	public ResponseEntity<List<LivroDTO>>listarLivros(){
		var listarLivros = livroServico.listarLivros().stream().map(LivroDTO::new).toList();
		return ResponseEntity.ok().body(listarLivros);
	}
	
	@GetMapping("{id}")
	@Operation(summary = "Endpoint responsável por buscar livro pelo id.") 
    @ApiResponse(responseCode = "200",description = " sucesso",content = {
   	@Content(mediaType = "application.json",schema = @Schema(implementation = ResponseEntity.class))
    })           
	public ResponseEntity<LivroDTO>buscarPorId(@PathVariable Long id){
		var buscar = livroServico.buscarPorId(id);
		return ResponseEntity.ok().body(new LivroDTO(buscar));
	}

	@PutMapping
	@Operation(summary = "Endpoint responsável por atualizar livro.") 
    @ApiResponse(responseCode = "200",description = " sucesso",content = {
   	@Content(mediaType = "application.json",schema = @Schema(implementation = ResponseEntity.class))
    })           
	public ResponseEntity<LivroDTO>atualizarLivro(@RequestBody @Valid LivroDTO livroDTO ){
		var atualizar = livroServico.AtualizarLivro(livroDTO);
		return ResponseEntity.ok().body(new LivroDTO(atualizar));
	}
	
	@DeleteMapping("{id}")
	@Operation(summary = "Endpoint responsável por excluir livro pelo id.") 
    @ApiResponse(responseCode = "204",description = " sucesso",content = {
   	@Content(mediaType = "application.json",schema = @Schema(implementation = ResponseEntity.class))
    })           
	public ResponseEntity<Void>excluirLivro(@PathVariable Long id){
		livroServico.excluirLivro(id);
		return ResponseEntity.noContent().build();
	}
}
