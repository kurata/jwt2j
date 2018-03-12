package br.com.aqueteron.jwt2j.util;

import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Comparator;

import org.junit.Test;
import org.mockito.Mockito;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.node.TextNode;

public class JsonZoneDateTimeDeserializerTest {

	private static final Integer ZERO = 0;

	@Test
	public void testSerializeZonedDateTimeJsonGeneratorSerializerProvider() throws IOException {
		JsonZoneDateTimeDeserializer deserializer = new JsonZoneDateTimeDeserializer();
		JsonParser jsonParserMock = Mockito.mock(JsonParser.class);
		ObjectCodec objectCodecMock = Mockito.mock(ObjectCodec.class);

		ZonedDateTime now = ZonedDateTime.now();
		ZonedDateTime nowInUTC = now.withZoneSameInstant(ZoneOffset.UTC);
		String dateString = now.format(DateTimeFormatter.ISO_INSTANT);

		Mockito.when(jsonParserMock.getCodec()).thenReturn(objectCodecMock);
		Mockito.when(objectCodecMock.readTree(Mockito.same(jsonParserMock))).thenReturn(new TextNode(dateString));

		ZonedDateTime result = deserializer.deserialize(jsonParserMock, null);
		Comparator<ZonedDateTime> comparator = Comparator.comparing(zdt -> zdt.truncatedTo(ChronoUnit.MINUTES));
		
		assertTrue(ZERO.equals(comparator.compare(nowInUTC, result)));
	}

}
