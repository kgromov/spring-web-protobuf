package com.kgromov.converter;

import com.kgromov.model.Person;
import org.junit.jupiter.api.Test;

import java.io.FileInputStream;
import java.io.IOException;

class ProtoJsonConverterTest {
    private ProtoJsonConverter converter = new ProtoJsonConverter();

    @Test
    void fromJson() {

    }

    @Test
    void toJson() {
        try (FileInputStream fis = new FileInputStream("D:\\workspace\\protobuf\\spring-web-protobuf\\.idea\\httpRequests\\2023-03-11T010257.200.protobuf")) {
            Person deserializedPerson = Person.parseFrom(fis);
            String json = converter.toJson(deserializedPerson);
            int id = deserializedPerson.getId();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}