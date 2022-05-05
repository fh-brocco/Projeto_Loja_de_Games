package com.generation.explosiongames.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.generation.explosiongames.model.CategoriaModel;

@Repository // informando para o spring que o CategoriaRepository é um repositorio
public interface CategoriaRepository extends JpaRepository<CategoriaModel, Long>{

	public List<CategoriaModel> findAllByDescricaoContainingIgnoreCase (String descricao);
		// select * tb_categoria where descricao like "%descricao%"

}
