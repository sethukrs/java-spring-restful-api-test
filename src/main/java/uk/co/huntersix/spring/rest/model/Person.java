package uk.co.huntersix.spring.rest.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicLong;

public class Person implements Serializable {
    private static final AtomicLong counter = new AtomicLong();

    @ApiModelProperty(position = 1, required = true, hidden=true, notes = "used to display user id")
    private Long id;

    @NotBlank
    @Size(min = 1)
    @Pattern(regexp = "^[a-zA-Z]*$")
    private String firstName;

    @NotBlank
    @Size(min = 1)
    @Pattern(regexp = "^[a-zA-Z]*$")
    private String lastName;

    private Person() {
        // empty
    }

    @JsonCreator
    public Person(@JsonProperty("firstName") String firstName, @JsonProperty("lastName") String lastName) {
        this.id = counter.incrementAndGet();
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return firstName.equals(person.firstName) && lastName.equals(person.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName);
    }
}
