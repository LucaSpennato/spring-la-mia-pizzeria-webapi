package org.generation.italy.demo.service;

import java.util.List;
import java.util.Optional;

import org.generation.italy.demo.pojo.Pizza;
import org.generation.italy.demo.repo.PizzaRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PizzaServ {

	@Autowired
	private PizzaRepo pr;
	
	public void save(Pizza p) {
		pr.save(p);
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
}
