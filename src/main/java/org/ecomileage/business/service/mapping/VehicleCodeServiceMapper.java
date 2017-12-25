/*
 * Created on 25 thg 10 2017 ( Time 10:42:33 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 */
package org.ecomileage.business.service.mapping;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Component;
import org.ecomileage.bean.VehicleCode;
import org.ecomileage.bean.jpa.VehicleCodeEntity;
import org.ecomileage.bean.jpa.UserEntity;

/**
 * Mapping between entity beans and display beans.
 */
@Component
public class VehicleCodeServiceMapper extends AbstractServiceMapper {

	/**
	 * ModelMapper : bean to bean mapping library.
	 */
	private ModelMapper modelMapper;
	
	/**
	 * Constructor.
	 */
	public VehicleCodeServiceMapper() {
		modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
	}

	/**
	 * Mapping from 'VehicleCodeEntity' to 'VehicleCode'
	 * @param vehicleCodeEntity
	 */
	public VehicleCode mapVehicleCodeEntityToVehicleCode(VehicleCodeEntity vehicleCodeEntity) {
		if(vehicleCodeEntity == null) {
			return null;
		}

		//--- Generic mapping 
		VehicleCode vehicleCode = map(vehicleCodeEntity, VehicleCode.class);

		//--- Link mapping ( link to User )
		if(vehicleCodeEntity.getUser() != null) {
			vehicleCode.setUserId(vehicleCodeEntity.getUser().getId());
		}
		return vehicleCode;
	}
	
	/**
	 * Mapping from 'VehicleCode' to 'VehicleCodeEntity'
	 * @param vehicleCode
	 * @param vehicleCodeEntity
	 */
	public void mapVehicleCodeToVehicleCodeEntity(VehicleCode vehicleCode, VehicleCodeEntity vehicleCodeEntity) {
		if(vehicleCode == null) {
			return;
		}

		//--- Generic mapping 
		map(vehicleCode, vehicleCodeEntity);

		//--- Link mapping ( link : vehicleCode )
		if( hasLinkToUser(vehicleCode) ) {
			UserEntity user1 = new UserEntity();
			user1.setId( vehicleCode.getUserId() );
			vehicleCodeEntity.setUser( user1 );
		} else {
			vehicleCodeEntity.setUser( null );
		}

	}
	
	/**
	 * Verify that User id is valid.
	 * @param User User
	 * @return boolean
	 */
	private boolean hasLinkToUser(VehicleCode vehicleCode) {
		if(vehicleCode.getUserId() != null) {
			return true;
		}
		return false;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected ModelMapper getModelMapper() {
		return modelMapper;
	}

	protected void setModelMapper(ModelMapper modelMapper) {
		this.modelMapper = modelMapper;
	}

}