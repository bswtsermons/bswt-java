package org.bswt.api.config;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.web.util.matcher.RequestMatcher;

@Configuration
@EnableResourceServer
public class ResourceServerConfiguration extends ResourceServerConfigurerAdapter
{
	@Value("${token.clientUrl:http://localhost:8080/oauth/check_token}")
	private String clientUrl;
	
	@Value("${bswt.api.resourceId:bswt}")
    private String resourceId;
	
	@Value("${token.clientId:trusted-app}")
	private String clientId;
	
	@Value("${token.clientSecret:secret}")
	private String clientSecret;
	
	@Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        resources
          // .tokenServices(remoteTokenServices())
          .resourceId(resourceId);
    }

	
	@Override
    public void configure(HttpSecurity http) throws Exception {
        // @formatter:off
        	http
        	  //.requestMatcher(new OAuthRequestedMatcher()).authorizeRequests()
        	  .authorizeRequests()
        	    .antMatchers(HttpMethod.OPTIONS).permitAll()
        	    .antMatchers(HttpMethod.PUT).authenticated()
        	    .antMatchers(HttpMethod.POST).authenticated()
        	    .antMatchers("/resources/*").authenticated()
        	    .anyRequest().permitAll()
        	    .and()
       	    
        	  .httpBasic()
        	    .and()
              .csrf().disable();
        // @formatter:on
    }
    
	
	private static class OAuthRequestedMatcher implements RequestMatcher {
        public boolean matches(HttpServletRequest request) {
            String auth = request.getHeader("Authorization");
            // Determine if the client request contained an OAuth Authorization
            boolean haveOauth2Token = (auth != null) && (auth.startsWith("Bearer") || auth.startsWith("bearer"));
            //haveOauth2Token = (auth != null) && auth.startsWith("Bearer");
            boolean haveAccessToken = request.getParameter("access_token")!=null;
			return haveOauth2Token || haveAccessToken;
        }
    }
}
