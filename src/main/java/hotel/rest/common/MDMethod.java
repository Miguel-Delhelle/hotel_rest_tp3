package hotel.rest.common;
import java.time.LocalDate;




public abstract class MDMethod{
	
	// Méthode autour de LocalDate 
	public static String dateToFrenchString(LocalDate date) {
		
		String[] dateTab = date.toString().split("-");
		String annee = dateTab[0];
		// Affichage mois
		String mois = dateTab[1];
		if (mois.equals("01")) {mois = "janvier";}
		if (mois.equals("02")) {mois = "février";}
		if (mois.equals("03")) {mois = "mars";}
		if (mois.equals("04")) {mois = "avril";}
		if (mois.equals("05")) {mois = "mai";}
		if (mois.equals("06")) {mois = "juin";}
		if (mois.equals("07")) {mois = "juillet";}
		if (mois.equals("08")) {mois = "août";}
		if (mois.equals("09")) {mois = "septembre";}
		if (mois.equals("10")) {mois = "octobre";}
		if (mois.equals("11")) {mois = "novembre";}
		if (mois.equals("12")) {mois = "decembre";}
		// Affichage jour
		String numeroJour = dateTab[2];

		return numeroJour+" "+mois+" "+annee; 
	}
	public static String getJour(LocalDate date) {
		String jour =""; 
		if ( date.getDayOfWeek().toString().equals("MONDAY")) {jour = "Lundi";}
		if ( date.getDayOfWeek().toString().equals("TUESDAY")) {jour = "Mardi";}
		if ( date.getDayOfWeek().toString().equals("WEDNESDAY")) {jour = "Mercredi";}
		if ( date.getDayOfWeek().toString().equals("THURSDAY")) {jour = "Jeudi";}
		if ( date.getDayOfWeek().toString().equals("FRIDAY")) {jour = "Vendredi";}
		if ( date.getDayOfWeek().toString().equals("SATURDAY")) {jour = "Samedi";}
		if ( date.getDayOfWeek().toString().equals("SUNDAY")) {jour = "Dimanche";}
		return jour;
	}
	public static Nationalite strToNat(String str) {
    	return Nationalite.valueOf(str.toUpperCase());
	}
	public static String natToStr(Nationalite n) {
		String tmp = n.toString();
		tmp = tmp.substring(0,1).toUpperCase()+tmp.substring(1).toLowerCase();
		return tmp;
	}
	
	public static LocalDate strToDat(String strDate) {
		String[] date = strDate.split("-");
		int jour = Integer.parseInt(date[0]);
		int mois = Integer.parseInt(date[1]);
		int annee = Integer.parseInt(date[2]);
		
		LocalDate localDate = LocalDate.of(annee, mois, jour);
		
		return localDate;
	}
	
}

