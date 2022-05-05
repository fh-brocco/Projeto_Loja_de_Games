package com.generation.explosiongames.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.generation.explosiongames.model.ProdutoModel;
import com.generation.explosiongames.repository.ProdutoRepository;

@RestController
@RequestMapping("/produtos")
@CrossOrigin("*")
public class ProdutoController {

	@Autowired
	private ProdutoRepository repository;
	
	@GetMapping 
	public ResponseEntity<List<ProdutoModel>> GetAll (){ 
		return ResponseEntity.ok(repository.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ProdutoModel> GetById (@PathVariable long id){
		return repository.findById(id)
				.map(resp -> ResponseEntity.ok(resp))
				.orElse(ResponseEntity.notFound().build());
		}
	
	@GetMapping("/nome/{nome}") 
	public ResponseEntity<List<ProdutoModel>> GetByCategoria (@PathVariable String nome){ 
		return ResponseEntity.ok(repository.findAllByNomeContainingIgnoreCase(nome)); 
	}
	
	@PostMapping 
	public ResponseEntity<ProdutoModel> Post (@RequestBody ProdutoModel produto){ 
		return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(produto)); 
	}
	
	@PutMapping 
	public ResponseEntity<ProdutoModel> Put (@RequestBody ProdutoModel produto){ 
		return repository.findById(produto.getId())
				.map(resposta -> ResponseEntity.ok().body(repository.save(produto)))
				.orElse(ResponseEntity.notFound().build());
			}
	
	@DeleteMapping("/{id}") 
	public ResponseEntity<?> ProdutoModel(@PathVariable Long id) {
		return repository.findById(id)
		.map(resposta -> {
			repository.deleteById(id);
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		})
		.orElse(ResponseEntity.notFound().build());
	}

}
