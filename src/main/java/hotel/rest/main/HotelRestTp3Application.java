package hotel.rest.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@EntityScan(basePackages = {"hotel.rest.models",
		"hotel.rest.common"})

@EnableJpaRepositories(basePackages = {"hotel.rest.repositories"})

@SpringBootApplication(scanBasePackages = {
		"hotel.rest.data",
		"hotel.rest.exceptions",
		"hotel.rest.controllers",
		})
public class HotelRestTp3Application {

	
	public static void main(String[] args) {
		SpringApplication.run(HotelRestTp3Application.class, args);
		System.out.println("Serveur pret");
	}

}
