package org.bswt.api;

import java.util.ArrayList;
import java.util.List;

import org.bswt.api.mvc.controller.JwtFilterBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMethod;

@EnableConfigurationProperties(SubjectsConfiguration.class)
@SpringBootApplication
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

	/**
	 * this filter secures PUT and POST requests for our CRUD services
	 * 
	 * @return
	 */
	@Bean
	public FilterRegistrationBean jwtFilter()
	{
		final JwtFilterBean jfb = new JwtFilterBean();
		jfb.addRequestMethod(RequestMethod.POST.toString());
		jfb.addRequestMethod(RequestMethod.PUT.toString());
		jfb.setSecret(secret);
		jfb.getRequiredRoles().addAll(adminRequiredRoles);

		final FilterRegistrationBean frb = new FilterRegistrationBean();
		frb.addUrlPatterns("/rest/service/*");
		frb.addUrlPatterns("/rest/media/*");
		frb.addUrlPatterns("/rest/website-config/*");
		frb.setFilter(jfb);

		return frb;
	}
}
