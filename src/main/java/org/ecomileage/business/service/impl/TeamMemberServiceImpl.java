/*
 * Created on 2 thg 10 2017 ( Time 15:10:01 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 */
package org.ecomileage.business.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

import org.ecomileage.bean.TeamMember;
import org.ecomileage.bean.jpa.TeamMemberEntity;
import java.util.Date;
import java.util.HashMap;

import org.ecomileage.business.service.TeamMemberService;
import org.ecomileage.business.service.mapping.TeamMemberServiceMapper;
import org.ecomileage.data.repository.jpa.TeamMemberJpaRepository;
import org.ecomileage.web.common.utils.DataUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
/**
 * Implementation of TeamMemberService
 */
@Component
@Transactional
public class TeamMemberServiceImpl implements TeamMemberService {

	@Resource
	private TeamMemberJpaRepository teamMemberJpaRepository;
	@Resource
	EntityManager em;
	
	private static final Integer PAGE_SIZE   = 15;

	@Resource
	private TeamMemberServiceMapper teamMemberServiceMapper;
	
	@Override
	public TeamMember findById(Integer id) {
		TeamMemberEntity teamMemberEntity = teamMemberJpaRepository.findOne(id);
		return teamMemberServiceMapper.mapTeamMemberEntityToTeamMember(teamMemberEntity);
	}

	@Override
	public TeamMemberEntity findOne(Integer id) {
		TeamMemberEntity teamMemberEntity = teamMemberJpaRepository.findOne(id);
		return teamMemberEntity;
	}

	@Override
	public Page<TeamMemberEntity> findAll(Integer pageNumber) {

		PageRequest request = new PageRequest(pageNumber - 1, PAGE_SIZE,
				new Sort(new Order(Direction.DESC, "id")));
		return teamMemberJpaRepository.findAll(request);
	}

	@Override
	public List<TeamMember> findAll() {
		Iterable<TeamMemberEntity> entities = teamMemberJpaRepository.findAll();
		List<TeamMember> beans = new ArrayList<TeamMember>();
		for(TeamMemberEntity teamMemberEntity : entities) {
			beans.add(teamMemberServiceMapper.mapTeamMemberEntityToTeamMember(teamMemberEntity));
		}
		return beans;
	}

	/**
	 * Count total entit
	 * @return Long
	 */
	public Long countTotal(){
		Long count = teamMemberJpaRepository.count();
		return count;
	}

	@Override
	public TeamMember save(TeamMember teamMember) {
		return update(teamMember) ;
	}

	@Override
	public TeamMember create(TeamMember teamMember) {
/*
		TeamMemberEntity teamMemberEntity = teamMemberJpaRepository.findOne(teamMember.getId());
		if( teamMemberEntity != null ) {
			throw new IllegalStateException("already.exists");
		}

		teamMemberEntity = new TeamMemberEntity();
		teamMemberServiceMapper.mapTeamMemberToTeamMemberEntity(teamMember, teamMemberEntity);
		TeamMemberEntity teamMemberEntitySaved = teamMemberJpaRepository.save(teamMemberEntity);
		return teamMemberServiceMapper.mapTeamMemberEntityToTeamMember(teamMemberEntitySaved);
*/
		TeamMemberEntity teamMemberEntity = new TeamMemberEntity();
		teamMemberServiceMapper.mapTeamMemberToTeamMemberEntity(teamMember, teamMemberEntity);
		TeamMemberEntity teamMemberEntitySaved = teamMemberJpaRepository.save(teamMemberEntity);
		return teamMemberServiceMapper.mapTeamMemberEntityToTeamMember(teamMemberEntitySaved);
	}

	@Override
	public TeamMember update(TeamMember teamMember) {
		TeamMemberEntity teamMemberEntity = teamMemberJpaRepository.findOne(teamMember.getId());
		teamMemberServiceMapper.mapTeamMemberToTeamMemberEntity(teamMember, teamMemberEntity);
		TeamMemberEntity teamMemberEntitySaved = teamMemberJpaRepository.save(teamMemberEntity);
		return teamMemberServiceMapper.mapTeamMemberEntityToTeamMember(teamMemberEntitySaved);
	}
	
	@Override
	public TeamMemberEntity update(TeamMemberEntity teamMemberEntity) {
		TeamMemberEntity teamMemberEntitySaved = teamMemberJpaRepository.save(teamMemberEntity);
		return teamMemberEntitySaved;
	}

	@Override
	public void delete(Integer id) {
		teamMemberJpaRepository.delete(id);
	}

	public TeamMemberJpaRepository getTeamMemberJpaRepository() {
		return teamMemberJpaRepository;
	}

	public void setTeamMemberJpaRepository(TeamMemberJpaRepository teamMemberJpaRepository) {
		this.teamMemberJpaRepository = teamMemberJpaRepository;
	}

	public TeamMemberServiceMapper getTeamMemberServiceMapper() {
		return teamMemberServiceMapper;
	}

	public void setTeamMemberServiceMapper(TeamMemberServiceMapper teamMemberServiceMapper) {
		this.teamMemberServiceMapper = teamMemberServiceMapper;
	}

