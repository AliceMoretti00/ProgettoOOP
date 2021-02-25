package it.univpm.TicketMasterUS.Utils.Statistics;

import java.util.Vector;

import org.json.simple.JSONObject;

import it.univpm.TicketMasterUS.Models.Event;
import it.univpm.TicketMasterUS.Models.Promoter;

public class StatsPromoter implements Stats {

	public Vector<Event> events_stats= new Vector<>();

	private String id_promoter;

	public StatsPromoter(Vector<Event> e, String id_promoter) {
		super();
		this.id_promoter = id_promoter;
		boolean daAggiungere = false;

		//costruisco il mio vettore di eventi selezionando solo quelli che hanno il promoter specificato
		for(Event event: e) {
			for(Promoter p: event.getPromoters()){
				if(p.getID().equals(id_promoter)) 
					daAggiungere = true;
			}
			if(daAggiungere) 
				events_stats.add(event);
		}
	}

	public int CalcoloTot() {

		return 0;
	}


	public JSONObject CalcoloperGenere() {

		return null;
	}

}
