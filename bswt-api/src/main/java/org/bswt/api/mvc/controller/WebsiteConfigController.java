package org.bswt.api.mvc.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.bswt.api.dao.WebsiteConfigRepository;
import org.bswt.api.model.WebsiteConfig;
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
	public List<WebsiteConfig> getAll()
	{
		List<WebsiteConfig> target = new ArrayList<>();
		websiteConfigRepository.findAll().forEach(target::add);
		return target;
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public WebsiteConfig get(@PathVariable long id)
	{
		return websiteConfigRepository.findOne(id);
	}

	@RequestMapping(value = "/by-key/{key}")
	public WebsiteConfig get(@PathVariable String key)
	{
		return websiteConfigRepository.findByKey(key);
	}
}
