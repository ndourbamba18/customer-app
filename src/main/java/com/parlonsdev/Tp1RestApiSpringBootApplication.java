package com.parlonsdev;

import com.parlonsdev.entities.Customer;
import com.parlonsdev.repository.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class Tp1RestApiSpringBootApplication {

	public static void main(String[] args) {

		SpringApplication.run(Tp1RestApiSpringBootApplication.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner(CustomerRepository customerRepository){
		return args -> {
			customerRepository.save(new Customer("Marie Louis", "marie-louise@test.edu.fr", "+3305450214874", "hjuy21547mloipuy", true, "Perpignant, France"));
			customerRepository.save(new Customer("Aminata Bah", "bah-aminata@test.edu.sn", "+221785412014", "hjuy54785mlpoiuj", false, "Ouakam, Dakar, Senegel"));
			customerRepository.save(new Customer("Salah Aidara", "salah12@test.edu.sn", "+221775412001", "koil12548oilkjuy", false, "Point E, Dakar, Senegel"));
			customerRepository.save(new Customer("Moussa Diop", "moussa-diop@test.edu.sn", "+221774120541", "lopi54128jhkiuyt", true, "Sacre Coeur 3, Dakar, Senegal"));
			customerRepository.save(new Customer("Fatimata Ndiaye", "fatimata01@test.edu.sn", "+221770541247", "ioku12587lkoiuhg", false, "Medina Rue14X10, Dakar, Senegal"));
			customerRepository.save(new Customer("Khadim Diop", "khadim-diop@test.edu.sn", "+221761245104", "serd45217lkjuioh", true, "Thies, Senegal"));
			customerRepository.save(new Customer("Bob Jane", "bob-jane@gmail.com", "+332154021457", "ghyt00125kjnbgty", true, "Marseille, France"));
			customerRepository.save(new Customer("Courra Diouf", "courra01@gmail.com", "+221778541204", "lkuy85421jhknbgt", false, "Kaolack, Senegal"));
			customerRepository.save(new Customer("Maimouna Bah", "maimouna-bah@gmail.com", "+221768541207", "sqdx45210lkoiytf", true, "Diourbel, Senegal"));
			customerRepository.save(new Customer("Sokhna Guisse", "sokhna-guisse01@gmail.com", "+221768410295", "gtre01125jkftres", true, "Yoff, Dakar, Senegal"));
			customerRepository.findAll().forEach(customer -> {
				System.out.println(customer.getName()+", is active : "+customer.getActive());
			});
		};
	}
}
