package com.sample.restservices.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.sample.restservices.vo.Person;

/*
 * It is just a helper class which should be replaced by database implementation.
 * It is not very well written class, it is just used for demonstration.
 */
@Path("v1/persons")
public class PersonSerivce {
	static HashMap<Integer, Person> personMap = getPersonMap();

	public PersonSerivce() {
		super();

		if (personMap == null) {
			personMap = new HashMap<Integer, Person>();
			Person prs1 = new Person(1, "Srikanth", "Hyderabad");
			Person prs2 = new Person(4, "Josyula", "Bengaluru");
			Person prs3 = new Person(3, "Srinivas", "Chennai");

			personMap.put(1, prs1);
			personMap.put(4, prs2);
			personMap.put(3, prs3);
		}
	}

	@GET
	@Produces(MediaType.APPLICATION_XML)
	public List<Person> fetchAllPersons() {
		List<Person> persons = new ArrayList<Person>(personMap.values());
		return persons;
	}

	@GET
	@Path("/{id: [a-zA-Z][a-zA-Z_0-9]*}")
	@Produces(MediaType.APPLICATION_JSON)
	public Person fetchPerson(@PathParam("id") int id) {
		Person person = personMap.get(id);
		return person;
	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Person createPerson(Person person) {
		person.setId(personMap.size() + 1);
		personMap.put(person.getId(), person);
		return person;
	}

	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	public Person updatePerson(Person person) {
		if (person.getId() <= 0)
			return null;
		personMap.put(person.getId(), person);
		return person;

	}
	@DELETE
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public void deletePerson(@PathParam("id") int id) {
		personMap.remove(id);
	}

	public static HashMap<Integer, Person> getPersonMap() {
		return personMap;
	}
}