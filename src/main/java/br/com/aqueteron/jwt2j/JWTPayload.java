package br.com.aqueteron.jwt2j;

import java.time.ZonedDateTime;

public interface JWTPayload {

	public String getIss();

	public void setIss(String iss);

	public String getSub();

	public void setSub(String sub);

	public String[] getAud();

	public void setAud(String... aud);

	public ZonedDateTime getExp();

	public void setExp(ZonedDateTime exp);

	public ZonedDateTime getNbf();

	public void setNbf(ZonedDateTime nbf);

	public ZonedDateTime getIat();

	public void setIat(ZonedDateTime iat);

	public String getJti();

	public void setJti(String jti);
}
