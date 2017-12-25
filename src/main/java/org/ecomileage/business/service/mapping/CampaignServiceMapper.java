/*
 * Created on 14 thg 10 2017 ( Time 19:02:43 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 */
package org.ecomileage.business.service.mapping;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Component;
import org.ecomileage.bean.Campaign;
import org.ecomileage.bean.jpa.CampaignEntity;

/**
 * Mapping between entity beans and display beans.
 */
@Component
public class CampaignServiceMapper extends AbstractServiceMapper {

	/**
	 * ModelMapper : bean to bean mapping library.
	 */
	private ModelMapper modelMapper;
	
	/**
	 * Constructor.
	 */
	public CampaignServiceMapper() {
		modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
	}

	/**
	 * Mapping from 'CampaignEntity' to 'Campaign'
	 * @param campaignEntity
	 */
	public Campaign mapCampaignEntityToCampaign(CampaignEntity campaignEntity) {
		if(campaignEntity == null) {
			return null;
		}

		//--- Generic mapping 
		Campaign campaign = map(campaignEntity, Campaign.class);

		return campaign;
	}
	
	/**
	 * Mapping from 'Campaign' to 'CampaignEntity'
	 * @param campaign
	 * @param campaignEntity
	 */
	public void mapCampaignToCampaignEntity(Campaign campaign, CampaignEntity campaignEntity) {
		if(campaign == null) {
			return;
		}

		//--- Generic mapping 
		map(campaign, campaignEntity);

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