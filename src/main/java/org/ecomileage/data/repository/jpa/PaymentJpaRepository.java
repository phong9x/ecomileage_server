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

import org.ecomileage.bean.Payment;
import org.ecomileage.bean.jpa.PaymentEntity;

/**
 * Repository : Payment.
 */
public interface PaymentJpaRepository
		extends PagingAndSortingRepository<PaymentEntity, Integer>, JpaSpecificationExecutor<PaymentEntity> {
	@Query("Select u From PaymentEntity u ")
	Page<PaymentEntity> listPaging(Pageable pageable);

	@Query("Select u From PaymentEntity u WHERE u.user.id =?1 AND u.typeMoney = ?2")
	List<PaymentEntity> listPagingBy_UserIdAndTypeMoney(Integer userId, Short typeMoney);

	@Query("Select COALESCE(SUM(u.bikePoint),0) From PaymentEntity u WHERE u.user.id =?1 AND u.typeMoney =2")
	Long getBikePointBy_UserId(Integer userId);
	
	@Query("Select COALESCE(SUM(u.tranferPoint),0) From PaymentEntity u WHERE u.user.id =?1 AND u.typeMoney =2")
	Long getTransferPointBy_UserId(Integer userId);
	
	@Query("Select COALESCE(SUM(u.previousPoint),0) From PaymentEntity u WHERE u.user.id =?1 AND u.typeMoney =2")
	Long getPreviousPointBy_UserId(Integer userId);

	@Query("Select COALESCE(SUM(u.money),0) From PaymentEntity u WHERE u.user.id =?1 AND u.typeMoney =2")
	Long getTotalPoint(Integer userId);

	@Query("Select COALESCE(SUM(u.money),0) From PaymentEntity u WHERE u.createDate < ?1")
	Long getTotalSponsor(Date date);
	
	@Query("Select COALESCE(SUM(u.money),0) From PaymentEntity u WHERE u.typeSponsor = ?1 ")
	Long getTotalSponsorBy_TypeSponsor(Short typeSponsor);

	@Query("Select u From PaymentEntity u WHERE u.createDate < ?1")
	List<PaymentEntity> listPagingBy_MonthAndYear(Date date);
	
	@Query("Select u From PaymentEntity u WHERE u.mileageApplyId =?1 ")
	PaymentEntity findByMileageApplyId(Integer mileageApplyId);

}
