package org.bswt.api.dao;

import java.util.Date;

import org.bswt.api.model.Service;
import org.springframework.data.repository.CrudRepository;

public interface ServiceRepository extends CrudRepository<Service, Long>
{
	Service findByDateAndSeries(Date date, int series);
}
