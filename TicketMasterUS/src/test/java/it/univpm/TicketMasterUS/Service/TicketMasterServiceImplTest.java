package it.univpm.TicketMasterUS.Service;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Vector;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import it.univpm.TicketMasterUS.Exceptions.WrongPeriodException;
import it.univpm.TicketMasterUS.Exceptions.WrongStateCodeException;

/** 
 * Classe che testa se viene lanciata correttamente l'eccezione WrongStateCodeException.
 * 
 * @author Alice Moretti
 * @author Arianna Ronci
 */ 
public class TicketMasterServiceImplTest {
	
	private TicketMasterServiceImpl service;
	private String stateCode;
	
	/** 
	 * Inizializza i componenti che verranno utilizzati per testare i metodi
	 * @throws Exception
	 */
	@BeforeEach
	void setUp() throws Exception{
		service = new TicketMasterServiceImpl();
		stateCode = "LA";
	}
	
	/**
	 * Distrugge ciò che è stato inizializzato nel metodo setUp
	 * @throws Exception
	 */
	@AfterEach
	void tearDown() throws Exception{
		
	}
	
	/**
	 * Test che verifica se viene generata correttamente l'eccezione WrongStateCode
	 */
	@Test
	@DisplayName("Corretta generazione dell'eccezione WrongStateCodeException")
	void stateCodeTest() {		
		assertThrows(WrongStateCodeException.class, () -> service.getEvents(stateCode));
	}
}
