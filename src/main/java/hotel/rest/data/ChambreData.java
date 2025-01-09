package hotel.rest.data;

import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import hotel.rest.common.Hotel;
import hotel.rest.common.TypeChambre;
import hotel.rest.models.Chambre;
import hotel.rest.repositories.ChambreRepository;

@Configuration
public class ChambreData {

	private org.slf4j.Logger logger = LoggerFactory.getLogger(ChambreData.class);
	private Hotel hotel = new Hotel();
	
	public void initHotel() {
		
	}
	
	@Bean
	public CommandLineRunner initDatabase(ChambreRepository repository) {
		return args -> {
			logger.info("Préchargement de la base de donnée avec"+ repository.save(
					new Chambre(TypeChambre.SUITE)));
			logger.info("Préchargement de la base de donnée avec"+ repository.save(
					new Chambre(TypeChambre.SIMPLE)));
		};
	}
}
