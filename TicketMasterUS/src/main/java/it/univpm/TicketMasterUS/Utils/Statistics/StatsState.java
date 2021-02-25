package it.univpm.TicketMasterUS.Utils.Statistics;

import java.util.Vector;

import org.json.simple.JSONObject;

import it.univpm.TicketMasterUS.Models.Event;
import it.univpm.TicketMasterUS.Models.Promoter;

public class StatsState implements Stats {

	public Vector<Event> events_stats= new Vector<>();

	private String state_code;

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

	public int CalcoloTot() {
		int num = 0;
		for(Event event: events_stats)
			num += event.getPromoters().size();

		return num;
	}

	/**
	 * Metodo che calcola il numero di promoter raggruppati per il genere di eventi che sponsorizzano
	 * @return JSONObject che rappresenta il numero di promoters raggruppati per genere
	 */
	public JSONObject CalcoloperGenere() {

		//i generi disponibili sono: Art&Theatre, Sport, Music, Film 
		//gli altri eventi sarranno raggruppati sotto il genere Altro
		String genre = " ";
		int numArt = 0; 
		int numSport = 0; 
		int numMusic = 0; 
		int numFilm = 0;
		int numAltro = 0; 

		for(Event event: events_stats) {
			genre = event.getGenre().getName();

			switch(genre) {
			case "Art&Theatre": 
				numArt += event.getPromoters().size();
				break;
			case "Sport":
				numSport += event.getPromoters().size();
				break;
			case "Music":
				numMusic += event.getPromoters().size();
				break;
			case "Film":
				numFilm += event.getPromoters().size();
				break;
			default:
				numAltro += event.getPromoters().size();
			}
		}

		JSONObject o = new JSONObject();
		o.put("Art&Theatre promoters", numArt);
		o.put("Music promoters", numMusic);
		o.put("Sport promoters", numSport);
		o.put("Film promoters", numFilm);
		o.put("Other genre promoters", numAltro);
		return o;
	}


}

