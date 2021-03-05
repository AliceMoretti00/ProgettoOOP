package it.univpm.TicketMasterUS.Utils.Statistics;

import java.util.Vector;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import it.univpm.TicketMasterUS.Models.Event;

/**
 * Classe che implementa l'interfaccia Stats e calcola le statistiche per uno stato.
 * 
 * @author Alice Moretti
 * @author Arianna Ronci
 */
public class StatsState implements Stats {

	private Vector<Event> events_stats= new Vector<>();

	private String state_code;
	
	/**
	 * Costrutture dell'oggetto.
	 * @param events_stats Vettore di eventi.
	 */
	public StatsState(Vector<Event> events_stats) {
		super();
		this.events_stats = events_stats;
	}

	/**
	 * Costrutture dell'oggetto.
	 * @param e Vettore di eventi.
	 * @param state_code Stringa che rappresenta lo state code di uno stato.
	 */
	public StatsState(Vector<Event> e, String state_code) {
		super();
		this.state_code = state_code;

		for(Event event: e){
			//costruisco il mio vettore di eventi selezionando solo quelli che hanno luogo nello stato specificato
			if(event.getPlace().getState_code().equals(state_code))
				events_stats.add(event);
		}
	}

	/**
	 * Metodo che calcola il numero totale di promoter che sponsorizzano eventi nello stato 
	 * con state code corrispondente all'attrributo della classe.
	 * @return numero totale dei promoter.
	 */
	public int calcoloTot() {

		int num = 0;
		Vector<String> present_id = new Vector<>();
		for(Event event: events_stats) 
			for(int i = 0; i < event.getPromoters().size(); i++) {
				if(!present_id.contains(event.getPromoters().get(i).getID())) num++;
				present_id.add(event.getPromoters().get(i).getID());

			}

		return num;
	}

	/**
	 * Metodo che calcola il numero di promoter raggruppati per il genere di eventi che sponsorizzano
	 * nello stato con state code corrispondente all'attrributo della classe.
	 * @return JSONObject che rappresenta il numero di promoters raggruppati per genere.
	 */
	public JSONObject calcoloperGenere() {

		//i generi disponibili sono: Art&Theatre, Sport, Music, Miscellaneous 
		//gli altri eventi sarranno raggruppati sotto il genere Altro
		String genre = " ";
		int numArt = 0; 
		int numSport = 0; 
		int numMusic = 0; 
		int numMisc = 0;
		int numAltro = 0; 

		Vector<String> Art_id = new Vector<>();
		Vector<String> Sport_id = new Vector<>();
		Vector<String> Music_id = new Vector<>();
		Vector<String> Miscellaneous_id = new Vector<>();
		Vector<String> Altro_id = new Vector<>();

		for(Event event: events_stats) {
			genre = event.getGenre().getName();

			switch(genre) {
			case "Arts & Theatre": 
				for(int i = 0; i < event.getPromoters().size(); i++) {
					if(!Art_id.contains(event.getPromoters().get(i).getID())) numArt++;
					Art_id.add(event.getPromoters().get(i).getID());
				}
				break;
			case "Sports":
				for(int i = 0; i < event.getPromoters().size(); i++) {
					if(!Sport_id.contains(event.getPromoters().get(i).getID())) numSport++;
					Sport_id.add(event.getPromoters().get(i).getID());
				}
				break;
			case "Music":
				for(int i = 0; i < event.getPromoters().size(); i++) {
					if(!Music_id.contains(event.getPromoters().get(i).getID())) numMusic++;
					Music_id.add(event.getPromoters().get(i).getID());
				}
				break;
			case "Miscellaneous":
				for(int i = 0; i < event.getPromoters().size(); i++) {
					if(!Miscellaneous_id.contains(event.getPromoters().get(i).getID())) numMisc++;
					Miscellaneous_id.add(event.getPromoters().get(i).getID());
				}
				break;
			default:
				for(int i = 0; i < event.getPromoters().size(); i++) {
					if(!Altro_id.contains(event.getPromoters().get(i).getID())) numAltro++;
					Altro_id.add(event.getPromoters().get(i).getID());
				}			
			}
		}

		JSONObject o = new JSONObject();
		o.put("Art&Theatre promoters", numArt);
		o.put("Music promoters", numMusic);
		o.put("Sport promoters", numSport);
		o.put("Miscellaneous promoters", numMisc);
		o.put("Other genre promoters", numAltro);

		return o;
	}

	/**
	 * Metodo che costruisce un JSONObject rappresentate le statiste complete per uno stato.
	 * @return JSONObject.
	 */
	public JSONObject compliteStats() {

		JSONObject jo = new JSONObject();

		jo.put("State", this.state_code);
		jo.put("Tot promoter", calcoloTot());
		jo.put("Tot promoter for genre", calcoloperGenere());

		return jo;
	}

}
