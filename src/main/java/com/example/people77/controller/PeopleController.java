package com.example.demo.controller;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException.BadRequest;

import com.example.people77.model.People;
import com.example.people77.repository.IPeopleRepository;


//Here is the rules of buisness with the Rest method

@RestController
public class PeopleController {
	
	@Autowired
	private IPeopleRepository peopleRepository;

	public PeopleController(IPeopleRepository peopleRepository) {
		super();
		this.peopleRepository = peopleRepository;
	}
			
	@GetMapping("/people")
	Collection<People> people(){
		return peopleRepository.findAll();
	}
	
	@GetMapping("/people/{id}")
	ResponseEntity<?> getPeople(@PathVariable Integer id){
		Optional<People> people = peopleRepository.findById(id);
		return people.map(response -> ResponseEntity.ok().body(response))
				.orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}
	
	@PostMapping("/people")
	ResponseEntity<People> createPeople(Exception ex, @RequestBody People people) throws Exception {
		if (ex instanceof HttpMediaTypeNotSupportedException) {
			return new ResponseEntity<>(HttpStatus.UNSUPPORTED_MEDIA_TYPE);
		}else if (ex instanceof BadRequest) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		People result = peopleRepository.save(people);
		return ResponseEntity.created(new URI("/people" + result.getNationalId())).body(result);
	}
	
	@PutMapping("/people/{id}")
	ResponseEntity<People> updateCategory(@RequestBody People people) throws URISyntaxException, HttpMediaTypeNotSupportedException {
		People result = peopleRepository.save(people);
		return ResponseEntity.ok().body(result);
	}
	
	@DeleteMapping("/people/{id}")
	ResponseEntity<?> deletePeople(@PathVariable Integer id) {
		try {
			peopleRepository.deleteById(id); 
		} catch (EmptyResultDataAccessException e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		 }
		return ResponseEntity.ok().build();
	}
	

}