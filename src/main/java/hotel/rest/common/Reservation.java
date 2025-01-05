package hotel.rest.common;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

import hotel.rest.models.Hotel;

public class Reservation { //Class associative

	// Attributs
	private Personne client;
	private Chambre chambreReservee;
	private Hotel hotelReservee;
	private LocalDate dateEntree;
	private LocalDate dateSortie;
	private ArrayList<LocalDate> dateReservee = new ArrayList<>();
	
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
	public Reservation(Personne client, Chambre chambreReservee, ArrayList<LocalDate> dateReservee) {
		super();
		this.client = client;
		this.chambreReservee = chambreReservee;
		this.dateReservee = dateReservee;
	}

	public Reservation(Chambre chambreReservee, ArrayList<LocalDate> dateReservee) {
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
	public ArrayList<LocalDate> getArrayDateReservee(){
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
	public ArrayList<LocalDate> getDateReservee() {
		return dateReservee;
	}
	public void setDateReservee(ArrayList<LocalDate> dateReservee) {
		this.dateReservee = dateReservee;
	}
	public void setChambreReservee(Chambre chambreReservee) {
		this.chambreReservee = chambreReservee;
	}
	public void setDateReservee(LocalDate dateReservee) {
		HashMap <LocalDate, String> dicoDispo = this.chambreReservee.getDisponibilite();
		if(dicoDispo.containsKey(dateReservee) && dicoDispo.get(dateReservee).equals("Disponible")){
			this.chambreReservee.getDisponibilite().put(dateReservee, "Reservee");
		}
		return ;
	}
	public void setDateReservee() {
		HashMap <LocalDate, String> dicoDispo = this.chambreReservee.getDisponibilite();
		for (int i = 0; i<this.dateReservee.size(); i++) {
			if(dicoDispo.containsKey(this.getArrayDateReservee().get(i)) && dicoDispo.get(this.getArrayDateReservee().get(i)).equals("Disponible")){
				this.getChambreReservee().getDisponibilite().put(getArrayDateReservee().get(i), "Reservée");
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
