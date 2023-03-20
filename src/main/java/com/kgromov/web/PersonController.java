package com.kgromov.web;

import com.kgromov.model.Address;
import com.kgromov.model.Person;
import com.kgromov.service.PersonService;
import io.micrometer.observation.Observation;
import io.micrometer.observation.ObservationRegistry;
import io.micrometer.observation.annotation.Observed;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/protobuf")
@RequiredArgsConstructor
public class PersonController {
    private final PersonService personService;
    private final ObservationRegistry observationRegistry;

    @GetMapping("/persons")
    public List<Person> getPersons() {
        return personService.getPersons();
    }

    @GetMapping("/persons/{id}")
    public Person getPerson(@PathVariable Integer id) {
        return Observation
                .createNotStarted("getPersonById", this.observationRegistry)
                .observe(() -> personService.buildPerson(id));
    }


    @Observed(name = "persons.short", contextualName="persons.short")
    @GetMapping("/persons/{id}/short")
    public Person getPersonPartially(@PathVariable Integer id) {
        return personService.buildPartialPerson(id);
    }

    @GetMapping("/persons/{id}/address")
    public Address getPersonAddress(@PathVariable Integer id) {
        return personService.buildPerson(id).getAddress();
    }

}
