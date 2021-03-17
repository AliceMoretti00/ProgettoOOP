package it.univpm.TicketMasterUS.Utils.Statistics;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Vector;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import it.univpm.TicketMasterUS.Models.Event;
import it.univpm.TicketMasterUS.Models.Genre;
import it.univpm.TicketMasterUS.Models.Place;
import it.univpm.TicketMasterUS.Models.Promoter;

/** 
 * Classe che testa il metodo calcoloTot. 
 * 
 * @author Alice Moretti
 * @author Arianna Ronci
 */
public class StatsStateTest {
	Vector<Promoter> promoter;
	Promoter prom;
	Place place;
	Genre genre;
	Vector<Event> event;
	StatsState stats;
	int num;

	/**
	 * Inizializza i componenti che verranno utilizzati per testare i metodi
	 * @throws Exception
	 */
	@BeforeEach
	void setUp() throws Exception{
		event = new Vector<>();
		stats = new StatsState(event);
		promoter = new Vector<>();
		place = new Place("Florida", "FL");
		genre = new Genre("KND", "Sports");
	}

	/**
	 * Distrugge ciò che è stato inizializzato nel metodo setUp
	 * @throws Exception
	 */
	@AfterEach
	void tearDown() throws Exception{

	}

	/**
	 * Test che verifica il corretto funzionamento del metodo calcoloTot 
	 */
	@Test
	@DisplayName("Corretto funzionamento del metodo calcoloTot")
	void infoPromoterTest() {
		prom = new Promoter("NHL REGULAR SEASON", "656");
		promoter.add(prom);
		
		Event e = new Event("Florida vs Detroit", "https://www.ticketmaster.com", "2021-03-06", promoter, place, genre);
		event.add(e);
		
		num = stats.calcoloTot();
		assertEquals(num,1);
	}
}
