package uk.co.huntersix.spring.rest.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.assertj.core.util.Lists;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import uk.co.huntersix.spring.rest.exception.PersonDoesNotExistException;
import uk.co.huntersix.spring.rest.model.Person;
import uk.co.huntersix.spring.rest.referencedata.PersonDataService;

import java.util.Arrays;
import java.util.List;

@RunWith(SpringRunner.class)
@WebMvcTest(PersonController.class)
public class PersonControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PersonDataService personDataService;

    @Test
    public void shouldReturnPersonFromService() throws Exception {
        when(personDataService.findPerson(any(), any())).thenReturn(new Person("Mary", "Smith"));
        this.mockMvc.perform(get("/person/smith/mary"))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(jsonPath("id").exists())
            .andExpect(jsonPath("firstName").value("Mary"))
            .andExpect(jsonPath("lastName").value("Smith"));
    }

    @Test
    public void shouldReturnAllPersonsWithSurname() throws Exception {
        when(personDataService.findPersonByLastName(any()))
                .thenReturn(Arrays.asList(new Person("Mary", "Smith"), new Person("John", "Smith")));

        this.mockMvc.perform(get("/person/smith"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[0].id").exists())
                .andExpect(jsonPath("$.[0].firstName").value("Mary"))
                .andExpect(jsonPath("$.[0].lastName").value("Smith"))
                .andExpect(jsonPath("$.[1].id").exists())
                .andExpect(jsonPath("$.[1].firstName").value("John"))
                .andExpect(jsonPath("$.[1].lastName").value("Smith"));
    }

    @Test
    public void shouldReturnNoPersonsWithInvalidSurname() throws Exception {
        when(personDataService.findPersonByLastName(any()))
                .thenReturn(Arrays.asList());

        this.mockMvc.perform(get("/person/null"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[0].id").doesNotExist());
    }

    @Test
    public void should_Return_404_When_User_DoesNot_Exist() throws Exception {
        doThrow(PersonDoesNotExistException.class).when(personDataService).findPerson(anyString(), anyString());
        this.mockMvc.perform(get("/person/none/none"))
                .andDo(print())
                .andExpect(status().isNotFound());
    }
}