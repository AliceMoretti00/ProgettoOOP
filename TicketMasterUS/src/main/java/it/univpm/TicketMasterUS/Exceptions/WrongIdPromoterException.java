package it.univpm.TicketMasterUS.Exceptions;

/**
 * Classe che implementa un metodo per lanciare un eccezione quando viene inserito nel body un IDPromoter non valido
 * @author Alice
 * @author Arianna
 */

public class WrongIdPromoterException extends Exception {

	//parametro richiesto dell'interfaccia Serializable implementata dalla classe Exception
	private static final long serialVersionUID = 1L;

	/**
	 * Costrutture dell'oggetto.
	 */
	public WrongIdPromoterException() {
		super("ID inserito non corrisponde a nessun promoter.");
	}

}
