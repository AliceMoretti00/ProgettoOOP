package it.univpm.TicketMasterUS.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import it.univpm.TicketMasterUS.Exceptions.WrongStateCodeException;
import it.univpm.TicketMasterUS.Service.TicketMasterService;

@RestController
public class Controller {

	@Autowired
	TicketMasterService service;
	
	@GetMapping("/events")
	public ResponseEntity<Object> getEvents(@RequestParam(name="stateCode", defaultValue= "NY") String stateCode) throws WrongStateCodeException
	{
		return new ResponseEntity<>(service.getEvents(stateCode), HttpStatus.OK);
	}
	
	
}
