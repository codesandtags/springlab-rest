package com.testing;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingController {

	private final AtomicLong id = new AtomicLong();
	private final String template = "Hello %s!";
	
	@RequestMapping(value="/", method=RequestMethod.GET)
	public String index(){
		StringBuffer html = new StringBuffer();
		html.append("<h2>Este es un ejemplo de una aplicacion REST muy simple! :D</h2><hr>");
		html.append("<ul>");
		html.append("<li><a href=\"greeting\">Saludo Simple</a></li>");
		html.append("<li><a href=\"greetings\">Lista de saludos</a></li>");
		html.append("</ul>");
		return html.toString();
	}
	
	@RequestMapping("/greeting")
	public Greeting greeting(@RequestParam(value = "name", defaultValue = "World", required = false) String name){
		return new Greeting(id.getAndIncrement(), 
							String.format(template, name));
	}
	
	@RequestMapping("/greetings")
	public List<Greeting> greetings(@RequestParam(value = "name", defaultValue = "World", required = false) String name){
		
		List<Greeting> greetings = new ArrayList<Greeting>();
		greetings.add(new Greeting(id.getAndIncrement(), String.format("Benvindo %s!", name)));
		greetings.add(new Greeting(id.getAndIncrement(), String.format("Tonces %s!", name)));
		greetings.add(new Greeting(id.getAndIncrement(), String.format("Hallo %s!", name)));
		greetings.add(new Greeting(id.getAndIncrement(), String.format("Salut %s!", name)));
		
		
		return greetings;
	}
	
	
	
}