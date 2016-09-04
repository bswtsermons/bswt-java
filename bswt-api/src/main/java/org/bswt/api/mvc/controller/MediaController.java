package org.bswt.api.mvc.controller;

import javax.annotation.Resource;

import org.bswt.api.dao.MediaRepository;
import org.bswt.api.dao.ServiceRepository;
import org.bswt.api.model.Media;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/rest/media")
public class MediaController 
{
	private static final Logger LOGGER = LoggerFactory.getLogger(MediaController.class);
	
	@Resource 
	ServiceRepository serviceRepository;
	
	@Resource
	private MediaRepository mediaRepository;

	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<Media> get(@PathVariable long id)
	{
		Media media = mediaRepository.findOne(id);
		if (media == null)
		{
			return new ResponseEntity<Media>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Media>(media, HttpStatus.OK);
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Void> create(@RequestBody Media media, UriComponentsBuilder uriComponentsBuilder)
	{
		Media existingMedia = mediaRepository.findByServiceAndTypeAndRepositoryAndExtension(media.getService(), media.getType(), media.getRepository(), media.getExtension());
		
		if (existingMedia != null)
		{
			LOGGER.info(String.format("duplicate media with service id %d and type %s and repository %s and extension %s", 
					                   media.getService().getId(), media.getType(), media.getRepository(), media.getExtension()));
            return new ResponseEntity<Void>(HttpStatus.CONFLICT);
		}
		
		mediaRepository.save(media);
		
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(uriComponentsBuilder.path("/rest/media/{id}").buildAndExpand(media.getId()).toUri());
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}
}
