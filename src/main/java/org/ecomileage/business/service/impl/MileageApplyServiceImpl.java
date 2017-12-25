/*
 * Created on 2 thg 10 2017 ( Time 15:10:01 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 */
package org.ecomileage.business.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

import org.ecomileage.bean.MileageApply;
import org.ecomileage.bean.jpa.MileageApplyEntity;
import java.util.Date;
import org.ecomileage.business.service.MileageApplyService;
import org.ecomileage.business.service.mapping.MileageApplyServiceMapper;
import org.ecomileage.data.repository.jpa.MileageApplyJpaRepository;
import org.ecomileage.web.common.utils.DataUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
/**
 * Implementation of MileageApplyService
 */
@Component
@Transactional
public class MileageApplyServiceImpl implements MileageApplyService {

	@Resource
	private MileageApplyJpaRepository mileageApplyJpaRepository;

	private static final Integer PAGE_SIZE   = 15;

	@Resource
	private MileageApplyServiceMapper mileageApplyServiceMapper;
	
	@Override
	public MileageApply findById(Integer id) {
		MileageApplyEntity mileageApplyEntity = mileageApplyJpaRepository.findOne(id);
		return mileageApplyServiceMapper.mapMileageApplyEntityToMileageApply(mileageApplyEntity);
	}

	@Override
	public MileageApplyEntity findOne(Integer id) {
		MileageApplyEntity mileageApplyEntity = mileageApplyJpaRepository.findOne(id);
		return mileageApplyEntity;
	}

	@Override
	public Page<MileageApplyEntity> findAll(Integer pageNumber) {

		PageRequest request = new PageRequest(pageNumber - 1, PAGE_SIZE,
				new Sort(new Order(Direction.DESC, "id")));
		return mileageApplyJpaRepository.findAll(request);
	}

	@Override
	public List<MileageApply> findAll() {
		Iterable<MileageApplyEntity> entities = mileageApplyJpaRepository.findAll();
		List<MileageApply> beans = new ArrayList<MileageApply>();
		for(MileageApplyEntity mileageApplyEntity : entities) {
			beans.add(mileageApplyServiceMapper.mapMileageApplyEntityToMileageApply(mileageApplyEntity));
		}
		return beans;
	}

	/**
	 * Count total entit
	 * @return Long
	 */
	public Long countTotal(){
		Long count = mileageApplyJpaRepository.count();
		return count;
	}

	@Override
	public MileageApply save(MileageApply mileageApply) {
		return update(mileageApply) ;
	}

	@Override
	public MileageApply create(MileageApply mileageApply) {
/*
		MileageApplyEntity mileageApplyEntity = mileageApplyJpaRepository.findOne(mileageApply.getId());
		if( mileageApplyEntity != null ) {
			throw new IllegalStateException("already.exists");
		}

		mileageApplyEntity = new MileageApplyEntity();
		mileageApplyServiceMapper.mapMileageApplyToMileageApplyEntity(mileageApply, mileageApplyEntity);
		MileageApplyEntity mileageApplyEntitySaved = mileageApplyJpaRepository.save(mileageApplyEntity);
		return mileageApplyServiceMapper.mapMileageApplyEntityToMileageApply(mileageApplyEntitySaved);
*/
		MileageApplyEntity mileageApplyEntity = new MileageApplyEntity();
		mileageApplyServiceMapper.mapMileageApplyToMileageApplyEntity(mileageApply, mileageApplyEntity);
		MileageApplyEntity mileageApplyEntitySaved = mileageApplyJpaRepository.save(mileageApplyEntity);
		return mileageApplyServiceMapper.mapMileageApplyEntityToMileageApply(mileageApplyEntitySaved);
	}

	@Override
	public MileageApply update(MileageApply mileageApply) {
		MileageApplyEntity mileageApplyEntity = mileageApplyJpaRepository.findOne(mileageApply.getId());
		mileageApplyServiceMapper.mapMileageApplyToMileageApplyEntity(mileageApply, mileageApplyEntity);
		MileageApplyEntity mileageApplyEntitySaved = mileageApplyJpaRepository.save(mileageApplyEntity);
		return mileageApplyServiceMapper.mapMileageApplyEntityToMileageApply(mileageApplyEntitySaved);
	}

