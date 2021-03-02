package it.univpm.TicketMasterUS.Exceptions;

/**
 * Classe che implementa un metodo per lanciare un eccezione quando un campo del body è vuoto
 * @author Alice
 * @author Arianna
 *
 */
public class EmptyFieldExcetpion extends Exception {
	
	//parametro richiesto dell'interfaccia Serializable implementata da Exception
	private static final long serialVersionUID = 1L;

	/**
	 * Costrutture dell'oggetto.
	 */
	public EmptyFieldExcetpion() {
		super("Uno o più campi della richiesta sono vuoti.");
	}

}
