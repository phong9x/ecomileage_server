package org.ecomileage.data.repository.jpa;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.ecomileage.bean.jpa.MileageFavouriteEntity;
/**
 * Repository : MileageFavourite.
 */
public interface MileageFavouriteJpaRepository extends PagingAndSortingRepository<MileageFavouriteEntity, Integer>,
		JpaSpecificationExecutor<MileageFavouriteEntity> {
	@Query(
			"Select u From MileageFavouriteEntity u "
			)
	Page<MileageFavouriteEntity> listPaging(Pageable pageable);

	@Query(
			"Select u From MileageFavouriteEntity u Where u.userId = ?1 "
			)
	Page<MileageFavouriteEntity> listPagingByUserId( Integer userId, Pageable pageable);



}
