package it.univpm.TicketMasterUS.Exceptions;

/**
 * Classe che implementa un metodo per lanciare un eccezione quando viene inserito nel corpo di una richesta
 * uno statecode non valido.
 * 
 * @author Alice Moretti
 * @author Arianna Ronci
 */

public class WrongStateCodeException extends Exception {

	//parametro richiesto dell'interfaccia Serializable implementata dalla classe Exception
	private static final long serialVersionUID = 1L;

	/**
	 * Costrutture dell'oggetto.
	 */
	public WrongStateCodeException() {
		super("Lo statecode inserito non e' tra quelli disponibili\n"
				+ "Gli stateCode disponibili sono: \nCA(California),\nFL(Florida),\nMa(Massachusetts),\nNY(New York)");
	}

}