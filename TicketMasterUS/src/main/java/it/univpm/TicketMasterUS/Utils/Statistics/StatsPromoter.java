package it.univpm.TicketMasterUS.Utils.Statistics;

import java.util.Vector;

import org.json.simple.JSONObject;

import it.univpm.TicketMasterUS.Models.Event;
import it.univpm.TicketMasterUS.Models.Promoter;

/**
 * Classe che implementa l'interfaccia Stats e calcola le statistiche per uno o più promoters 
 * @author Alice
 * @author Arianna
 */
public class StatsPromoter implements Stats {

	private Vector<Event> events_stats= new Vector<>();

	private Vector<String> id_promoter = new Vector<>();

	public StatsPromoter(Vector<Event> events_stats) {
		super();
		this.events_stats = events_stats;
	}

	/**
	 * Costrutture dell'oggetto (completo).
	 * @param events
	 * @param id_promoter
	 */
	public StatsPromoter(Vector<Event> e, Vector<String> id_promoter) {
		super();
		this.id_promoter = id_promoter;
		boolean daAggiungere = false;

		//costruisco il mio vettore di eventi selezionando solo quelli che hanno uno dei promoter specificati
		for(Event event: e) {
			for(Promoter p: event.getPromoters()) {
				for(String id: id_promoter) {
					if(p.getID().equals(id))
						daAggiungere = true;
				}
				if(daAggiungere ) { 
					events_stats.add(event);
					daAggiungere = false;
				}
			}
		}
	}

	/**
	 * Metodo che calcola il numero totale di eventi che sono sponsorizzati da uno dei promoter specificati
	 * @return numero totale dei eventi che verificano la condizione
	 */
	public int calcoloTot() {
		
		Vector<Event> event= new Vector<>();
		for(Event e: events_stats)
			if(!event.contains(e))  
				event.add(e);
		
		return event.size();
	}

	/**
	 * Metodo che calcola iil numero totale di eventi che sono sponsorizzati da uno dei promoter specificati
	 *  raggruppati per il genere di eventi
	 * @return JSONObject che rappresenta il numero di eventi raggruppati per genere
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

		Vector<Event> event= new Vector<>();
		for(Event e: events_stats)
			if(!event.contains(e))  
				event.add(e);
		
		for(Event e: event) {
			genre = e.getGenre().getName();

			switch(genre) {
			case "Arts & Theatre": 
				numArt ++;
				break;
			case "Sports":
				numSport ++;
				break;
			case "Music":
				numMusic ++;
				break;
			case "Film":
				numFilm ++;
				break;
			default:
				numAltro ++;
			}
		}

		JSONObject o = new JSONObject();
		o.put("Art&Theatre events", numArt);
		o.put("Music events", numMusic);
		o.put("Sport events", numSport);
		o.put("Film events", numFilm);
		o.put("Other genre events", numAltro);
		return o;
	}


	/**
	 * Metodo che calcola il numero totale di stati in cui uno dei promoter specificati sponsorizza almeno un evento
	 * @return numero totale dei stati che verificano la condizione
	 */
	public int calcoloState() {

		int numState = 0;
		boolean pCA = false;
		boolean pFL = false;
		boolean pMA = false;
		boolean pNY = false;

		for(Event e: events_stats) {
			
			if(e.getPlace().getState_code().equals("CA")) pCA = true;
			if(e.getPlace().getState_code().equals("FL")) pFL = true;
			if(e.getPlace().getState_code().equals("MA")) pMA = true;
			if(e.getPlace().getState_code().equals("NY")) pNY = true;
		}

		if(pCA) numState++;
		if(pFL) numState++;
		if(pMA) numState++;
		if(pNY) numState++;

		return numState;

	}

	public JSONObject compliteStats() {


		JSONObject o = new JSONObject();
		String all_id = "";

		for(String p : id_promoter)
			all_id += p + " ";

		o.put("Promoter", all_id);
		o.put("Tot event", calcoloTot());
		o.put("Tot event for genre", calcoloperGenere());
		o.put("Tot State", calcoloState());

		return o;
	}
}
