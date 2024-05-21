package br.com.alimentos.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

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
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/alimentos")
@Tag(name = "Alimentos", description = "Endpoints para gerenciamento Alimentos")
public class AlimentosController {
	
	private AlimentosService service;
	
	
	
	public AlimentosController(AlimentosService service) {
		this.service = service;
	}
	
	@PostMapping
	@Operation(summary = "Adicionando um novo Alimento", description = "Adicionando um novo Alimento",
	tags = {"Alimentos"},
	responses = {
			@ApiResponse(description = "Success", responseCode = "200",
					content = @Content(schema = @Schema(implementation = AlimentosEntity.class))),
			
			@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
			@ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
			@ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)

	})
	
	
	public ResponseEntity<AlimentosEntity> create(@RequestBody AlimentosEntity entity){
		return ResponseEntity.status(201).body(service.create(entity));
		
	}
	
	@GetMapping
	@Operation(summary = "Buscar todos Alimentos", description = "Buscar todos Alimentos",
	 tags = {"Alimentos"}, 
	 responses = {
			 @ApiResponse(description = "Success", responseCode = "200", 
					 content = { @Content(
							 mediaType = "application/json",
							 array = @ArraySchema(schema = @Schema(implementation = AlimentosEntity.class)))}),
			 
			 @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
			 @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
			 @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
			 @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
			
	})
	public ResponseEntity<List<AlimentosEntity>> GetAllAlimentos(){
       List<AlimentosEntity> allAlimentos = service.GetAllAlimentos();
       
       allAlimentos.stream().map(repository -> 
       repository.add(linkTo(methodOn(AlimentosController.class).findById(repository.getId()))
    		   .withSelfRel())).toList();
	 
		
		return ResponseEntity.status(200).body(service.GetAllAlimentos());
				}

		
		
	@GetMapping(value = "/{id}")
	@Operation(summary = "Buscar um alimento", description = "Buscar um alimento",
	 tags = {"Alimentos"}, 
	 responses = {
			 @ApiResponse(description = "Success", responseCode = "200", 
					 content = @Content(schema = @Schema(implementation = AlimentosEntity.class))),
			 
			 @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
		     @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
			 @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
			 @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
			 @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)

	 })
	
	public ResponseEntity<?> findById(@PathVariable(value = "id") Long id){
		AlimentosEntity entity = service.GetById(id);
		entity.add(linkTo(methodOn(AlimentosController.class)
				.GetAllAlimentos()).withRel("Retornar para todos os alimentos"));
		
		
		return ResponseEntity.status(200).body(entity);
	}
	
	@PutMapping
	@Operation(summary = "Atualizando o alimento", description = "Atualizando o alimento",
	  tags = {"Alimentos"}, 
	  responses = {
	 		 @ApiResponse(description = "Updated", responseCode = "200", 
	 				 content = @Content(schema = @Schema(implementation = AlimentosEntity.class))),
	 		 
	 	     @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
	 		 @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
	 		 @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
	 		 @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
	  })
	public ResponseEntity<AlimentosEntity> update(@RequestBody AlimentosEntity entity){
	
		return ResponseEntity.status(200).body(service.update(entity));
	}
	
	@DeleteMapping("{id}")
	 @Operation(summary = "Deletar Alimento", description = "Deletar Alimento",
	  tags = {"Alimentos"}, 
	  responses = {
	 		 @ApiResponse(description = "No content", responseCode = "204", content = @Content),
	 		 @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
	 		 @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
	 		 @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
	 		 @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)

	  })
	public ResponseEntity<?> delete(@PathVariable Long id){
		
		service.delete(id);
		return ResponseEntity.status(204).build();
		
	}
	
	

}
