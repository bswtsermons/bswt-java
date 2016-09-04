package org.bswt.api.dao;

import org.bswt.api.model.WebsiteConfig;
import org.springframework.data.repository.CrudRepository;

public interface WebsiteConfigRepository extends CrudRepository<WebsiteConfig, Long> 
{
	WebsiteConfig findByKey(String key);
}
