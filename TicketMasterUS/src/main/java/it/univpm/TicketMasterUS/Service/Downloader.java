package it.univpm.TicketMasterUS.Service;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Vector;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.ParseException;

import it.univpm.TicketMasterUS.Models.*;

/** 
 * Classe che permette di scaricare i dati di alcuni stati e permette di memorizzarli in una struttura dati.
 * 
 * @author Alice Moretti
 * @author Arianna Ronci
 */
public class Downloader {


	/**
	 * apiKey è la chiave necessaria per ottenere informazioni dall'API di TicketMaster.
	 */
	private String apiKey = "vcxnJP2zGc6QpAUZjLjuEc7BIQIzA8kv";


	/**
	 * Metodo che permette di scaricare i tutti dati relativi agli eventi che hanno luogo in un determinato stato
	 * identificato dallo state code specificato nella richiesta e restituisce un JSONArray.
	 * @param state_code codice postale che identifica lo stato di cui si vuole ottenere le informazioni relative agli eventi.
	 * @return JSONArray che contiene tutte informazioni di tutti gli eventi che hanno luogo nello stato identificato dallo state_code.
	 */
	public JSONArray downloadEvents(String state_code){
		JSONArray eventsUS = new JSONArray();

		String url="https://app.ticketmaster.com/discovery/v2/events.json?size=200&page=1&countryCode=US&stateCode="+ state_code +"&apikey=" + apiKey;

		try {
			HttpURLConnection connection= (HttpURLConnection) new URL(url).openConnection();
			connection.setRequestMethod("GET");
			connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:25.0) Gecko/20100101 Firefox/25.0");
			connection.setRequestProperty("Content-Type", "application/json");


			InputStream in = connection.getInputStream(); 
			String data = "";
			String line = "";


			try {
				InputStreamReader inR = new InputStreamReader( in );
				BufferedReader buf = new BufferedReader( inR );

				while ( ( line = buf.readLine() ) != null ) {
					data+= line;
					System.out.println(line);
				}

			} finally {
				in.close();
			}
			JSONObject obj = (JSONObject)JSONValue.parseWithException(data);
			JSONObject o = (JSONObject)(obj.get("_embedded"));
			eventsUS = (JSONArray)(o.get("events")); 

		} catch(ParseException e){
			e.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}

		return eventsUS;
	}

	/**
	 * Metodo che popola la mia struttura dati di eventi con le relative informazioni,
	 * selezionando solo quelle di interesse tra tutte quelle scaricate con il metodo downloadEvents.
	 * @param Stringa che rappresenta il codice postale che identifica lo stato.
	 * @return Vettore di eventi.
	 */
	public Vector<Event> EventsInfo(String state_code) {

		JSONArray eventUS = downloadEvents(state_code);

		Vector<Event> events = new Vector<>();

		for(Object o: eventUS) 
		{ 
			JSONObject e = (JSONObject) o;

			Event event;

			String name = (String) e.get("name");
			//String description = (String) e.get("description");
			String url = (String) e.get("url");

			/*if(description == null)
				description="Description not available";*/

			JSONObject d = (JSONObject) e.get("dates");
			JSONObject start= (JSONObject) d.get("start");
			String data = (String) start.get("localDate");

			event = new Event(name,url,data);

			event.setPromoters(PromotersInfo(e));
			event.setPlace(PlaceInfo(state_code));
			event.setGenre(GenereInfo(e));

			events.add(event);
		}

		return events;
	}

	/**
	 * Metodo che costruisce il vettore di promoters di un certo evento. 
	 * @param JSONObject che rappresenta l'evento.
	 * @return Vettore dei promoters dell'evento.
	 */
	public Vector<Promoter> PromotersInfo(JSONObject e) {

		Vector<Promoter> p = new Vector<>();
		Promoter promoter = new Promoter();
		String name = "";
		String ID = "";

		JSONArray promoters = (JSONArray) e.get("promoters"); 

		if (promoters == null)
		{
			//nel caso in cui il promoter dell'evento non è presente assegnamo i seguenti valori di default
			name = "default promoter";
			ID = "000";

			promoter.setID(ID);
			promoter.setName(name);
			p.add(promoter);

		}else 
		{
			for(Object o: promoters) 
			{ 
				JSONObject obj = (JSONObject) o;

				ID = (String) obj.get("id");
				name = (String) obj.get("name");	

				promoter.setID(ID);
				promoter.setName(name);
				p.add(promoter);
			}
		}

		return p;
	}

	/**
	 * Metodo che restituisce un oggetto della classe Place sulla base del codice postale.
	 * @param Stringa che rappresenta il codice postale dello stato.
	 * @return Oggetto della classe Place in cui ha luogo l'evento.
	 */
	private Place PlaceInfo(String state_code) {

		Place place = new Place();

		place.setState_code(state_code);

		switch(state_code) {
		case "CA": place.setState("California");break;
		case "FL": place.setState("Florida");break;
		case "MA": place.setState("Massachusetts");break;
		case "NY": place.setState("New York");break;
		}

		return place;
	}

	/**
	 * Metodo che restituisce un oggetto della classe Genre che rappresenta il genere di un evento.
	 * @param JSONObject che rappresenta l'evento.
	 * @return Oggetto della classe Genre che rappresenta il genere dell'evento.
	 */
	private Genre GenereInfo(JSONObject e) {

		Genre genre = new Genre();
		String name = "";
		String ID = "";

		JSONArray classification = (JSONArray) e.get("classifications");

		for(Object g: classification) {

			JSONObject obj = (JSONObject) g;

			JSONObject o = (JSONObject) obj.get("segment");
			ID = (String)o.get("id");
			name = (String)o.get("name");

			genre.setID(ID);
			genre.setName(name);
		}

		return genre;
	}
}