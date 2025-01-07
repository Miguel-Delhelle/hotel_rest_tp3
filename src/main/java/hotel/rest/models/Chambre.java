package hotel.rest.models;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map.Entry;

import hotel.rest.common.MDMethod;
import hotel.rest.common.Reservation;
import hotel.rest.common.TypeChambre;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Chambre {

	// Attributs//
	
	@Id
	@GeneratedValue
	private int numeroChambre;
	private int nombreLit;
	private double prix;
	private TypeChambre typeChambre;
	private Hotel hotel;
	private HashMap <LocalDate, String> disponibilite;
	private Reservation reservation;
	
	// Constructeurs
	
	public Chambre (TypeChambre typeChambre) {
		this.nombreLit = typeChambre.getNombreLit();
		this.prix = typeChambre.getPrix();
		this.disponibilite = this.setDisponibiliteInitial();
		this.typeChambre = typeChambre;
	}
	
	public Chambre(int numeroChambre, int nombreLit, double prix, Hotel hotel) {
		super();
		this.numeroChambre = numeroChambre;
		this.nombreLit = nombreLit;
		this.prix = prix;
		this.hotel = hotel;
		this.disponibilite = this.setDisponibiliteInitial();
		this.typeChambre = TypeChambre.SIMPLE;
	}
	public Chambre(int numeroChambre, int nombreLit, double prix, Hotel hotel, TypeChambre typeChambre) {
		super();
		this.numeroChambre = numeroChambre;
		this.nombreLit = nombreLit;
		this.prix = prix;
		this.hotel = hotel;
		this.disponibilite = this.setDisponibiliteInitial();
		this.typeChambre = typeChambre;
	}
	public Chambre(int numeroChambre, int nombreLit, double prix) {
		super();
		this.numeroChambre = numeroChambre;
		this.nombreLit = nombreLit;
		this.prix = prix;
		this.disponibilite = this.setDisponibiliteInitial();
	}
	public Chambre(int numeroChambre, Hotel hotel, TypeChambre typeChambre) {
		super();
		this.numeroChambre = numeroChambre;
		this.nombreLit = typeChambre.getNombreLit();
		this.prix = typeChambre.getPrix();
		this.hotel = hotel;
		this.disponibilite = this.setDisponibiliteInitial();
		this.typeChambre = typeChambre;
	}
	public Chambre() {
		super();
		this.disponibilite = this.setDisponibiliteInitial();
	}
	// Accesseurs //
	
	public int getNumeroChambre() {
		return numeroChambre;
	}
	public void setNumeroChambre(int numeroChambre) {
		this.numeroChambre = numeroChambre;
	}
	public int getNombreLit() {
		return nombreLit;
	}
	public void setNombreLit(int nombreLit) {
		this.nombreLit = nombreLit;
	}
	public double getPrix() {
		return prix;
	}
	public void setPrix(double prix) {
		this.prix = prix;
	}
	public Hotel getHotel() {
		return hotel;
	}
	public void setHotel(Hotel hotel) {
		this.hotel = hotel;
	}
	
	public Reservation getReservation() {
		return reservation;
	}
	public void setReservation(Reservation reservation) {
		this.reservation = reservation;
	}
	public void setDisponibilite(HashMap<LocalDate, String> disponibilite) {
		this.disponibilite = disponibilite;
	}
	
	public TypeChambre getTypeChambre() {
		return typeChambre;
	}
	public void setTypeChambre(TypeChambre typeChambre) {
		this.typeChambre = typeChambre;
	}
	
	
	// Méthode
	
	

	public HashMap<LocalDate, String> getDisponibilite() {
		return disponibilite;
	}
	public HashMap <LocalDate, String> setDisponibiliteInitial() {
		LocalDate auj = LocalDate.now();
		HashMap <LocalDate, String> ListeDisponibilite = new HashMap<LocalDate, String>();
		ListeDisponibilite.put(auj, "Disponible");
		for (int i = 0; i <= 20; i++) { // le calendrier n'ira plus loin qu'un quart d'année 
			ListeDisponibilite.put(auj.plusDays(i), "Disponible");
		}
		return ListeDisponibilite;
	}
	public ArrayList<LocalDate> getDateDisponible() {
		ArrayList<LocalDate> dateDisponible = new ArrayList<>();
        HashMap <LocalDate, String> dicoDisponibilite= this.getDisponibilite();
		for (Entry<LocalDate, String> iteDico: dicoDisponibilite.entrySet()) {
			if (iteDico.getValue().equals("Disponible")){
				dateDisponible.add(iteDico.getKey());
			}
		}
		Collections.sort(dateDisponible); // Je voulais au départ faire une fonction de tri et j'ai realisé que c'était clairement pas nécessaire
		return dateDisponible;
	}
	// A IMPLANTER: DEVOIR MODIFIER LES VALEURS DES DATES, POUR AVOIR DES DATES DISPONIBLE OU RESERVEE. (Possiblement modifier le String en boolean). 
	// CREE UN TABLEAU DE DATE DISPONIBLE ORDONEE POUR L'AFFICHAGE.
	public String stringDateDisponible() {
		ArrayList<LocalDate> listeDate = this.getDateDisponible();
		String dateDispo ="";
		for (int i = 0; i< listeDate.size(); i++) {
			LocalDate dateTmp = listeDate.get(i);
			dateDispo = dateDispo+MDMethod.dateToFrenchString(dateTmp)+"\n";
		}
		return dateDispo;
	}
	public boolean testDisponible(LocalDate date) {
		if (this.getDisponibilite().get(date).equals("Disponible")) {
			return true;
		}
		else
		{
			return false;
		}
	}
	
	@Override
	public String toString() {
		String str = "Numéro: "+this.getNumeroChambre()+"\nNombre lit: "+this.getNombreLit()+"\nPrix: "+this.getPrix()+"\nType de chambre: "+this.getTypeChambre().toString();
		return str;
	}
	public boolean idEquals(Chambre chambre2) {
		if (this.getNumeroChambre() == chambre2.getNumeroChambre()) {
			return true;
		}
		return false;
	}
	public boolean equals(Chambre chambre2){
		if ((this.getNombreLit() == chambre2.getNombreLit()) && (this.getPrix() == chambre2.getPrix() && (this.getHotel().equals(chambre2.getHotel()))) 
				&& (this.getHotel().equals(chambre2.getHotel()))){
			return true;
		}
		return false;
	}
		
}