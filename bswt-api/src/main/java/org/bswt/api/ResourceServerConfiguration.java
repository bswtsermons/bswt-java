package org.bswt.api;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

public class ResourceServerConfiguration extends WebSecurityConfigurerAdapter
{
	/*
	@Override
	protected void configure(HttpSecurity http) throws Exception 
	{
		http
		  .antMatcher("/**")
		  .authorizeRequests()
		  .anyRequest()
		    .authenticated()
		;
	}
	*/
}
