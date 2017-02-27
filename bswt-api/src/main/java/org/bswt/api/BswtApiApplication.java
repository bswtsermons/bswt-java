package org.bswt.api;

import org.bswt.api.config.ClientCredentialsConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableConfigurationProperties(ClientCredentialsConfiguration.class)
@SpringBootApplication
@EnableSwagger2
public class BswtApiApplication
{
	public static void main(String[] args)
	{
		SpringApplication.run(BswtApiApplication.class, args);
	}
}
