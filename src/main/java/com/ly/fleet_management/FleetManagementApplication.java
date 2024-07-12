package com.ly.fleet_management;

import com.ly.fleet_management.entity.Role;
import com.ly.fleet_management.repository.RoleRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableScheduling
@EnableCaching
public class FleetManagementApplication {
	public static void main(String[] args) {
		SpringApplication.run(FleetManagementApplication.class, args);
	}
	@Bean
	CommandLineRunner run(RoleRepository roleRepository) {
		return args -> {
			if (roleRepository.findByName("ROLE_USER").isEmpty()) {
				Role roleUser = new Role();
				roleUser.setName("ROLE_USER");
				roleRepository.save(roleUser);
			}

			if (roleRepository.findByName("ROLE_ADMIN").isEmpty()) {
				Role roleAdmin = new Role();
				roleAdmin.setName("ROLE_ADMIN");
				roleRepository.save(roleAdmin);
			}
		};
	}
}