package br.com.aqueteron.jwt2j.signature;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import br.com.aqueteron.jwt2j.JWTPayload;
import br.com.aqueteron.jwt2j.SimpleJWTPayload;

public class JWTSignatureDecoderTest {

	private JWTSignatureDecoder<SimpleJWTPayload> jwtSignatureDecoder;

	private JWTSignatureVerifier verifierMock;

	@Before
	public void setUp() throws Exception {
		this.verifierMock = Mockito.mock(JWTSignatureVerifier.class);
		this.jwtSignatureDecoder = new JWTSignatureDecoder<SimpleJWTPayload>(this.verifierMock, SimpleJWTPayload.class);
	}

	@Test
	public void testJWTSignatureDecoder() {
		JWTSignatureDecoder<SimpleJWTPayload> jwtSignatureDecoder = new JWTSignatureDecoder<SimpleJWTPayload>(null,
				SimpleJWTPayload.class);
		assertNotNull(jwtSignatureDecoder);
	}

	@Test(expected = InvalidJWTTokenException.class)
	public void testDecodeInvalidJWTTokenException() throws InvalidJWTTokenException {
		this.jwtSignatureDecoder.decode("eyJhbGciOiJhbGdvcml0aG1OYW1lIiwidHlwIjoiand0In0=");
	}

	@Test
	public void testDecode() throws InvalidJWTTokenException {
		when(this.verifierMock.verifyJOSEHeader(any())).thenReturn(Boolean.TRUE);
		when(this.verifierMock.verifySignature(any(), any())).thenReturn(Boolean.TRUE);

		JWTPayload result = this.jwtSignatureDecoder.decode(
				"eyJhbGciOiJhbGdvcml0aG1OYW1lIiwidHlwIjoiand0In0=.eyJpc3MiOiJhcXVldGVyb24uY29tLmJyIiwic3ViIjoiYXF1ZXRlcm9uLmNvbS5iciIsImF1ZCI6WyJ0ZXN0LmFxdWV0ZXJvbi5jb20uYnIiXSwiZXhwIjpudWxsLCJuYmYiOm51bGwsImlhdCI6bnVsbCwianRpIjpudWxsfQ==.ZXlKaGJHY2lPaUpoYkdkdmNtbDBhRzFPWVcxbElpd2lkSGx3SWpvaWFuZDBJbjA9LmV5SnBjM01pT2lKaGNYVmxkR1Z5YjI0dVkyOXRMbUp5SWl3aWMzVmlJam9pWVhGMVpYUmxjbTl1TG1OdmJTNWljaUlzSW1GMVpDSTZXeUowWlhOMExtRnhkV1YwWlhKdmJpNWpiMjB1WW5JaVhTd2laWGh3SWpwdWRXeHNMQ0p1WW1ZaU9tNTFiR3dzSW1saGRDSTZiblZzYkN3aWFuUnBJanB1ZFd4c2ZRPT0=");
		assertNotNull(result);
		assertEquals("aqueteron.com.br", result.getIss());
		assertEquals("aqueteron.com.br", result.getSub());
	}

	@Test
	public void testDecodeTwoSegments() throws InvalidJWTTokenException {
		when(this.verifierMock.verifyJOSEHeader(any())).thenReturn(Boolean.TRUE);
		when(this.verifierMock.verifySignature(any(), any())).thenReturn(Boolean.TRUE);

		JWTPayload result = this.jwtSignatureDecoder.decode(
				"eyJhbGciOiJub25lIiwidHlwIjoiand0IiwiZW5jIjpudWxsfQ==.eyJpc3MiOiJhcXVldGVyb24uY29tLmJyIiwic3ViIjoiYXF1ZXRlcm9uLmNvbS5iciIsImF1ZCI6WyJ0ZXN0LmFxdWV0ZXJvbi5jb20uYnIiXSwiZXhwIjpudWxsLCJuYmYiOm51bGwsImlhdCI6bnVsbCwianRpIjpudWxsfQ==");
		assertNotNull(result);
		assertEquals("aqueteron.com.br", result.getIss());
		assertEquals("aqueteron.com.br", result.getSub());
	}

	@Test(expected = InvalidJWTTokenException.class)
	public void testDecodeInvalidSignature() throws InvalidJWTTokenException {
		when(this.verifierMock.verifyJOSEHeader(any())).thenReturn(Boolean.TRUE);
		when(this.verifierMock.verifySignature(any(), any())).thenReturn(Boolean.FALSE);

		this.jwtSignatureDecoder.decode(
				"eyJhbGciOiJhbGdvcml0aG1OYW1lIiwidHlwIjoiand0In0=.eyJpc3MiOiJhcXVldGVyb24uY29tLmJyIiwic3ViIjoiYXF1ZXRlcm9uLmNvbS5iciIsImF1ZCI6WyJ0ZXN0LmFxdWV0ZXJvbi5jb20uYnIiXSwiZXhwIjpudWxsLCJuYmYiOm51bGwsImlhdCI6bnVsbCwianRpIjpudWxsfQ==.ZXlKaGJHY2lPaUpoYkdkdmNtbDBhRzFPWVcxbElpd2lkSGx3SWpvaWFuZDBJbjA9LmV5SnBjM01pT2lKaGNYVmxkR1Z5YjI0dVkyOXRMbUp5SWl3aWMzVmlJam9pWVhGMVpYUmxjbTl1TG1OdmJTNWljaUlzSW1GMVpDSTZXeUowWlhOMExtRnhkV1YwWlhKdmJpNWpiMjB1WW5JaVhTd2laWGh3SWpwdWRXeHNMQ0p1WW1ZaU9tNTFiR3dzSW1saGRDSTZiblZzYkN3aWFuUnBJanB1ZFd4c2ZRPT0=");
	}

	@Test(expected = InvalidJWTTokenException.class)
	public void testDecodeInvalidJOSEHeader() throws InvalidJWTTokenException {
		when(this.verifierMock.verifyJOSEHeader(any())).thenReturn(Boolean.FALSE);

		this.jwtSignatureDecoder.decode(
				"eyJhbGciOiJhbGdvcml0aG1OYW1lIiwidHlwIjoiand0In0=.eyJpc3MiOiJhcXVldGVyb24uY29tLmJyIiwic3ViIjoiYXF1ZXRlcm9uLmNvbS5iciIsImF1ZCI6WyJ0ZXN0LmFxdWV0ZXJvbi5jb20uYnIiXSwiZXhwIjpudWxsLCJuYmYiOm51bGwsImlhdCI6bnVsbCwianRpIjpudWxsfQ==.ZXlKaGJHY2lPaUpoYkdkdmNtbDBhRzFPWVcxbElpd2lkSGx3SWpvaWFuZDBJbjA9LmV5SnBjM01pT2lKaGNYVmxkR1Z5YjI0dVkyOXRMbUp5SWl3aWMzVmlJam9pWVhGMVpYUmxjbTl1TG1OdmJTNWljaUlzSW1GMVpDSTZXeUowWlhOMExtRnhkV1YwWlhKdmJpNWpiMjB1WW5JaVhTd2laWGh3SWpwdWRXeHNMQ0p1WW1ZaU9tNTFiR3dzSW1saGRDSTZiblZzYkN3aWFuUnBJanB1ZFd4c2ZRPT0=");
	}

}
