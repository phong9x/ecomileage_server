/*
 * Created on 7 thg 12 2017 ( Time 14:57:33 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 */
package org.ecomileage.business.service.mapping;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Component;
import org.ecomileage.bean.MileageApply;
import org.ecomileage.bean.jpa.MileageApplyEntity;
import org.ecomileage.bean.jpa.UserEntity;
import org.ecomileage.bean.jpa.CategoryEntity;

/**
 * Mapping between entity beans and display beans.
 */
@Component
public class MileageApplyServiceMapper extends AbstractServiceMapper {

	/**
	 * ModelMapper : bean to bean mapping library.
	 */
	private ModelMapper modelMapper;
	
	/**
	 * Constructor.
	 */
	public MileageApplyServiceMapper() {
		modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
	}

	/**
	 * Mapping from 'MileageApplyEntity' to 'MileageApply'
	 * @param mileageApplyEntity
	 */
	public MileageApply mapMileageApplyEntityToMileageApply(MileageApplyEntity mileageApplyEntity) {
		if(mileageApplyEntity == null) {
			return null;
		}

		//--- Generic mapping 
		MileageApply mileageApply = map(mileageApplyEntity, MileageApply.class);

		//--- Link mapping ( link to User )
		if(mileageApplyEntity.getUser() != null) {
			mileageApply.setUserId(mileageApplyEntity.getUser().getId());
		}
		//--- Link mapping ( link to Category )
		if(mileageApplyEntity.getCategory() != null) {
			mileageApply.setGiftId(mileageApplyEntity.getCategory().getId());
		}
		return mileageApply;
	}
	
	/**
	 * Mapping from 'MileageApply' to 'MileageApplyEntity'
	 * @param mileageApply
	 * @param mileageApplyEntity
	 */
	public void mapMileageApplyToMileageApplyEntity(MileageApply mileageApply, MileageApplyEntity mileageApplyEntity) {
		if(mileageApply == null) {
			return;
		}

		//--- Generic mapping 
		map(mileageApply, mileageApplyEntity);

		//--- Link mapping ( link : mileageApply )
		if( hasLinkToUser(mileageApply) ) {
			UserEntity user1 = new UserEntity();
			user1.setId( mileageApply.getUserId() );
			mileageApplyEntity.setUser( user1 );
		} else {
			mileageApplyEntity.setUser( null );
		}

		//--- Link mapping ( link : mileageApply )
		if( hasLinkToCategory(mileageApply) ) {
			CategoryEntity category2 = new CategoryEntity();
			category2.setId( mileageApply.getGiftId() );
			mileageApplyEntity.setCategory( category2 );
		} else {
			mileageApplyEntity.setCategory( null );
		}

	}
	
	/**
	 * Verify that User id is valid.
	 * @param User User
	 * @return boolean
	 */
	private boolean hasLinkToUser(MileageApply mileageApply) {
		if(mileageApply.getUserId() != null) {
			return true;
		}
		return false;
	}

	/**
	 * Verify that Category id is valid.
	 * @param Category Category
	 * @return boolean
	 */
	private boolean hasLinkToCategory(MileageApply mileageApply) {
		if(mileageApply.getGiftId() != null) {
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