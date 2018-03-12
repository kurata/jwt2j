# jwt2j

A Java implementation of JSON Web Tocken.

## Installation

### Maven

<dependency>
	<groupId>br.com.aqueteron</groupId>
	<artifactId>jwt2j</artifactId>
	<version>0.0.1</version>
</dependency>

## Funcionalities

The library implements JWT Signatures using the following algorithms:

JWS 	Algorithm 	Description
HS256 	HMAC256 	HMAC with SHA-256
HS384 	HMAC384 	HMAC with SHA-384
HS512 	HMAC512 	HMAC with SHA-512
RS256 	RSA256 	RSASSA-PKCS1-v1_5 with SHA-256
RS384 	RSA384 	RSASSA-PKCS1-v1_5 with SHA-384
RS512 	RSA512 	RSASSA-PKCS1-v1_5 with SHA-512
ES256 	ECDSA256 	ECDSA with curve P-256 and SHA-256
ES384 	ECDSA384 	ECDSA with curve P-384 and SHA-384
ES512 	ECDSA512 	ECDSA with curve P-521 and SHA-512

## Usage

The JWTEncoders 

### Generating JWT

		JWTEncoder jwtSignatureEncode = JWTEncoders.rs256(privateKey);
		SimpleJWTPayload jwtPayload = new SimpleJWTPayload();
		String jwtToken = jwtSignatureEncode.encode(jwtPayload);

### Verifing JWT

		JWTSignatureVerifier verifier = JWTSignatureVerifiers.rs256(publicKey);
		JWTDecoder jwtDecoder = new JWTSignatureDecoder(verifier, SimpleJWTPayload.class);
		JWTPayload resultPayload = jwtDecoder.decode(jwtToken);
