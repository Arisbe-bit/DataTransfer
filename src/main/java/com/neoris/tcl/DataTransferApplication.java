package com.neoris.tcl;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DataTransferApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(DataTransferApplication.class, args);
//		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
//		String password[] = { "Neoris2021", "Cemex2021", "Mor#7310" };
//		for (int i = 0; i < password.length; i++)
//			System.out.println( String.format("%s = %s", password[i], passwordEncoder.encode(password[i])) );
	}

}
