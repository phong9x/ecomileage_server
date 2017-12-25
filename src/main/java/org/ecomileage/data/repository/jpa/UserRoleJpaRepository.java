package org.ecomileage.data.repository.jpa;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.ecomileage.bean.jpa.UserRoleEntity;
/**
 * Repository : UserRole.
 */
public interface UserRoleJpaRepository extends PagingAndSortingRepository<UserRoleEntity, Integer>,
		JpaSpecificationExecutor<UserRoleEntity> {
	@Query(
			"Select u From UserRoleEntity u "
			)
	Page<UserRoleEntity> listPaging(Pageable pageable);



}
