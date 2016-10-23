package org.bswt.api.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "website_config", uniqueConstraints = { @UniqueConstraint(columnNames = { "config_key" }) })
public class WebsiteConfig
{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "website_flag_id")
	private Long id;

	@Column(name = "config_key")
	private String key;

	private String value;

	public Long getId()
	{
		return id;
	}

	public void setId(Long id)
	{
		this.id = id;
	}

	public String getKey()
	{
		return key;
	}

	public void setKey(String key)
	{
		this.key = key;
	}

	public String getValue()
	{
		return value;
	}

	public void setValue(String value)
	{
		this.value = value;
	}
}
