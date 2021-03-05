package it.univpm.TicketMasterUS.Service;

import java.time.LocalDate;
import java.util.Vector;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Service;

import it.univpm.TicketMasterUS.Exceptions.EmptyFieldExcetpion;
import it.univpm.TicketMasterUS.Exceptions.ParamException;
import it.univpm.TicketMasterUS.Exceptions.WrongIdPromoterException;
import it.univpm.TicketMasterUS.Exceptions.WrongPeriodException;
import it.univpm.TicketMasterUS.Exceptions.WrongStateCodeException;
import it.univpm.TicketMasterUS.Models.Event;
import it.univpm.TicketMasterUS.Models.Promoter;
import it.univpm.TicketMasterUS.Utils.Filters.Filter;
import it.univpm.TicketMasterUS.Utils.Statistics.GlobalStats;
import it.univpm.TicketMasterUS.Utils.Statistics.StatsPromoter;
import it.univpm.TicketMasterUS.Utils.Statistics.StatsState;

/**
 * Classe per la gestione dei metodi chiamati dalla classe Controller.
 * 
 * @author Alice Moretti
 * @author Arianna Ronci
 */

@Service
public class TicketMasterServiceImpl implements TicketMasterService {

	/**
	 * Metodo che ritorna la lista degli eventi di uno stato scelto dall'utente.
	 * @param state_code codice postale che identifica uno stato.
	 * @return JSONArray che contiene tutti gli eventi e le relative informazioni.
	 * @throws WrongStateCodeException se si e' inserito un state code non ammesso.
	 */
	public JSONArray getEvents(String state_code)throws WrongStateCodeException{

		Downloader d = new Downloader();
		Parser p = new Parser();

		Event events = new Event();
		Vector<Event> e = new Vector<>();

		JSONObject jo = new JSONObject();
		JSONArray ja = new JSONArray();

		if(!(state_code.equals("CA") || state_code.equals("MA") || state_code.equals("FL") || state_code.equals("NY")))
			throw new WrongStateCodeException();

		e = d.EventsInfo(state_code);

		for (Event event: e) {			
			jo = p.eventToJSONObject(event);
			ja.add(jo);
		}

		return ja;
	}

	/**
	 * Metodo che ritorna la lista di promoter di eventi di uno stato scelto dall'utente.
	 * @param state_code codice postale che identifica uno stato.
	 * @return JSONArray che contiene tutti i promoter e le relative informazioni.
	 * @throws WrongStateCodeException se si e' inserito un state code non ammesso.
	 */
	public JSONArray getPromoters(String state_code) throws WrongStateCodeException {

		Downloader d = new Downloader();
		Vector<Event> e = new Vector<>();
		Vector<Promoter> diversi = new Vector<>();

		if(!(state_code.equals("CA") || state_code.equals("MA") || state_code.equals("FL") || state_code.equals("NY")))
			throw new WrongStateCodeException();

		e = d.EventsInfo(state_code);

		for (Event event: e)
			for(Promoter prom : event.getPromoters())
				if(!diversi.contains(prom))
					diversi.add(prom);

		JSONArray ja = new JSONArray();

		for(Promoter prom: diversi) {
			JSONObject jo = new JSONObject();

			jo.put("name promoter", prom.getName());
			jo.put("ID promoter", prom.getID());
			ja.add(jo);
		}

		return ja;
	}


	/**
	 * Metodo che mi restituisce le statistiche di ogni stato.
	 * @return JSONArray che contiene le statistiche per ogni stato.
	 */
	public JSONArray getStatsState() {

		Downloader d = new Downloader();

		JSONArray ja = new JSONArray();

		StatsState sCA = new StatsState(d.EventsInfo("CA"),"CA");
		ja.add(sCA.compliteStats());

		StatsState sFL = new StatsState(d.EventsInfo("FL"), "FL");
		ja.add(sFL.compliteStats());

		StatsState sMA = new StatsState(d.EventsInfo("MA"), "MA");
		ja.add(sMA.compliteStats());

		StatsState sNY = new StatsState(d.EventsInfo("NY"), "NY");
		ja.add(sNY.compliteStats());

		return ja;
	}

