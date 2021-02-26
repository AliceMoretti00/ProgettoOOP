package it.univpm.TicketMasterUS.Service;

import java.util.Vector;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Service;

import it.univpm.TicketMasterUS.Exceptions.EmptyFieldExcetpion;
import it.univpm.TicketMasterUS.Exceptions.WrongIdPromoterException;
import it.univpm.TicketMasterUS.Exceptions.WrongPeriodException;
import it.univpm.TicketMasterUS.Exceptions.WrongStateCodeException;
import it.univpm.TicketMasterUS.Models.Event;
import it.univpm.TicketMasterUS.Utils.Statistics.StatsPromoter;
import it.univpm.TicketMasterUS.Utils.Statistics.StatsState;

/**
 * Classe per la gestione dei metodi chiamati dalla classe Controller 
 * @author Alice
 * @author Arianna
 */

@Service
public class TicketMasterServiceImpl implements TicketMasterService {

	Downloader d = new Downloader();
	Parser p = new Parser();

	public JSONArray getEvents(String state_code)throws WrongStateCodeException{

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

		Vector<Event> e = new Vector<>();

		if(!(state_code.equals("CA") || state_code.equals("MA") || state_code.equals("FL") || state_code.equals("NY")))
			throw new WrongStateCodeException();

		e = d.EventsInfo(state_code);

		JSONArray ja = new JSONArray();
		JSONObject jo = new JSONObject();

		for (Event event: e) {
			jo = p.eventToJSONObject(event);
			ja.add(jo.get("promoters"));
		}

		return ja;
	}


	public JSONArray getStatsState() {

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

		JSONObject jo = new JSONObject();

		if(ID == null) throw new EmptyFieldExcetpion();

		Vector<Event> all_events = new Vector<>();
		for(Event e: d.EventsInfo("CA") )
			all_events.add(e);
		for(Event e: d.EventsInfo("FL") )
			all_events.add(e);
		for(Event e: d.EventsInfo("MA") )
			all_events.add(e);
		for(Event e: d.EventsInfo("NY") )
			all_events.add(e);

		int numID = ID.size();
		int numContained = 0;
		for(Event e: all_events)
			for(String id : ID)
				if(!e.getPromoters().contains(id)) numContained++; 
		if(numContained == 0) throw new  WrongIdPromoterException();

		StatsPromoter s = new StatsPromoter(all_events, ID);
		jo.put("Stats promoter", s.compliteStats());

		return jo;
	}


	public JSONArray getFilterStats(JSONObject filters) throws WrongStateCodeException,
	WrongIdPromoterException, WrongPeriodException {
		return null;
	}



}