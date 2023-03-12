package com.kgromov.converter;

import com.google.protobuf.AbstractMessage;
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
        // TODO: in order to make this shit generic - target class should be provided - actually what is done in
        var structBuilder = Struct.newBuilder();
        JsonFormat.parser().ignoringUnknownFields().merge(json, structBuilder);
        return structBuilder.build();
    }

    @SneakyThrows
    @SuppressWarnings("unchecked")
    public <B extends Message.Builder, T extends AbstractMessage> T fromJson(B builder, String json) {
        JsonFormat.parser().ignoringUnknownFields().merge(json, builder);
        return (T) builder.build();
    }

    @SneakyThrows
    public String toJson(MessageOrBuilder message) {
        return JsonFormat.printer().print(message);
    }
}
