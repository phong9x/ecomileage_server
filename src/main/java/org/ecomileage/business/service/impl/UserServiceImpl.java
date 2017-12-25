/*
 * Created on 2 thg 10 2017 ( Time 15:10:02 )
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

import org.ecomileage.bean.User;
import org.ecomileage.bean.jpa.UserEntity;
import java.util.Date;
import java.util.List;
import org.ecomileage.business.service.UserService;
import org.ecomileage.business.service.mapping.UserServiceMapper;
import org.ecomileage.data.repository.jpa.UserJpaRepository;
import org.ecomileage.web.common.ApplicationDefine;
import org.ecomileage.web.common.utils.DataUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
/**
 * Implementation of UserService
 */
@Component
@Transactional
public class UserServiceImpl implements UserService {

	@Resource
	private UserJpaRepository userJpaRepository;

	private static final Integer PAGE_SIZE   = 15;

	@Resource
	private UserServiceMapper userServiceMapper;
	@Resource
	private EntityManager em;
	
	@Override
	public User findById(Integer id) {
		UserEntity userEntity = userJpaRepository.findOne(id);
		return userServiceMapper.mapUserEntityToUser(userEntity);
	}

	@Override
	public UserEntity findOne(Integer id) {
		UserEntity userEntity = userJpaRepository.findOne(id);
		return userEntity;
	}

	@Override
	public Page<UserEntity> findAll(Integer pageNumber) {

		PageRequest request = new PageRequest(pageNumber - 1, PAGE_SIZE,
				new Sort(new Order(Direction.DESC, "id")));
		return userJpaRepository.findAll(request);
	}

	@Override
	public List<User> findAll() {
		Iterable<UserEntity> entities = userJpaRepository.findAll();
		List<User> beans = new ArrayList<User>();
		for(UserEntity userEntity : entities) {
			beans.add(userServiceMapper.mapUserEntityToUser(userEntity));
		}
		return beans;
	}

	/**
	 * Count total entit
	 * @return Long
	 */
	public Long countTotal(){
		Long count = userJpaRepository.count();
		return count;
	}

	@Override
	public User save(User user) {
		return update(user) ;
	}

	@Override
	public User create(User user) {
/*
		UserEntity userEntity = userJpaRepository.findOne(user.getId());
		if( userEntity != null ) {
			throw new IllegalStateException("already.exists");
		}

		userEntity = new UserEntity();
		userServiceMapper.mapUserToUserEntity(user, userEntity);
		UserEntity userEntitySaved = userJpaRepository.save(userEntity);
		return userServiceMapper.mapUserEntityToUser(userEntitySaved);
*/
		UserEntity userEntity = new UserEntity();
		userServiceMapper.mapUserToUserEntity(user, userEntity);
		UserEntity userEntitySaved = userJpaRepository.save(userEntity);
		return userServiceMapper.mapUserEntityToUser(userEntitySaved);
	}

	@Override
	public User update(User user) {
		UserEntity userEntity = userJpaRepository.findOne(user.getId());
		userServiceMapper.mapUserToUserEntity(user, userEntity);
		UserEntity userEntitySaved = userJpaRepository.save(userEntity);
		return userServiceMapper.mapUserEntityToUser(userEntitySaved);
	}

	@Override
	public void delete(Integer id) {
		userJpaRepository.delete(id);
	}

	public UserJpaRepository getUserJpaRepository() {
		return userJpaRepository;
	}

	public void setUserJpaRepository(UserJpaRepository userJpaRepository) {
		this.userJpaRepository = userJpaRepository;
	}

	public UserServiceMapper getUserServiceMapper() {
		return userServiceMapper;
	}

	public void setUserServiceMapper(UserServiceMapper userServiceMapper) {
		this.userServiceMapper = userServiceMapper;
	}

