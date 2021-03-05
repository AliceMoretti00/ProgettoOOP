package it.univpm.TicketMasterUS.Utils.Statistics;

import java.util.Vector;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import it.univpm.TicketMasterUS.Models.Event;
import it.univpm.TicketMasterUS.Models.Promoter;

/**
 * Classe che implementa l'interfaccia Stats e calcola le statistiche per un promoter.
 * 
 * @author Alice Moretti
 * @author Arianna Ronci
 */
public class StatsPromoter implements Stats {

	private Vector<Event> events_stats= new Vector<>();

	private String id_promoter;

	/**
	 * Costrutture dell'oggetto.
	 * @param Vettore di eventi.
	 */
	public StatsPromoter(Vector<Event> events_stats) {
		super();
		this.events_stats = events_stats;
	}

	/**
	 * Costrutture dell'oggetto.
	 * @param Vettore di eventi.
	 * @param Stringa rappresentante un id di un promoter.
	 */
	public StatsPromoter(Vector<Event> e, String id_promoter) {
		super();
		this.id_promoter = id_promoter;
		boolean daAggiungere = false;

		//costruisco il mio vettore di eventi selezionando solo quelli che hanno uno dei promoter specificati
		for(Event event: e) {
			for(Promoter p: event.getPromoters()) {
				if(p.getID().equals(id_promoter))
					daAggiungere = true;
			}
			if(daAggiungere) { 
				events_stats.add(event);
				daAggiungere = false;
			}
		}
	}


	/**
	 * Metodo che calcola il numero totale di eventi che sono sponsorizzati dal promoter
	 * identificato dell'attributo della classe id_promoter.
	 * @return numero totale dei eventi che verificano la condizione.
	 */
	public int calcoloTot() {

		return events_stats.size();
	}

	/**
	 * Metodo che calcola il numero totale di eventi che sono sponsorizzati dal promoter 
	 * identificato dell'attributo della classe id_promoter raggruppati per il genere di eventi.
	 * @return JSONObject che rappresenta il numero di eventi raggruppati per genere.
	 */
	public JSONObject calcoloperGenere() {

		//i generi disponibili sono: Arts&Theatre, Sports, Music, Miscellaneous 
		//gli altri eventi sarranno raggruppati sotto il genere Altro
		String genre = " ";
		int numArt = 0; 
		int numSport = 0; 
		int numMusic = 0; 
		int numMisc = 0;
		int numAltro = 0; 

		Vector<Event> event= new Vector<>();
		for(Event e: events_stats) {
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
			case "Miscellaneous":
				numMisc ++;
				break;
			default:
				numAltro ++;
			}
		}

		JSONObject o = new JSONObject();
		o.put("Art&Theatre events", numArt);
		o.put("Music events", numMusic);
		o.put("Sport events", numSport);
		o.put("Miscellaneous events", numMisc);
		o.put("Other genre events", numAltro);
		return o;
	}


	/**
	 * Metodo che calcola il numero totale di stati in cui il promoter 
	 * identificato dell'attributo della classe id_promoter sponsorizza almeno un evento.
	 * @return numero totale dei stati che verificano la condizione.
	 */
	public int calcoloState() {

		int numState = 0;
		boolean pCA = false;
		boolean pFL = false;
		boolean pMA = false;
		boolean pNY = false;

		for(Event e: events_stats){
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

	/**
	 * Metodo che costruisce un JSONObject rappresentate le statiste complete per un promoter.
	 * @return JSONObject con le statistiche complete. 
	 */
	public JSONObject compliteStats() {

		JSONObject jo = new JSONObject();

		jo.put("Promoter", this.id_promoter);
		jo.put("Tot event", calcoloTot());
		jo.put("Tot event for genre", calcoloperGenere());
		jo.put("Tot State", calcoloState());

		return jo;
	}
}
