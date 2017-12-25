/*
 * Created on 7 thg 12 2017 ( Time 17:30:58 )
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
import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.ecomileage.bean.Payment;
import org.ecomileage.bean.jpa.PaymentEntity;
import java.util.Date;
import org.ecomileage.business.service.PaymentService;
import org.ecomileage.business.service.mapping.PaymentServiceMapper;
import org.ecomileage.data.repository.jpa.PaymentJpaRepository;
import org.ecomileage.web.common.utils.DataUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
/**
 * Implementation of PaymentService
 */
@Component
@Transactional
public class PaymentServiceImpl implements PaymentService {

	@Resource
	private PaymentJpaRepository paymentJpaRepository;

	private static final Integer PAGE_SIZE   = 15;

	@Resource
	private PaymentServiceMapper paymentServiceMapper;
	
	@Resource
	EntityManager em;	

	@Override
	public Payment findById(Integer id) {
		PaymentEntity paymentEntity = paymentJpaRepository.findOne(id);
		return paymentServiceMapper.mapPaymentEntityToPayment(paymentEntity);
	}

	@Override
	public PaymentEntity findOne(Integer id) {
		PaymentEntity paymentEntity = paymentJpaRepository.findOne(id);
		return paymentEntity;
	}

	@Override
	public Page<PaymentEntity> findAll(Integer pageNumber) {

		PageRequest request = new PageRequest(pageNumber - 1, PAGE_SIZE,
				new Sort(new Order(Direction.DESC, "id")));
		return paymentJpaRepository.findAll(request);
	}

	@Override
	public List<Payment> findAll() {
		Iterable<PaymentEntity> entities = paymentJpaRepository.findAll();
		List<Payment> beans = new ArrayList<Payment>();
		for(PaymentEntity paymentEntity : entities) {
			beans.add(paymentServiceMapper.mapPaymentEntityToPayment(paymentEntity));
		}
		return beans;
	}

	/**
	 * Count total entit
	 * @return Long
	 */
	public Long countTotal(){
		Long count = paymentJpaRepository.count();
		return count;
	}

	@Override
	public Payment save(Payment payment) {
		return update(payment) ;
	}

	@Override
	public Payment create(Payment payment) {
/*
		PaymentEntity paymentEntity = paymentJpaRepository.findOne(payment.getId());
		if( paymentEntity != null ) {
			throw new IllegalStateException("already.exists");
		}

		paymentEntity = new PaymentEntity();
		paymentServiceMapper.mapPaymentToPaymentEntity(payment, paymentEntity);
		PaymentEntity paymentEntitySaved = paymentJpaRepository.save(paymentEntity);
		return paymentServiceMapper.mapPaymentEntityToPayment(paymentEntitySaved);
*/
		PaymentEntity paymentEntity = new PaymentEntity();
		paymentServiceMapper.mapPaymentToPaymentEntity(payment, paymentEntity);
		PaymentEntity paymentEntitySaved = paymentJpaRepository.save(paymentEntity);
		return paymentServiceMapper.mapPaymentEntityToPayment(paymentEntitySaved);
	}

	@Override
	public Payment update(Payment payment) {
		PaymentEntity paymentEntity = paymentJpaRepository.findOne(payment.getId());
		paymentServiceMapper.mapPaymentToPaymentEntity(payment, paymentEntity);
		PaymentEntity paymentEntitySaved = paymentJpaRepository.save(paymentEntity);
		return paymentServiceMapper.mapPaymentEntityToPayment(paymentEntitySaved);
	}

	@Override
	public void delete(Integer id) {
		paymentJpaRepository.delete(id);
	}

	public PaymentJpaRepository getPaymentJpaRepository() {
		return paymentJpaRepository;
	}

	public void setPaymentJpaRepository(PaymentJpaRepository paymentJpaRepository) {
		this.paymentJpaRepository = paymentJpaRepository;
	}

	public PaymentServiceMapper getPaymentServiceMapper() {
		return paymentServiceMapper;
	}

	public void setPaymentServiceMapper(PaymentServiceMapper paymentServiceMapper) {
		this.paymentServiceMapper = paymentServiceMapper;
	}

