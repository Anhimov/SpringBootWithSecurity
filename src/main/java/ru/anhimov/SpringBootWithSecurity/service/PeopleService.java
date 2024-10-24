package ru.anhimov.SpringBootWithSecurity.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.anhimov.SpringBootWithSecurity.model.Person;
import ru.anhimov.SpringBootWithSecurity.repositories.PeopleRepository;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class PeopleService {
    private final PeopleRepository peopleRepository;

    @Autowired
    public PeopleService(PeopleRepository peopleRepository) {
        this.peopleRepository = peopleRepository;
    }

    public Optional<Person> findByUsername(String username) {
        return peopleRepository.findByUsername(username);
    }

    @Transactional
    public void register(Person person) {
        enrichPerson(person);
        peopleRepository.save(person);
    }

    private void enrichPerson(Person person) {
        person.setRole("ROLE_USER");
    }

    public List<Person> findAllPeople() {
        return peopleRepository.findAll();
    }

    public Person findById(int id) {
        return peopleRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("User not found"));
    }

    @Transactional
    public void changePersonRole(Integer personId, String role) {
        Person personToUpdate = findById(personId);
        personToUpdate.setRole(role);
        peopleRepository.save(personToUpdate);
    }
}
