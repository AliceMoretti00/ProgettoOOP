package it.univpm.TicketMasterUS.Models;

import java.util.Vector;

/** 
 * Classe che descrive le carartteristiche di un evento.
 * @autor Alice
 * @autor Arianna
 */ 

public class Event {

	private String name;
	private String description;
	private String url;
	private String data;
	private Vector<Promoter> promoters;
	private Place place;
	private Genre genre;
	
	/** 
	 * Costruttore dell'oggetto (senza parametri). 
	 */
	public Event() {
		super();
	}
	
	/**
	 * Costrutture dell'oggetto (completo).
	 * @param name
	 * @param description
	 * @param url
	 * @param data
	 */
	public Event(String name, String description, String url, String data) {
		super();
		this.name = name;
		this.description = description;
		this.url = url;
		this.data = data;
	}

	/**
	 * Costrutture dell'oggetto (completo).
	 * @param name
	 * @param description
	 * @param url
	 * @param data
	 * @param promoters
	 * @param place
	 * @param genre
	 */
	public Event(String name, String description, String url, String data, Vector<Promoter> promoters, Place place,
			Genre genre) {
		super();
		this.name = name;
		this.description = description;
		this.url = url;
		this.data = data;
		this.promoters = promoters;
		this.place = place;
		this.genre = genre;
	}

	/**
     * Metodo che restituisce il nome dell'evento
     * @return Stringa che rappresenta il nome dell'evento
     */
	public String getName() {
		return name;
	}

	/**
     * Metodo che permette di settare il nome dell'evento
     * @param Stringa che rappresenta il nome dell'evento
     */
	public void setName(String name) {
		this.name = name;
	}

	/**
     * Metodo che restituisce la descrizione dell'evento.
     * @return Stringa che rappresenta la descrizione dell'evento
     */
	public String getDescription() {
		return description;
	}

	/**
     * Metodo che permette di settare la descrizione dell'evento.
     * @param Stringa che rappresenta la descrizione dell'evento
     */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
     * Metodo che restitusce l'url della pagina web dell'evento.
     * @return Stringa che rappresenta l'url
     */
	public String getUrl() {
		return url;
	}

	/**
     * Metodo che permette di settare l'url della pagina web dell'evento.
     * @param Stringa che rappresenta l'url
     */
	public void setUrl(String url) {
		this.url = url;
	}

	/**
     * Metodo che restituisce la data dell'evento.
     * @return Stringa che rappresenta la data dell'evento
     */
	public String getData() {
		return data;
	}

	/**
     * Metodo che permette di settare la data dell'evento.
     * @param Stringa che rappresenta la data dell'evento
     */
	public void setData(String data) {
		this.data = data;
	}

	/**
     * Metodo che restituisce i promoters dell'evento.
     * @return Vettore di oggetti della classe Promoter
     */
	public Vector<Promoter> getPromoters() {
		return promoters;
	}

	/**
     * Metodo che permette di settare i promoters dell'evento.
     * @param Vettore di oggetti della classe Promoter
     */
	public void setPromoters(Vector<Promoter> promoters) {
		this.promoters = promoters;
	}

	/**
     * Metodo che restituisce il luogo dell'evento.
     * @return oggetto della classe Place
     */
	public Place getPlace() {
		return place;
	}

	/**
     * Metodo che permette di settare il luogo dell'evento.
     * @param oggetto della classe Place
     */
	public void setPlace(Place place) {
		this.place = place;
	}

	/**
     * Metodo che restituisce il genere dell'evento.
     * @return oggetto della classe Genre
     */
	public Genre getGenre() {
		return genre;
	}

	/**
     * Metodo che permette di settare il genere dell'evento.
     * @param oggetto della classe Genre
     */
	public void setGenre(Genre genre) {
		this.genre = genre;
	}
	
	/**
     * Metodo che permette di scrivere il vettore di promoter come una unica stringa.
     * @return Stringa che continene tutti gli elementi del vettore (promoter dell'evento)
     */
	public String VectortoString(Vector<Promoter> vector) {
		String s = "";
		for (int i=0; i<vector.size(); i++)
			s += vector.get(i).toString();
		return s;
	} 
	
	/**
	 * Override del metodo toString (ereditato dalla classe Object).
	 * @return String che descrive un evento e tutte le sue carattestistiche
	 */
	@Override
	public String toString() {
		return "Event [Name=" + name + ", Description=" + description + ", URL=" + url + ", Data=" + data
				+ ", Promoters=" + VectortoString(promoters) + ", " + place + ", " + genre + "]";
	}
	
	/**
	 * Override del metodo equals (ereditato dalla classe Object).
	 * @param oggetto della classe Event da confrontare
	 * @return boolean che assume valore true o false a seconda che i due oggetti siano uguali o meno
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Event other = (Event) obj;
		if (data == null) {
			if (other.data != null)
				return false;
		} else if (!data.equals(other.data))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (genre == null) {
			if (other.genre != null)
				return false;
		} else if (!genre.equals(other.genre))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (place == null) {
			if (other.place != null)
				return false;
		} else if (!place.equals(other.place))
			return false;
		if (url == null) {
			if (other.url != null)
				return false;
		} else if (!url.equals(other.url))
			return false;
		return true;
	}
	
}
