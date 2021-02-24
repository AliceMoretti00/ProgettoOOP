package it.univpm.TicketMasterUS.Service;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import it.univpm.TicketMasterUS.Exceptions.EmptyFieldExcetpion;
import it.univpm.TicketMasterUS.Exceptions.GenreNotFoundException;
import it.univpm.TicketMasterUS.Exceptions.NoBodyException;
import it.univpm.TicketMasterUS.Exceptions.WrongIdPromoterException;
import it.univpm.TicketMasterUS.Exceptions.WrongPeriodException;
import it.univpm.TicketMasterUS.Exceptions.WrongStateCodeException;

public interface TicketMasterService {

	public abstract JSONArray getEvents(String state_code);
	public abstract JSONArray getPromoters(String state_code)throws WrongStateCodeException;
	public abstract JSONArray getStatsState();
	public abstract JSONArray getStatsPromoters(String ID)throws WrongIdPromoterException, EmptyFieldExcetpion;
	public abstract JSONArray getFilterStats(JSONObject filters) throws NoBodyException, WrongStateCodeException, WrongIdPromoterException, WrongPeriodException, GenreNotFoundException;
}
