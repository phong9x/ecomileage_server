/*
 * Created on 2 thg 10 2017 ( Time 15:10:02 )
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

import org.ecomileage.bean.VehicleCode;
import org.ecomileage.bean.jpa.VehicleCodeEntity;
import java.util.Date;
import java.util.List;
import org.ecomileage.business.service.VehicleCodeService;
import org.ecomileage.business.service.mapping.VehicleCodeServiceMapper;
import org.ecomileage.data.repository.jpa.VehicleCodeJpaRepository;
import org.ecomileage.web.common.ApplicationDefine;
import org.ecomileage.web.common.utils.ConstantCommon;
import org.ecomileage.web.common.utils.DataUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
/**
 * Implementation of VehicleCodeService
 */
@Component
@Transactional
public class VehicleCodeServiceImpl implements VehicleCodeService {

	@Resource
	private VehicleCodeJpaRepository vehicleCodeJpaRepository;

	private static final Integer PAGE_SIZE   = 15;

	@Resource
	private VehicleCodeServiceMapper vehicleCodeServiceMapper;
	
	@Override
	public VehicleCode findById(Integer id) {
		VehicleCodeEntity vehicleCodeEntity = vehicleCodeJpaRepository.findOne(id);
		return vehicleCodeServiceMapper.mapVehicleCodeEntityToVehicleCode(vehicleCodeEntity);
	}

	@Override
	public VehicleCodeEntity findOne(Integer id) {
		VehicleCodeEntity vehicleCodeEntity = vehicleCodeJpaRepository.findOne(id);
		return vehicleCodeEntity;
	}

	@Override
	public Page<VehicleCodeEntity> findAll(Integer pageNumber) {

		PageRequest request = new PageRequest(pageNumber - 1, PAGE_SIZE,
				new Sort(new Order(Direction.DESC, "id")));
		return vehicleCodeJpaRepository.findAll(request);
	}

	@Override
	public List<VehicleCode> findAll() {
		Iterable<VehicleCodeEntity> entities = vehicleCodeJpaRepository.findAll();
		List<VehicleCode> beans = new ArrayList<VehicleCode>();
		for(VehicleCodeEntity vehicleCodeEntity : entities) {
			beans.add(vehicleCodeServiceMapper.mapVehicleCodeEntityToVehicleCode(vehicleCodeEntity));
		}
		return beans;
	}

	/**
	 * Count total entit
	 * @return Long
	 */
	public Long countTotal(){
		Long count = vehicleCodeJpaRepository.count();
		return count;
	}

	@Override
	public VehicleCode save(VehicleCode vehicleCode) {
		return update(vehicleCode) ;
	}

	@Override
	public VehicleCode create(VehicleCode vehicleCode) {
/*
		VehicleCodeEntity vehicleCodeEntity = vehicleCodeJpaRepository.findOne(vehicleCode.getId());
		if( vehicleCodeEntity != null ) {
			throw new IllegalStateException("already.exists");
		}

		vehicleCodeEntity = new VehicleCodeEntity();
		vehicleCodeServiceMapper.mapVehicleCodeToVehicleCodeEntity(vehicleCode, vehicleCodeEntity);
		VehicleCodeEntity vehicleCodeEntitySaved = vehicleCodeJpaRepository.save(vehicleCodeEntity);
		return vehicleCodeServiceMapper.mapVehicleCodeEntityToVehicleCode(vehicleCodeEntitySaved);
*/
		VehicleCodeEntity vehicleCodeEntity = new VehicleCodeEntity();
		vehicleCodeServiceMapper.mapVehicleCodeToVehicleCodeEntity(vehicleCode, vehicleCodeEntity);
		VehicleCodeEntity vehicleCodeEntitySaved = vehicleCodeJpaRepository.save(vehicleCodeEntity);
		return vehicleCodeServiceMapper.mapVehicleCodeEntityToVehicleCode(vehicleCodeEntitySaved);
	}

