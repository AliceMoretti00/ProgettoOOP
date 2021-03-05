package it.univpm.TicketMasterUS.Utils.Statistics;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 * Interfaccia per le statistiche.
 * 
 * @author Alice Moretti
 * @author Arianna Ronci
 */
public interface Stats {

	public abstract int calcoloTot();
	public abstract JSONObject calcoloperGenere();
	public JSONObject compliteStats();
}