	@Override
	public void delete(Integer id) {
		mileageApplyJpaRepository.delete(id);
	}

	public MileageApplyJpaRepository getMileageApplyJpaRepository() {
		return mileageApplyJpaRepository;
	}

	public void setMileageApplyJpaRepository(MileageApplyJpaRepository mileageApplyJpaRepository) {
		this.mileageApplyJpaRepository = mileageApplyJpaRepository;
	}

	public MileageApplyServiceMapper getMileageApplyServiceMapper() {
		return mileageApplyServiceMapper;
	}

	public void setMileageApplyServiceMapper(MileageApplyServiceMapper mileageApplyServiceMapper) {
		this.mileageApplyServiceMapper = mileageApplyServiceMapper;
	}

	@Override
	public Page<MileageApplyEntity> listPaging(Integer page, Integer size) {
		try {
			PageRequest pageable = new PageRequest(page-1, size, new Sort(new Order(Direction.DESC, "createDate")));
			return mileageApplyJpaRepository.listPaging(pageable);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	

	@Override
	public Page<MileageApplyEntity> listPagingByUserId(Integer page, Integer size, Integer userId) {
		try {
			PageRequest pageable = new PageRequest(page-1, size, new Sort(new Order(Direction.DESC, "createDate")));
			return mileageApplyJpaRepository.listPagingByUserId(userId, pageable);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}


	@Override
	public Page<MileageApplyEntity> filterAdmin( Map<String, Object> params, Integer page, Integer size) {

		Specification<MileageApplyEntity> spec = new Specification<MileageApplyEntity>() {
			@Override
			public Predicate toPredicate(Root<MileageApplyEntity> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
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
				
				if(params.get("registerTimeStart") != "" && params.get("registerTimeStart") != null){
					String startTime = String.valueOf(params.get("registerTimeStart"));
					Predicate predicate = cb.greaterThanOrEqualTo(root.<Date>get("createDate"),DataUtils.parseDate(startTime, DataUtils.FormatDate.YYYYMMDD.getPattern()));
					predicates.add(predicate);
				}
				
				if(params.get("registerTimeEnd") != "" && params.get("registerTimeEnd") != null){
					String startTime = String.valueOf(params.get("registerTimeEnd"));
					Predicate predicate = cb.lessThanOrEqualTo(root.<Date>get("createDate"),DataUtils.parseDate(startTime, DataUtils.FormatDate.YYYYMMDD.getPattern()));
					predicates.add(predicate);
				}
				
				if (params.get("fullnameOrUsername") != null && params.get("fullnameOrUsername") != "") {
					String key = String.valueOf(params.get("fullnameOrUsername"));
					Predicate predicate =cb.or(cb.like(root.get("user").get("fullname"), "%"+key+"%"), cb.like(root.get("user").get("username"), "%"+key+"%")) ;
					predicates.add(predicate);
				}
				
				if (params.get("status") != null && params.get("status") != "") {
					String status = String.valueOf(params.get("status"));
					Predicate predicate = cb.equal(root.get("status"), status);
					predicates.add(predicate);
				}
				
				if (params.get("type") != null && params.get("type") != "") {
					String type = String.valueOf(params.get("type"));
					Predicate predicate = cb.equal(root.get("type"), type);
					predicates.add(predicate);
				}
				
				if (params.get("giftId") != null && params.get("giftId") != "") {
					String giftId = String.valueOf(params.get("giftId"));
					Predicate predicate = cb.equal(root.get("category").get("id"), giftId);
					predicates.add(predicate);
				}
				
				if (params.get("typeSend") != null && params.get("typeSend") != "") {
					String typeSend = String.valueOf(params.get("typeSend"));
					Predicate predicate = cb.equal(root.get("typeSend"), typeSend);
					predicates.add(predicate);
				}
				
				if (params.get("registerItem") != null && params.get("registerItem") != "") {
					String key = String.valueOf(params.get("registerItem"));
					Predicate predicate = cb.like(root.get("registerItem"), "%"+key+"%");
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
		return mileageApplyJpaRepository.findAll(spec, pageable);
	}

}