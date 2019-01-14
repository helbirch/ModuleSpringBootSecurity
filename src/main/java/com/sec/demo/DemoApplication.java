package com.sec.demo;

import com.sec.demo.entities.AppRole;
import com.sec.demo.service.AccounteService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.stream.Stream;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {

		SpringApplication.run(DemoApplication.class, args);
	}
	@Bean
	CommandLineRunner start(AccounteService accounteService){
		return args -> {
			accounteService.save(new AppRole(null,"USER"));
			accounteService.save(new AppRole(null,"ADMIN"));
			Stream.of("user1","user2","user3","admin").forEach(un->{
				accounteService.saveUser(un,"123","123");
			});
			accounteService.addRoleToUser("admin","ADMIN");
		};
	}
	@Bean
	 BCryptPasswordEncoder getBCPE(){
		return new BCryptPasswordEncoder();
	 }
}

