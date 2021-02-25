package it.univpm.TicketMasterUS.Service;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.ParseException;
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

	
	public JSONArray getEvents(String state_code){
		
		//if (state_code != "CA" || state_code != "FL" || state_code != "MA" || state_code != "NY")
		//	throw new WrongStateCodeException();

		Event events = new Event();
		Vector<Event> evento = new Vector<>();
		
		JSONObject jo = new JSONObject();
		JSONArray ja = new JSONArray();		

		evento = d.EventsInfo(state_code);

		for (Event e: evento) {			
			jo = p.eventToJSONObject(e);
			ja.add(jo);
		}
		
		return ja;


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
