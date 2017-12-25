/*
 * Created on 2 thg 10 2017 ( Time 15:10:01 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 */
package org.ecomileage.business.service.mapping;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Component;
import org.ecomileage.bean.TeamMember;
import org.ecomileage.bean.jpa.TeamMemberEntity;
import org.ecomileage.bean.jpa.TeamEntity;
import org.ecomileage.bean.jpa.UserEntity;

/**
 * Mapping between entity beans and display beans.
 */
@Component
public class TeamMemberServiceMapper extends AbstractServiceMapper {

	/**
	 * ModelMapper : bean to bean mapping library.
	 */
	private ModelMapper modelMapper;
	
	/**
	 * Constructor.
	 */
	public TeamMemberServiceMapper() {
		modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
	}

	/**
	 * Mapping from 'TeamMemberEntity' to 'TeamMember'
	 * @param teamMemberEntity
	 */
	public TeamMember mapTeamMemberEntityToTeamMember(TeamMemberEntity teamMemberEntity) {
		if(teamMemberEntity == null) {
			return null;
		}

		//--- Generic mapping 
		TeamMember teamMember = map(teamMemberEntity, TeamMember.class);

		//--- Link mapping ( link to Team )
		if(teamMemberEntity.getTeam() != null) {
			teamMember.setGroupId(teamMemberEntity.getTeam().getId());
		}
		//--- Link mapping ( link to User )
		if(teamMemberEntity.getUser() != null) {
			teamMember.setMemberId(teamMemberEntity.getUser().getId());
		}
		return teamMember;
	}
	
	/**
	 * Mapping from 'TeamMember' to 'TeamMemberEntity'
	 * @param teamMember
	 * @param teamMemberEntity
	 */
	public void mapTeamMemberToTeamMemberEntity(TeamMember teamMember, TeamMemberEntity teamMemberEntity) {
		if(teamMember == null) {
			return;
		}

		//--- Generic mapping 
		map(teamMember, teamMemberEntity);

		//--- Link mapping ( link : teamMember )
		if( hasLinkToTeam(teamMember) ) {
			TeamEntity team1 = new TeamEntity();
			team1.setId( teamMember.getGroupId() );
			teamMemberEntity.setTeam( team1 );
		} else {
			teamMemberEntity.setTeam( null );
		}

		//--- Link mapping ( link : teamMember )
		if( hasLinkToUser(teamMember) ) {
			UserEntity user2 = new UserEntity();
			user2.setId( teamMember.getMemberId() );
			teamMemberEntity.setUser( user2 );
		} else {
			teamMemberEntity.setUser( null );
		}

	}
	
	/**
	 * Verify that Team id is valid.
	 * @param Team Team
	 * @return boolean
	 */
	private boolean hasLinkToTeam(TeamMember teamMember) {
		if(teamMember.getGroupId() != null) {
			return true;
		}
		return false;
	}

	/**
	 * Verify that User id is valid.
	 * @param User User
	 * @return boolean
	 */
	private boolean hasLinkToUser(TeamMember teamMember) {
		if(teamMember.getMemberId() != null) {
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