	@Override
	public VehicleCode update(VehicleCode vehicleCode) {
		VehicleCodeEntity vehicleCodeEntity = vehicleCodeJpaRepository.findOne(vehicleCode.getId());
		vehicleCodeServiceMapper.mapVehicleCodeToVehicleCodeEntity(vehicleCode, vehicleCodeEntity);
		VehicleCodeEntity vehicleCodeEntitySaved = vehicleCodeJpaRepository.save(vehicleCodeEntity);
		return vehicleCodeServiceMapper.mapVehicleCodeEntityToVehicleCode(vehicleCodeEntitySaved);
	}

	@Override
	public void delete(Integer id) {
		vehicleCodeJpaRepository.delete(id);
	}

	public VehicleCodeJpaRepository getVehicleCodeJpaRepository() {
		return vehicleCodeJpaRepository;
	}

	public void setVehicleCodeJpaRepository(VehicleCodeJpaRepository vehicleCodeJpaRepository) {
		this.vehicleCodeJpaRepository = vehicleCodeJpaRepository;
	}

	public VehicleCodeServiceMapper getVehicleCodeServiceMapper() {
		return vehicleCodeServiceMapper;
	}

	public void setVehicleCodeServiceMapper(VehicleCodeServiceMapper vehicleCodeServiceMapper) {
		this.vehicleCodeServiceMapper = vehicleCodeServiceMapper;
	}

