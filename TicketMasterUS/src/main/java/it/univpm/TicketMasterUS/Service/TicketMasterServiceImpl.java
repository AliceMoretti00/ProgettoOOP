package it.univpm.TicketMasterUS.Service;

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
 * Classe per la gestione dei metodi chiamati dalla classe Controller 
 * @author Alice Moretti
 * @author Arianna Ronci
 */

@Service
public class TicketMasterServiceImpl implements TicketMasterService {


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

	public JSONArray getPromoters(String state_code) throws WrongStateCodeException {

		Downloader d = new Downloader();
		Parser p = new Parser();

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


	public JSONArray getStatsState() {

		Downloader d = new Downloader();

		JSONArray ja = new JSONArray();

		StatsState sCA = new StatsState(d.EventsInfo("CA"), "CA");
		ja.add(sCA.compliteStats());

		StatsState sFL = new StatsState(d.EventsInfo("FL"), "FL");
		ja.add(sFL.compliteStats());

		StatsState sMA = new StatsState(d.EventsInfo("MA"), "MA");
		ja.add(sMA.compliteStats());

		StatsState sNY = new StatsState(d.EventsInfo("NY"), "NY");
		ja.add(sNY.compliteStats());

		return ja;
	}


	public JSONObject getStatsPromoters(Vector<String> ID) throws WrongIdPromoterException, EmptyFieldExcetpion {

		Downloader d = new Downloader();

		JSONObject jo = new JSONObject();

		if(ID.isEmpty()) throw new EmptyFieldExcetpion();

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

		StatsPromoter s = new StatsPromoter(all_events, ID);
		jo.put("Stats promoter", s.compliteStats());

		return jo;
	}

	public JSONArray getGlobalStats() {

		Downloader d = new Downloader();

		Vector<Event> e = new Vector<>();
		e.addAll(d.EventsInfo("CA"));
		e.addAll(d.EventsInfo("NY"));
		e.addAll(d.EventsInfo("MA"));
		e.addAll(d.EventsInfo("FL"));

		GlobalStats stats = new GlobalStats();

		JSONArray ja = new JSONArray();
		ja.add(stats.min_maxEvent(e));
		ja.add(stats.min_maxPromoter());

		return ja;
	}

	public JSONArray getFilterStats(JSONObject filters) throws WrongStateCodeException, WrongPeriodException,
	EmptyFieldExcetpion, ParamException{

		JSONArray ja = new JSONArray();
		JSONObject jo = new JSONObject();
		Parser p = new Parser();

		if(p.infoStete(filters).isEmpty() && p.infoGenre(filters).isEmpty() && p.infoPromoter(filters).isEmpty()
				&& filters.containsKey("periodo"))
			throw new EmptyFieldExcetpion();

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
			jo.put("Tot State", s.calcoloState());
			ja.add(jo);

		}
		else
			throw new ParamException();

		return ja;
	}



}