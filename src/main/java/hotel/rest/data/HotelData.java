package hotel.rest.data;

import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import hotel.rest.models.Adresse;
import hotel.rest.models.Hotel;
import hotel.rest.models.TypeChambre;
import hotel.rest.repositories.HotelRepository;

@Configuration
public class HotelData {

	private org.slf4j.Logger logger = LoggerFactory.getLogger(HotelData.class);
	//private Hotel hotel = initHotel();
	
	public Hotel initHotel() {
		Adresse adresseHotel = new Adresse ("1", "Avenue des champs élysée","75008","Paris","France");
		Hotel  leParisien= new Hotel("Le Parisien", adresseHotel,4);
		leParisien.generateurChambre(4, TypeChambre.SUITE);
		leParisien.generateurChambre(12, TypeChambre.SIMPLE);
		leParisien.generateurChambre(30, TypeChambre.LUXE);
		leParisien.generateurChambre(12, TypeChambre.DOUBLE);
		return leParisien;
	}
	
	@Bean
	public CommandLineRunner initDatabaseHotel(HotelRepository repository) {
		return args -> {
			Hotel leParisien = initHotel();
			logger.info("Préchargement de la base de donnée avec"+ repository.save(
					leParisien));
		};
	}
	
	/*@Bean
	public CommandLineRunner initDatabaseChambre(ChambreRepository repository) {
		return args -> {
			List<Chambre> listeChambre = new ArrayList<Chambre>();
			listeChambre.addAll(generateurChambre(4, TypeChambre.SUITE));
			listeChambre.addAll(generateurChambre(12, TypeChambre.SIMPLE));
			listeChambre.addAll(generateurChambre(30, TypeChambre.LUXE));
			listeChambre.addAll(generateurChambre(12, TypeChambre.DOUBLE)); 
			Hotel leParisien = initHotel();
			logger.info("Préchargement de la base de donnée avec"+ repository.saveAll(listeChambre));
		};
	}
	
	public List<Chambre> generateurChambre(int nbrChambre,TypeChambre typeChambre) {
		ArrayList <Chambre> listeChambre = new ArrayList<>();
		for (int i =0; i < nbrChambre; i++) {
			Chambre tmpChambre = new Chambre(typeChambre);
			listeChambre.add(tmpChambre);
		}
		return listeChambre;
	} */
	
}
