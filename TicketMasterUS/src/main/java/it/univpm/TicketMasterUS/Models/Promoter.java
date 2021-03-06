package it.univpm.TicketMasterUS.Models;

/** 
 * Classe che descrive le caratteristiche di un promoter di un evento.
 * 
 * @autor Alice Moretti
 * @autor Arianna Ronci
 */

public class Promoter {
	
	private String name;
	private String ID;
	
	/** 
	 * Costruttore dell'oggetto. 
	 */
	public Promoter() {
		super();
	}
	
	/**
	 * Costrutture dell'oggetto.
	 * @param name nome del promoter di un evento.
	 * @param ID   ID corrispondetnte al promoter.
	 */
	public Promoter(String name, String ID) {
		super();
		this.name = name;
		this.ID = ID;
	}
	
	/**
     * Metodo che restituisce il nome del promoter di un evento.
     * @return Stringa che rappresenta il nome del promoter.
     */
	public String getName() {
		return name;
	}

	/**
     * Metodo che permette di settare il nome del promoter di un evento.
     * @param name Stringa che rappresenta il nome del promoter. 
     */
	public void setName(String name) {
		this.name = name;
	}

	/**
     * Metodo che restituisce l'ID del promoter di un evento.
     * @return Stringa che rappresenta l'ID del promoter.
     */
	public String getID() {
		return ID;
	}

	/**
     * Metodo che permette di settare l'ID del promoter di un evento.
     * @param ID Stringa che rappresenta l'ID del promoter.
     */
	public void setID(String ID) {
		this.ID = ID;
	}

	/**
	 * Override del metodo toString (ereditato dalla classe Object).
	 * @return Stringa che descrive un promoter di un evento.
	 */
	@Override
	public String toString() {
		return "Promoter [name=" + name + ", ID=" + ID + "]";
	}

	/**
	 * Override del metodo equals (ereditato dalla classe Object).
	 * @param oggetto della classe Promoters da confrontare.
	 * @return boolean che assume valore true o false a seconda che i due oggetti siano uguali o meno.
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Promoter))
			return false;
		Promoter other = (Promoter) obj;
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
