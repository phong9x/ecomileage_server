/*
 * Created on 2 thg 10 2017 ( Time 15:10:01 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 */
package org.ecomileage.business.service;

import java.util.Date;
import java.util.List;
import java.util.Map;
import org.ecomileage.bean.jpa.SponsorEntity;
import org.ecomileage.bean.Sponsor;
import org.springframework.data.domain.Page;

/**
 * Business Service Interface for entity Sponsor.
 */
public interface SponsorService { 

	/**
	 * Loads an entity from the database using its Primary Key
	 * @param id
	 * @return entity
	 */
	Sponsor findById( Integer id  ) ;

	/**
	 * Loads an entity from the database using its Primary Key
	 * @param id
	 * @return entity
	 */
	SponsorEntity findOne( Integer id  ) ;

	/** 
	 * Loads all entities.
	 * @return all entities
	 */
	List<Sponsor> findAll();
	
	/**
	 * Loads entities in page number.
	 * @return entities
	 */

	Page<SponsorEntity> findAll(Integer page);

	/**
	 * Count all entities
	 * @return Long
	 */
	Long countTotal();
	

	/**
	 * Saves the given entity in the database (create or update)
	 * @param entity
	 * @return entity
	 */
	Sponsor save(Sponsor entity);

	/**
	 * Updates the given entity in the database
	 * @param entity
	 * @return
	 */
	Sponsor update(Sponsor entity);

	/**
	 * Creates the given entity in the database
	 * @param entity
	 * @return
	 */
	Sponsor create(Sponsor entity);
	
	/**
	 * Deletes an entity using its Primary Key
	 * @param id
	 */
	void delete( Integer id );

	Page<SponsorEntity> listPaging(Integer page,Integer size);
	
	Page<SponsorEntity> filterAdmin( Map<String, Object> params, Integer page, Integer size);
}
