package org.ecomileage.data.repository.jpa;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

import org.ecomileage.bean.jpa.CategoryEntity;
/**
 * Repository : Category.
 */
public interface CategoryJpaRepository extends PagingAndSortingRepository<CategoryEntity, Integer>,
		JpaSpecificationExecutor<CategoryEntity> {
	@Query(
			"Select u From CategoryEntity u "
			)
	Page<CategoryEntity> listPaging(Pageable pageable);

	List<CategoryEntity> findByGroupId(Short groupId);

}
