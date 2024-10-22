package ru.anhimov.SpringBootWithSecurity.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.anhimov.SpringBootWithSecurity.model.Person;

import java.util.Optional;

/**
 * @author Neil Alishev
 */
@Repository
public interface PeopleRepository extends JpaRepository<Person, Integer> {
    Optional<Person> findByUsername(String username);
}