	@Override
	public Page<PaymentEntity> listPaging(Integer page, Integer size) {
		try {
			PageRequest pageable = new PageRequest(page-1, size, new Sort(new Order(Direction.DESC, "createDate")));
			return paymentJpaRepository.listPaging(pageable);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@Override
	public Page<PaymentEntity> filterAdmin( Map<String, Object> params, Integer page, Integer size) {

		Specification<PaymentEntity> spec = new Specification<PaymentEntity>() {
			@Override
			public Predicate toPredicate(Root<PaymentEntity> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> predicates = new ArrayList<>();

				if (params.get("key") != null) {
				String key = String.valueOf(params.get("key"));
					if (params.get("type") != null) {
						String type = String.valueOf(params.get("type"));
						Predicate predicate = cb.equal(root.get(type), "%"+key+"%");
						predicates.add(predicate);
					}
				}
				if(params.get("startTime") != null && params.get("startTime") != ""){
					String startTime = String.valueOf(params.get("startTime"));
					Predicate predicate = cb.greaterThanOrEqualTo(root.<Date>get("createDate"),DataUtils.parseDate(startTime, DataUtils.FormatDate.YYYYMMDD.getPattern()));
					predicates.add(predicate);
				}
				
				if(params.get("endTime") != null && params.get("endTime") != ""){
					Date end = DataUtils.parseDate(params.get("endTime") + " 23:59:59", "yyyy/MM/dd HH:mm:ss");
					Predicate predicate = cb.lessThanOrEqualTo(root.get("createDate"), end);
					predicates.add(predicate);
				}
				
				if(params.get("fullnameOrUsername") != null && params.get("fullnameOrUsername")!=""){
					String fullnameOrUsername = String.valueOf(params.get("fullnameOrUsername"));
					Predicate predicate = cb.or(cb.like(root.get("user").get("fullname"), "%"+fullnameOrUsername +"%"), cb.like(root.get("user").get("username"), "%"+fullnameOrUsername+"%"));
					predicates.add(predicate);
				}

				if(params.get("typeMoney") != null && params.get("typeMoney") != ""){
					Short typeMoney = Short.valueOf(String.valueOf(params.get("typeMoney")));
					Predicate predicate = cb.equal(root.get("typeMoney"), typeMoney);
					predicates.add(predicate);
				}
				
				if(params.get("typeUse") != null && params.get("typeUse") != ""){
					Short typeUse = Short.valueOf(String.valueOf(params.get("typeUse")));
					Predicate predicate = cb.equal(root.get("typeUse"), typeUse);
					predicates.add(predicate);
				}
				
				if(params.get("typeSponsor") != null && params.get("typeSponsor") != ""){
					Short typeSponsor = Short.valueOf(String.valueOf(params.get("typeSponsor")));
					Predicate predicate = cb.equal(root.get("typeSponsor"), typeSponsor);
					predicates.add(predicate);
				}
				if(params.get("status") != null && params.get("status") != ""){
					Short status = Short.valueOf(String.valueOf(params.get("status")));
					Predicate predicate = cb.equal(root.get("status"), status);
					predicates.add(predicate);
				}
				
				if(params.get("paymentMethod") != null && params.get("paymentMethod") != ""){
					String paymentMethod = String.valueOf(String.valueOf(params.get("paymentMethod")));
					Predicate predicate = cb.equal(root.get("paymentMethod"), paymentMethod);
					predicates.add(predicate);
				}
				
				return cb.and(predicates.toArray(new Predicate[] {}));
			}

		};

		Order orderCreateDate = new Order(Direction.DESC, "createDate");
		Order orderId = new Order(Direction.DESC, "id");
		if (params.get("orderBy") != null) {
			String orderBy = String.valueOf(params.get("orderBy"));
			orderCreateDate = new Order(Direction.DESC, orderBy);
		}

		PageRequest pageable = new PageRequest(page - 1, size, new Sort(orderCreateDate,orderId));
		return paymentJpaRepository.findAll(spec, pageable);
	}
	
	@Override
	public Long getTotalMoneyTem(Map<String, Object> params) {
		try {
			String SELECT = "SELECT COALESCE(SUM(p.money),0) ";
			String FROM  =	"FROM PaymentEntity p ";
			String WHERE = 	"WHERE 1=1 ";
			
			if(params.get("typeMoney") != null && params.get("typeMoney") != ""){
				WHERE += "AND p.typeMoney = "+params.get("typeMoney");
			}
			
			if(params.get("startTime") != null && params.get("startTime") != ""){
				WHERE += "AND DATE(p.createDate) >= '"+params.get("startTime")+"' ";
			}
			
			if(params.get("endTime") != null && params.get("endTime") != ""){
				WHERE += "AND DATE(p.createDate) <= '"+params.get("endTime")+"' ";
			}
			
			if(params.get("fullnameOrUsername") != null && params.get("fullnameOrUsername")!=""){
				String fullnameOrUsername = String.valueOf(params.get("fullnameOrUsername"));
				WHERE += "AND (p.user.fullname like '%"+fullnameOrUsername+"%' OR p.user.username like '%"+fullnameOrUsername+"%') ";
			}
			
			if(params.get("typeUse") != null && params.get("typeUse") != ""){
				WHERE += "AND p.typeUse = "+params.get("typeUse");
			}
			if(params.get("typeSponsor") != null && params.get("typeSponsor") != ""){
				WHERE += "AND p.typeSponsor = "+params.get("typeSponsor");
			}
			if(params.get("status") != null && params.get("status") != ""){
				WHERE += "AND p.status = "+params.get("status");
			}
			
			Query query = em.createQuery(SELECT + FROM + WHERE);
			@SuppressWarnings("unchecked")
			Long result = DataUtils.parseLong(query.getSingleResult());
			
			return result;

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@Override
	public Long getTotalMoney(Map<String, Object> params) {
		try {
			String SELECT = "SELECT COALESCE(SUM(p.money),0) ";
			String FROM  =	"FROM PaymentEntity p ";
			String WHERE = 	"WHERE 1=1 ";
			
			if(params.get("typeMoney") != null && params.get("typeMoney") != ""){
				WHERE += "AND p.typeMoney = "+params.get("typeMoney");
			}
			
			if(params.get("startTime") != null && params.get("startTime") != ""){
				WHERE += "AND DATE(p.createDate) >= '"+params.get("startTime")+"' ";
			}
			
			if(params.get("endTime") != null && params.get("endTime") != ""){
				WHERE += "AND DATE(p.createDate) <= '"+params.get("endTime")+"' ";
			}
			
			if(params.get("fullnameOrUsername") != null && params.get("fullnameOrUsername")!=""){
				String fullnameOrUsername = String.valueOf(params.get("fullnameOrUsername"));
				WHERE += "AND (p.user.fullname like '%"+fullnameOrUsername+"%' OR p.user.username like '%"+fullnameOrUsername+"%') ";
			}
			
			Query query = em.createQuery(SELECT + FROM + WHERE);
			@SuppressWarnings("unchecked")
			Long result = DataUtils.parseLong(query.getSingleResult());
			
			return result;

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	@Override
	public List<PaymentEntity> listPagingBy_UserIdAndTypeMoney(Integer userId, Short typeMoney) {
		try {
			return paymentJpaRepository.listPagingBy_UserIdAndTypeMoney(userId, typeMoney);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<Map<String,Object>> listSponsorBy_TypeSponsorAndMonth(Short typeSponsor, Date date) {
		try {
			String SELECT = "SELECT new map("
						  + "COUNT(p.id) as totalUser, "
						  + "COALESCE(SUM(p.money),0) as totalMoney"
					      + ") ";
			String FROM  =	"FROM PaymentEntity p ";
			String WHERE = 	"WHERE p.typeSponsor ="+typeSponsor+" "
						  + "AND DATE(p.createDate) < '"+DataUtils.parseStringFromDate(date, "yyyy-MM-dd")+"' ";
			
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
	public Long getTotalSponsor(Date date) {
		try {
			return paymentJpaRepository.getTotalSponsor(date);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<PaymentEntity> listPagingBy_MonthAndYear(Date date) {
		try {
			return paymentJpaRepository.listPagingBy_MonthAndYear(date);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public PaymentEntity findByMileageApplyId(Integer mileageApplyId) {
		try {
			return paymentJpaRepository.findByMileageApplyId(mileageApplyId);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Long getTotalSponsorBy_TypeSponsor(Short typeSponsor) {
		try {
			return paymentJpaRepository.getTotalSponsorBy_TypeSponsor(typeSponsor);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
