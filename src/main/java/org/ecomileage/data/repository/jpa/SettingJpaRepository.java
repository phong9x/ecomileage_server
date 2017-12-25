package org.ecomileage.data.repository.jpa;

import java.util.Date;
import java.util.List;
import org.ecomileage.bean.jpa.SettingEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Repository : Setting.
 */
public interface SettingJpaRepository
		extends PagingAndSortingRepository<SettingEntity, Integer>, JpaSpecificationExecutor<SettingEntity> {
	@Query("Select u From SettingEntity u ")
	Page<SettingEntity> listPaging(Pageable pageable);

	@Query("SELECT u FROM SettingEntity u WHERE  u.startTime <= ?1 AND ?1 <= u.endTime ")
	List<SettingEntity> findByDate(Date now);

	@Query(value="SELECT IF(COUNT(*) > 0, 'true', 'false') AS NewResult "
			+ " FROM setting "
			+ " WHERE "
			+ " (start_time <= ?1 and ?1 <= end_time) or  "
			+ " (start_time <= ?2 and ?2 <= end_time) and id != ?3 ", nativeQuery=true)
	boolean checkDuplicateStartDateAndEndDate(Date startDate, Date endDate, Integer id);
	
	@Query(value="SELECT IF(COUNT(*) > 0, 'true', 'false') AS NewResult "
			+ " FROM setting "
			+ " WHERE "
			+ " (start_time <= ?1 and ?1 <= end_time) or  "
			+ " (start_time <= ?2 and ?2 <= end_time) ", nativeQuery=true)
	boolean checkDuplicateStartDateAndEndDate(Date startDate, Date endDate);
}
