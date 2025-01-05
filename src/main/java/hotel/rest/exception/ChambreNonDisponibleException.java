package hotel.rest.exception;

public class ChambreNonDisponibleException extends Exception{

	private static final long serialVersionUID = -8215022543194699817L;

	private int statusCode;
	
	public ChambreNonDisponibleException() {
		super("Chambre non disponible");
		this.statusCode = 401;
	}

	
}
