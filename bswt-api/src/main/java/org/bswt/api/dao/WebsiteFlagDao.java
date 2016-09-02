package org.bswt.api.dao;

import java.io.StringReader;
import java.util.concurrent.TimeUnit;

import org.bswt.api.model.WebsiteFlag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;

public interface WebsiteFlagDao 
{
	WebsiteFlag get(String key);
	
	WebsiteFlag set(String key, boolean flag);
	
	WebsiteFlag set(String key, boolean flag, long timeout, TimeUnit timeUnit);
	
	void unsetFlag(String key);
}
