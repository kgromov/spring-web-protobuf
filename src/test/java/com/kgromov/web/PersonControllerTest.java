package com.kgromov.web;

import com.kgromov.model.Person;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import static org.assertj.core.api.Assertions.assertThat;

@WebMvcTest
class PersonControllerTest {
    @Autowired
    private RestTemplate restTemplate;

    @Test
    void getPerson() {
//        HttpClient httpClient = HttpClient.newHttpClient();
        ResponseEntity<Person> response = restTemplate.getForEntity("http://localhost:8080/persons/1", Person.class);
        Person person = response.getBody();
        assertThat(person).isNotNull();
        assertThat(person.getId()).isEqualTo(1);
        assertThat(person.getAddress()).isNotNull();
    }
}