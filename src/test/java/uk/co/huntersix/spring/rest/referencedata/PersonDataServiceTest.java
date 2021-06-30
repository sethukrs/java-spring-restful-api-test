package uk.co.huntersix.spring.rest.referencedata;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import uk.co.huntersix.spring.rest.exception.PersonAlreadyExistsException;
import uk.co.huntersix.spring.rest.exception.PersonDoesNotExistException;
import uk.co.huntersix.spring.rest.model.Person;

import java.util.Arrays;
import java.util.List;

import static junit.framework.TestCase.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PersonDataServiceTest {

    @Autowired
    private PersonDataService personDataService;

    @Test
    public void shouldReturnExistingUserByFirstAndLastName() {
        Person expected = new Person("Mary", "Smith");

        Person actual = personDataService.findPerson("Smith", "Mary");

        assertEquals(expected.getFirstName(), actual.getFirstName());
        assertEquals(expected.getLastName(), actual.getLastName());
    }

    @Test
    public void shouldReturnExistingUsersByLastName() {
        List<Person> expected = Arrays.asList(new Person("Mary", "Smith"));

        List<Person> actual = personDataService.findPersonByLastName("Smith");

        assertEquals(expected.size(), actual.size());
        assertEquals(expected.get(0).getFirstName(), actual.get(0).getFirstName());
        assertEquals(expected.get(0).getLastName(), actual.get(0).getLastName());
    }

    @Test (expected = PersonDoesNotExistException.class)
    public void shouldReturnEmptyListOfUsersByInvalidLastName() {

        personDataService.findPersonByLastName("null");
    }

    @Test (expected = PersonDoesNotExistException.class)
    public void shouldThrowPersonDoesNotExistWhenNoPersonFoundByName() throws Exception {

        personDataService.findPerson("None", "None");
    }

    @Test (expected = PersonAlreadyExistsException.class)
    public void shouldThrowPersonAlreadyExist() throws Exception {

        personDataService.savePerson(new Person("Mary", "Smith"));
    }

    @Test
    public void shouldSavePerson() {

        //when
        personDataService.savePerson(new Person("David", "Beckham"));

        //then
        Person actual = personDataService.findPerson("Beckham", "David");
        assertEquals("David", actual.getFirstName());
        assertEquals("Beckham", actual.getLastName());
    }
}
