package org.generation.italy.demo;

import org.generation.italy.demo.pojo.Pizza;
import org.generation.italy.demo.repo.PizzaRepo;
import org.generation.italy.demo.service.PizzaServ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringLaMiaPizzeriaWebapiApplication implements CommandLineRunner {

	@Autowired
	private PizzaServ pr;
	
	public static void main(String[] args) {
		SpringApplication.run(SpringLaMiaPizzeriaWebapiApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		Pizza p1 = new Pizza("Margherita", "margheritozza", 2);
		Pizza p2 = new Pizza("Diavola", "diavolazza", 3);
		Pizza p3 = new Pizza("Quattro Stagioni", "four season", 4);
		
		pr.save(p1);
		pr.save(p2);
		pr.save(p3);
		
	}

}
