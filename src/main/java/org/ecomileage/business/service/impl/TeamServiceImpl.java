/*
 * Created on 2 thg 10 2017 ( Time 15:10:01 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 */
package org.ecomileage.business.service.impl;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;
import org.ecomileage.bean.Group;
import org.ecomileage.bean.Team;
import org.ecomileage.bean.jpa.TeamEntity;
import java.util.Date;
import java.util.List;
import org.ecomileage.business.service.TeamService;
import org.ecomileage.business.service.mapping.TeamServiceMapper;
import org.ecomileage.data.repository.jpa.TeamJpaRepository;
import org.ecomileage.web.common.PageCustom;
import org.ecomileage.web.common.utils.DataUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
/**
 * Implementation of TeamService
 */
@Component
@Transactional
public class TeamServiceImpl implements TeamService {

	@Resource
	private TeamJpaRepository teamJpaRepository;
	@Resource
	private EntityManager em;
	
	private static final Integer PAGE_SIZE   = 15;

	@Resource
	private TeamServiceMapper teamServiceMapper;
	
	@Override
	public Team findById(Integer id) {
		TeamEntity teamEntity = teamJpaRepository.findOne(id);
		return teamServiceMapper.mapTeamEntityToTeam(teamEntity);
	}

	@Override
	public TeamEntity findOne(Integer id) {
		TeamEntity teamEntity = teamJpaRepository.findOne(id);
		return teamEntity;
	}

	@Override
	public Page<TeamEntity> findAll(Integer pageNumber) {

		PageRequest request = new PageRequest(pageNumber - 1, PAGE_SIZE,
				new Sort(new Order(Direction.DESC, "id")));
		return teamJpaRepository.findAll(request);
	}

	@Override
	public List<Team> findAll() {
		Iterable<TeamEntity> entities = teamJpaRepository.findAll();
		List<Team> beans = new ArrayList<Team>();
		for(TeamEntity teamEntity : entities) {
			beans.add(teamServiceMapper.mapTeamEntityToTeam(teamEntity));
		}
		return beans;
	}

	/**
	 * Count total entit
	 * @return Long
	 */
	public Long countTotal(){
		Long count = teamJpaRepository.count();
		return count;
	}

	@Override
	public Team save(Team team) {
		return update(team) ;
	}

	@Override
	public Team create(Team team) {
/*
		TeamEntity teamEntity = teamJpaRepository.findOne(team.getId());
		if( teamEntity != null ) {
			throw new IllegalStateException("already.exists");
		}

		teamEntity = new TeamEntity();
		teamServiceMapper.mapTeamToTeamEntity(team, teamEntity);
		TeamEntity teamEntitySaved = teamJpaRepository.save(teamEntity);
		return teamServiceMapper.mapTeamEntityToTeam(teamEntitySaved);
*/
		TeamEntity teamEntity = new TeamEntity();
		teamServiceMapper.mapTeamToTeamEntity(team, teamEntity);
		TeamEntity teamEntitySaved = teamJpaRepository.save(teamEntity);
		return teamServiceMapper.mapTeamEntityToTeam(teamEntitySaved);
	}

	@Override
	public Team update(Team team) {
		TeamEntity teamEntity = teamJpaRepository.findOne(team.getId());
		teamServiceMapper.mapTeamToTeamEntity(team, teamEntity);
		TeamEntity teamEntitySaved = teamJpaRepository.save(teamEntity);
		return teamServiceMapper.mapTeamEntityToTeam(teamEntitySaved);
	}

	@Override
	public void delete(Integer id) {
		teamJpaRepository.delete(id);
	}

	public TeamJpaRepository getTeamJpaRepository() {
		return teamJpaRepository;
	}

	public void setTeamJpaRepository(TeamJpaRepository teamJpaRepository) {
		this.teamJpaRepository = teamJpaRepository;
	}

	public TeamServiceMapper getTeamServiceMapper() {
		return teamServiceMapper;
	}

	public void setTeamServiceMapper(TeamServiceMapper teamServiceMapper) {
		this.teamServiceMapper = teamServiceMapper;
	}

