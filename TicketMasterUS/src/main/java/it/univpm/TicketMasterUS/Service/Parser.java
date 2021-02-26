package it.univpm.TicketMasterUS.Service;

import java.util.Vector;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import it.univpm.TicketMasterUS.Exceptions.WrongIdPromoterException;
import it.univpm.TicketMasterUS.Exceptions.WrongStateCodeException;
import it.univpm.TicketMasterUS.Models.Event;

/** 
 * Classe che implementa il metodo per la scrittura di un oggetto in JSONObject e viceversa.
 * @author Alice
 * @author Arianna
 */
public class Parser {

	Event event = new Event();


	public JSONObject stringToJSONObject(String s) {
		JSONObject object = new JSONObject();
		
		return object;
	}
	/**
	 * Metodo che permette di costruire un JSONObject corrispondente a un oggetto della classe Event 
	 * @param evnt evento oggetto della classe Event dal quale si vuole ottenere un JSONObject.
	 * @return JSONObject che rappresenta l'evento
	 */
	public JSONObject eventToJSONObject(Event event) {

		JSONObject object = new JSONObject();

		object.put("name", event.getName());
		object.put("description", event.getDescription());
		object.put("url", event.getUrl());
		object.put("data", event.getData());

		JSONArray a = new JSONArray();
		for(int i = 0; i < event.getPromoters().size(); i++) {
			JSONObject promoter = new JSONObject();
			promoter.put("name_promoter", event.getPromoters().get(i).getName());
			promoter.put("ID_promoter", event.getPromoters().get(i).getID());
			a.add(promoter);
		}

		object.put("promoters", a);

		JSONObject place = new JSONObject();
		place.put("state", event.getPlace().getState());
		place.put("statecode", event.getPlace().getState_code());

		object.put("place", place);

		JSONObject genre = new JSONObject();
		genre.put("ID_genre", event.getGenre().getID());
		genre.put("name_genre", event.getGenre().getName());

		object.put("genre", genre);

		return object;

	}

	public Vector<String> filterStete(JSONObject obj)throws WrongStateCodeException{

		Vector<String> state_codeToFilter = new Vector<>();

		JSONArray ja = (JSONArray)(obj.get("state")); 
		String s = "";

		for(Object jo : ja)
		{
			JSONObject o = (JSONObject) jo;

			s = (String) o.get("state_code");
			if(!(s.equals("CA") || s.equals("MA") || s.equals("FL") || s.equals("NY")))
				throw new WrongStateCodeException();

			state_codeToFilter.add(s);
		}
		return state_codeToFilter;
	}

	public Vector<String> filterPromoter(JSONObject obj){

		Vector<String> promoterFilter = new Vector<>();

		JSONArray ja = (JSONArray)(obj.get("Promoter")); 
		String s = "";

		for(Object jo : ja)
		{
			JSONObject o = (JSONObject) jo;

			s = (String) o.get("ID");

			promoterFilter.add(s);
		}
		return promoterFilter;
	}
	
	public Vector<String> filterGenre(JSONObject obj){

		Vector<String> genreFilter = new Vector<>();

		JSONArray ja = (JSONArray)(obj.get("Genre")); 
		String s = "";

		for(Object jo : ja)
		{
			JSONObject o = (JSONObject) jo;

			s = (String) o.get("ID");

			genreFilter.add(s);
		}
		return genreFilter;
	}
	
}