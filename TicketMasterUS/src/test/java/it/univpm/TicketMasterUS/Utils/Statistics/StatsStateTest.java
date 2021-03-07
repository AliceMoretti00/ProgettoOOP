package it.univpm.TicketMasterUS.Utils.Statistics;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Vector;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import it.univpm.TicketMasterUS.Models.Event;

public class StatsStateTest {
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
		num = stats.calcoloTot();
		assertEquals(num,0);
	}
}
