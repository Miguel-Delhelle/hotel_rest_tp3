package hotel.rest.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hotel.rest.models.Chambre;
import hotel.rest.repositories.ChambreRepository;

@RestController
@RequestMapping("/allChambre")
public class Controller {

	@Autowired
	private ChambreRepository repository;
	
	@GetMapping
	public List<Chambre> getAllChambre(){
		return repository.findAll();
	}
	
	/*public String afficherHotel();
	
	public String listeChambreDisponibleToString(String strDateEntree, String strDateSortie);
		
	public String afficherNomHotel();
		
	public List<TypeChambre> listeTypeChambre();
		
	public void setReservation(String dateEntree, String dateSortie, String typeDeChambre) throws ReservationFailedException, ChambreNonDisponibleException {
	}
	
	public void setReservationAuth(Personne clientAuth) {
	} */
}
