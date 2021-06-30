package uk.co.huntersix.spring.rest.referencedata;

import org.springframework.stereotype.Service;
import uk.co.huntersix.spring.rest.exception.PersonDoesNotExistException;
import uk.co.huntersix.spring.rest.model.Person;

import java.util.Arrays;
import java.util.List;

@Service
public class PersonDataService {
    public static final List<Person> PERSON_DATA = Arrays.asList(
        new Person("Mary", "Smith"),
        new Person("Brian", "Archer"),
        new Person("Collin", "Brown")
    );

    public Person findPerson(String lastName, String firstName) {
        return PERSON_DATA.stream()
            .filter(p -> p.getFirstName().equalsIgnoreCase(firstName)
                && p.getLastName().equalsIgnoreCase(lastName))
                .findFirst()
                .orElseThrow(
                        () -> new PersonDoesNotExistException("User with lastName : " + lastName
                        + ", firstName : " + firstName + " does not exist"));
    }
}