	@Override
	public Page<TeamMemberEntity> listPaging(Integer page, Integer size) {
		try {
			PageRequest pageable = new PageRequest(page-1, size, new Sort(new Order(Direction.DESC, "createDate")));
			return teamMemberJpaRepository.listPaging(pageable);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}


	@Override
	public Page<TeamMemberEntity> filterAdmin( Map<String, Object> params, Integer page, Integer size) {

		Specification<TeamMemberEntity> spec = new Specification<TeamMemberEntity>() {
			@Override
			public Predicate toPredicate(Root<TeamMemberEntity> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> predicates = new ArrayList<>();


				if(params.get("startTime") != null){
					String startTime = String.valueOf(params.get("startTime"));
					Predicate predicate = cb.equal(root.<Date>get("createDate"),DataUtils.parseDate(startTime, DataUtils.FormatDate.YYYYMMDD.getPattern()));
					predicates.add(predicate);
				}
				
				if(params.get("endTime") != null){
					String endTime = String.valueOf(params.get("endTime"));
					Predicate predicate = cb.equal(root.<Date>get("createDate"),DataUtils.parseDate(endTime, DataUtils.FormatDate.YYYYMMDD.getPattern()));
					predicates.add(predicate);
				}
				
				if(params.get("groupId") != null && params.get("groupId") != ""){
					Integer groupId = DataUtils.parseInt(params.get("groupId"));
					Predicate predicate = cb.equal(root.get("team").get("id"),groupId);
					predicates.add(predicate);
				}

				if(params.get("username") != null && params.get("username") != ""){
					String username = String.valueOf(params.get("username"));
					Predicate predicate = cb.or(cb.like(root.get("user").get("username"),"%"+username+"%"),cb.like(root.get("user").get("fullname"),"%"+username+"%"));
					predicates.add(predicate);
				}

				Predicate predicate = cb.equal(root.get("isDelete"),false);
				predicates.add(predicate);
				
				return cb.and(predicates.toArray(new Predicate[] {}));
			}

		};

		Order order = new Order(Direction.DESC, "createDate");
		if (params.get("orderBy") != null) {
			String orderBy = String.valueOf(params.get("orderBy"));
			order = new Order(Direction.DESC, orderBy);
		}

		PageRequest pageable = new PageRequest(page - 1, size, new Sort(order));
		return teamMemberJpaRepository.findAll(spec, pageable);
	}

	@Override
	public List<TeamMemberEntity> findByUserId(Integer userId) {
		try {
			return teamMemberJpaRepository.findByUserId(userId);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Long countMemberByGroupId(Integer groupId) {
		try {
			return teamMemberJpaRepository.countMemberByGroupId(groupId);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Long countCO2ByGroupId(Integer groupId) {
		try {
			Long sum = teamMemberJpaRepository.countCO2ByGroupId(groupId);
			if(sum == null) {
				sum = 0l;
			}
			return sum;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public TeamMemberEntity findByUserIdAndGroupId(Integer userId, Integer groupId) {
		try {
			return teamMemberJpaRepository.findByUserIdAndGroupId(userId, groupId);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public void update_isDelete_byGroupId(Integer id) {
		try {
			teamMemberJpaRepository.update_isDelete_byGroupId(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void update_isDelete_byGroupIdAndUserId(Integer groupId, Integer userId) {
		try {
			teamMemberJpaRepository.update_isDelete_byGroupIdAndUserId(groupId, userId);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void update_TranformManager(Integer groupId, Integer mangerId) {
		try {
			teamMemberJpaRepository.update_TranformManager(groupId, mangerId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public Long countGroupByUserId(Integer userId) {
		try {
			Long sum = teamMemberJpaRepository.countGroupByUserId(userId);
			if(sum == null) {
				sum = 0l;
			}
			return sum;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<Map<String, Object>> findByGroupId(Integer groupId) {
		try {
			String SELECT = "SELECT new map("
					  + "t.id as id, "
					  + "t.user.fullname as fullname,"
					  + "t.user.username as username, "
					  + "t.createDate as createDate, "
					  + "t.isManager as isManager, "
					  + "(SELECT ROUND(COALESCE(SUM(u.totalMileage),0),0) FROM MileageHistoryEntity u WHERE  u.user.id = t.user.id AND u.type = 1 ) as totalMileage, "
					  + "(SELECT ROUND(COALESCE(SUM(u.totalReduceCo2),0),0) FROM MileageHistoryEntity u WHERE u.user.id = t.user.id AND u.type = 1 ) as totalReduceCo2, "
					  + "(SELECT COALESCE(SUM(u.point),0) FROM MileageHistoryEntity u WHERE  u.user.id = t.user.id ) as totalTree "
				      + ") ";
			String FROM  =	"FROM TeamMemberEntity t ";
			String WHERE = 	"WHERE t.isDelete = false AND t.team.id ="+groupId+" ";
			String ORDER_BY = 	"ORDER BY t.id DESC";
			Query query = em.createQuery(SELECT + FROM + WHERE+ ORDER_BY);
			@SuppressWarnings("unchecked")
			
			List<Map<String,Object>> result = query.getResultList();
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public void update_Manager(Integer groupId, Integer mangerId) {
		try {
			teamMemberJpaRepository.update_Manager(groupId, mangerId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}