	@Override
	public Page<UserEntity> listPaging(Integer page, Integer size) {
		try {
			PageRequest pageable = new PageRequest(page-1, size, new Sort(new Order(Direction.DESC, "createDate")));
			return userJpaRepository.listPaging(pageable);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	

	@Override
	public UserEntity findByUsername(String username) {
		try {
			return userJpaRepository.findByUsername(username);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	@Override
	public void update_isDelete(Integer id) {
		 try {
			 userJpaRepository.update_isDelete(id);
			} catch (Exception e) {
				e.printStackTrace();
			}
	}
	

	@Override
	public User login(String username, String password, Integer[] roleIds) {
		try {
			UserEntity u = userJpaRepository.login(username, password, roleIds);
			return userServiceMapper.mapUserEntityToUser(u);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Page<UserEntity> filterAdmin( Map<String, Object> params, Integer page, Integer size) {

		Specification<UserEntity> spec = new Specification<UserEntity>() {
			@Override
			public Predicate toPredicate(Root<UserEntity> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> predicates = new ArrayList<>();

				if (params.get("key") != null && params.get("key") != "") {
				String key = String.valueOf(params.get("key"));
					if (params.get("type") != null) {
						String type = String.valueOf(params.get("type"));
						Predicate predicate = cb.like(root.get(type), "%"+key+"%");
						predicates.add(predicate);
					}
				}

				if (params.get("fullname") != null && params.get("fullname") != "") {
					String fullname = String.valueOf(params.get("fullname"));
					Predicate predicate = cb.like(root.get("fullname"), "%"+fullname+"%");
					predicates.add(predicate);
				}
				
				if (params.get("city") != null && params.get("city") != "") {
					String city = String.valueOf(params.get("city"));
					Predicate predicate = cb.like(root.get("city"), "%"+city+"%");
					predicates.add(predicate);
				}
				
				if (params.get("district") != null && params.get("district") != "") {
					String district = String.valueOf(params.get("district"));
					Predicate predicate = cb.like(root.get("district"), "%"+district+"%");
					predicates.add(predicate);
				}
				
				if (params.get("roleId") != null && params.get("roleId") != "") {
					Integer roleId = DataUtils.parseInt(params.get("roleId"));
					Predicate predicate ;
					if (roleId == 4) {
						predicate = cb.equal(root.get("isDelete"), true) ;
					} else {
						predicate = cb.and(cb.equal(root.get("userRole").get("id"), roleId),cb.equal(root.get("isDelete"), false)) ;
					}
					
					predicates.add(predicate);
				}else {
					Integer[] roleIds = new Integer[3];
					roleIds[0] = ApplicationDefine.UserRole.StandardMember.getCode();
					roleIds[1] = ApplicationDefine.UserRole.OfficalMember.getCode();
					roleIds[2] = ApplicationDefine.UserRole.SponsorMember.getCode();
					Predicate predicate = cb.isTrue(root.get("userRole").get("id").in(roleIds));
					predicates.add(predicate);
				}
				
				if (params.get("fullnameOrUsername") != null && params.get("fullnameOrUsername") != "") {
					String key = String.valueOf(params.get("fullnameOrUsername"));
					Predicate predicate =cb.or(cb.like(root.get("fullname"), "%"+key+"%"), cb.like(root.get("username"), "%"+key+"%")) ;
					predicates.add(predicate);
				}
				
				if(params.get("startTime") != null && params.get("startTime") != ""){
					String startTime = String.valueOf(params.get("startTime"));
					Predicate predicate = cb.greaterThanOrEqualTo(root.<Date>get("createDate"),DataUtils.parseDate(startTime, DataUtils.FormatDate.YYYYMMDD.getPattern()));
					predicates.add(predicate);
				}
				
				if(params.get("endTime") != null && params.get("endTime") != ""){
					String endTime = String.valueOf(params.get("endTime"));
					Predicate predicate = cb.lessThanOrEqualTo(root.<Date>get("createDate"),DataUtils.parseDate(endTime, DataUtils.FormatDate.YYYYMMDD.getPattern()));
					predicates.add(predicate);
				}

				return cb.and(predicates.toArray(new Predicate[] {}));
			}

		};

		Order order = new Order(Direction.DESC, "createDate");
		if (params.get("orderBy") != null) {
			String orderBy = String.valueOf(params.get("orderBy"));
			order = new Order(Direction.DESC, orderBy);
		}

		PageRequest pageable = new PageRequest(page - 1, size, new Sort(order));
		return userJpaRepository.findAll(spec, pageable);
	}
	
	@Override
	public List<Map<String,Object>> listExportExcel(Map<String, Object> params) {
		try {
			String SELECT = "SELECT new map(u.id as id, u.username as username, "
					      + "(SELECT CASE WHEN SUM(v.totalMileage) is null THEN 0 ELSE SUM(v.totalMileage) END FROM MileageHistoryEntity v WHERE v.user.id = u.id) as totalMileage,"
					      + "(SELECT COUNT(v) FROM MileageHistoryEntity v WHERE v.user.id = u.id AND v.type=1) as totalRide,"
					      + "(SELECT COUNT(v) FROM MileageHistoryEntity v WHERE v.user.id = u.id AND v.type in (2,3)) as totalTranfer,"
					      + "(SELECT CASE WHEN SUM(v.totalReduceCo2) is null THEN 0 ELSE SUM(v.totalReduceCo2) END FROM MileageHistoryEntity v WHERE v.user.id = u.id ) as totalReduceCo2,"
					      + "u.address as address,"
					      + "u.createDate as createDate,"
					      + "u.email as email,"
					      + "u.phone as phone"
					      + ") ";
			String FROM=	"FROM UserEntity u ";
			String WHERE = 	"WHERE  1=1 ";
			if(params.get("fullnameOrUsername") != null && params.get("fullnameOrUsername") != "") {
				String key = String.valueOf(params.get("fullnameOrUsername"));
				WHERE += "AND (u.fullname like '%"+key+"%' OR u.username like '%"+key+"%')";
			}
			if(params.get("roleId") != null && params.get("roleId") != "") {
				Integer roleId = Integer.parseInt(params.get("roleId").toString());
				WHERE += "AND u.userRole.id ="+roleId;
			}else {
				WHERE += "AND u.userRole.id in ("+ApplicationDefine.UserRole.StandardMember.getCode()+","+ApplicationDefine.UserRole.SponsorMember.getCode()+","+ApplicationDefine.UserRole.OfficalMember.getCode()+")";
			}
			if(params.get("city") != null && params.get("city") != "") {
				String city = String.valueOf(params.get("city"));
				WHERE += "AND u.city like '%"+city+"%'";
			}
			if(params.get("district") != null && params.get("district") != "") {
				String district = String.valueOf(params.get("district"));
				WHERE += "AND u.district like '%"+district+"%'";
			}
			if(params.get("startTime") != null && params.get("startTime") != "") {
				String startTime = String.valueOf(params.get("startTime"));
				WHERE += "AND u.createDate >= '"+startTime+"' ";
			}
			if(params.get("endTime") != null && params.get("endTime") != "") {
				String endTime = String.valueOf(params.get("endTime"));
				WHERE += "AND u.createDate <= '"+endTime+"' ";
			}
			String ORDER_BY = "ORDER BY u.createDate DESC";
			Query query = em.createQuery(SELECT + FROM + WHERE + ORDER_BY);
			@SuppressWarnings("unchecked")
			List<Map<String,Object>> result = query.getResultList();
			
			return result;

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<UserEntity> findByEmailAndPhoneAndIsDelete(String email, String phone) {
		try {
			List<UserEntity>  list = userJpaRepository.findByEmailAndPhoneAndIsDelete(email, phone, false);
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public User findByUsernameAndPhoneAndIsDelete(String username, String phone) {
		try {
			UserEntity u = userJpaRepository.findByUsernameAndPhoneAndIsDelete(username, phone, false);
			return userServiceMapper.mapUserEntityToUser(u);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public User loginSNS(String snsType, String snsToken, Integer[] roleIds) {
		try {
			UserEntity u = userJpaRepository.loginSNS(snsType, snsToken, roleIds);
			return userServiceMapper.mapUserEntityToUser(u);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<UserEntity> findByPhoneAndIsDelete(String phone) {
		try {
			List<UserEntity> list = userJpaRepository.findByPhoneAndIsDelete(phone, false);
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public UserEntity findBySnsTypeAndSnsTokenAndIsDelete(String snsType, String socialId) {
		try {
			UserEntity u = userJpaRepository.findBySnsTypeAndSocialIdAndIsDelete(snsType, socialId, false);
			return u;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Long countUserActive() {
		try {
			Long count = userJpaRepository.countUserActive();
			if (count == null) {
				count = 0l;
			}
			return count;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public Date getMinStartTime() {
		return userJpaRepository.getMinStartTime();
	}

	@Override
	public Long countUserJoinByYear(Integer year) {
		try {
			Long count = userJpaRepository.countUserJoinByYear(year);
			if (count == null) {
				count = 0l;
			}
			return count;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}