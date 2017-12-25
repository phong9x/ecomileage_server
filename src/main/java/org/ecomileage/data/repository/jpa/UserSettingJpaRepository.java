package org.ecomileage.data.repository.jpa;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.ecomileage.bean.jpa.UserSettingEntity;
/**
 * Repository : UserSetting.
 */
public interface UserSettingJpaRepository extends PagingAndSortingRepository<UserSettingEntity, Integer>,
		JpaSpecificationExecutor<UserSettingEntity> {
	@Query(
			"Select u From UserSettingEntity u "
			)
	Page<UserSettingEntity> listPaging(Pageable pageable);



}
