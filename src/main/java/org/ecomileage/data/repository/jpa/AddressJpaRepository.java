package org.ecomileage.data.repository.jpa;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

import org.ecomileage.bean.jpa.AddressEntity;
/**
 * Repository : Address.
 */
public interface AddressJpaRepository extends PagingAndSortingRepository<AddressEntity, Integer>,
		JpaSpecificationExecutor<AddressEntity> {
	@Query("Select u From AddressEntity u ")
	Page<AddressEntity> listPaging(Pageable pageable);

	List<AddressEntity> findByParentId(Integer parentId);
	
	@Query("Select u From AddressEntity u where u.name = ?1 ")
	List<AddressEntity> findByParentName(String parentName);
	
	@Query("Select u From AddressEntity u where u.name = ?1 ")
	AddressEntity findOneByParentName(String parentName);
	
	@Query("Select u From AddressEntity u where u.parentId > 0")
	List<AddressEntity> getDistricts();

	
}
