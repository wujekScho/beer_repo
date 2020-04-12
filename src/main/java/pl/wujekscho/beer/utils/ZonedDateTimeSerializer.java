package pl.wujekscho.beer.utils;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class ZonedDateTimeSerializer extends JsonSerializer<ZonedDateTime> {

    private static final ThreadLocal<DateTimeFormatter> DTF = ThreadLocal.withInitial(
            () -> DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss zzz")
    );

    @Override
    public void serialize(ZonedDateTime value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        if (value != null) {
            gen.writeString(value.format(DTF.get()));
        }
    }
}