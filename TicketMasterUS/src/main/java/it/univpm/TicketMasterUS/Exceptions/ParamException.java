package it.univpm.TicketMasterUS.Exceptions;

/**
 * Classe che implementa un metodo per lanciare un eccezione quando il parametro di una richista
 * inserito dell'utenete non è valido.
 * 
 * @author Alice Moretti
 * @author Arianna Ronci
 */
public class ParamException extends Exception {

	String message;

	//parametro richiesto dell'interfaccia Serializable implementata dalla classe Exception
	private static final long serialVersionUID = 1L;

	/**
	 * Costrutture dell'oggetto.
	 */
	public ParamException() {
		super("Il parametro specificato non è valido.");
	}

	/**
	 * Costrutture dell'oggetto.
	 * @param message Stringa che rappresenta il messaggio di errore.
	 */
	public ParamException(String message) {
		super();
		this.message = message;
	}

	/**
	 * Metodo che restituisce un messaggio di errore passato dal costruttore
	 * quando viene lanciata un'eccezione ParamException.
	 * @return Stringa che contiene il messaggio d'errore da stampare.
	 */
	public String getMessage() {
		return message;
	}
}

