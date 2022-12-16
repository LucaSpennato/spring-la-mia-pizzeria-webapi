package org.generation.italy.demo;

import java.util.HashSet;
import java.util.Set;

import org.generation.italy.demo.pojo.Ingredient;
import org.generation.italy.demo.pojo.Pizza;
import org.generation.italy.demo.repo.PizzaRepo;
import org.generation.italy.demo.service.IngredientService;
import org.generation.italy.demo.service.PizzaServ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringLaMiaPizzeriaWebapiApplication implements CommandLineRunner {

	@Autowired
	private PizzaServ pr;
	
	@Autowired
	private IngredientService is;
	
	public static void main(String[] args) {
		SpringApplication.run(SpringLaMiaPizzeriaWebapiApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		
		Ingredient i1 = new Ingredient("Olive");
		Ingredient i2 = new Ingredient("Mozzarella");
		Ingredient i3 = new Ingredient("Salame");
		
		is.save(i1);
		is.save(i2);
		is.save(i3);
		
		Pizza p1 = new Pizza("Margherita", "margheritozza", 2);
		Pizza p2 = new Pizza("Diavola", "diavolazza", 3);
		Pizza p3 = new Pizza("Quattro Stagioni", "four season", 4);
		
		pr.save(p1);
		pr.save(p2);
		pr.save(p3);
		
		Set<Ingredient> ingredients1 = new HashSet<>();
		ingredients1.add(i1);
		ingredients1.add(i2);
		
		Pizza pizz1 = pr.findById(1).get();
		
		pizz1.setIngredients(ingredients1);
		pr.save(pizz1);
		
	}

}
