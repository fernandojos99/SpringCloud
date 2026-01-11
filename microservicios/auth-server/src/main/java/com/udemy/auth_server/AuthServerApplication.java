package com.udemy.auth_server;

//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
//public class AuthServerApplication  implements CommandLineRunner {
 public class AuthServerApplication   {

//    @Autowired
//    private BCryptPasswordEncoder encoder;

	public static void main(String[] args) {
		SpringApplication.run(AuthServerApplication.class, args);
	}
	
	/*Esto va a hassear la palabra "secret y nos devolvera el hash"*/
//    @Override
//    public void run(String... args) throws Exception {
//        System.out.println("Password: " + this.encoder.encode("secret"));
//    }

}


