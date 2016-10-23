package org.bswt.api.dao;

import org.bswt.api.model.Media;
import org.bswt.api.model.Service;
import org.springframework.data.repository.CrudRepository;

public interface MediaRepository extends CrudRepository<Media, Long>
{
	Media findByServiceAndTypeAndRepositoryAndExtension(Service service, String type, String repository,
			String extension);
}
