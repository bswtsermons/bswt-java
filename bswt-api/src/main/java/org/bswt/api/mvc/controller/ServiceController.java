package org.bswt.api.mvc.controller;

import javax.annotation.Resource;

import org.bswt.api.dao.ServiceDao;
import org.bswt.api.model.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest")
public class ServiceController 
{
	@Resource
	private ServiceDao serviceDao;
	
	@RequestMapping("/service")
	public Service service(long id)
	{
		throw new IllegalAccessError("unimplemented");
	}
}
