package org.bswt.api.mvc.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureException;

public class JwtFilterBean extends HttpFilterBean
{
	private static final Logger LOG = LoggerFactory.getLogger(JwtFilterBean.class);
	
	private String secret;
	
	private List<String> requiredRoles = new ArrayList<>();
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException
	{
        final HttpServletRequest httpRequest = (HttpServletRequest) request;
        
        LOG.info("request method: {}", httpRequest.getMethod());
        
        if (requestMethodFound(httpRequest.getMethod()))
        {
        	final String authHeader = httpRequest.getHeader("Authorization");
            if (authHeader == null || !authHeader.startsWith("Bearer ")) 
            {
                throw new ServletException("Missing or invalid Authorization header.");
            }
            
            final String token = authHeader.substring(7); // The part after "Bearer "
            try 
            {
                final Claims claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
                @SuppressWarnings("unchecked")
				Set<String> roles = new HashSet<>(claims.get("roles", List.class));
                request.setAttribute("claims", claims);
                
                for (String requiredRole : requiredRoles)
               	{
                	if (!roles.contains(requiredRole))
               		{
               			// they don't have one of our required claims.  no dice.
               			throw new ServletException("Required permissions not present.");
               		}
               	}
            }
            catch (final SignatureException e) 
            {
                throw new ServletException("Invalid token.");
            }

        }
        
        chain.doFilter(request, response);
	}

	public String getSecret()
	{
		return secret;
	}

	public void setSecret(String secret)
	{
		this.secret = secret;
	}
	
	public void addRequiredRole(String requiredRole)
	{
		requiredRoles.add(requiredRole);
	}

	public List<String> getRequiredRoles()
	{
		return requiredRoles;
	}

	/**
	 * @param requiredRoles
	 */
	public void setRequiredRoles(List<String> requiredRoles)
	{
		this.requiredRoles = requiredRoles;
	}
		
}
