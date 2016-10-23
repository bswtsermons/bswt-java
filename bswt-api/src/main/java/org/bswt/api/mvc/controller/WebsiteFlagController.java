package org.bswt.api.mvc.controller;

import java.util.concurrent.TimeUnit;

import org.bswt.api.dao.WebsiteFlagDao;
import org.bswt.api.model.WebsiteFlag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest/website-flag")
public class WebsiteFlagController
{
	@Autowired
	private WebsiteFlagDao websiteFlagDao;

	@Value("${bswt.websiteFlag.liveVideoKey:live-video-on}")
	private String liveVideoKey;

	@Value("${bswt.websiteFlag.liveAudioKey:live-audio-on}")
	private String liveAudioKey;

	@Value("${bswt.websiteFlag.liveVideoExpire:14400}")
	private Long liveVideoExpire;

	@Value("${bswt.websiteFlag.liveAudioExpire:14400}")
	private Long liveAudioExpire;

	@Value("${bswt.websiteFlag.defaultTimeout:-1}")
	private Long defaultTimeout;

	@Value("${bswt.websiteFlag.defaultTimeUnit:SECONDS}")
	private String defaultTimeUnit;

	@RequestMapping(path = "/{key}", method = RequestMethod.GET)
	public ResponseEntity<WebsiteFlag> get(@PathVariable String key)
	{
		return new ResponseEntity<>(websiteFlagDao.get(key), HttpStatus.OK);
	}

	@RequestMapping(path = "/{key}/{flag}", method = RequestMethod.PUT)
	public ResponseEntity<WebsiteFlag> set(@PathVariable String key, @PathVariable String flag,
			@RequestParam(required = false) Long timeout, @RequestParam(required = false) String timeUnit)
	{
		if ((flag == null || !(flag.equals("on") || flag.equals("off"))))
		{
			throw new IllegalArgumentException("flag must be 'on' or 'off'");
		}
		boolean bflag = flag.equals("on");

		// defaults
		long to = (timeout != null ? timeout : defaultTimeout).longValue();
		TimeUnit tu = TimeUnit.valueOf(timeUnit != null ? timeUnit : defaultTimeUnit);

		return new ResponseEntity<>(websiteFlagDao.set(key, bflag, to, tu), HttpStatus.OK);
	}

}
