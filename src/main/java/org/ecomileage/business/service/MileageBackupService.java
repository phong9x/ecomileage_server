/*
 * Created on 2 thg 10 2017 ( Time 15:10:01 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 */
package org.ecomileage.business.service;

import java.util.List;
import java.util.Map;
import org.ecomileage.bean.jpa.MileageBackupEntity;
import org.ecomileage.bean.MileageBackup;
import org.springframework.data.domain.Page;

/**
 * Business Service Interface for entity MileageBackup.
 */
public interface MileageBackupService { 

	/**
	 * Loads an entity from the database using its Primary Key
	 * @param id
	 * @return entity
	 */
	MileageBackup findById( Integer id  ) ;

	/**
	 * Loads an entity from the database using its Primary Key
	 * @param id
	 * @return entity
	 */
	MileageBackupEntity findOne( Integer id  ) ;

	/** 
	 * Loads all entities.
	 * @return all entities
	 */
	List<MileageBackup> findAll();
	
	/**
	 * Loads entities in page number.
	 * @return entities
	 */

	Page<MileageBackupEntity> findAll(Integer page);

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
	MileageBackup save(MileageBackup entity);

	/**
	 * Updates the given entity in the database
	 * @param entity
	 * @return
	 */
	MileageBackup update(MileageBackup entity);

	/**
	 * Creates the given entity in the database
	 * @param entity
	 * @return
	 */
	MileageBackup create(MileageBackup entity);
	
	/**
	 * Deletes an entity using its Primary Key
	 * @param id
	 */
	void delete( Integer id );

	Page<MileageBackupEntity> listPaging(Integer page,Integer size);
	
	MileageBackup findBySenderId(Integer senderId);

	Page<MileageBackupEntity> filterAdmin( Map<String, Object> params, Integer page, Integer size);
}
