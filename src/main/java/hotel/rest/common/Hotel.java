package hotel.rest.common;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import hotel.rest.exception.ChambreNonDisponibleException;
import hotel.rest.exception.ReservationFailedException;
import hotel.rest.models.Chambre;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

public class Hotel{
	
	// Attribut//
	
	@Id
	private long id;
	private String nom;
	@ManyToOne
	private Adresse adresse;
	private int nombreEtoile = 0;
	@OneToMany(mappedBy = "hotel", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List <Chambre> listeChambre = new ArrayList <Chambre>();
	
	// Constructeur
	
	public Hotel(String nom, Adresse adresse, int nombreEtoile, int nombreChambre, ArrayList<Chambre> listeChambre) {
		super();
		this.nom = nom;
		this.adresse = adresse;
		this.nombreEtoile = nombreEtoile;
		this.listeChambre = listeChambre;
	}
	
	
	public Hotel(String nom, Adresse adresse) {
		super();
		this.nom = nom;
		this.adresse = adresse;
	}
	public Hotel(String nom, Adresse adresse, int nbrEtoile) {
		super();
		this.nom = nom;
		this.adresse = adresse;
		this.nombreEtoile = nbrEtoile;
	}

	public Hotel() {
		super();
	}
	

	public Hotel(String nom) {
		super();
		this.nom = nom;
	}


	// Accesseurs//
	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}
	public Adresse getAdresse() {
		return adresse;
	}
	public void setAdresse(Adresse adresse) {
		this.adresse = adresse;
	}
	public int getNombreEtoile() {
		return nombreEtoile;
	}
	public String getNombreEtoileToString() {
		String str ="";
		for (int i=0; i <= this.nombreEtoile; i++) {
			str = str+"★";
		}
		return str;
	}
	public void setNombreEtoile(int nombreEtoile) {
		this.nombreEtoile = nombreEtoile;
	}
	public List<Chambre> getListeChambre() {
		return listeChambre;
	}
	/*public Chambre getChambreDispo(LocalDate date) {
		int i=0;
		while(this.getListeChambre().get(i).testDisponible(date)) {
			this.listeChambre.get(i).
		}
		return ;
	} */
	public void setListeChambre(ArrayList<Chambre> listeChambre) {
		this.listeChambre = listeChambre;
	} 
	
	
	// Méthode
	public void generateurChambre(int nbrChambre, int nbrLit, double prix) {
		ArrayList <Chambre> listeChambre = new ArrayList<>();
		for (int i =0; i < nbrChambre; i++) {
			Chambre tmpChambre = new Chambre(i+1+this.getListeChambre().size(),nbrLit,prix, this);
			listeChambre.add(tmpChambre);
		}
		this.getListeChambre().addAll(listeChambre);
	}
	public void generateurChambre(int nbrChambre,TypeChambre typeChambre) {
		ArrayList <Chambre> listeChambre = new ArrayList<>();
		for (int i =0; i < nbrChambre; i++) {
			Chambre tmpChambre = new Chambre(i+1+this.getListeChambre().size(),this, typeChambre);
			listeChambre.add(tmpChambre);
		}
		this.getListeChambre().addAll(listeChambre);
	}
	
	
	public String listeChambreDisponible(LocalDate uneDate) {
		String str ="";
		Chambre tmpChambre = new Chambre();
		for (int i = 0; i< this.getListeChambre().size();i++) {
			if (this.getListeChambre().get(i).getDateDisponible().contains(uneDate) && !this.getListeChambre().get(i).equals(tmpChambre)){
				str = str+"\n"+this.getListeChambre().get(i).toString();
				tmpChambre = this.getListeChambre().get(i);
			}
		}
		return str+"\nDisponible le: "+ MDMethod.dateToFrenchString(uneDate);
	}
	public String listeChambreDisponible(LocalDate dateEntree, LocalDate dateSortie) {
		String str ="";
		Chambre tmpChambre = new Chambre();
		LocalDate dateTmp = dateEntree;
		// Initialisation liste de date
		ArrayList<LocalDate> arrayDateReservee = new ArrayList<>();
		while (!dateTmp.equals(dateSortie)) {
			arrayDateReservee.add(dateTmp);
			dateTmp = dateTmp.plusDays(1);
		}
		
		for (int i = 0; i< this.getListeChambre().size();i++) {
			if (this.getListeChambre().get(i).getDateDisponible().containsAll(arrayDateReservee) && 
					!this.getListeChambre().get(i).equals(tmpChambre)){
				str = str+"\n"+this.getListeChambre().get(i).toString();
				tmpChambre = this.getListeChambre().get(i);
			}
		}
		if (str.equals("")) {
			return "Aucune date disponible à ce moment";
		}
		return str+"\nDisponible du: "+ MDMethod.dateToFrenchString(dateEntree)+" jusqu'au "+ MDMethod.dateToFrenchString(dateSortie);
	}
	
