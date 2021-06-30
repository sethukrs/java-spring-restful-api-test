package uk.co.huntersix.spring.rest.referencedata;

import org.springframework.stereotype.Service;
import uk.co.huntersix.spring.rest.exception.PersonAlreadyExistsException;
import uk.co.huntersix.spring.rest.exception.PersonDoesNotExistException;
import uk.co.huntersix.spring.rest.model.Person;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.stream.Collectors;

@Service
public class PersonDataService {
    public static final Set<Person> PERSON_DATA = ConcurrentHashMap.newKeySet();

    static {
        PERSON_DATA.addAll(Arrays.asList(
                new Person("Mary", "Smith"),
                new Person("Brian", "Archer"),
                new Person("Collin", "Brown")));
    }

    public Person findPerson(String lastName, String firstName) {
        return PERSON_DATA.stream()
            .filter(p -> p.getFirstName().equalsIgnoreCase(firstName)
                && p.getLastName().equalsIgnoreCase(lastName))
                .findFirst()
                .orElseThrow(
                        () -> new PersonDoesNotExistException("User with lastName : " + lastName
                        + ", firstName : " + firstName + " does not exist"));
    }

    public List<Person> findPersonByLastName(String lastName) {
        List<Person> personList = PERSON_DATA.stream()
                .filter(p -> p.getLastName().equalsIgnoreCase(lastName))
                .collect(Collectors.toList());

        if (personList.isEmpty()) {
            throw new PersonDoesNotExistException();
        }
            return personList;
    }

    public void savePerson(Person person) {
        boolean alreadyExists = PERSON_DATA.add(person);

        if (!alreadyExists) {
            throw new PersonAlreadyExistsException();
        }
    }
}
