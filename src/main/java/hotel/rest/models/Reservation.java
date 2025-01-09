package hotel.rest.models;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import hotel.rest.common.MDMethod;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;

@Entity
public class Reservation { //Class associative

	// Attributs
	
	@GeneratedValue
	@Id
	private long numeroReservation;
	@OneToOne (cascade = CascadeType.ALL)
	@JoinColumn(name = "personne_id")
	private Personne client;
	
	@OneToOne (cascade = CascadeType.ALL)
	@JoinColumn(name = "chambre_id")
	private Chambre chambreReservee;
	
	@ManyToOne (cascade = CascadeType.ALL)
	@JoinColumn(name = "hotel_id")
	private Hotel hotelReservee;
	private LocalDate dateEntree;
	private LocalDate dateSortie;
	
	private List<LocalDate> dateReservee = new ArrayList<LocalDate>();
	
	// Constructeurs
	public Reservation(Personne client, Chambre chambreReservee) {
		super();
		this.client = client;
		this.chambreReservee = chambreReservee;
	}
	public Reservation(Personne client, Hotel hotelReservee, LocalDate dateEntree, LocalDate dateSortie) {
		this.client = client;
		this.hotelReservee = hotelReservee;
		this.dateEntree = dateEntree;
		this.dateSortie = dateSortie;
		this.dateReservee = setArrayDateReservee();
		this.chambreReservee = this.hotelReservee.getChambreDisponible(this);
		this.hotelReservee.getChambreDisponible(this).setReservation(this);
		this.setDateReservee();
	}
	
	public Reservation(Personne client, Chambre chambreReservee, LocalDate dateEntree, LocalDate dateSortie) {
		this.client = client;
		this.chambreReservee = chambreReservee;
		this.hotelReservee = chambreReservee.getHotel();
		this.dateEntree = dateEntree;
		this.dateSortie = dateSortie;
		this.dateReservee = setArrayDateReservee();
		this.setDateReservee();
	}

	public Reservation(LocalDate dateEntree, LocalDate dateSortie) {

		this.dateEntree = dateEntree;
		this.dateSortie = dateSortie;
		this.dateReservee = setArrayDateReservee();
	}
	public Reservation(Personne client, Chambre chambreReservee, List<LocalDate> dateReservee) {
		super();
		this.client = client;
		this.chambreReservee = chambreReservee;
		this.dateReservee = dateReservee;
	}

	public Reservation(Chambre chambreReservee, List<LocalDate> dateReservee) {
		super();
		this.chambreReservee = chambreReservee;
		this.dateReservee = dateReservee;
	}

	public Reservation(Chambre chambreReservee) {
		super();
		this.chambreReservee = chambreReservee;
	}
	public Reservation() {
		super();
	}
	// Accesseurs

	public Personne getClient() {
		return client;
	}

	public void setClient(Personne client) {
		this.client = client;
	}

	public Chambre getChambreReservee() {
		return this.chambreReservee;
	}
	public ArrayList<LocalDate> setArrayDateReservee() {
		LocalDate dateEntree = this.dateEntree;
		LocalDate dateTmp = dateEntree;
		LocalDate dateSortie = this.dateSortie;
		ArrayList<LocalDate> arrayDateReservee = new ArrayList<>();
		while (!dateTmp.equals(dateSortie)) {
			arrayDateReservee.add(dateTmp);
			dateTmp = dateTmp.plusDays(1);
		}
		return arrayDateReservee;
	}
	public List<LocalDate> getArrayDateReservee(){
		return this.dateReservee;
	}

	
	public Hotel getHotelReservee() {
		return hotelReservee;
	}
	public void setHotelReservee(Hotel hotelReservee) {
		this.hotelReservee = hotelReservee;
	}
	public LocalDate getDateEntree() {
		return dateEntree;
	}
	public void setDateEntree(LocalDate dateEntree) {
		this.dateEntree = dateEntree;
	}
	public LocalDate getDateSortie() {
		return dateSortie;
	}
	public void setDateSortie(LocalDate dateSortie) {
		this.dateSortie = dateSortie;
	}
	public List<LocalDate> getDateReservee() {
		return dateReservee;
	}
	public void setDateReservee(ArrayList<LocalDate> dateReservee) {
		this.dateReservee = dateReservee;
	}
	public void setChambreReservee(Chambre chambreReservee) {
		this.chambreReservee = chambreReservee;
	}
	public void setDateReservee(LocalDate dateReservee) {
		List <LocalDate> dicoDispo = this.chambreReservee.getDateReservee();
		if(!this.chambreReservee.getDateReservee().contains(dateReservee)){
			this.chambreReservee.getDateReservee().add(dateReservee);
		}
		return ;
	}
	public void setDateReservee() {
		List <LocalDate> dicoDispo = this.chambreReservee.getDateReservee();
		for (int i = 0; i<this.dateReservee.size(); i++) {
			if(!dicoDispo.contains(this.getArrayDateReservee().get(i))){
				this.getChambreReservee().getDateReservee().add(getArrayDateReservee().get(i));
			}
		}
		return ;
	}
	@Override
	public String toString() {
		return "Reservation [client=" + client + ", chambreReservee=" + chambreReservee + ", hotelReservee="
				+ hotelReservee + ", dateReservee=" + dateReservee + "]";
	}
	public double coutReservation() {
		return this.getChambreReservee().getPrix()*(this.getArrayDateReservee().size()+1);
	}
	public String afficherConfirmation() {
		return "Vous avez bien réservée à l'hotel \""+this.getHotelReservee().getNom()+
				"\" au "+this.getHotelReservee().getAdresse().toString()+
				" du "+MDMethod.dateToFrenchString(this.getDateEntree())+" jusqu'au "+MDMethod.dateToFrenchString(this.getDateSortie())
				+" au prix de "+this.coutReservation();
	}
	
	
	
	
	
	
}
