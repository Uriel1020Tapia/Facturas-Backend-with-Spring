package com.bolsadeideas.springboot.backend.apirest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.bolsadeideas.springboot.backend.apirest.models.services.UsuarioService;

@SpringBootApplication
public class SpringBootBackendApirestApplication implements CommandLineRunner{
	
	private Logger logger = LoggerFactory.getLogger(SpringBootBackendApirestApplication.class);

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	public static void main(String[] args) {
		SpringApplication.run(SpringBootBackendApirestApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		String password = "12345";
		
		for(int i = 0; i < 4; i++) {
			String passwordBcrypt = passwordEncoder.encode(password);
			System.out.println("###############AQUI LAS PASSWORD#################  " + passwordBcrypt);
			logger.info("PASSWORD'S ENCRIPTADAS " + passwordBcrypt);
		}
	}

}

