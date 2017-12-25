package org.ecomileage.data.repository.jpa;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.ecomileage.bean.jpa.CampaignEntity;
/**
 * Repository : Campaign.
 */
public interface CampaignJpaRepository extends PagingAndSortingRepository<CampaignEntity, Integer>,
		JpaSpecificationExecutor<CampaignEntity> {
	@Query(
			"Select u From CampaignEntity u "
			)
	Page<CampaignEntity> listPaging(Pageable pageable);


	CampaignEntity findByYear(Integer year);
}
