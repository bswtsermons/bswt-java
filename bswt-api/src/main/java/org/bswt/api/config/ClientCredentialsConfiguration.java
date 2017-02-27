package org.bswt.api.config;

import java.util.ArrayList;
import java.util.List;

import org.bswt.api.model.ClientCredential;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "bswt.api.credentials")
public class ClientCredentialsConfiguration
{
	private List<ClientCredential> clientCredentials = new ArrayList<>();

	public List<ClientCredential> getClientCredentials() 
	{
		return clientCredentials;
	}

	public void setClientCredentials(List<ClientCredential> clientCredentials) 
	{
		this.clientCredentials = clientCredentials;
	}
}
