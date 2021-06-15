package com.neoris.tcl;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;

@SpringBootApplication
@EnableEncryptableProperties
public class DataTransferApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(DataTransferApplication.class, args);
	}

}
