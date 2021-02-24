package it.univpm.TicketMasterUS.Exceptions;

/**
 * Classe che implementa un metodo per lanciare un eccezione quando il genere specificato
 * non corrisponde a nessun genere di un evento
 * @author Alice
 * @author Arianna
 *
 */
public class GenreNotFoundException extends Exception {

	private String message;

	//parametro richiesto dell'interfaccia Serializable implementata dalla classe Exception
	private static final long serialVersionUID = 1L;

	/**
	 * Costrutture dell'oggetto.
	 */
	public GenreNotFoundException() {
		super();
		this.message = "Il genere inserito nella richiesta non è valido";
	}

	/**
	 * Metodo che restituisce un messaggio di errore passato dal costruttore quando viene lanciata un'eccezione GenreNotFoundException.
	 * @return Stringa che contiene il messaggio d'errore da stampare.
	 */
	public String getMessage() {
		return message;
	}

}
