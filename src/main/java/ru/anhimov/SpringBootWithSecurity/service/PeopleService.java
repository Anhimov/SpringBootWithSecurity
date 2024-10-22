package ru.anhimov.SpringBootWithSecurity.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.anhimov.SpringBootWithSecurity.model.Person;
import ru.anhimov.SpringBootWithSecurity.repositories.PeopleRepository;

import java.util.Optional;

@Service
public class PeopleService {
    private final PeopleRepository peopleRepository;

    @Autowired
    public PeopleService(PeopleRepository peopleRepository) {
        this.peopleRepository = peopleRepository;
    }

    public Optional<Person> findByUsername(String username) {
        return peopleRepository.findByUsername(username);
    }

    public void save(Person person) {
        enrichPerson(person);
        person.setRole("ROLE_USER");
        peopleRepository.save(person);
    }

    private void enrichPerson(Person person) {
        person.setRole("ROLE_USER");
    }
}
