package hotel.rest.controllers;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hotel.rest.data.request.ReservationRequest;
import hotel.rest.exception.ChambreNonDisponibleException;
import hotel.rest.exception.ReservationFailedException;
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
	public List<Chambre> getAllChambre(){
		return ChambreRepository.findAll();
	}
	
	@GetMapping("/hotel")
	public Hotel getHotel(){
		Optional<Hotel> OptionnalHotel = hotelRepository.findById((long)0);
		Hotel hotel = OptionnalHotel.get();
		return hotel;
	}
	
	@PostMapping("/reservation")
	public ResponseEntity<String> setReservation( @RequestBody ReservationRequest requete) 
					throws ReservationFailedException, ChambreNonDisponibleException {
		Optional<Hotel> OptionnalHotel = hotelRepository.findById((long)0);
		Hotel hotel = OptionnalHotel.get();

		TypeChambre typeDeChambre = requete.getTypeDeChambre();
		System.err.println(typeDeChambre.toString());
		LocalDate dateEntree = requete.getDateEntree();
		LocalDate dateSortie = requete.getDateSortie();
		Personne client = new Personne();
		//Chambre chambreReservee = hotel.getChambreDisponible(dateEntree, dateSortie, typeDeChambre);
		Reservation reservation = new Reservation(client,hotel.getChambreDisponible(dateEntree, dateSortie, typeDeChambre),dateEntree,dateSortie);
		reservationRepository.save(reservation);
		System.out.println(reservation.toString());
		System.out.println(reservation.afficherConfirmation()); 
		
        return ResponseEntity.ok("Reservation effectué: " + reservation.afficherConfirmation());
		
		
	
	}
	
	/*public String afficherHotel();
	
	public String listeChambreDisponibleToString(String strDateEntree, String strDateSortie);
		
	public String afficherNomHotel();
		
	public List<TypeChambre> listeTypeChambre();
	
	
	
	public void setReservationAuth(Personne clientAuth) {
	} */
}
