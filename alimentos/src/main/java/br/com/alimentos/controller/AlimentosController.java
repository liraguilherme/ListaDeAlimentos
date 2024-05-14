package br.com.alimentos.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.alimentos.entity.AlimentosEntity;
import br.com.alimentos.service.AlimentosService;

@RestController
@RequestMapping("/alimentos")
public class AlimentosController {
	
	private AlimentosService service;
	
	public AlimentosController(AlimentosService service) {
		this.service = service;
	}
	
	@PostMapping
	public ResponseEntity<AlimentosEntity> create(@RequestBody AlimentosEntity entity){
		return ResponseEntity.status(201).body(service.create(entity));
		
	}
	
	@GetMapping
	public ResponseEntity<List<AlimentosEntity>> GetAllAlimentos(){
		return ResponseEntity.status(200).body(service.GetAllAlimentos());
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<?> findById(@PathVariable(value = "id") Long id){
		AlimentosEntity entity = service.GetById(id);
		
		return ResponseEntity.status(200).body(entity);
	}
	
	@PutMapping
	public ResponseEntity<AlimentosEntity> update(@RequestBody AlimentosEntity entity){
	
		return ResponseEntity.status(200).body(service.update(entity));
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<?> delete(@PathVariable Long id){
		
		service.delete(id);
		return ResponseEntity.status(204).build();
		
	}
	
	

}
