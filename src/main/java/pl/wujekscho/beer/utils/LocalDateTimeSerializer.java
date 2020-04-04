package pl.wujekscho.beer.utils;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LocalDateTimeSerializer extends JsonSerializer<LocalDateTime> {

    private static final ThreadLocal<DateTimeFormatter> DTF = ThreadLocal.withInitial(
            () -> DateTimeFormatter.ofPattern("HH:mm:ss dd/MM/yyyy")
    );

    @Override
    public void serialize(LocalDateTime localDateTime, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (localDateTime != null) {
            jsonGenerator.writeString(localDateTime.format(DTF.get()));
        }
    }
}
