package hotel.rest.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import hotel.rest.models.Chambre;
import hotel.rest.repositories.ChambreRepository;

public class Controller {

	@Autowired
	private ChambreRepository repository;
	
	public List<Chambre> getAllChambre(){
		
		List<Chambre> chbr = new ArrayList<>(); 
		return chbr;
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
