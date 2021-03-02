package it.univpm.TicketMasterUS.Utils.Statistics;

import java.util.Vector;

import org.json.simple.JSONObject;

import it.univpm.TicketMasterUS.Models.Event;

/**
 * Classe che implementa l'interfaccia Stats e calcola le statistiche per ogni stato
 * @author Alice
 * @author Arianna
 */
public class StatsState implements Stats {

	public Vector<Event> events_stats= new Vector<>();

	private String state_code;
	
	public StatsState(Vector<Event> events_stats) {
		super();
		this.events_stats = events_stats;
	}

	/**
	 * Costrutture dell'oggetto (completo).
	 * @param events
	 * @param state_code
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
	 * Metodo che calcola il numero totale di promoter che sponsorizzano eventi in quello stato
	 * @return numero totale dei promoter in quella regione
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
	 * @return JSONObject che rappresenta il numero di promoters raggruppati per genere
	 */
	public JSONObject calcoloperGenere() {

		//i generi disponibili sono: Art&Theatre, Sport, Music, Film 
		//gli altri eventi sarranno raggruppati sotto il genere Altro
		String genre = " ";
		int numArt = 0; 
		int numSport = 0; 
		int numMusic = 0; 
		int numFilm = 0;
		int numAltro = 0; 

		Vector<String> Art_id = new Vector<>();
		Vector<String> Sport_id = new Vector<>();
		Vector<String> Music_id = new Vector<>();
		Vector<String> Film_id = new Vector<>();
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
			case "Film":
				for(int i = 0; i < event.getPromoters().size(); i++) {
					if(!Film_id.contains(event.getPromoters().get(i).getID())) numFilm++;
					Film_id.add(event.getPromoters().get(i).getID());
				}
				break;
			default:
				for(int i = 0; i < event.getPromoters().size(); i++) {
					if(!Altro_id.contains(event.getPromoters().get(i).getID())) numAltro++;
					Altro_id.add(event.getPromoters().get(i).getID());
				}			}
		}

		JSONObject o = new JSONObject();
		o.put("Art&Theatre promoters", numArt);
		o.put("Music promoters", numMusic);
		o.put("Sport promoters", numSport);
		o.put("Film promoters", numFilm);
		o.put("Other genre promoters", numAltro);

		return o;
	}

	public JSONObject compliteStats() {
		JSONObject o = new JSONObject();
		o.put("State", state_code);
		o.put("Tot promoter", calcoloTot());
		o.put("Tot promoter for genre", calcoloperGenere());

		return o;
	}

}
