/*
 * Created on 25 thg 10 2017 ( Time 14:50:49 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 */
package org.ecomileage.business.service;

import java.util.List;
import java.util.Map;
import org.ecomileage.bean.jpa.MileageFavouriteEntity;
import org.ecomileage.bean.MileageFavourite;
import org.springframework.data.domain.Page;

/**
 * Business Service Interface for entity MileageFavourite.
 */
public interface MileageFavouriteService { 

	/**
	 * Loads an entity from the database using its Primary Key
	 * @param id
	 * @return entity
	 */
	MileageFavourite findById( Integer id  ) ;

	/**
	 * Loads an entity from the database using its Primary Key
	 * @param id
	 * @return entity
	 */
	MileageFavouriteEntity findOne( Integer id  ) ;

	/** 
	 * Loads all entities.
	 * @return all entities
	 */
	List<MileageFavourite> findAll();
	
	/**
	 * Loads entities in page number.
	 * @return entities
	 */

	Page<MileageFavouriteEntity> findAll(Integer page);

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
	MileageFavourite save(MileageFavourite entity);

	/**
	 * Updates the given entity in the database
	 * @param entity
	 * @return
	 */
	MileageFavourite update(MileageFavourite entity);

	/**
	 * Creates the given entity in the database
	 * @param entity
	 * @return
	 */
	MileageFavourite create(MileageFavourite entity);
	
	/**
	 * Deletes an entity using its Primary Key
	 * @param id
	 */
	void delete( Integer id );

	Page<MileageFavouriteEntity> listPaging(Integer page,Integer size);
	
	Page<MileageFavouriteEntity> listPagingByUserId( Integer page, Integer size, Integer userId);



	Page<MileageFavouriteEntity> filterAdmin( Map<String, Object> params, Integer page, Integer size);
}
