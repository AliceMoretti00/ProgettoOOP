package it.univpm.TicketMasterUS.Service;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import it.univpm.TicketMasterUS.Exceptions.EmptyFieldExcetpion;
import it.univpm.TicketMasterUS.Exceptions.GenreNotFoundException;
import it.univpm.TicketMasterUS.Exceptions.NoBodyException;
import it.univpm.TicketMasterUS.Exceptions.WrongIdPromoterException;
import it.univpm.TicketMasterUS.Exceptions.WrongPeriodException;
import it.univpm.TicketMasterUS.Exceptions.WrongStateCodeException;

/**
 * Classe per la gestione dei metodi chiamati dalla classe Controller 
 * @author Alice
 * @author Arianna
 *
 */

@Service
public class TicketMasterServiceImpl implements TicketMasterService {

	/**
	 * apiKey è la chiave necessaria per ottenere informazioni dall'API di TicketMaster.
	 */
	private String apiKey = "vcxnJP2zGc6QpAUZjLjuEc7BIQIzA8kv";
	

	public JSONObject getEvents(String state_code) throws WrongStateCodeException {
		
		
		return null;
	}


	public JSONArray getPromoters(String state_code) throws WrongStateCodeException {
		return null;
	}


	public JSONArray getStatsState() {
		return null;
	}


	public JSONArray getStatsPromoters(String ID) throws WrongIdPromoterException, EmptyFieldExcetpion {
		return null;
	}


	public JSONArray getFilterStats(JSONObject filters) throws NoBodyException, WrongStateCodeException,
			WrongIdPromoterException, WrongPeriodException, GenreNotFoundException {
		return null;
	}
	
	

}
