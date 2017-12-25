package org.ecomileage.data.repository.jpa;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.ecomileage.bean.MileageBackup;
import org.ecomileage.bean.jpa.MileageBackupEntity;

/**
 * Repository : MileageBackup.
 */
public interface MileageBackupJpaRepository extends PagingAndSortingRepository<MileageBackupEntity, Integer>,
		JpaSpecificationExecutor<MileageBackupEntity> {
	@Query("Select u From MileageBackupEntity u ")
	Page<MileageBackupEntity> listPaging(Pageable pageable);
	
	@Query("select u from MileageBackupEntity u where u.user2.id=?1 ")
	MileageBackupEntity findBySenderId(Integer senderId);
}
