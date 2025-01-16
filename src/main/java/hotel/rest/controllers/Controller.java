package hotel.rest.controllers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hotel.rest.data.request.ReservationRequest;
import hotel.rest.exception.ChambreNonDisponibleException;
import hotel.rest.models.Adresse;
import hotel.rest.models.Chambre;
import hotel.rest.models.Hotel;
import hotel.rest.models.Personne;
import hotel.rest.models.Reservation;
import hotel.rest.models.TypeChambre;
import hotel.rest.repositories.AdresseRepository;
import hotel.rest.repositories.ChambreRepository;
import hotel.rest.repositories.HotelRepository;
import hotel.rest.repositories.PersonneRepository;
import hotel.rest.repositories.ReservationRepository;

@RestController
@RequestMapping("/Hotel")
public class Controller {

	@Autowired
	private ChambreRepository ChambreRepository;
	@Autowired
	private HotelRepository hotelRepository;
	@Autowired
	private AdresseRepository adresseRepository;
	@Autowired
	private PersonneRepository personneRepository;
	@Autowired
	private ReservationRepository reservationRepository;

	
	@GetMapping("/chambre")
	public ResponseEntity<List<Chambre>> getAllChambre(){
		return ResponseEntity.ok(ChambreRepository.findAll());
	}
	
	@GetMapping("/hotel")
	public ResponseEntity<Hotel> getHotel(){
		Optional<Hotel> OptionnalHotel = hotelRepository.findById((long)0);
		Hotel hotel = OptionnalHotel.get();
		
		return ResponseEntity.ok(hotel);
	}
	
	@PostMapping("/reservation")
	public ResponseEntity<String> setReservation(@RequestBody ReservationRequest requete) {
		try {
		Optional<Hotel> OptionnalHotel = hotelRepository.findById((long)0);
		Hotel hotel = OptionnalHotel.get();
		
		TypeChambre typeDeChambre = TypeChambre.valueOf(requete.getTypeDeChambre());
		System.err.println(typeDeChambre.toString());
		LocalDate dateEntree = requete.getDateEntree();
		LocalDate dateSortie = requete.getDateSortie();
		Personne client = new Personne();
		//Chambre chambreReservee = hotel.getChambreDisponible(dateEntree, dateSortie, typeDeChambre);
		Reservation reservation;
		
			reservation = new Reservation(client,hotel.getChambreDisponible(dateEntree, dateSortie, typeDeChambre),dateEntree,dateSortie);
			reservationRepository.save(reservation);
			System.out.println(reservation.toString());
			System.out.println(reservation.afficherConfirmation()); 
			
	        return ResponseEntity.ok("Reservation effectué: " + reservation.afficherConfirmation());
		} catch (ChambreNonDisponibleException e) {
			// TODO Auto-generated catch block
	        return ResponseEntity.status(HttpStatus.CONFLICT).body("Cette chambre n'est plus disponible");
		}
	}
	
	
	@GetMapping("/nameHotel")
	public ResponseEntity<String> afficherHotel() {
		Optional<Hotel> OptionnalHotel = hotelRepository.findById((long)0);
		Hotel hotel = OptionnalHotel.get();
		return ResponseEntity.ok(hotel.getNom());
	}
	
	@GetMapping("/adress")
	public ResponseEntity<Adresse> adresseHotel() {
		Optional<Hotel> OptionnalHotel = hotelRepository.findById((long)0);
		Hotel hotel = OptionnalHotel.get();
		return ResponseEntity.ok(hotel.getAdresse());
	}
			
	@GetMapping("/chambre/type")
	public ResponseEntity<List<TypeChambre>> listeTypeChambre(){
		List<Chambre> listeChambre = ChambreRepository.findAll();
		List<TypeChambre> listeTypeChambre = new ArrayList<TypeChambre>();
		for (Chambre chambreTmp : listeChambre) {
			if (!listeTypeChambre.contains(chambreTmp.getTypeChambre())) {
				listeTypeChambre.add(chambreTmp.getTypeChambre());
			}
		}
		return ResponseEntity.ok(listeTypeChambre);
	}
	
	@GetMapping("/presentation")
    public ResponseEntity<Resource> getVueSurTourEiffel() throws IOException {

	String pathStr = "src/main/resources/image/hotelParis.jpeg";
    Path path = Paths.get(pathStr);
    Resource ressource = new UrlResource(path.toUri());
    String contentType = Files.probeContentType(path);
    	
    return ResponseEntity.ok().contentType(MediaType.parseMediaType(contentType)).body(ressource);
    }


}