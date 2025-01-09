package hotel.rest.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import hotel.rest.data.HotelData;
import hotel.rest.models.Hotel;


@EntityScan(basePackages = {"hotel.rest.models",
		"hotel.rest.common"})

@EnableJpaRepositories(basePackages = {"hotel.rest.repositories"})

@SpringBootApplication(scanBasePackages = {
		"hotel.rest.data",
		"hotel.rest.exceptions",
		"hotel.rest.controllers",
		})
public class HotelRestTp3Application {

	private Hotel mainHotel = new HotelData().getHotel();
	
	public static void main(String[] args) {
		SpringApplication.run(HotelRestTp3Application.class, args);
		System.out.println("Serveur pret");
	}

	
	
	public HotelRestTp3Application() {
		
	}

	public Hotel getMainHotel() {
		return mainHotel;
	}

	
	
}
