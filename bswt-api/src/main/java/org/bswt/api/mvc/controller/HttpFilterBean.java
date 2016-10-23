package org.bswt.api.mvc.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.filter.GenericFilterBean;

public abstract class HttpFilterBean extends GenericFilterBean
{
	private List<String> requestMethods = new ArrayList<>();

	public List<String> getRequestMethod()
	{
		return requestMethods;
	}

	public void setRequestMethod(List<String> requestMethods)
	{
		this.requestMethods = requestMethods;
	}

	public void addRequestMethod(String requestMethod)

	{
		requestMethods.add(requestMethod);
	}

	public boolean requestMethodFound(String requestMethod)
	{
		return requestMethods.contains(requestMethod);
	}

}
