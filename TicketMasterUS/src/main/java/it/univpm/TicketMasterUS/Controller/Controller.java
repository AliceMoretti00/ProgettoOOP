package it.univpm.TicketMasterUS.Controller;

import java.util.Vector;

import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import it.univpm.TicketMasterUS.Exceptions.EmptyFieldExcetpion;
import it.univpm.TicketMasterUS.Exceptions.ParamException;
import it.univpm.TicketMasterUS.Exceptions.WrongIdPromoterException;
import it.univpm.TicketMasterUS.Exceptions.WrongPeriodException;
import it.univpm.TicketMasterUS.Exceptions.WrongStateCodeException;
import it.univpm.TicketMasterUS.Service.Parser;
import it.univpm.TicketMasterUS.Service.TicketMasterService;

/**
 * Classe che gestisce tutte le chiamate al server che il client può fare.
 * 
 * @author Alice Moretti
 * @author Arianna Ronci
 */
@RestController
public class Controller {

	@Autowired
	TicketMasterService service;

	/**
	 * Rotta di tipo GET che restituisce le informazioni su gli eventi futuri che si terrano 
	 * nello stato identificato dallo state code inserito dall'utente.
	 * 
	 * @param state_code codice postale che identifica lo stato di cui si richiede l'elenco degli eventi futuri.
	 * @return elenco degli eventi futuri che si terranno in quello stato specificato.
	 * @throws WrongStateCodeException se si è inserito un state code non ammesso.
	 */
	@GetMapping(value="/events")
	public ResponseEntity<Object> getEvents(@RequestParam(name="statecode", defaultValue= "NY") String state_code) throws WrongStateCodeException
	{
		return new ResponseEntity<>(service.getEvents(state_code), HttpStatus.OK);
	}

	/**
	 * Rotta di tipo GET che restituisce le informazioni sui promoter che sponsorizzano eventi
	 * nello stato identificato dallo state code inserito dall'utente.
	 * 
	 * @param state_code codice postale che identifica lo stato di cui si richiede l'elenco degli eventi futuri.
	 * @return elenco dei promoter che sponsorizzano eventi nello stato specificato.
	 * @throws WrongStateCodeException se si è inserito un state code non ammesso.
	 */
	@GetMapping(value="/promoters")
	public ResponseEntity<Object> getPromoters(@RequestParam(name="statecode", defaultValue= "NY") String state_code) throws WrongStateCodeException
	{
		return new ResponseEntity<>(service.getPromoters(state_code), HttpStatus.OK);
	}

	/**
	 * Rotta di tipo POST che restituisce le statistiche per ogni stato: 
	 * il numero totale di promoter che sponsorizzano eventi in quello stato, 
	 * il numero di promoter raggruppati per il genere di eventi che sponsorizzano.
	 * 
	 * @return statistiche per ogni stato.
	 */
	@PostMapping(value="/statsState")
	public ResponseEntity<Object> statsState() {

		return new ResponseEntity<>(service.getStatsState(), HttpStatus.OK);

	}

	/**
	 * Rotta di tipo POST che restituisce le statistiche globali: 
	 * lo stato con il numero minimo e massimo di eventi nel prossimo mese, 
	 * lo stato con il numero minimo e massimo di promoter di eventi. 
	 * 
	 * @return statistiche globali.
	 */
	@PostMapping(value="/globalStats")
	public ResponseEntity<Object> globalStats() {

		return new ResponseEntity<>(service.getGlobalStats(), HttpStatus.OK);

	}

	/**
	 * Rotta di tipo POST che restituisce le statistiche in base a uno o più promoter
	 * inseriti dell'utentenel corpo della richiesta.
	 * Per ogni promoter retituisce il numeron di eventi totali, eventi per ogni genere 
	 * e numero di stati in cui il promoter sponsorizza l'evento.
	 * L'utente deve inserire un JSONObject di questo tipo:
	 * {
	 *     "Promoter": [
	 *        {
	 *          "ID": "000"
	 *        },
	 *        {
	 *          "ID": "494"
	 *        }
	 *      ]
	 *  }
	 * 
	 * @param body è un JSONObject come indicato.
	 * @return statistiche per ciascuno dei promoter scelti dell'utente.
	 * 
	 * @throws WrongIdPromoterException se si è inserito un ID che non corrisponde a un promoter.
	 * @throws EmptyFieldExcetpion se i campi del body non sono stati inseriti.
	 * @throws ParseException
	 */
	@PostMapping(value="/statsPromoter")
	public ResponseEntity<Object> statsPromoter(@RequestBody String body) throws WrongIdPromoterException, EmptyFieldExcetpion, ParseException {

		Parser p = new Parser();
		Vector<String> promoters = new Vector<>();
		JSONObject jo = new JSONObject();
		try {

			jo =(JSONObject) JSONValue.parseWithException(body);

		} catch(ParseException e){
			e.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}

		if(jo.isEmpty()) throw new EmptyFieldExcetpion();

		promoters = p.infoPromoter(jo);

		return new ResponseEntity<>(service.getStatsPromoters(promoters), HttpStatus.OK);

	}

	/**
	 * Rotta di tipo POST che filtra le informazione che vengono richieste (in base al valore del param).
	 * L'utente deve inserire un JSONObject di questo tipo:
	 * {
	 *     "Promoter": [
	 *        {
	 *          "ID": "000"
	 *        },
	 *        {
	 *          "ID": "494"
	 *        }
	 *      ],
	 *      
	 *     "Genre": [
	 *        {
	 *          "name genre": "Music"
	 *        },
	 *        {
	 *          "name genre": "Sports"
	 *        }
	 *      ],
	 *      "State": [
	 *        {
	 *          "state code": "MA"
	 *        }
	 *      ],
	 *      
	 *     "param": "event",
	 *     "period": 1
	 *  }
	 * (non è necessario che tutti i campi siano presenti; è possibile filtrare solo sulla base di alcune voci,
	 * ma è necessario che almeno uno dei campi sia inserito e che sia specificato il capo param)
	 * 
	 * @param body è un JSONObject come indicato.
	 * @return JSONArray che contiene ciò che viene richiesto dall'utente filtrato sulla base dei campi del body.
	 * @throws EmptyFieldExcetpion se nessuno dei campi del body sono stati inseriti.
	 * @throws WrongPeriodException se il numero immesso non è valido come periodo.
	 * @throws ParamException se viene inserita una stringa errata come param.	 
	 * @throws WrongStateCodeException se si è inserito un state code non ammesso.
	 * @throws ParseException
	 */
	@PostMapping(value="/filteredStats")
	public ResponseEntity<Object> filteredStats(@RequestBody String body) throws EmptyFieldExcetpion,
	ParseException, WrongStateCodeException, WrongPeriodException, ParamException {

		JSONObject jo = new JSONObject();
		try {

			jo =(JSONObject) JSONValue.parseWithException(body);

		} catch(ParseException e){
			e.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}

		if(jo.isEmpty()) throw new EmptyFieldExcetpion();


		return new ResponseEntity<>(service.getFilterStats(jo), HttpStatus.OK);

	}

}
