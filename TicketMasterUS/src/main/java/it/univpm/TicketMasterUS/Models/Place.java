package it.univpm.TicketMasterUS.Models;

/** 
 * Classe che descrive le carartteristiche del luogo (stato) in cui si tiene un evento.
 * 
 * @autor Alice Moretti
 * @autor Arianna Ronci
 */
public class Place {

	private static final String country = "US";
	private String state;
	private String state_code;

	/** 
	 * Costruttore dell'oggetto. 
	 */
	public Place() {
		super();
	}

	/**
	 * Costrutture dell'oggetto .
	 * @param name       nome dello stato.
	 * @param state_code codice postale corrispondente allo stato.
	 */
	public Place(String state, String state_code) {
		super();
		this.state = state;
		this.state_code = state_code;
	}

	/**
	 * Metodo che restituisce il paese a cui appartiene lo stato.
	 * @return Stringa che rappresenta il paese.
	 */
	public static String getCountry() {
		return country;
	}

	/**
	 * Metodo che restituisce il nome dello stato. 
	 * @return Stringa che rappresenta il nome dello stato. 
	 */
	public String getState() {
		return state;
	}

	/**
	 * Metodo che permette di settare il nome di uno stato.
	 * @param state Stringa che rappresenta il nome di uno stato.
	 */
	public void setState(String state) {
		this.state = state;
	}

	/**
	 * Metodo che restituisce lo statecode dello stato.
	 * @return Stringa che rappresenta lo statecode dello stato. 
	 */
	public String getState_code() {
		return state_code;
	}

	/**
	 * Metodo che permette di settare lo statecode di uno stato.
	 * @param state_code Stringa che rappresenta lo statecode di uno stato.
	 */
	public void setState_code(String state_code) {
		this.state_code = state_code;
	}

	/**
	 * Override del metodo toString (ereditato dalla classe Object).
	 * @return Stringa che descrive lo stato in cui ha luogo un evento.
	 */
	@Override
	public String toString() {
		return "Place [state=" + state + ", state_code=" + state_code + "]";
	}

	/**
	 * Override del metodo equals (ereditato dalla classe Object).
	 * @param oggetto della classe Place da confrontare.
	 * @return boolean che assume valore true o false a seconda che i due oggetti siano uguali o meno.
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Place))
			return false;
		Place other = (Place) obj;
		if (state == null) {
			if (other.state != null)
				return false;
		} else if (!state.equals(other.state))
			return false;
		if (state_code == null) {
			if (other.state_code != null)
				return false;
		} else if (!state_code.equals(other.state_code))
			return false;
		return true;
	}

}
