package org.generation.italy.demo.apicontroller;

import java.util.List;

import org.generation.italy.demo.pojo.Pizza;
import org.generation.italy.demo.service.PizzaServ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/1/pizza")
@CrossOrigin("*")
public class PizzaApiController {

	@Autowired
	private PizzaServ pr;
	
	@GetMapping("/all")
	public List<Pizza> getAllPizzas() {
		
		List<Pizza> pizzas = pr.findAllWithIngredients();
		
		System.err.println("TEST-------------------------");
		for (Pizza pizza : pizzas) {
			System.err.println(pizza.getIngredients());
		}		
		return pizzas;
	}
}
