package it.univpm.TicketMasterUS.Utils.Filters;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;
import java.util.Vector;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import it.univpm.TicketMasterUS.Models.Event;
import it.univpm.TicketMasterUS.Models.Promoter;
import it.univpm.TicketMasterUS.Service.Downloader;
import it.univpm.TicketMasterUS.Service.Parser;

public class Filter {

	private Vector<String> filterState = new Vector<>();
	private Vector<String> filterGenre = new Vector<>();
	private Vector<String> filterPromoter = new Vector<>();
	private long filterPeriod;
	private String param;

	private Vector<Event> filteredEvent = new Vector<>();

	public Filter() {
		super();
	}

	public Filter(Vector<String> filterState, Vector<String> filterGenre, Vector<String> filterPromoter) {
		super();
		this.filterState = filterState;
		this.filterGenre = filterGenre;
		this.filterPromoter = filterPromoter;
		this.filterPeriod = 0;
	}

	public void filtering() {

		Vector<Event> e = new Vector<>();

		Downloader d = new Downloader();
		Parser p = new Parser();

		if(!filterState.isEmpty()) {
			for(String state_code: filterState)
				e.addAll(d.EventsInfo(state_code));
		}
		else{
			e.addAll(d.EventsInfo("CA"));
			e.addAll(d.EventsInfo("NY"));
			e.addAll(d.EventsInfo("MA"));
			e.addAll(d.EventsInfo("FL"));
		}

		boolean toAdd = true;

		for (Event event: e) {

			toAdd = true;

			if(!filterPromoter.isEmpty()) {
				int validID = 0;
				for(Promoter prom: event.getPromoters()) 
					if(filterPromoter.contains(prom.getID())) validID++;

				if(validID == 0) toAdd = false;
			}

			if(!filterGenre.isEmpty())
				if(!filterGenre.contains(event.getGenre().getName())) toAdd = false;

			if(filterPeriod != 0) {
				LocalDate limitData = LocalDate.now().plusMonths(filterPeriod);
				LocalDate dateEvent = LocalDate.parse(event.getData());

				if(dateEvent.isAfter(limitData)) toAdd = false;
			}

			if(toAdd) filteredEvent.add(event);
		}
	}


	public long getFilterPeriod() {
		return filterPeriod;
	}


	public void setFilterPeriod(long filterPeriod) {
		this.filterPeriod = filterPeriod;
	}

	public Vector<String> getFilterState() {
		return filterState;
	}

	public void setFilterState(Vector<String> filterState) {
		this.filterState = filterState;
	}

	public Vector<String> getFilterGenre() {
		return filterGenre;
	}

	public void setFilterGenre(Vector<String> filterGenre) {
		this.filterGenre = filterGenre;
	}

	public Vector<String> getFilterPromoter() {
		return filterPromoter;
	}

	public void setFilterPromoter(Vector<String> filterPromoter) {
		this.filterPromoter = filterPromoter;
	}

	public Vector<Event> getFilteredEvent() {
		filtering();
		return filteredEvent;
	}

	public void setFilteredEvent(Vector<Event> filteredEvent) {
		this.filteredEvent = filteredEvent;
	}
	
	public String getParam() {
		return param;
	}

	public void setParam(String param) {
		this.param = param;
	}


}
