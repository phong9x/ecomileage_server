package org.ecomileage.data.repository.jpa;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Date;
import java.util.List;

import org.ecomileage.bean.jpa.TeamMemberEntity;
/**
 * Repository : TeamMember.
 */
public interface TeamMemberJpaRepository extends PagingAndSortingRepository<TeamMemberEntity, Integer>,
		JpaSpecificationExecutor<TeamMemberEntity> {
	@Query(
			"Select u From TeamMemberEntity u "
			)
	Page<TeamMemberEntity> listPaging(Pageable pageable);
	
	@Query(
			"Select u From TeamMemberEntity u Where u.user.id = ?1 AND u.isDelete = false AND u.team.isDelete = false"
			)
	List<TeamMemberEntity> findByUserId(Integer userId);
	
	@Query(
			"Select u From TeamMemberEntity u Where u.team.id = ?1 AND u.isDelete = false "
			)
	List<TeamMemberEntity> findByGroupId(Integer groupId);
	
	@Query(
			"Select COUNT(u) From TeamMemberEntity u Where u.user.id = ?1 AND u.isDelete = false"
			)
	Long countGroupByUserId(Integer userId);

	@Query(
			"Select u From TeamMemberEntity u WHERE u.user.id = ?1 AND u.team.id =?2"
			)
	TeamMemberEntity findByUserIdAndGroupId(Integer userId, Integer groupId);
	
	@Query(
			"Select COUNT(DISTINCT u.user.id) From TeamMemberEntity u Where u.team.id = ?1 AND u.isDelete = false"
			)
	Long countMemberByGroupId(Integer groupId);
	
	@Query(
			"Select SUM(u.totalReduceCo2) From TeamMemberEntity u Where u.team.id = ?1 AND u.isDelete = false"
			)
	Long countCO2ByGroupId(Integer groupId);
	
	@Modifying
	@Query(
			value="UPDATE team_member SET is_delete = true WHERE group_id = ?1",nativeQuery=true
			)
	void update_isDelete_byGroupId(Integer id);
	
	@Modifying
	@Query(
			value="UPDATE team_member SET is_delete = true WHERE group_id = ?1 AND member_id =?2",nativeQuery=true
			)
	void update_isDelete_byGroupIdAndUserId(Integer groupId, Integer userId);
	
	@Modifying
	@Query(
			value="UPDATE team_member SET is_manager = false, status = 0 WHERE group_id = ?1 AND member_id !=?2",nativeQuery=true
			)
	void update_TranformManager(Integer groupId, Integer mangerId);
	
	@Modifying
	@Query(
			value="UPDATE team_member SET is_manager = false WHERE group_id = ?1 AND member_id !=?2",nativeQuery=true
			)
	void update_Manager(Integer groupId, Integer mangerId);

}