	public ArrayList<Chambre> listeChambreDisponible_ARRAYLIST(LocalDate dateEntree, LocalDate dateSortie) {
		String str ="";
		Chambre tmpChambre = new Chambre();
		LocalDate dateTmp = dateEntree;
		// Initialisation liste de date
		ArrayList<LocalDate> arrayDateReservee = new ArrayList<>();
		while (!dateTmp.equals(dateSortie)) {
			arrayDateReservee.add(dateTmp);
			dateTmp = dateTmp.plusDays(1);
		}
		ArrayList<Chambre> listeChambreDispo = new ArrayList<>();
		for (int i = 0; i< this.getListeChambre().size();i++) {
			if (this.getListeChambre().get(i).getDateDisponible().containsAll(arrayDateReservee) && 
					!this.getListeChambre().get(i).equals(tmpChambre)){
				str = str+"\n"+this.getListeChambre().get(i).toString();
				listeChambreDispo.add(this.getListeChambre().get(i));
			}
		}
		if (str.equals("")) {
			return null;
		}
		return listeChambreDispo;
	}
	
	// Méthode Overridé
	
	@Override
	public String toString() {
		return this.getNom();
	}
	
	
	public boolean equals(Hotel hotel2) {
		if ((this.getNom().equals(hotel2.getNom())) && (this.getAdresse().equals(hotel2.getAdresse()))) {
			return true;
		}
		return false;
	}
	
	
	public boolean isEmpty() {
		if (((this.getNom() == null) || this.getNom().isEmpty()) && (this.getNombreEtoile() == 0) && (this.getListeChambre().isEmpty())){
			return true;
		}
		else {
			return false;
		}
	}

	// Web Methode
	
	public Chambre getChambreDisponible(Reservation reservation) {
		ArrayList <LocalDate> arrayDateReservee = (ArrayList) reservation.getArrayDateReservee();
		Chambre tmpChambre= new Chambre();
		for (int i = 0; i< this.getListeChambre().size();i++) {
			if (this.getListeChambre().get(i).getDateDisponible().containsAll(arrayDateReservee) && !this.getListeChambre().get(i).equals(tmpChambre)){
				tmpChambre = this.getListeChambre().get(i);
			}
		}
		return tmpChambre;
	}
	
	/*@WebMethod
	public Chambre getChambreDisponible(Reservation reservation, TypeChambre typeDeChambre) throws ChambreNonDisponibleException {
		ArrayList <LocalDate> arrayDateReservee = reservation.getArrayDateReservee();
		Chambre tmpChambre= new Chambre();
		for (int i = 0; i< this.getListeChambre().size();i++) {
			if (this.getListeChambre().get(i).getDateDisponible().containsAll(arrayDateReservee) && !this.getListeChambre().get(i).equals(tmpChambre)){
				tmpChambre = this.getListeChambre().get(i);
				if (tmpChambre.getTypeChambre().equals(typeDeChambre)) {
					return tmpChambre;
				}
			}
		}
		throw new ChambreNonDisponibleException();
	} */

