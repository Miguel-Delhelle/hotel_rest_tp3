/*package ico.hai704i.tp2soap;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;


import common.MDMethod;

@SuppressWarnings("unused")
public class Main {

	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//SpringApplication.run(Main.class, args);
		Scanner sc = new Scanner(System.in);
		System.out.println("PROGRAM CLI de test de la réservation d'hotel");
		
		// Afficher la liste des hotels
		
		// Initialitation de l'agence
		Adresse adresseHotel = new Adresse ("97", "Route de Clairmarais","62500","Saint-Omer","France");
		Adresse adresseHotel2 = new Adresse ("23", "Place de la Comédie", "34000", "Montpellier", "France");
		Adresse adresseHotel3 = new Adresse ("12"," Place du général de Gaulle", "59000","Lille", "France");
		Hotel  nordFrance= new Hotel("à Saint-O", adresseHotel,3);
		Hotel  mtpFrance= new Hotel("à la Comédie", adresseHotel2,4);
		Hotel  lilleFrance= new Hotel("chez les flamands", adresseHotel3, 3);
		lilleFrance.generateurChambre(30, 2, 45);
		mtpFrance.generateurChambre(15, 1, 300);
		nordFrance.generateurChambre(30, 2, 35);
		nordFrance.generateurChambre(12, 6, 200);
		Agence franceHotel = new Agence("France Hotel");
		franceHotel.addHotel(lilleFrance);franceHotel.addHotel(mtpFrance);franceHotel.addHotel(nordFrance);
		
		// UI
		System.out.println("Bienvenue: ");
		System.out.println("Veuillez rentrer votre nom, prénom, nationalité, et votre age: ");
		String nom = sc.nextLine();
		String prenom = sc.nextLine();
		// String nationalite = sc.nextLine();
		int age = sc.nextInt();
		Personne client = new Personne (nom, prenom, age);
		System.out.println("Voici la liste des hotel disponible: "+franceHotel.toString());
		
		System.out.println("Veuillez rentrer la commande souhaitée: ");
		String userInput = "";
		while (!userInput.equals("exit")) {
				userInput = sc.nextLine();
			if (userInput.equals("reservation")) {
				System.out.println("Veuillez choisir un hotel");
				String hotelChoisi = sc.nextLine();
				franceHotel.stringToHotel(hotelChoisi);
				System.out.println("Veuillez rentrez vos dates d'entrées et de sortie, selon le format ANNEE-MOIS-JOUR");
				String strDateEntree = sc.nextLine();
				String strDateSortie = sc.nextLine();
				LocalDate dateEntree = MDMethod.strToDat(strDateEntree);
				LocalDate dateSortie = MDMethod.strToDat(strDateSortie);
				System.out.println(franceHotel.stringToHotel(hotelChoisi).listeChambreDisponible(dateEntree, dateSortie));
				System.out.println("Voulez vous choisir une chambre en particulier dans la liste proposées ? (yes/no)");
				String input2 = sc.nextLine();
				if (input2.equalsIgnoreCase("y") || input2.equalsIgnoreCase("yes")) {
					// Création de la réservation avec l'id de la chambre
					System.out.println("Rentrez l'identifiant (id) de la chambre souhaitée");
					int chmbrId = sc.nextInt();
					client.setReservation(franceHotel.stringToHotel(hotelChoisi).getListeChambre().get(chmbrId-1), dateEntree, dateSortie);
					System.out.println(client.getReservation().afficherConfirmation());
				}
				else if (input2.equalsIgnoreCase("n") || input2.equalsIgnoreCase("no")){
					client.setReservation(franceHotel.stringToHotel(hotelChoisi), dateEntree, dateSortie);
					System.out.println(client.getReservation().afficherConfirmation());				}
				else {
					System.err.print("Réservation abbandonnée, Retour au menu principal: ");
				}
			}
		} 
		sc.close();
	} 
}*/
package hotel.rest.common;