	@Override
	public Page<TeamEntity> listPaging(Integer page, Integer size) {
		try {
			PageRequest pageable = new PageRequest(page-1, size, new Sort(new Order(Direction.DESC, "createDate")));
			return teamJpaRepository.listPaging(pageable);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	

	@Override
	public void update_isDelete(Integer id) {
		 try {
			 teamJpaRepository.update_isDelete(id);
			} catch (Exception e) {
				e.printStackTrace();
			}
	}
	


	@Override
	public Page<TeamEntity> filterAdmin( Map<String, Object> params, Integer page, Integer size) {

		Specification<TeamEntity> spec = new Specification<TeamEntity>() {
			@Override
			public Predicate toPredicate(Root<TeamEntity> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> predicates = new ArrayList<>();

				if (params.get("key") != null) {
				String key = String.valueOf(params.get("key"));
					if (params.get("type") != null) {
						String type = String.valueOf(params.get("type"));
						Predicate predicate = cb.equal(root.get(type), "%"+key+"%");
						predicates.add(predicate);
					}
				}

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

				Predicate predicateIsDelete = cb.equal(root.get("isDelete"), false);
				predicates.add(predicateIsDelete);
				
				return cb.and(predicates.toArray(new Predicate[] {}));
			}

		};

		Order order = new Order(Direction.DESC, "createDate");
		if (params.get("orderBy") != null) {
			String orderBy = String.valueOf(params.get("orderBy"));
			order = new Order(Direction.DESC, orderBy);
		}

		PageRequest pageable = new PageRequest(page - 1, size, new Sort(order));
		return teamJpaRepository.findAll(spec, pageable);
	}

	
	@Override
	public PageCustom<Map<String,Object>> listPagingCustom(Map<String, Object> params, Integer page, Integer size) {
		try {
			Integer userId = DataUtils.parseInt(params.get("userId"));
			String SELECT_COUNT = "SELECT COUNT(t) ";
			String SELECT = "SELECT new map(t.id as id, t.name as name, t.imageUrl as imageUrl , t.deleteDate as deleteDate, "
					      + "(SELECT COUNT(tm) FROM "
					      + "TeamMemberEntity tm "
					      + "WHERE tm.team.id = t.id AND tm.isDelete = false) as totalMember, "
					      + "(SELECT CASE WHEN COUNT(tm) >0 THEN True ELSE False END "
					      + "FROM TeamMemberEntity tm "
					      + "WHERE tm.team.id = t.id AND tm.isDelete = false AND tm.user.id = "+userId+") as joinedThisGroup"
					      + ") ";
			String FROM=	"FROM TeamEntity t ";
			String WHERE = 	"WHERE t.isDelete = false AND t.deleteDate is null  ";
			Integer offset = DataUtils.getOffset(page, size);
			if(params.get("name") != null) {
				String name = String.valueOf(params.get("name"));
				WHERE += "AND t.name like '%"+name+"%' ";
			}
			
			Query query = em.createQuery(SELECT + FROM + WHERE );
			query.setMaxResults(size);
			query.setFirstResult(offset);
			@SuppressWarnings("unchecked")
			//List<Group> results = (List<Group>) query.getResultList();
			
			List<Map<String,Object>> result = query.getResultList();
			Query queryCount = em.createQuery(SELECT_COUNT + FROM + WHERE );
			Long totalCount = (Long)queryCount.getSingleResult();
			
			return new PageCustom<>(result, totalCount, page, size);

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@Override
	public PageCustom<Map<String,Object>> listPagingAdminCustom(Map<String, Object> params, Integer page, Integer size) {
		try {
			String SELECT_COUNT = "SELECT COUNT(t) ";
			String SELECT = "SELECT new map(t.id as id, t.name as name ,"
						  + "(SELECT tm.user.fullname FROM TeamMemberEntity tm WHERE tm.team.id = t.id AND tm.isManager =true) as fullname, "
					      + "(SELECT COUNT(tm) FROM "
					      + "TeamMemberEntity tm "
					      + "WHERE tm.team.id = t.id AND tm.isDelete = false) as totalMember, "
					      + "t.createDate as createDate, "
					      + "t.isDelete as isDelete) ";
			String FROM=	"FROM TeamEntity t ";
			String WHERE = 	"WHERE 1=1 ";
			
			Integer offset = DataUtils.getOffset(page, size);
			if(params.get("name") != null && params.get("name") != "") {
				String name = String.valueOf(params.get("name"));
				WHERE += "AND t.name like '%"+name+"%' ";
			}
			if(params.get("isDelete") != null && params.get("isDelete") != "") {
				WHERE += "AND t.isDelete = "+params.get("isDelete")+" ";
			}
			
			System.out.println(SELECT + FROM + WHERE + " order by t.createDate DESC ");
			Query query = em.createQuery(SELECT + FROM + WHERE + " order by t.createDate DESC " );
			query.setMaxResults(size);
			query.setFirstResult(offset);
			@SuppressWarnings("unchecked")
			
			List<Map<String,Object>> result = query.getResultList();
			Query queryCount = em.createQuery(SELECT_COUNT + FROM + WHERE );
			Long totalCount = (Long)queryCount.getSingleResult();
			
			return new PageCustom<>(result, totalCount, page, size);

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@Override
	public List<Map<String,Object>> listMyGroup(Map<String, Object> params) {
		try {
			String SELECT = "SELECT new map(tm.team.id as id, tm.team.name as name, tm.team.imageUrl as imageUrl , tm.team.deleteDate as deleteDate, "
					      + "(SELECT COUNT(t) FROM "
					      + "TeamMemberEntity t "
					      + "WHERE t.team.id = tm.team.id AND t.isDelete = false) as totalMember "
					      + ") ";
			String FROM=	"FROM TeamMemberEntity tm ";
			String WHERE = 	"WHERE tm.isDelete = false AND tm.team.isDelete = false ";
			if(params.get("userId") != null) {
				Integer userId = DataUtils.parseInt(params.get("userId"));
				WHERE += "AND tm.user.id = "+userId;
			}
			Query query = em.createQuery(SELECT + FROM + WHERE);
			@SuppressWarnings("unchecked")
			
			List<Map<String,Object>> result = query.getResultList();
			
			return result;

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	

	@Override
	public List<TeamEntity> findByName(String name) {
		try {
			return teamJpaRepository.findByName(name);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<TeamEntity> listByDeleteDate(Date date) {
		try {
			return teamJpaRepository.listByDeleteDate(date);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
