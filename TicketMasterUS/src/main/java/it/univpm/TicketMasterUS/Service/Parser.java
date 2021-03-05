package it.univpm.TicketMasterUS.Service;

import java.util.Vector;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import it.univpm.TicketMasterUS.Exceptions.WrongIdPromoterException;
import it.univpm.TicketMasterUS.Exceptions.WrongStateCodeException;
import it.univpm.TicketMasterUS.Models.Event;

/** 
 * Classe che implementa metodi per la scrittura di un oggetto in JSONObject e viceversa.
 * 
 * @author Alice Moretti
 * @author Arianna Ronci
 */
public class Parser {
	
	/**
	 * Metodo che permette di costruire un JSONObject corrispondente a un oggetto della classe Event.
	 * @param oggetto della classe Event dal quale si vuole ottenere un JSONObject.
	 * @return JSONObject che rappresenta l'evento.
	 */
	public JSONObject eventToJSONObject(Event event) {

		JSONObject object = new JSONObject();

		object.put("name", event.getName());
		//object.put("description", event.getDescription());
		object.put("url", event.getUrl());
		object.put("data", event.getData());

		JSONArray a = new JSONArray();
		for(int i = 0; i < event.getPromoters().size(); i++) {
			JSONObject promoter = new JSONObject();
			promoter.put("name promoter", event.getPromoters().get(i).getName());
			promoter.put("ID promoter", event.getPromoters().get(i).getID());
			a.add(promoter);
		}

		object.put("promoters", a);

		JSONObject place = new JSONObject();
		place.put("state", event.getPlace().getState());
		place.put("state code", event.getPlace().getState_code());

		object.put("place", place);

		JSONObject genre = new JSONObject();
		genre.put("ID genre", event.getGenre().getID());
		genre.put("name genre", event.getGenre().getName());

		object.put("genre", genre);

		return object;

	}

	/**
	 * Metodo che permette di costruire un vettore di stringhe rappresentanti state code a partire da JSONObject.
	 * @param JSONObject.
	 * @return Vettore di Stringhe ciascuna rappresentante uno state code.
	 */
	public Vector<String> infoStete(JSONObject obj)throws WrongStateCodeException{

		Vector<String> state_code = new Vector<>();

		JSONArray ja = (JSONArray)(obj.get("State")); 
		String s = "";

		for(Object jo : ja)
		{
			JSONObject o = (JSONObject) jo;

			s = (String) o.get("state code");
			if(!(s.equals("CA") || s.equals("MA") || s.equals("FL") || s.equals("NY")))
				throw new WrongStateCodeException();

			state_code.add(s);
		}
		return state_code;
	}

	/**
	 * Metodo che permette di costruire un vettore di Promoter a partire da JSONObject.
	 * @param JSONObject.
	 * @return Vettore di Promoter.
	 */
	public Vector<String> infoPromoter(JSONObject obj) {

		Vector<String> promoter = new Vector<>();

		JSONArray ja = (JSONArray)(obj.get("Promoter")); 
		String s = "";

		for(Object jo : ja)
		{
			JSONObject o = (JSONObject) jo;

			s = (String) o.get("ID");

			promoter.add(s);
		}
		return promoter;
	}
	
	/**
	 * Metodo che permette di costruire un vettore di stringhe rappresentanti generi a partire da JSONObject.
	 * @param JSONObject.
	 * @return vettore di Stringhe ciascuna rappresentante il nome di un genere.
	 */
	public Vector<String> infoGenre(JSONObject obj){

		Vector<String> genre = new Vector<>();

		JSONArray ja = (JSONArray)(obj.get("Genre")); 
		String s = "";

		for(Object jo : ja)
		{
			JSONObject o = (JSONObject) jo;

			s = (String) o.get("name genre");

			genre.add(s);
		}
		return genre;
	}
	
}
