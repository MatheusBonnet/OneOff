package com.oneoff.aep.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.oneoff.aep.data.PontoDTO;
import com.oneoff.aep.entities.Ponto;
import com.oneoff.aep.model.Response;
import com.oneoff.aep.services.IPontoService;

@RestController
@RequestMapping("/api/v1/ponto")
public class PontoController {

	public static final String UPDATE = "Atualizado com sucesso.";

	public static final String DELETE = "Deletado com sucesso";


	@Autowired
	private IPontoService pontoService;
	

	@GetMapping
	public ResponseEntity<Page<Ponto>> listarPontos(Pageable pageable) {
		Page<Ponto> page = pontoService.listarTodas(pageable);
		return ResponseEntity.ok(page);
		
	}
	
	@GetMapping("/user")
	public ResponseEntity<List<Ponto>> listarPontosById() {
		List<Ponto> page = pontoService.listarByUser();
		return ResponseEntity.ok(page);
		
	}

	@PutMapping("/{id}")
	public ResponseEntity<Response<Ponto>> atualizarPonto(@RequestBody Ponto ponto){
		Response<Ponto> response = new Response<>();
		response.setData(this.pontoService.atualizar(ponto));
		response.setStatusCode(HttpStatus.OK.value());
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}

	@PostMapping
	public ResponseEntity<Response<Ponto>> inserirPonto(@RequestBody PontoDTO ponto) {
		Response<Ponto> response = new Response<>();
		response.setData(this.pontoService.inserirPonto(ponto));
		response.setStatusCode(HttpStatus.CREATED.value());
		response.add(WebMvcLinkBuilder
				.linkTo(WebMvcLinkBuilder.methodOn(PontoController.class).inserirPonto(ponto)).withSelfRel());
		response.add(WebMvcLinkBuilder
				.linkTo(WebMvcLinkBuilder.methodOn(PontoController.class).inserirPonto(ponto)).withRel(UPDATE));
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Response<Ponto>> excluirPonto(@PathVariable Long id) {
		Response<Ponto> response = new Response<>();
		response.setData(this.pontoService.deletar(id));
		response.setStatusCode(HttpStatus.OK.value());
		response.add(WebMvcLinkBuilder
				.linkTo(WebMvcLinkBuilder.methodOn(PontoController.class).excluirPonto(id)).withSelfRel());
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}

}
