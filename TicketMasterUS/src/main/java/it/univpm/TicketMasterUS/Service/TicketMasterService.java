package it.univpm.TicketMasterUS.Service;

import java.util.Vector;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import it.univpm.TicketMasterUS.Exceptions.EmptyFieldExcetpion;
import it.univpm.TicketMasterUS.Exceptions.ParamException;
import it.univpm.TicketMasterUS.Exceptions.WrongIdPromoterException;
import it.univpm.TicketMasterUS.Exceptions.WrongPeriodException;
import it.univpm.TicketMasterUS.Exceptions.WrongStateCodeException;

/** 
 * Interfaccia di TicketMasterServiceImpl e contiene i metodi richiamati dal Controller.
 * 
 * @author Alice Moretti
 * @author Arianna Ronci 
 */
public interface TicketMasterService {

	public abstract JSONArray getEvents(String state_code) throws WrongStateCodeException;
	public abstract JSONArray getPromoters(String state_code)throws WrongStateCodeException;
	public abstract JSONArray getStatsState();
	public abstract JSONArray getStatsPromoters(Vector<String> ID)throws WrongIdPromoterException, EmptyFieldExcetpion;
	public abstract JSONArray getGlobalStats();
	public abstract JSONArray getFilterStats(JSONObject filters) throws WrongStateCodeException, WrongPeriodException, EmptyFieldExcetpion, ParamException;
}
