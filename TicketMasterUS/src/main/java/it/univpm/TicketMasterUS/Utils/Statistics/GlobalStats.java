package it.univpm.TicketMasterUS.Utils.Statistics;

import java.util.Vector;

import org.json.simple.JSONObject;

import it.univpm.TicketMasterUS.Models.Event;
import it.univpm.TicketMasterUS.Service.Downloader;

/**
 * Classe che calcola le statistiche globali.
 * @author Alice Moretti
 * @author Arianna Ronci
 */
public class GlobalStats {

	/**
	 * Metodo che calcola gli stato con il maggiore e con il minor numero di eventi nell'arco di un mese.
	 * @param e Vettori di eventi in tutti gli stati considerati.
	 * @return JSONObject che rappresenta gli stati con il minore e il maggior numero di eventi.
	 */
	public JSONObject min_maxEvent(Vector<Event> e) {

		String state_code = "";
		int numCA = 0;
		int numFL = 0;
		int numMA = 0;
		int numNY = 0;

		for(Event event: e) {
			state_code = event.getPlace().getState_code();
			
			switch(state_code) {
			case "CA": 
				numCA++; 
				break;
			case "FL": 
				numFL++;
				break;
			case "MA": 
				numMA++; 
				break;
			case "NY":
				numNY++; 
				break;
			}	
		}

		int[] n = {numCA, numFL, numNY, numMA};
		int min = n[0]; int max = n[0];
		for(int i = 1; i < n.length; i++) {
			if(min > n[i]) min = n[i];
			if(max < n[i]) max = n[i];

		}

		JSONObject o = new JSONObject();

		if(min == numCA) o.put("Stato con il numero minimo di eventi: ", "California");
		if(min == numFL) o.put("Stato con il numero minimo di eventi: ", "Florida");
		if(min == numMA) o.put("Stato con il numero minimo di eventi: ", "Massachussets");
		if(min == numNY) o.put("Stato con il numero minimo di eventi: ", "New York");

		if(max == numCA) o.put("Stato con il numero massimo di eventi: ", "California");
		if(max == numFL) o.put("Stato con il numero massimo di eventi: ", "Florida");
		if(max == numMA) o.put("Stato con il numero massimo di eventi: ", "Massachussets");
		if(max == numNY) o.put("Stato con il numero massimo di eventi: ", "New York");

		return o;
	}

	/**
	 * Metodo che calcola gli stato con il maggiore e con il minor numero di promoter.
	 * @return JSONObject che rappresenta gli stati con il minore e il maggior numero di promoter.
	 */
	public JSONObject min_maxPromoter() {

		int numCA = 0;
		int numFL = 0;
		int numMA = 0;
		int numNY = 0;

		Downloader d = new Downloader();

		StatsState sCA = new StatsState(d.EventsInfo("CA"), "CA");
		numCA = sCA.calcoloTot();

		StatsState sFL = new StatsState(d.EventsInfo("FL"), "FL");
		numFL = sFL.calcoloTot();

		StatsState sMA = new StatsState(d.EventsInfo("MA"), "MA");
		numMA = sMA.calcoloTot();

		StatsState sNY = new StatsState(d.EventsInfo("NY"), "NY");
		numNY = sNY.calcoloTot();

		int[] n = {numCA, numFL, numMA, numNY};
		int min = n[0]; int max = n[0];
		for(int i = 1; i < n.length; i++) {
			if(min > n[i]) min = n[i];
			if(max < n[i]) max = n[i];

		}

		JSONObject o = new JSONObject();

		if(min == numCA) o.put("Stato con il numero minimo di promoter: ", "California");
		if(min == numFL) o.put("Stato con il numero minimo di promoter: ", "Florida");
		if(min == numMA) o.put("Stato con il numero minimo di promoter: ", "Massachussets");
		if(min == numNY) o.put("Stato con il numero minimo di promoter: ", "New York");

		if(max == numCA) o.put("Stato con il numero massimo di promoter: ", "California");
		if(max == numFL) o.put("Stato con il numero massimo di promoter: ", "Florida");
		if(max == numMA) o.put("Stato con il numero massimo di promoter: ", "Massachussets");
		if(max == numNY) o.put("Stato con il numero massimo di promoter: ", "New York");

		return o;
	}
}
