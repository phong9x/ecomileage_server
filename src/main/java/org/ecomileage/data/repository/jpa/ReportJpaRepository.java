package org.ecomileage.data.repository.jpa;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.ecomileage.bean.jpa.ReportEntity;
/**
 * Repository : Report.
 */
public interface ReportJpaRepository extends PagingAndSortingRepository<ReportEntity, Integer>,
		JpaSpecificationExecutor<ReportEntity> {
	@Query(
			"Select u From ReportEntity u "
			)
	Page<ReportEntity> listPaging(Pageable pageable);



}
