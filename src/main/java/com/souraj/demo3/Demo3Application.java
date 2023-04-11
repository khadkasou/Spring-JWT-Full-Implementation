package com.souraj.demo3;

import com.souraj.demo3.model.Role;
import com.souraj.demo3.repository.RoleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@RequestMapping("/role")
public class Demo3Application {

	public static void main(String[] args) {
		SpringApplication.run(Demo3Application.class, args);
	}
	@Autowired
	private  RoleRepo roleRepo;
	@GetMapping
	public String addRole(){
		Role role = new Role();
		role.setName("ADMIN");
		roleRepo.save(role);
		Role role2 = new Role();
		role2.setName("USER");
		roleRepo.save(role2);
		return "saved";
	}
}
