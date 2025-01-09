package hotel.rest.models;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;

@Entity
public class Chambre {

	// Attributs//
	
	@Id
	@GeneratedValue
	private long id;
	private int nombreLit;
	private double prix;
	private TypeChambre typeChambre;
	
	@ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "hotel_id")
	private Hotel hotel;
	
	private List <LocalDate> dateReservee = new ArrayList <LocalDate>();
	
	@OneToOne
	private Reservation reservation;
	
	// Constructeurs
	
	public Chambre (TypeChambre typeChambre) {
		this.nombreLit = typeChambre.getNombreLit();
		this.prix = typeChambre.getPrix();
		// this.dateReservee = this.setDisponibiliteInitial();
		this.typeChambre = typeChambre;
	}
	
	public Chambre(long numeroChambre, int nombreLit, double prix, Hotel hotel) {
		super();
		this.id = numeroChambre;
		this.nombreLit = nombreLit;
		this.prix = prix;
		this.hotel = hotel;
		// this.dateReservee = this.setDisponibiliteInitial();
		this.typeChambre = TypeChambre.SIMPLE;
	}
	public Chambre(long numeroChambre, int nombreLit, double prix, Hotel hotel, TypeChambre typeChambre) {
		super();
		this.id = numeroChambre;
		this.nombreLit = nombreLit;
		this.prix = prix;
		this.hotel = hotel;
		// this.dateReservee = this.setDisponibiliteInitial();
		this.typeChambre = typeChambre;
	}
	public Chambre(long numeroChambre, int nombreLit, double prix) {
		super();
		this.id = numeroChambre;
		this.nombreLit = nombreLit;
		this.prix = prix;
		// this.dateReservee = this.setDisponibiliteInitial();
	}
	public Chambre(long numeroChambre, Hotel hotel, TypeChambre typeChambre) {
		super();
		this.id = numeroChambre;
		this.nombreLit = typeChambre.getNombreLit();
		this.prix = typeChambre.getPrix();
		this.hotel = hotel;
		// this.dateReservee = this.setDisponibiliteInitial();
		this.typeChambre = typeChambre;
	}
	public Chambre(Hotel hotel, TypeChambre typeChambre) {
		super();
		this.nombreLit = typeChambre.getNombreLit();
		this.prix = typeChambre.getPrix();
		this.hotel = hotel;
		// this.dateReservee = this.setDisponibiliteInitial();
		this.typeChambre = typeChambre;
	}
	public Chambre() {
		super();
		// this.dateReservee = this.setDisponibiliteInitial();
	}
	// Accesseurs //
	
	public long getNumeroChambre() {
		return id;
	}
	public void setNumeroChambre(long numeroChambre) {
		this.id = numeroChambre;
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
	public void setDisponibilite(List<LocalDate> disponibilite) {
		this.dateReservee = disponibilite;
	}
	
	public TypeChambre getTypeChambre() {
		return typeChambre;
	}
	public void setTypeChambre(TypeChambre typeChambre) {
		this.typeChambre = typeChambre;
	}
	
	
	// Méthode
	
	

	public List<LocalDate> getDateReservee() {
		return dateReservee;
	}
	
	/*public HashMap <LocalDate, String> setDisponibiliteInitial() {
		LocalDate auj = LocalDate.now();
		HashMap <LocalDate, String> ListeDisponibilite = new HashMap<LocalDate, String>();
		ListeDisponibilite.put(auj, "Disponible");
		for (int i = 0; i <= 20; i++) { // le calendrier n'ira plus loin qu'un quart d'année 
			ListeDisponibilite.put(auj.plusDays(i), "Disponible");
		}
		return ListeDisponibilite;
	} */
	
	/*public ArrayList<LocalDate> getDateDisponible() {
		ArrayList<LocalDate> dateDisponible = new ArrayList<>();
        List <LocalDate> dicoDisponibilite= this.getDateReservee();
		for (Entry<LocalDate, String> iteDico: dicoDisponibilite.entrySet()) {
			if (iteDico.getValue().equals("Disponible")){
				dateDisponible.add(iteDico.getKey());
			}
		}
		Collections.sort(dateDisponible); // Je voulais au départ faire une fonction de tri et j'ai realisé que c'était clairement pas nécessaire
		return dateDisponible;
	} */
	
	// A IMPLANTER: DEVOIR MODIFIER LES VALEURS DES DATES, POUR AVOIR DES DATES DISPONIBLE OU RESERVEE. (Possiblement modifier le String en boolean). 
	// CREE UN TABLEAU DE DATE DISPONIBLE ORDONEE POUR L'AFFICHAGE.
	/*public String stringDateDisponible() {
		ArrayList<LocalDate> listeDate = this.getDateDisponible();
		String dateDispo ="";
		for (int i = 0; i< listeDate.size(); i++) {
			LocalDate dateTmp = listeDate.get(i);
			dateDispo = dateDispo+MDMethod.dateToFrenchString(dateTmp)+"\n";
		}
		return dateDispo;
	} */
	
	public boolean testDisponible(LocalDate date) {
		if (this.getDateReservee().contains(date)) {
			return false;
		}
		else
		{
			return true;
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