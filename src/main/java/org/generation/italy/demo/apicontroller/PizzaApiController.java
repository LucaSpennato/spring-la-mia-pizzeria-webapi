package org.generation.italy.demo.apicontroller;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.generation.italy.demo.pojo.Ingredient;
import org.generation.italy.demo.pojo.Pizza;
import org.generation.italy.demo.service.PizzaServ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/1/pizza")
@CrossOrigin("*")
public class PizzaApiController {

	@Autowired
	private PizzaServ pr;
	
	@GetMapping("/all")
	public List<Pizza> getAllPizzas() {
		
		List<Pizza> pizzas = pr.findAll();
		
		return pizzas;
	}
	
	@GetMapping("/ingredient/{id}")
	public Set<Ingredient> getIngredient(@PathVariable("id")int id) {
		
		Pizza p = pr.findById(id).get();
		
		return p.getIngredients();
	}
	
	@PostMapping("/store")
	public Object[] storePizza(@Valid @RequestBody Pizza pizza) {
		
		Object[] array = new Object[3];
		
		try {
			Pizza p = pr.save(pizza);
			
			array[0] = p;
			array[1] = true;
			array[2] = "success";
			
		} catch (Exception e) {
			array[0] = null;
			array[1] = false;
			array[2] = e.getMessage();
		}
		
		return array;
	}
	
	@PostMapping("/update/{id}")
	public Pizza updatePizza(@PathVariable("id") int id, @Valid @RequestBody Pizza pizza) {
		
		
		//prendiamo gli ingredienti della pizza dal db e lo settiamo nel pizza entrante
		// altrimewnti resetterebbe gli ingredienti in quanto vuoto
		Pizza oldPizza = pr.findById(id).get();
		
		pizza.setIngredients(oldPizza.getIngredients());
		
		Pizza p = pr.save(pizza);
		
		return p;
	}
	
	@GetMapping("/delete/{id}")
	public boolean deletePizza(@PathVariable("id") int id) {
		
		try {
			Pizza p = pr.findById(id).get();
			pr.delete(p);			
		} catch (Exception e) {
			return false;
		}
		
		
		return true;
	}
	
	
	
}
