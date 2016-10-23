package org.bswt.api;

import java.util.ArrayList;
import java.util.List;

import org.bswt.api.mvc.controller.AuthorizationController.Subject;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "bswt.api.rest")
public class SubjectsConfiguration
{
	private List<Subject> subjects = new ArrayList<>();

	public List<Subject> getSubjects()
	{
		return subjects;
	}

	public void setSubjects(List<Subject> subjects)
	{
		this.subjects = subjects;
	}
}