	/**
	 * Metodo che mi restituisce le statistiche in base ai promoter inseriti dall'utente.
	 * @param ID Vettore di stringhe ciascuna rappresentate un ID di un promoter 
	 *           per il quale si vuole calcolare le statistiche.
	 * @return JSONArray che contiene le statistiche per ognuno dei promoter inseriti.
	 * @throws WrongIdPromoterException se si e' inserito un ID che non corrisponde a un promoter.
	 * @throws EmptyFieldExcetpion se i campi del body non sono stati inseriti.
	 */
	public JSONArray getStatsPromoters(Vector<String> ID) throws WrongIdPromoterException, EmptyFieldExcetpion {

		Downloader d = new Downloader();

		if(ID.isEmpty()) throw new EmptyFieldExcetpion("Devi inserire almeno un ID.");

		Vector<Event> all_events = new Vector<>();
		all_events.addAll(d.EventsInfo("CA"));
		all_events.addAll(d.EventsInfo("FL"));
		all_events.addAll(d.EventsInfo("MA"));
		all_events.addAll(d.EventsInfo("NY"));

		Vector<String> valid_id = new Vector<>();

		for(Event e: all_events)
			for(Promoter prom: e.getPromoters())
				if(!valid_id.contains(prom.getID()))
					valid_id.add(prom.getID());

		if(!valid_id.containsAll(ID)) throw new  WrongIdPromoterException();

		JSONArray ja = new JSONArray();

		for(String id : ID) {
			StatsPromoter s = new StatsPromoter(all_events, id);
			ja.add(s.compliteStats());
		}		

		return ja;
	}

	/**
	 * Metodo che mi restituisce le statistiche globali:
	 * lo stato con il numero minimo e massimo di eventi nel prossimo mese, 
	 * lo stato con il numero minimo e massimo di promoter di eventi.
	 * @return JSONArray che contiene le statistiche globali.
	 */
	public JSONArray getGlobalStats() {

		Downloader d = new Downloader();

		Vector<Event> e = new Vector<>();
		e.addAll(d.EventsInfo("CA"));
		e.addAll(d.EventsInfo("NY"));
		e.addAll(d.EventsInfo("MA"));
		e.addAll(d.EventsInfo("FL"));

		LocalDate limitData = LocalDate.now().plusMonths(1);
		
		Vector<Event> nextMonthEvent = new Vector<>();
		for(Event event: e) {
			LocalDate dateEvent = LocalDate.parse(event.getData());
			if(limitData.isBefore(dateEvent))
				nextMonthEvent.add(event);
		}
			
		GlobalStats stats = new GlobalStats();

		JSONArray ja = new JSONArray();
		ja.add(stats.min_maxEvent(nextMonthEvent));
		ja.add(stats.min_maxPromoter());

		return ja;
	}

	/**
	 * Metodo che mi restituisce in base al parametro specificato dall'utentre nel body 
	 * la lista degli eventi o le statistiche filtrate.
	 * @param filters JSONObject corpo della richiesta inserito dall'utente.
	 * @return JSONArray.
	 * @throws WrongStateCodeException se si e' inserito un state code non ammesso.
	 * @throws WrongPeriodException se il numero immesso non e' valido come periodo.
	 * @throws EmptyFieldExcetpion se i campi del body non sono stati inseriti.
	 * @throws ParamException se viene inserita una stringa errata come param.	 
	 */
	public JSONArray getFilterStats(JSONObject filters) throws WrongStateCodeException, WrongPeriodException,
	EmptyFieldExcetpion, ParamException{

		JSONArray ja = new JSONArray();
		JSONObject jo = new JSONObject();
		Parser p = new Parser();

		if(p.infoStete(filters).isEmpty() && p.infoGenre(filters).isEmpty() && p.infoPromoter(filters).isEmpty()
				&& !filters.containsKey("periodo"))
			throw new EmptyFieldExcetpion();
		if(!filters.containsKey("param"))
			throw new EmptyFieldExcetpion("Il parametro e' un campo obbligatorio");

		Filter f = new Filter();

		if(filters.containsKey("State")) 
			f.setFilterState(p.infoStete(filters));;
			if(filters.containsKey("Promoter")) 
				f.setFilterPromoter(p.infoPromoter(filters));
			if(filters.containsKey("Genre")) 
				f.setFilterGenre(p.infoGenre(filters));

			if(filters.containsKey("periodo")) {
				long period = (long) filters.get("periodo");
				if( (period<1) || (period>6) ) throw new WrongPeriodException();
				f.setFilterPeriod(period);
			}

			if(filters.get("param").equals("events")) {
				for(Event event: f.getFilteredEvent()) 
					ja.add(p.eventToJSONObject(event));
			}
			else if(filters.get("param").equals("statsState")) {
				StatsState s = new StatsState(f.getFilteredEvent());
				jo.put("Tot promoter", s.calcoloTot());
				jo.put("Tot promoter for genre", s.calcoloperGenere());
				ja.add(jo);
			}
			else if(filters.get("param").equals("statsPromoter")) {
				StatsPromoter s = new StatsPromoter(f.getFilteredEvent());

				jo.put("Tot event", s.calcoloTot());
				jo.put("Tot event for genre", s.calcoloperGenere());
				ja.add(jo);

			}
			else
				throw new ParamException("Il parametro specificato non e' tra quelli disponibili");

			return ja;
	}



}