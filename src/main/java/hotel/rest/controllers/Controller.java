package hotel.rest.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hotel.rest.exception.ChambreNonDisponibleException;
import hotel.rest.exception.ReservationFailedException;
import hotel.rest.models.Chambre;
import hotel.rest.repositories.ChambreRepository;

@RestController
@RequestMapping("/Hotel")
public class Controller {

	@Autowired
	private ChambreRepository ChambreRepository;
	
	@GetMapping("/chambre")
	public List<Chambre> getAllChambre(){
		return ChambreRepository.findAll();
	}
	
	/*@PostMapping
	public void setReservation(String dateEntree, String dateSortie, String typeDeChambre) throws ReservationFailedException, ChambreNonDisponibleException {
		
	} */
	
	/*public String afficherHotel();
	
	public String listeChambreDisponibleToString(String strDateEntree, String strDateSortie);
		
	public String afficherNomHotel();
		
	public List<TypeChambre> listeTypeChambre();
	
	
	
	public void setReservationAuth(Personne clientAuth) {
	} */
}
