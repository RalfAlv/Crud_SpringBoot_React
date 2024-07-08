package com.SpringBootCrud.SpringBootCrud;

import com.SpringBootCrud.SpringBootCrud.model.Employee;
import com.SpringBootCrud.SpringBootCrud.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringBootCrudApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootCrudApplication.class, args);
	}

	@Autowired
	private EmployeeRepository employeeRepository;

	@Override
	public void run(String... args) throws Exception {
		/*Employee employee = new Employee();
		employee.setFirstname("Hello");
		employee.setLastname("World");
		employee.setEmailId("HelloWorld@gmail.com");
		employeeRepository.save(employee);

		Employee employee1 = new Employee();
		employee1.setFirstname("perro");
		employee1.setLastname("Gato");
		employee1.setEmailId("HelloWorld@gmail.com");
		employeeRepository.save(employee1);*/
	}
}
