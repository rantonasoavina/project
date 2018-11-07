/**
 * 
 */
package com.webmvc.controller;

import java.util.Arrays;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.webmvc.model.Person;
import com.webmvc.repo.PersonRepository;

/**
 * @author Ranto
  * modif1
 * modif2
 */
@Controller
public class WebController {

	@Autowired
	PersonRepository repository;
	
	org.slf4j.Logger log = LoggerFactory.getLogger(this.getClass());
	
	@RequestMapping(value="/form", method=RequestMethod.GET)
    public String customerForm(Model model) {
        model.addAttribute("person", new Person("", ""));
        return "form";
    }
 
    @RequestMapping(value="/form", method=RequestMethod.POST)
    public String customerSubmit(@ModelAttribute Person person, Model model) {
         
        model.addAttribute("person", person);
        String info = String.format("Customer Submission: firstname = %s, lastname = %s", person.getFirstName(), person.getLastName());
        log.info(info);
        
        // save a single Person
 		repository.save(person);
         
        return "result";
    }
	
	@RequestMapping("/save")
	public String process(){
		// save a single Person
		repository.save(new Person("Jack", "Smith"));
		
		// save a list of Persons
		repository.saveAll(Arrays.asList(new Person("Adam", "Johnson"), new Person("Kim", "Smith"),
										new Person("David", "Williams"), new Person("Peter", "Davis")));
		
		return "Done";
	}
	
	
	@RequestMapping("/findall")
	public String findAll(){
		String result = "";
		
		for(Person cust : repository.findAll()){
			result += cust.toString() + "<br>";
		}
		
		return result;
	}
	
	@RequestMapping("/findbyid")
	public String findById(@RequestParam("id") long id){
		String result = "";
		result = repository.findById(id).toString();
		return result;
	}
	
	@RequestMapping("/findbylastname")
	public String fetchDataByLastName(@RequestParam("lastname") String lastName){
		String result = "";
		
		for(Person cust: repository.findByLastName(lastName)){
			result += cust.toString() + "<br>"; 
		}
		
		return result;
	}
}
