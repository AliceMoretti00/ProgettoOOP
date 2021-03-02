package it.univpm.TicketMasterUS.Exceptions;

/**
 * Classe che implementa un metodo per lanciare un eccezione quando viene inserito nel body uno statecode non valido
 * @author Alice
 * @author Arianna
 */

public class WrongStateCodeException extends Exception {

	//parametro richiesto dell'interfaccia Serializable implementata dalla classe Exception
	private static final long serialVersionUID = 1L;

	/**
	 * Costrutture dell'oggetto.
	 */
	public WrongStateCodeException() {
		super("Lo statecode inserito non è tra quelli disponibili\n"
				+ "Gli stateCode disponibili sono: \nCA(California),\nFL(Florida),\nMa(Massachusetts),\nNY(New York)");
	}

}