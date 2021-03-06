/*
 * Created on 14 thg 10 2017 ( Time 19:02:43 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 */
package org.ecomileage.business.service;

import java.util.List;
import java.util.Map;
import org.ecomileage.bean.jpa.CampaignEntity;
import org.ecomileage.bean.Campaign;
import org.springframework.data.domain.Page;

/**
 * Business Service Interface for entity Campaign.
 */
public interface CampaignService { 

	/**
	 * Loads an entity from the database using its Primary Key
	 * @param id
	 * @return entity
	 */
	Campaign findById( Integer id  ) ;

	/**
	 * Loads an entity from the database using its Primary Key
	 * @param id
	 * @return entity
	 */
	CampaignEntity findOne( Integer id  ) ;

	/** 
	 * Loads all entities.
	 * @return all entities
	 */
	List<Campaign> findAll();
	
	/**
	 * Loads entities in page number.
	 * @return entities
	 */

	Page<CampaignEntity> findAll(Integer page);

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
	Campaign save(Campaign entity);

	/**
	 * Updates the given entity in the database
	 * @param entity
	 * @return
	 */
	Campaign update(Campaign entity);
	
	Campaign update(CampaignEntity campaign);

	/**
	 * Creates the given entity in the database
	 * @param entity
	 * @return
	 */
	Campaign create(Campaign entity);
	
	/**
	 * Deletes an entity using its Primary Key
	 * @param id
	 */
	void delete( Integer id );

	Page<CampaignEntity> listPaging(Integer page,Integer size);
	
	CampaignEntity findByYear(Integer year);

	Page<CampaignEntity> filterAdmin( Map<String, Object> params, Integer page, Integer size);
}
