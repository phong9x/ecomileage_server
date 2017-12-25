package org.ecomileage.data.repository.jpa;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.ecomileage.bean.jpa.MileageTransferEntity;
/**
 * Repository : MileageTransfer.
 */
public interface MileageTransferJpaRepository extends PagingAndSortingRepository<MileageTransferEntity, Integer>,
		JpaSpecificationExecutor<MileageTransferEntity> {
	@Query(
			"Select u From MileageTransferEntity u "
			)
	Page<MileageTransferEntity> listPaging(Pageable pageable);

	@Query(
			"Select u From MileageTransferEntity u Where u.userId = ?1 "
			)
	Page<MileageTransferEntity> listPagingByUserId( Integer userId, Pageable pageable);



}
