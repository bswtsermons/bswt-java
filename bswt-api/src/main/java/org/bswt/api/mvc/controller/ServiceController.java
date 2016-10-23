package org.bswt.api.mvc.controller;

import java.util.Date;
import java.util.TimeZone;

import javax.annotation.Resource;

import org.bswt.api.dao.ServiceRepository;
import org.bswt.api.model.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/rest/service")
public class ServiceController
{
	private static final Logger LOGGER = LoggerFactory.getLogger(ServiceController.class);

	@Resource
	private ServiceRepository serviceRepository;

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Service> get(@PathVariable long id)
	{
		Service service = serviceRepository.findOne(id);
		if (service == null)
		{
			return new ResponseEntity<Service>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Service>(service, HttpStatus.OK);
	}

	@RequestMapping(value = "/by-date-and-serial", method = RequestMethod.GET)
	public ResponseEntity<Service> getByDateAndSerial(@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date date,
			@PathVariable @RequestParam int series)
	{
		Service service = serviceRepository.findByDateAndSeries(date, series);
		if (service == null)
		{
			return new ResponseEntity<Service>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Service>(service, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> create(@RequestBody Service service, UriComponentsBuilder uriComponentsBuilder)
	{
		LOGGER.info("tz:" + TimeZone.getDefault());
		Service existingService = serviceRepository.findByDateAndSeries(service.getDate(), service.getSeries());

		if (existingService != null)
		{
			LOGGER.info(String.format("duplicate service with date %s and series %s", service.getDate(),
					service.getSeries()));
			return new ResponseEntity<Void>(HttpStatus.CONFLICT);
		}

		serviceRepository.save(service);

		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(uriComponentsBuilder.path("/rest/service/{id}").buildAndExpand(service.getId()).toUri());
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}
}