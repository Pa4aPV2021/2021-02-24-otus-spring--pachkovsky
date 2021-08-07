package ru.otus.spring.batch;



import org.springframework.batch.item.ItemProcessor;

import ru.otus.spring.batch.h2.domain.Person;

public class PersonItemProcessor implements ItemProcessor<Person, Person> {

	@Override
	public Person process(Person person) throws Exception {
		return new Person(person.getFirstName().toUpperCase(), person.getLastName().toUpperCase());
	}

}

