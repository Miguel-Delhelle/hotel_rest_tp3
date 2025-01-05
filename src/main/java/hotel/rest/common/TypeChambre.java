package hotel.rest.common;

public enum TypeChambre {

	SIMPLE(1,50),
	DOUBLE(2,60),
	SUITE(2,120),
	LUXE(2,300);
	
	private int nombreLit;
	private double prix;
	
	
	private TypeChambre(int nombreLit, double prix) {
		this.nombreLit = nombreLit;
		this.prix = prix;
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
	
	
}
