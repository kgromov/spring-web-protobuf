package com.kgromov.converter;

import com.google.protobuf.Message;
import com.google.protobuf.MessageOrBuilder;
import com.google.protobuf.Struct;
import com.google.protobuf.util.JsonFormat;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;

@Component
public class ProtoJsonConverter {

    @SneakyThrows
    public Message fromJson(String json) {
        Message.Builder structBuilder = Struct.newBuilder();
        JsonFormat.parser().ignoringUnknownFields().merge(json, structBuilder);
        return structBuilder.build();
    }

    @SneakyThrows
    public String toJson(MessageOrBuilder message) {
        return JsonFormat.printer().print(message);
    }
}
