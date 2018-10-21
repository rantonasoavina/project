package com.webmvc.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.webmvc.repo.PersonRepository;

@SpringBootApplication
@ComponentScan({"com.webmvc.controller"})
@EntityScan("com.webmvc.model")
@EnableJpaRepositories("com.webmvc.repo")
public class WebmvcApplication implements CommandLineRunner {
	
//	@RequestMapping("/")
//	@ResponseBody
//	String home() {
//		return "Greetings from Java Tutorial Network";
//	}
	@Autowired
	PersonRepository repository;

	public static void main(String[] args) {
		SpringApplication.run(WebmvcApplication.class, args);
	}
	
	@Override
	public void run(String... arg0) throws Exception {
		// clear all record if existed before do the tutorial with new data 
		repository.deleteAll();
	}
}

