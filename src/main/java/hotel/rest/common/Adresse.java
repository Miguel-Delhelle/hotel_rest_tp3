package hotel.rest.common;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Adresse {
	
	@GeneratedValue
	@Id
	private long id;
	private String pays;
	private String rue;
	private String numero;
	private String codePostal;
	private String ville;
	
	// Constructeurs
	public Adresse(String numero, String rue, String codePostal, String ville, String pays){
		this.pays = pays;
		this.rue = rue;
		this.numero = numero;
		this.codePostal = codePostal;
		this.ville = ville;
	}
	
	// Accesseurs

	public String getPays() {
		return pays;
	}

	public void setPays(String pays) {
		this.pays = pays;
	}

	public String getRue() {
		return rue;
	}

	public void setRue(String rue) {
		this.rue = rue;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getCodePostal() {
		return codePostal;
	}

	public void setCodePostal(String codePostal) {
		this.codePostal = codePostal;
	}

	public String getVille() {
		return ville;
	}

	public void setVille(String ville) {
		this.ville = ville;
	}
	// Méthode

	@Override
	public String toString() {
		return this.getNumero()+" "+this.getRue()+" à "+this.getVille()+" en "+this.getPays();
	}

	public boolean equals(Adresse adresse2) {
		if (this.getPays().equals(adresse2.getPays()) && 
				this.getCodePostal().equals(adresse2.getCodePostal()) &&
				this.getNumero().equals(adresse2.getNumero()) &&
				this.getRue().equals(adresse2.getRue()) &&
				this.getVille().equals(adresse2.getVille())) {
			return true;
		}
		return false;
	}
	
	
	
}
