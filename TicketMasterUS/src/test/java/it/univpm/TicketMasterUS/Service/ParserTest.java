package it.univpm.TicketMasterUS.Service;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Vector;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import it.univpm.TicketMasterUS.Models.Event;
import it.univpm.TicketMasterUS.Models.Genre;
import it.univpm.TicketMasterUS.Models.Place;
import it.univpm.TicketMasterUS.Models.Promoter;

/** 
 * Classe che testa il metodo eventToJSONObject.
 * 
 * @author Alice Moretti
 * @author Arianna Ronci
 */
public class ParserTest {
	Event event;
	Vector<Promoter> promoter;
	Promoter prom;
	Place place;
	Genre genre;
	Parser parser;
	
	/**
	 * Inizializza i componenti che verranno utilizzati per testare i metodi
	 * @throws Exception
	 */
	@BeforeEach
	void setUp() throws Exception{
		event = new Event();
		promoter = new Vector<>();
		place = new Place("Florida", "FL");
		genre = new Genre("KND", "Sports");
		parser = new Parser();
	}

	/**
	 * Distrugge ciò che è stato inizializzato nel metodo setUp
	 * @throws Exception
	 */
	@AfterEach
	void tearDown() throws Exception{

	}
	
	/**
	 * Test che verifica se l'oggetto event viene convertito correttamente in un JSONObject
	 */
	@Test
	@DisplayName("Corretto parsing")
	void toJSONObjectTest() {	
		event.setName("Florida vs Detroit");
		event.setUrl("https://www.ticketmaster.com");
		event.setData("2021-03-06");	
		
		prom = new Promoter("NHL REGULAR SEASON", "656");
		promoter.add(prom);
		event.setPromoters(promoter);
		
		event.setPlace(place);
		event.setGenre(genre);
		
		Event e = new Event("Florida vs Detroit", "https://www.ticketmaster.com", "2021-03-06", promoter, place, genre);
		
		JSONObject eventObj = new JSONObject();
		
		eventObj.put("name", e.getName());
		eventObj.put("url", e.getUrl());
		eventObj.put("data", e.getData());
		
		JSONArray a = new JSONArray();
		JSONObject pr = new JSONObject();
		pr.put("name promoter", e.getPromoters().get(0).getName());
		pr.put("ID promoter", e.getPromoters().get(0).getID());
		a.add(pr);
		eventObj.put("promoters", a);
		
		JSONObject p = new JSONObject();
		p.put("state", e.getPlace().getState());
		p.put("state code", e.getPlace().getState_code());
		eventObj.put("place", p);
		
		JSONObject g = new JSONObject();
		g.put("ID genre", e.getGenre().getID());
		g.put("name genre", e.getGenre().getName());
		eventObj.put("genre", g);
		
		assertEquals(eventObj.toString(), parser.eventToJSONObject(e).toString());
	}
}