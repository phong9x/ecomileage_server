package org.ecomileage.data.repository.jpa;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.ecomileage.bean.jpa.SupportBikeCenterEntity;
/**
 * Repository : SupportBikeCenter.
 */
public interface SupportBikeCenterJpaRepository extends PagingAndSortingRepository<SupportBikeCenterEntity, Integer>,
		JpaSpecificationExecutor<SupportBikeCenterEntity> {
	@Query(
			"Select u From SupportBikeCenterEntity u "
			)
	Page<SupportBikeCenterEntity> listPaging(Pageable pageable);



}
