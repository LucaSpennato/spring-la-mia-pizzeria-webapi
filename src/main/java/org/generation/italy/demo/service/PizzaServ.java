package org.generation.italy.demo.service;

import java.util.List;
import java.util.Optional;

import org.generation.italy.demo.pojo.Pizza;
import org.generation.italy.demo.repo.PizzaRepo;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

@Service
public class PizzaServ {

	@Autowired
	private PizzaRepo pr;
	
	public Pizza save(Pizza p) {
		return pr.save(p);
	}
	
	public void delete(Pizza p) {
		pr.delete(p);
	}
	
	public Optional<Pizza> findById(int id){
		return pr.findById(id);
	}
	
	public List<Pizza> findAll(){
		return pr.findAll();
	}
	
	public List<Pizza> findPizzaByName(String s){
		return pr.findByNameContainingIgnoreCase(s);
	}
	
	@Transactional
	public List<Pizza> findAllWithIngredients(){
		
		List<Pizza> pizzas = pr.findAll();
		
		for (Pizza pizza : pizzas) {
			Hibernate.initialize(pizza.getIngredients());
		}
		
		return pizzas;
	}
}
