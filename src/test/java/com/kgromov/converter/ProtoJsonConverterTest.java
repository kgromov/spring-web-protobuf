package com.kgromov.converter;

import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.util.JsonFormat;
import com.kgromov.model.Gender;
import com.kgromov.model.Person;
import org.junit.jupiter.api.Test;

import java.io.FileInputStream;
import java.io.IOException;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class ProtoJsonConverterTest {
    private final ProtoJsonConverter converter = new ProtoJsonConverter();

    @Test
    void fromJson() throws InvalidProtocolBufferException {
        Person person = Person.newBuilder()
                .setId(1)
                .setFirstName("John")
                .setLastName("Doe")
                .setGender(Gender.MALE)
                .build();
        String json = converter.toJson(person);
        var builder = Person.newBuilder();
        JsonFormat.parser().ignoringUnknownFields().merge(json, builder);
        Person dPerson = builder.build();

        // No smart methods with generics to target type -
        // that's why org.springframework.messaging.converter.ProtobufMessageConverter was introduced
        /*Message message = converter.fromJson(json);
        Person dPerson = Person.newBuilder().mergeFrom(message).build();*/

        assertThat(dPerson).isEqualTo(person);
    }

    @Test
    void fromJsonGeneric() throws Exception {
        Person person = Person.newBuilder()
                .setId(1)
                .setFirstName("John")
                .setLastName("Doe")
                .setGender(Gender.MALE)
                .build();
        String json = converter.toJson(person);

        Person dPerson = converter.fromJson(Person.newBuilder(), json);
        assertThat(dPerson).isEqualTo(person);
    }

    @Test
    void toJson() {
        try (FileInputStream fis = new FileInputStream("D:\\workspace\\protobuf\\spring-web-protobuf\\.idea\\httpRequests\\2023-03-11T010257.200.protobuf")) {
            Person deserializedPerson = Person.parseFrom(fis);
            String json = converter.toJson(deserializedPerson);
            assertThat(deserializedPerson.getId()).isEqualTo(2);
            assertThat(deserializedPerson.getFirstName()).isEqualTo("Florencio");
            assertThat(deserializedPerson.getLastName()).isEqualTo("Cruickshank");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}