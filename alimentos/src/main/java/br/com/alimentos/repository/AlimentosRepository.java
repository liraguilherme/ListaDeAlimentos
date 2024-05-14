package br.com.alimentos.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.alimentos.entity.AlimentosEntity;

public interface AlimentosRepository extends JpaRepository<AlimentosEntity, Long> {

}