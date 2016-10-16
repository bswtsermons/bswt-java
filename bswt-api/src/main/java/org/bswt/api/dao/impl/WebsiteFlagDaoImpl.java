package org.bswt.api.dao.impl;

import java.util.concurrent.TimeUnit;

import org.bswt.api.dao.WebsiteFlagDao;
import org.bswt.api.model.WebsiteFlag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.BoundValueOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

@Component
public class WebsiteFlagDaoImpl implements WebsiteFlagDao
{
	@Autowired
	private StringRedisTemplate template;
	
	@Value("${bswt.websiteFlag.prefix:bswt:website-flag}")
	private String prefix;
	
	@Override
	public WebsiteFlag get(String key) 
	{
		WebsiteFlag wf = new WebsiteFlag(key);
		BoundValueOperations<String, String> bvoss = template.boundValueOps(prefix+":"+key);
		if (bvoss != null && bvoss.get() != null && bvoss.get().trim() != "") 
		{
			wf.setFlag(!(bvoss.get().equals("0")));
		}
		return wf;
	}
	
	@Override
	public WebsiteFlag set(String key, boolean flag, long timeout, TimeUnit timeUnit) 
	{
		BoundValueOperations<String, String> bvoss = template.boundValueOps(prefix+":"+key);
		if (timeout > 0 && timeUnit != null)
		{
			bvoss.set(flag ? "1" : "0", timeout, timeUnit);
		}
		else
		{
			bvoss.set(flag ? "1" : "0");
		}
		return new WebsiteFlag(key, flag);
	}

	@Override
	public WebsiteFlag set(String key, boolean flag) 
	{
		return set(key, flag, -1, null);
	}
	
	@Override
	public void unsetFlag(String key) 
	{
		template.delete(key);
	}
		
}
