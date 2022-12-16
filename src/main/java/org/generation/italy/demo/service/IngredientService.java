package org.generation.italy.demo.service;

import java.util.List;
import java.util.Optional;

import org.generation.italy.demo.pojo.Ingredient;
import org.generation.italy.demo.repo.IngredientRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IngredientService {

	@Autowired
	private IngredientRepo ir;
	
	public List<Ingredient> findAll() {
		return ir.findAll();
	}
	
	public void save(Ingredient i) {
		ir.save(i);
	}
	public void delete(Ingredient i) {
		ir.delete(i);
	}
	
	public Optional<Ingredient> findById(int id){
		return ir.findById(id);
	}
}
