package hotel.rest.exception;

public class ReservationFailedException extends Exception{

	private static final long serialVersionUID = -3039879385113500860L;

	public ReservationFailedException() {
		super("Réservation échoué");
	}

	
}
