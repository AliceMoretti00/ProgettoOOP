package it.univpm.TicketMasterUS.Exceptions;

/**
 * Classe che implementa un metodo per lanciare un eccezione quando uno o più campi  campo del body sono vuoti.
 * 
 * @author Alice Moretti
 * @author Arianna Ronci
 */
public class EmptyFieldExcetpion extends Exception {

	String message;

	//parametro richiesto dell'interfaccia Serializable implementata da Exception
	private static final long serialVersionUID = 1L;

	/**
	 * Costrutture dell'oggetto.
	 */
	public EmptyFieldExcetpion() {
		super("Uno o più campi della richiesta sono vuoti.");
	}

	/**
	 * Costrutture dell'oggetto.
	 * @param Stringa che rappresenta il messaggio di errore.
	 */
	public EmptyFieldExcetpion(String message) {
		super();
		this.message = message;
	}

	/**
	 * Metodo che restituisce il messaggio di errore passato dal costruttore 
	 * quando viene lanciata un'eccezione EmptyFieldException.
	 * @return Stringa che contiene il messaggio d'errore da stampare.
	 */
	public String getMessage() {
		return message;
	}

}
