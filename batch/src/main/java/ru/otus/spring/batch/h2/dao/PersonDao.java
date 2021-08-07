package ru.otus.spring.batch.h2.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import ru.otus.spring.batch.h2.domain.Person;





public interface PersonDao extends JpaRepository<Person, String> {
}
