package uk.co.huntersix.spring.rest.referencedata;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import uk.co.huntersix.spring.rest.exception.PersonDoesNotExistException;
import uk.co.huntersix.spring.rest.model.Person;

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

    @Test (expected = PersonDoesNotExistException.class)
    public void shouldThrowPersonDoesNotExistWhenNoPersonFoundByName() throws Exception {

        personDataService.findPerson("None", "None");

    }
}
