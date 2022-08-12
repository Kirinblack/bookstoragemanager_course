package com.lguilherme.bookstoragemanager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class BookstoragemanagerApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookstoragemanagerApplication.class, args);
	}
    @GetMapping("/")
    public String index(){
		return "Olá Mundo" ;
	}
}
