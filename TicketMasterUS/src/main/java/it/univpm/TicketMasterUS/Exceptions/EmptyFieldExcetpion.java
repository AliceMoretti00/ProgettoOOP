package it.univpm.TicketMasterUS.Exceptions;

/**
 * Classe che implementa un metodo per lanciare un eccezione quando un campo del body è vuoto
 * @author Alice
 * @author Arianna
 *
 */
public class EmptyFieldExcetpion extends Exception {

	private String message;
	
	//parametro richiesto dell'interfaccia Serializable implementata da Exception
	private static final long serialVersionUID = 1L;

	/**
	 * Costrutture dell'oggetto.
	 */
	public EmptyFieldExcetpion() {
		super();
		this.message = "Uno o più campi della richiesta sono vuoti.";
	}

	/**
	 * Metodo che restituisce un messaggio di errore passato dal costruttore quando viene lanciata un'eccezione EmptyFieldException.
	 * @return Stringa che contiene il messaggio d'errore da stampare.
	 */
	public String getMessage() {
		return message;
	}

}
