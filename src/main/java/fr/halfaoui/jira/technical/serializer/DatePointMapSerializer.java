package fr.halfaoui.jira.technical.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;

/**
 * @author amirouche
 */
public class DatePointMapSerializer extends JsonSerializer<Map<Date, Long>> {
    @Override
    public void serialize(Map<Date, Long> map, JsonGenerator gen, SerializerProvider serializers) throws IOException, JsonProcessingException {
        gen.writeStartArray();
        Iterator<Date> iterator = map.keySet().iterator();
        while (iterator.hasNext()) {
            Date date = iterator.next();
            Long value = map.get(date);
            gen.writeStartArray();
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM");
            String formattedDate = formatter.format(date);
            gen.writeString(formattedDate);
            gen.writeObject(value);
            gen.writeEndArray();
        }
        gen.writeEndArray();
    }
}
