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
import it.univpm.TicketMasterUS.Exceptions.WrongIdPromoterException;
import it.univpm.TicketMasterUS.Exceptions.WrongStateCodeException;
import it.univpm.TicketMasterUS.Service.Parser;
import it.univpm.TicketMasterUS.Service.TicketMasterService;

@RestController
public class Controller {

	@Autowired
	TicketMasterService service;

	@GetMapping("/events")
	public ResponseEntity<Object> getEvents(@RequestParam(name="statecode", defaultValue= "NY") String state_code) throws WrongStateCodeException
	{
		return new ResponseEntity<>(service.getEvents(state_code), HttpStatus.OK);
	}


	@GetMapping("/promoters")
	public ResponseEntity<Object> getPromoters(@RequestParam(name="statecode", defaultValue= "NY") String state_code) throws WrongStateCodeException
	{
		return new ResponseEntity<>(service.getPromoters(state_code), HttpStatus.OK);
	}


	@PostMapping(value="/statsState")
	public ResponseEntity<Object> statsState() {

		return new ResponseEntity<>(service.getStatsState(), HttpStatus.OK);

	}
	
	@PostMapping(value="/globalStats")
	public ResponseEntity<Object> globalStats() {

		return new ResponseEntity<>(service.getGlobalStats(), HttpStatus.OK);

	}

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

		promoters = p.filterPromoter(jo);

		return new ResponseEntity<>(service.getStatsPromoters(promoters), HttpStatus.OK);

	}

}
