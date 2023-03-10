package com.kgromov.service;

import com.github.javafaker.Faker;
import com.github.javafaker.Name;
import com.github.javafaker.service.RandomService;
import com.kgromov.model.Address;
import com.kgromov.model.Gender;
import com.kgromov.model.Person;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonService {

    public List<Person> getPersons() {
        RandomService random = new Faker().random();
        return List.of(
                this.buildPerson(random.nextInt(Integer.MIN_VALUE)),
                this.buildPerson(random.nextInt(Integer.MIN_VALUE))
        );
    }

    public Person buildPerson(int id) {
        return this.personBuilder(id)
                .setAddress(buildAddress())
                .build();
    }

    public Person buildPartialPerson(int id) {
        return this.personBuilder(id)
                .buildPartial();
    }

    public Person.Builder personBuilder(int id) {
        Faker faker = new Faker();
        Name name = faker.name();
        return Person.newBuilder()
                .setId(id)
                .setFirstName(name.firstName())
                .setLastName(name.lastName())
                .setGender(Gender.forNumber(faker.random().nextInt(1)));
    }

    public Address buildAddress() {
        var address = new Faker().address();
        return Address.newBuilder()
                .setStreet(address.streetAddress())
                .setAlternativeAddress(address.streetName())
                .setCity(address.city())
                .setCountry(address.country())
                .setZipCode(address.zipCode())
                .build();
    }
}
