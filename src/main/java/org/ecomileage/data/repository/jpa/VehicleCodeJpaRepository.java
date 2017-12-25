package org.ecomileage.data.repository.jpa;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

import org.ecomileage.bean.jpa.VehicleCodeEntity;

/**
 * Repository : VehicleCode.
 */
public interface VehicleCodeJpaRepository
		extends PagingAndSortingRepository<VehicleCodeEntity, Integer>, JpaSpecificationExecutor<VehicleCodeEntity> {
	@Query("Select u From VehicleCodeEntity u ")
	Page<VehicleCodeEntity> listPaging(Pageable pageable);

	@Query("Select u From VehicleCodeEntity u WHERE u.typeVehicle in ?1 AND u.user.id = ?2")
	List<VehicleCodeEntity> findByTypeVehicleAndUserId(Short[] typeVehicle, Integer userId, Pageable pageable);

	List<VehicleCodeEntity> findByUserId(Integer userId);

	@Query("Select u From VehicleCodeEntity u WHERE u.serialNumber = ?1 AND u.user.id = ?2")
	VehicleCodeEntity findBySerialNumberAndUserId(String serialNumber, Integer userId);

	VehicleCodeEntity findBySerialNumber(String serialNumber);

	VehicleCodeEntity findBySerialNumberAndTypeVehicle(String serialNumber, Short typeVehicle);

	@Query("Select COUNT(u.id) From VehicleCodeEntity u WHERE u.typeVehicle = ?1 ")
	Long countByTypeVehicle(Short typeVehicle);
	
	@Query("Select u From VehicleCodeEntity u WHERE u.serialNumber = ?1 AND u.typeVehicle != 1 ")
	VehicleCodeEntity findBySerialNumberByBusAndSubway(String serialNumber);
}
