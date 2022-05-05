package com.generation.explosiongames.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.generation.explosiongames.model.ProdutoModel;

@Repository
public interface ProdutoRepository extends JpaRepository<ProdutoModel,Long> {
	public List<ProdutoModel> findAllByNomeContainingIgnoreCase (String nome);

}
