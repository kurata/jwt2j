package br.com.aqueteron.jwt2j.util;

import java.io.IOException;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

public class JsonZoneDateTimeSerializer extends JsonSerializer<ZonedDateTime> {

	@Override
	public void serialize(final ZonedDateTime zonedDateTime, final JsonGenerator jsonGenerator,
			final SerializerProvider serializerProvider) throws IOException {
		String dateString = zonedDateTime.format(DateTimeFormatter.ISO_INSTANT);
		jsonGenerator.writeString(dateString);
	}
}
