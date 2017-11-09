package edu.csupomona.cs480.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class WebController {
	@RequestMapping(value="/home/",method = RequestMethod.GET)
    public String homePage(){
        return "paired";
        
    }
	@RequestMapping(value="/events/",method = RequestMethod.GET)
    public String eventPage(){
        return "pairedEvent";
    }
	@RequestMapping(value="/yourEvent/",method = RequestMethod.GET)
    public String yourEventsPage(){
        return "pairedYourEvents";
    }
	@RequestMapping(value="/createEvent/",method = RequestMethod.GET)
    public String createEventPage(){
        return "pairedCreateEvent";
    }
	@RequestMapping(value="/testDB/",method = RequestMethod.GET)
    public String dbTestPage(){
        return "dbTest";
    }
}