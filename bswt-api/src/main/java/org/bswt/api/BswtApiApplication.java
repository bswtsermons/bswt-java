package org.bswt.api;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.web.bind.annotation.RequestMethod;

@SpringBootApplication
@EnableResourceServer
public class BswtApiApplication
{
	@Value("${bswt.api.rest.secret:bswt-secret}")
	private String secret;

	@Value("#{'${bswt.api.rest.admin.requiredRoles:admin}'.split(',')}")
	private List<String> adminRequiredRoles = new ArrayList<>();

	public static void main(String[] args)
	{
		SpringApplication.run(BswtApiApplication.class, args);
	}

}
