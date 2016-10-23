package org.bswt.api.mvc.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.servlet.ServletException;

import org.apache.commons.lang3.StringUtils;
import org.bswt.api.SubjectsConfiguration;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@RestController
@RequestMapping("/rest/auth")
public class AuthorizationController
{
	@Resource
	private SubjectsConfiguration subjectsConfiguration; 
	
	@Value("${bswt.api.rest.secret:bswt-secret}")
	private String secret;
	
	private Map<String, Subject> subjectMap = new HashMap<>();
	
	// TODO: maybe put the subjects into a separate properties file or something
	@PostConstruct
	private void init()
	{
		for (Subject subject : subjectsConfiguration.getSubjects())
		{
			subjectMap.put(subject.getName(), subject);
		}
	}
	
	@RequestMapping(method = RequestMethod.POST)
    public LoginResponse login(@RequestBody final Subject subject) throws ServletException 
	{
		if (StringUtils.isBlank(subject.getName())) 
        {
            throw new ServletException("Invalid authorization request");
        }
		
		Subject namedSubject = subjectMap.get(subject.getName());
		if (namedSubject == null || !StringUtils.equals(namedSubject.getSecret(), subject.getSecret()))
		{
			throw new ServletException("Invalid authorization request");
		}
		
        return new LoginResponse(Jwts.builder().setSubject(subject.getName())
            .claim("roles", subjectMap.get(subject.getName()).getClaims()).setIssuedAt(new Date())
            .signWith(SignatureAlgorithm.HS256, secret).compact());
    }
	
	public static class Subject
	{
        public String name;
		public String secret;
        public List<String> claims = new ArrayList<>();
        
		public String getName()
		{
			return name;
		}
		public void setName(String name)
		{
			this.name = name;
		}
		public String getSecret()
		{
			return secret;
		}
		public void setSecret(String secret)
		{
			this.secret = secret;
		}
		public List<String> getClaims()
		{
			return claims;
		}
		public void setClaims(List<String> claims)
		{
			this.claims = claims;
		}
    }
	
	private static class LoginResponse
	{
        @SuppressWarnings("unused")
		public String token;

        public LoginResponse(final String token) 
        {
            this.token = token;
        }
    }
}
