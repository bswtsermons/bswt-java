package org.bswt.api;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.token.grant.client.ClientCredentialsResourceDetails;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource(locations="classpath:application-test.properties")
public class BswtApiApplicationTests
{
	@Test
	public void contextLoads()
	{
		System.out.println(getRestTemplate().getForObject("https://localhost:8080/rest/service/1531", String.class));
	}
	
	public RestTemplate getRestTemplate()
	{
		ClientCredentialsResourceDetails resourceDetails = new ClientCredentialsResourceDetails();
		resourceDetails.setId("1");
		resourceDetails.setClientId("trusted-app");
		resourceDetails.setClientSecret("d4e63d07-8fb6-440f-97d1-95344df8125a");
		resourceDetails.setAccessTokenUri("http://localhost:8080/oauth/token");
		
	    OAuth2RestTemplate restTemplate = new OAuth2RestTemplate(resourceDetails);
	    DefaultOAuth2AccessToken dt = (DefaultOAuth2AccessToken)restTemplate.getAccessToken();
	    dt.setTokenType("Bearer");
	    
	    return restTemplate;
	}

}
