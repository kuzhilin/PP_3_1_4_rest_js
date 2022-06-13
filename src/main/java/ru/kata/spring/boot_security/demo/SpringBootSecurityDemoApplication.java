package ru.kata.spring.boot_security.demo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.repository.RoleRepository;
import ru.kata.spring.boot_security.demo.repository.UserRepository;

import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class SpringBootSecurityDemoApplication implements CommandLineRunner {

	private final RoleRepository roleRepository;
	private final UserRepository userRepository;

	public SpringBootSecurityDemoApplication(RoleRepository roleRepository, UserRepository userRepository) {
		this.roleRepository = roleRepository;
		this.userRepository = userRepository;
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringBootSecurityDemoApplication.class, args);
	}


	@Override
	public void run(String... args) throws Exception {

		Role adminRole = new Role("ADMIN");
		Role userRole = new Role("USER");
		roleRepository.save(adminRole);
		roleRepository.save(userRole);

		Set<Role> adminRoles = new HashSet<>();
		adminRoles.add(adminRole);
		adminRoles.add(userRole);
		Set<Role> userRoles = new HashSet<>();
		userRoles.add(userRole);

		User admin = new User("admin","admin",25,"admin@gmail.com","admin",adminRoles);
		User user = new User("user","user",27,"user@gmail.com","user",userRoles);
		userRepository.save(admin);
		userRepository.save(user);
	}
}
