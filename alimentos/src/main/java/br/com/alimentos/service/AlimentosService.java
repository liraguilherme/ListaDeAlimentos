package br.com.alimentos.service;

import java.util.List;

import org.springframework.stereotype.Service;

import br.com.alimentos.entity.AlimentosEntity;
import br.com.alimentos.exceptions.ResourceNotFoundException;
import br.com.alimentos.repository.AlimentosRepository;

@Service
public class AlimentosService {
	
	private AlimentosRepository repository;

	public AlimentosService(AlimentosRepository repository) {
		this.repository = repository;
		
	}
	
	public AlimentosEntity create(AlimentosEntity entity) {
		AlimentosEntity novoAlimento = repository.save(entity);
		return novoAlimento;
	}
	
	public List<AlimentosEntity> GetAllAlimentos(){
		List<AlimentosEntity> list = repository.findAll();
		return list;
	}
	
	public AlimentosEntity GetById(Long id) {
		return repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
	}
	
	
	public AlimentosEntity update(AlimentosEntity entity) {
		AlimentosEntity atualizarAlimento = repository.save(entity);
		return atualizarAlimento;
	}
	
	public List<AlimentosEntity> delete (Long id){
		repository.deleteById(id);
		return repository.findAll();
		
	}
	
}
