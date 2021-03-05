package it.univpm.TicketMasterUS.Models;

/** 
 * Classe che descrive le carartteristiche del genere di un evento.
 * 
 * @autor Alice Moretti
 * @autor Arianna Ronci
 */

public class Genre {

	private String ID;
	private String name;
	
	/** 
	 * Costruttore dell'oggetto. 
	 */
	public Genre() {
		super();
	}
	
	/**
	 * Costrutture dell'oggetto.
	 * @param ID   ID che identifica il genere.
	 * @param name nome del genere.
	 */
	public Genre(String ID, String name) {
		super();
		this.ID = ID;
		this.name = name;
	}
	
	/**
     * Metodo che restituisce l'ID del genere di un evento.
     * @return Stringa che rappresenta l'ID del genere.
     */
	public String getID() {
		return ID;
	}
	
	/**
     * Metodo che permette di settare l'ID del genere di un evento.
     * @param ID Stringa che rappresenta l'ID del genere.
     */
	public void setID(String ID) {
		this.ID = ID;
	}
	
	/**
     * Metodo che restituisce il nome del genere di un evento.
     * @return Stringa che rappresenta il nome del genere.
     */
	public String getName() {
		return name;
	}
	
	/**
     * Metodo che permette di settare il nome del genere di un evento.
     * @param name Stringa che rappresenta il nome del genere.
     */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * Override del metodo toString (ereditato dalla classe Object).
	 * @return Stringa che descrive un genere di un evento.
	 */
	@Override
	public String toString() {
		return "Genre [ID=" + ID + ", name=" + name + "]";
	}
	

	/**
	 * Override del metodo equals (ereditato dalla classe Object).
	 * @param oggetto della classe Genre da confrontare.
	 * @return boolean che assume valore true o false a seconda che i due oggetti siano uguali o meno.
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Genre))
			return false;
		Genre other = (Genre) obj;
		if (ID == null) {
			if (other.ID != null)
				return false;
		} else if (!ID.equals(other.ID))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
}
