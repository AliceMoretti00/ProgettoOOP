package it.univpm.TicketMasterUS.Exceptions;

/**
 * Classe che implementa un metodo per lanciare un eccezione quando viene inserito nel corpo di una richiesta
 * uno o più IDPromoter non validi.
 * 
 * @author Alice Moretti
 * @author Arianna Ronci
 */

public class WrongIdPromoterException extends Exception {

	//parametro richiesto dell'interfaccia Serializable implementata dalla classe Exception
	private static final long serialVersionUID = 1L;

	/**
	 * Costrutture dell'oggetto.
	 */
	public WrongIdPromoterException() {
		super("uno o più ID inseriti non corrisponde a nessun promoter tra quelli che sponzorizzano eventi.");
	}

}