	@Override
	public Page<VehicleCodeEntity> listPaging(Integer page, Integer size) {
		try {
			PageRequest pageable = new PageRequest(page-1, size, new Sort(new Order(Direction.DESC, "createDate")));
			return vehicleCodeJpaRepository.listPaging(pageable);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Page<VehicleCodeEntity> filterAdmin( Map<String, Object> params, Integer page, Integer size) {

		Specification<VehicleCodeEntity> spec = new Specification<VehicleCodeEntity>() {
			@Override
			public Predicate toPredicate(Root<VehicleCodeEntity> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> predicates = new ArrayList<>();

				if (params.get("key") != null) {
				String key = String.valueOf(params.get("key"));
					if (params.get("type") != null) {
						String type = String.valueOf(params.get("type"));
						Predicate predicate = cb.like(root.get(type), "%"+key+"%");
						predicates.add(predicate);
					}
				}

				if(params.get("startTime") != "" && params.get("startTime") != null){
					String startTime = String.valueOf(params.get("startTime"));
					Predicate predicate = cb.greaterThanOrEqualTo(root.<Date>get("createDate"),DataUtils.parseDate(startTime, DataUtils.FormatDate.YYYYMMDD.getPattern()));
					predicates.add(predicate);
				}
				
				if(params.get("endTime") != "" && params.get("startTime") != null){
					String endTime = String.valueOf(params.get("endTime"));
					Predicate predicate = cb.lessThanOrEqualTo(root.<Date>get("createDate"),DataUtils.parseDate(endTime, DataUtils.FormatDate.YYYYMMDD.getPattern()));
					predicates.add(predicate);
				}
				
				if (params.get("locationBus") != null) {
					String location = String.valueOf(params.get("locationBus")).trim();
					Predicate predicate = cb.or(
							cb.like(root.get("busCompanyName"), "%"+location+"%")
							,cb.like(root.get("busNo"), "%"+location+"%")
							,cb.like(root.get("busNumber"), "%"+location+"%")
							,cb.like(root.get("location"), "%"+location+"%")) ;
					predicates.add(predicate);
				}
				
				if (params.get("locationSubway") != null) {
					String location = String.valueOf(params.get("locationSubway")).trim();
					Predicate predicate = cb.or(
							cb.like(root.get("subwayLineNo"), "%"+location+"%")
							,cb.like(root.get("subwayStationName"), "%"+location+"%")
							,cb.like(root.get("subwayDirection"), "%"+location+"%")
							,cb.like(root.get("subwayDoorNo"), "%"+location+"%")) ;
					predicates.add(predicate);
				}
				
				if (params.get("fullnameOrUsername") != null) {
					String key = String.valueOf(params.get("fullnameOrUsername"));
					Predicate predicate =cb.or(cb.like(root.get("user").get("fullname"), "%"+key+"%"), cb.like(root.get("user").get("username"), "%"+key+"%")) ;
					predicates.add(predicate);
				}
				
				if (params.get("status") != null && params.get("status") != "") {
					String status = String.valueOf(params.get("status"));
					Predicate predicate = cb.equal(root.get("status"), status);
					predicates.add(predicate);
				}
				
				if (params.get("statuss") != null && params.get("statuss") != "") {
					Integer[] sta = (Integer[]) params.get("statuss");
					Predicate predicate = cb.isTrue(root.get("status").in(sta));
					predicates.add(predicate);
				}
				
				if (params.get("typeVehicle") != null && params.get("typeVehicle") != "") {
					String typeVehicle = String.valueOf(params.get("typeVehicle"));
					Predicate predicate = cb.equal(root.get("typeVehicle"), typeVehicle);
					predicates.add(predicate);
				}
				
				if (params.get("typeSend") != null && params.get("typeSend") != "" ) {
					String typeSend = String.valueOf(params.get("typeSend"));
					Predicate predicate = cb.equal(root.get("typeSend"), typeSend);
					predicates.add(predicate);
				}
				
				if (params.get("typeRegister") != null && params.get("typeRegister") != "") {
					String typeRegister = String.valueOf(params.get("typeRegister"));
					Predicate predicate = cb.equal(root.get("typeRegister"), typeRegister);
					predicates.add(predicate);
				}
				
				if(params.get("registerTimeStart") != "" && params.get("registerTimeStart") != null){
					String startTime = String.valueOf(params.get("registerTimeStart"));
					Predicate predicate = cb.greaterThanOrEqualTo(root.<Date>get("registerDate"),DataUtils.parseDate(startTime, DataUtils.FormatDate.YYYYMMDD.getPattern()));
					predicates.add(predicate);
				}
				
				if(params.get("registerTimeEnd") != "" && params.get("registerTimeEnd") != null){
					String endTime = String.valueOf(params.get("registerTimeEnd"));
					Predicate predicate = cb.lessThanOrEqualTo(root.<Date>get("registerDate"),DataUtils.parseDate(endTime, DataUtils.FormatDate.YYYYMMDD.getPattern()));
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
		return vehicleCodeJpaRepository.findAll(spec, pageable);
	}

	@Override
	public List<VehicleCodeEntity> findTypeVehicleAndUserId(Short[] typeVehicle, Integer userId) {
		try {
			PageRequest pageable = new PageRequest(0, 1, new Sort(new Order(Direction.DESC, "createDate")));
			return vehicleCodeJpaRepository.findByTypeVehicleAndUserId(typeVehicle, userId, pageable);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public VehicleCode findBySerialNumberAndUserId(String serialNumber, Integer userId) {
		try {
			VehicleCodeEntity v = vehicleCodeJpaRepository.findBySerialNumberAndUserId(serialNumber,userId);
			return vehicleCodeServiceMapper.mapVehicleCodeEntityToVehicleCode(v);
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public VehicleCodeEntity findByUserId(Integer userId) {
		try {
			List<VehicleCodeEntity> list = vehicleCodeJpaRepository.findByUserId(userId);
			if(list.size() > 0) {
				return list.get(0);
			}else {
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public VehicleCode findBySerialNumberAndTypeVehicle(String serialNumber, Short typeVehicle) {
		try {
			VehicleCodeEntity v = vehicleCodeJpaRepository.findBySerialNumberAndTypeVehicle(serialNumber,typeVehicle);
			return vehicleCodeServiceMapper.mapVehicleCodeEntityToVehicleCode(v);
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public VehicleCode findBySerialNumber(String serialNumber) {
		try {
			VehicleCodeEntity v = vehicleCodeJpaRepository.findBySerialNumber(serialNumber);
			return vehicleCodeServiceMapper.mapVehicleCodeEntityToVehicleCode(v);
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@Override
	public VehicleCode findBySerialNumberByBusAndSubway(String serialNumber) {
		try {
			VehicleCodeEntity v = vehicleCodeJpaRepository.findBySerialNumberByBusAndSubway(serialNumber);
			return vehicleCodeServiceMapper.mapVehicleCodeEntityToVehicleCode(v);
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Long countByTypeVehicle(Short typeVehicle) {
		try {
			return vehicleCodeJpaRepository.countByTypeVehicle( typeVehicle)+1;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}