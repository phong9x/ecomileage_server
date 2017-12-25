package org.ecomileage.data.repository.jpa;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

import org.ecomileage.bean.jpa.NoticeEntity;

/**
 * Repository : Notice.
 */
public interface NoticeJpaRepository
		extends PagingAndSortingRepository<NoticeEntity, Integer>, JpaSpecificationExecutor<NoticeEntity> {
	@Query("Select u From NoticeEntity u ")
	Page<NoticeEntity> listPaging(Pageable pageable);

	@Query("Select u From NoticeEntity u WHERE u.type =?1")
	Page<NoticeEntity> findByType(Short type, Pageable pageable);

	@Query("Select u From NoticeEntity u WHERE u.type =?1")
	List<NoticeEntity> listByType(Short type);
	
}
