package it.univpm.TicketMasterUS.Exceptions;

/**
 * Classe che implementa un metodo per lanciare un eccezione quando viene inserito nel body un periodo non valido
 * @author Alice
 * @author Arianna
 */

public class WrongPeriodException extends Exception {

	//parametro richiesto dell'interfaccia Serializable implementata dalla classe Exception
	private static final long serialVersionUID = 1L;

	/**
	 * Costrutture dell'oggetto.
	 */
	public WrongPeriodException() {
		super("Il periodo specificato non è valido");
	}

}
