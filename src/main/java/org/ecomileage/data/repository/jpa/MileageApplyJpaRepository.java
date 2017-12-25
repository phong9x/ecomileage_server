package org.ecomileage.data.repository.jpa;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.ecomileage.bean.jpa.MileageApplyEntity;
/**
 * Repository : MileageApply.
 */
public interface MileageApplyJpaRepository extends PagingAndSortingRepository<MileageApplyEntity, Integer>,
		JpaSpecificationExecutor<MileageApplyEntity> {
	@Query(
			"Select u From MileageApplyEntity u "
			)
	Page<MileageApplyEntity> listPaging(Pageable pageable);

	@Query(
			"Select u From MileageApplyEntity u Where u.user.id = ?1 "
			)
	Page<MileageApplyEntity> listPagingByUserId( Integer userId, Pageable pageable);



}
