package org.bswt.api.mvc.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.bswt.api.dao.WebsiteConfigRepository;
import org.bswt.api.model.WebsiteConfig;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest/website-config")
public class WebsiteConfigController
{
	@Resource
	private WebsiteConfigRepository websiteConfigRepository;

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<WebsiteConfig>> getAll()
	{
		List<WebsiteConfig> target = new ArrayList<>();
		websiteConfigRepository.findAll().forEach(target::add);
		return new ResponseEntity<List<WebsiteConfig>>(target, HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<WebsiteConfig> get(@PathVariable long id)
	{
		ResponseEntity<WebsiteConfig> re;
		WebsiteConfig wsc = websiteConfigRepository.findOne(id);
		if (wsc == null)
		{
			re = new ResponseEntity<WebsiteConfig>(HttpStatus.NOT_FOUND);
		}
		else
		{
			re = new ResponseEntity<WebsiteConfig>(wsc, HttpStatus.OK);
		}
		return re;
	}

	@RequestMapping(value = "/by-key/{key}", method=RequestMethod.GET)
	public ResponseEntity<WebsiteConfig> get(@PathVariable String key)
	{
		ResponseEntity<WebsiteConfig> re;
		WebsiteConfig wsc = websiteConfigRepository.findByKey(key);
		if (wsc == null)
		{
			re = new ResponseEntity<WebsiteConfig>(HttpStatus.NOT_FOUND);
		}
		else
		{
			re = new ResponseEntity<WebsiteConfig>(wsc, HttpStatus.OK);
		}
		return re;
	}
}