	public Chambre getChambreDisponible(LocalDate dateEntree, LocalDate dateSortie, TypeChambre typeDeChambre) throws ChambreNonDisponibleException {
		Reservation traitementInfoDate = new Reservation(dateEntree,dateSortie);
		ArrayList <LocalDate> arrayDateReservee = (ArrayList) traitementInfoDate.getArrayDateReservee();
		Chambre tmpChambre= new Chambre();
		for (int i = 0; i< this.getListeChambre().size();i++) {
			if (this.getListeChambre().get(i).getDateDisponible().containsAll(arrayDateReservee) && !this.getListeChambre().get(i).equals(tmpChambre)){
				tmpChambre = this.getListeChambre().get(i);
				if (tmpChambre.getTypeChambre().equals(typeDeChambre)) {
					return tmpChambre;
				}
			}
		}
		throw new ChambreNonDisponibleException();
	}
	
	public String listeChambreDisponibleToString(String strDateEntree, String strDateSortie) {
		LocalDate dateEntree = MDMethod.strToDat(strDateEntree);
		LocalDate dateSortie = MDMethod.strToDat(strDateSortie);
		String str ="";
		Chambre tmpChambre = new Chambre();
		LocalDate dateTmp = dateEntree;
		// Initialisation liste de date
		ArrayList<LocalDate> arrayDateReservee = new ArrayList<>();
		while (!dateTmp.equals(dateSortie)) {
			arrayDateReservee.add(dateTmp);
			dateTmp = dateTmp.plusDays(1);
		}
		
		for (int i = 0; i< this.getListeChambre().size();i++) {
			if (this.getListeChambre().get(i).getDateDisponible().containsAll(arrayDateReservee) && 
					!this.getListeChambre().get(i).equals(tmpChambre)){
				str = str+"\n"+this.getListeChambre().get(i).toString()+"\n******************************";
				tmpChambre = this.getListeChambre().get(i);
			}
		}
		if (str.equals("")) {
			return "Aucune date disponible à ce moment";
		}
		return str+"\nCes chambres sont disponibles du: "+ MDMethod.dateToFrenchString(dateEntree)+" jusqu'au "+ MDMethod.dateToFrenchString(dateSortie);
	}
	// Cette méthode affiche que les chambres disponible ET différente ! Puisque ça sert à rien de montrer 42fois la même chambre.
	
	public List<TypeChambre> listeTypeChambre() {
		List<TypeChambre> typeChambreInHotel = new ArrayList<>();
		for (Chambre chambreIte : this.getListeChambre()) {
			if (!typeChambreInHotel.contains(chambreIte.getTypeChambre())) {
				typeChambreInHotel.add(chambreIte.getTypeChambre());
			}
		}
		
		return typeChambreInHotel;
	}


	public void setReservationWM_Auth(Personne clientAuth) {
		// TODO Auto-generated method stub
		
	}


	public void setReservationWM(String strDateEntree, String strDateSortie, String strTypeDeChambre) throws ReservationFailedException, ChambreNonDisponibleException {
		
		TypeChambre typeDeChambre = TypeChambre.valueOf(strTypeDeChambre);
		LocalDate dateEntree = MDMethod.strToDat(strDateEntree);
		LocalDate dateSortie = MDMethod.strToDat(strDateSortie);
		Personne client = new Personne();
		Reservation reservation = new Reservation(client,this.getChambreDisponible(dateEntree, dateSortie, typeDeChambre),dateEntree,dateSortie);
		
		System.out.println(reservation.toString());
		System.out.println(reservation.afficherConfirmation());
	}
	
	public String afficherHotel() {
		return "Etoiles: "+this.getNombreEtoileToString()+"\nHotel "+ this.getNom() + " à "+ this.getAdresse().getVille() + 
				"\nNombre de chambre: "+ this.getListeChambre().size();
	}
	
	public String afficherNomHotel() {
		return this.getNom();
	}
	
}
