package it.univpm.TicketMasterUS.Exceptions;

/**
 * Classe che implementa un metodo per lanciare un eccezione quando viene inserito nel body un periodo non valido
 * @author Alice
 * @author Arianna
 */

public class WrongPeriodException extends Exception {
	
	private String message;

	//parametro richiesto dell'interfaccia Serializable implementata dalla classe Exception
	private static final long serialVersionUID = 1L;

	/**
	 * Costrutture dell'oggetto.
	 */
	public WrongPeriodException() {
		super();
		this.message = "Il periodo specificato non è valido";
	}

	/**
	 * Metodo che restituisce un messaggio di errore passato dal costruttore quando viene lanciata un'eccezione WrongPeriodException.
	 * @return Stringa che contiene il messaggio d'errore da stampare.
	 */
	public String getMessage() {
		return message;
	}

}
