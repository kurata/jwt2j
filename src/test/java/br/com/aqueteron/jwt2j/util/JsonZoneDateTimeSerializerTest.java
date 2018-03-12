package br.com.aqueteron.jwt2j.util;

import java.io.IOException;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;

import org.junit.Test;
import org.mockito.Mockito;

import com.fasterxml.jackson.core.JsonGenerator;

public class JsonZoneDateTimeSerializerTest {

	private static final String DASH = "-";

	private static final String T_STRING = "T";

	private static final String COLON = ":";

	private static final String DOT = ".";

	private static final String Z_STRING = "Z";
	
	private static final String TWO_NUMBERS = "%02d";
	
	private static final String TREE_NUMBERS = "%03d";

	@Test
	public void testSerializeZonedDateTimeJsonGeneratorSerializerProvider() throws IOException {
		JsonZoneDateTimeSerializer serializer = new JsonZoneDateTimeSerializer();
		JsonGenerator jsonGeneratorMock = Mockito.mock(JsonGenerator.class);
		StringBuilder sb = new StringBuilder();

		ZonedDateTime now = ZonedDateTime.now();
		ZonedDateTime control = now.withZoneSameInstant(ZoneOffset.UTC);
		sb.append(control.getYear());
		sb.append(DASH);
		sb.append(String.format(TWO_NUMBERS, control.getMonthValue()));
		sb.append(DASH);
		sb.append(String.format(TWO_NUMBERS, control.getDayOfMonth()));
		sb.append(T_STRING);
		sb.append(String.format(TWO_NUMBERS, control.getHour()));
		sb.append(COLON);
		sb.append(String.format(TWO_NUMBERS, control.getMinute()));
		sb.append(COLON);
		sb.append(String.format(TWO_NUMBERS, control.getSecond()));
		sb.append(DOT);
		sb.append(String.format(TREE_NUMBERS, (control.getNano() / 1000000)));
		sb.append(Z_STRING);

		serializer.serialize(now, jsonGeneratorMock, null);
		Mockito.verify(jsonGeneratorMock).writeString(sb.toString());
	}

}
