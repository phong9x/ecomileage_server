package org.ecomileage.data.repository.jpa;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.ecomileage.bean.jpa.SponsorEntity;
/**
 * Repository : Sponsor.
 */
public interface SponsorJpaRepository extends PagingAndSortingRepository<SponsorEntity, Integer>,
		JpaSpecificationExecutor<SponsorEntity> {
	@Query(
			"Select u From SponsorEntity u "
			)
	Page<SponsorEntity> listPaging(Pageable pageable);



}
