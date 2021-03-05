package it.univpm.TicketMasterUS.Exceptions;

/**
 * Classe che implementa un metodo per lanciare un eccezione quando viene inserito nel corpo della richiesta
 * un periodo non valido.
 * 
 * @author Alice Moretti
 * @author Arianna Ronci
 */

public class WrongPeriodException extends Exception {

	//parametro richiesto dell'interfaccia Serializable implementata dalla classe Exception
	private static final long serialVersionUID = 1L;

	/**
	 * Costrutture dell'oggetto.
	 */
	public WrongPeriodException() {
		super("Il periodo specificato non e' valido\n" +
				"E' consentito inserire un valore tra 1 e 6 che rappresenta il numero di mesi");
	}

}
