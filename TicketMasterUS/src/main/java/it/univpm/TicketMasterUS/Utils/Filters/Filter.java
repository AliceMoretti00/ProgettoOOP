package it.univpm.TicketMasterUS.Utils.Filters;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;
import java.util.Vector;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import it.univpm.TicketMasterUS.Models.Event;
import it.univpm.TicketMasterUS.Models.Promoter;
import it.univpm.TicketMasterUS.Service.Downloader;
import it.univpm.TicketMasterUS.Service.Parser;

/** 
 * Classe per la gestione dei filtri e del filtraggio dati.
 * 
 * @author Alice Moretti
 * @author Arianna Ronci
 */
public class Filter {

	private Vector<String> filterState = new Vector<>();
	private Vector<String> filterGenre = new Vector<>();
	private Vector<String> filterPromoter = new Vector<>();
	private long filterPeriod;
	private String param;

	private Vector<Event> filteredEvent = new Vector<>();

	/**
	 * Costrutture dell'oggetto.
	 */
	public Filter() {
		super();
	}

	/**
	 * Costrutture dell'oggetto.
	 * @param filterState Vettore di Stringhe ciascuna rappresentante uno state code per cui si vuole filtrare.
	 * @param filterGenre Vettore di Stringhe ciascuna rappresentante uno genere di eventi per cui si vuole filtrare.
	 * @param filterPromoter Vettore di Stringhe ciascuna rappresentante un ID di un promoter di eventi cui si vuole filtrare.
	 */
	public Filter(Vector<String> filterState, Vector<String> filterGenre, Vector<String> filterPromoter) {
		super();
		this.filterState = filterState;
		this.filterGenre = filterGenre;
		this.filterPromoter = filterPromoter;
		this.filterPeriod = 0;
	}

	/**
	 * Metodo che filtra gli eventi.
	 */
	public void filtering() {

		Vector<Event> e = new Vector<>();

		Downloader d = new Downloader();
		Parser p = new Parser();

		if(!filterState.isEmpty()) {
			for(String state_code: filterState)
				e.addAll(d.EventsInfo(state_code));
		}
		else{
			e.addAll(d.EventsInfo("CA"));
			e.addAll(d.EventsInfo("NY"));
			e.addAll(d.EventsInfo("MA"));
			e.addAll(d.EventsInfo("FL"));
		}

		boolean toAdd = true;

		for (Event event: e) {

			toAdd = true;

			if(!filterPromoter.isEmpty()) {
				int validID = 0;
				for(Promoter prom: event.getPromoters()) 
					if(filterPromoter.contains(prom.getID())) validID++;

				if(validID == 0) toAdd = false;
			}

			if(!filterGenre.isEmpty())
				if(!filterGenre.contains(event.getGenre().getName())) toAdd = false;

			if(filterPeriod != 0) {
				LocalDate limitData = LocalDate.now().plusMonths(filterPeriod);
				LocalDate dateEvent = LocalDate.parse(event.getData());

				if(dateEvent.isAfter(limitData)) toAdd = false;
			}

			if(toAdd) filteredEvent.add(event);
		}
	}

	/**
     * Metodo che restitusce il periodo per cui si vuole filtrare.
     * @return periodo per cui si vuole filtrare..
     */
	public long getFilterPeriod() {
		return filterPeriod;
	}

	/**
     * Metodo che permette di settare  il periodo per cui si vuole filtrare.
     * @param filterPeriod periodo per cui si vuole filtrare.
     */
	public void setFilterPeriod(long filterPeriod) {
		this.filterPeriod = filterPeriod;
	}

	/**
     * Metodo che restitusce un vettore di Stringhe ciascuna rappresentante 
     * uno state code corrispondente allo stato per cui si vuole filtrare.
     * @return Vettore di Stringhe ciascuna rappresentante uno state code corrispondente ad uno stato.
     */
	public Vector<String> getFilterState() {
		return filterState;
	}

	/**
     * Metodo che permette di settare gli stati per pui si vuole filtrare.
     * @param filterState Vettore di Stringhe ciascuna rappresentante uno state code corrispondente ad uno stato.
     */
	public void setFilterState(Vector<String> filterState) {
		this.filterState = filterState;
	}

	/**
     * Metodo che restitusce un vettore di Stringhe ciascuna rappresentante 
     * un genere per cui si vuole filtrare.
     * @return Vettore di Stringhe ciascuna rappresentante il nome di un genere di eventi.
     */
	public Vector<String> getFilterGenre() {
		return filterGenre;
	}
	
	/**
     * Metodo che permette di settare i generi per pui si vuole filtrare.
     * @param filterGenre Vettore di Stringhe ciascuna rappresentante il nome di un genere di eventi.
     */
	public void setFilterGenre(Vector<String> filterGenre) {
		this.filterGenre = filterGenre;
	}

	/**
     * Metodo che restitusce un vettore di Stringhe ciascuna rappresentante 
     * un id corrispondente a un promoter per cui si vuole filtrare.
     * @return Vettore di Stringhe ciascuna rappresentante un id corrispondente a un promoter.
     */
	public Vector<String> getFilterPromoter() {
		return filterPromoter;
	}

	/**
     * Metodo che permette di settare i promoters per pui si vuole filtrare.
     * @param filterPromoter Vettore di Stringhe ciascuna rappresentante un id corrispondente a un promoter.
     */
	public void setFilterPromoter(Vector<String> filterPromoter) {
		this.filterPromoter = filterPromoter;
	}

	/**
     * Metodo che restitusce il vettore di eventi filtrati.
     * @return Vettore di eventi.
     */
	public Vector<Event> getFilteredEvent() {
		filtering();
		return filteredEvent;
	}

	/**
     * Metodo che permette di settare il vettore di eventi filtrati.
     * @param filteredEvent Vettore di eventi.
     */
	public void setFilteredEvent(Vector<Event> filteredEvent) {
		this.filteredEvent = filteredEvent;
	}
	
	/**
     * Metodo che restitusce il parametro che specifica quale informazione si vuole filtrare.
     * @return Stringa che rappresenta il parametro.
     */
	public String getParam() {
		return param;
	}

	/**
     * Metodo che permette di settare il parametro che specifica quale informazione si vuole filtrare.
     * @param Stringa che rappresenta il parametro.
     */
	public void setParam(String param) {
		this.param = param;
	}


}
