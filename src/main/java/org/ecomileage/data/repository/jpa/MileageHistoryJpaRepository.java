package org.ecomileage.data.repository.jpa;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Date;

import org.ecomileage.bean.jpa.MileageHistoryEntity;
/**
 * Repository : MileageHistory.
 */
public interface MileageHistoryJpaRepository extends PagingAndSortingRepository<MileageHistoryEntity, Integer>,
		JpaSpecificationExecutor<MileageHistoryEntity> {
	@Query(
			"Select u From MileageHistoryEntity u WHERE u.isDelete = false"
			)
	Page<MileageHistoryEntity> listPaging(Pageable pageable);
	
	@Query(
			"SELECT u FROM MileageHistoryEntity u WHERE u.user.id =?1 AND u.type in ?2 AND u.isDelete = false"
			)
	Page<MileageHistoryEntity> listPagingHistory(Integer userId, Short[] type, Pageable pageable);

	 @Query("SELECT COUNT(DISTINCT  u.user.id) FROM MileageHistoryEntity u WHERE YEAR(u.createDate) =?1 AND u.user.userRole.id != 4 ")
	 Long countUserJoin_ByYear(Integer year);
	 
	 @Query("SELECT COUNT(DISTINCT  u.user.id) FROM MileageHistoryEntity u")
	 Long countUserJoin();
	 
	 @Query("SELECT ROUND(SUM(u.totalReduceCo2),0) FROM MileageHistoryEntity u WHERE YEAR(u.createDate) =?1 AND u.status in ?2 AND u.type = 1")
	 Long countReduceCo2_ByYear(Integer year, Short[] status);
	 
	 @Query("SELECT SUM(u.totalReduceCo2) FROM MileageHistoryEntity u WHERE YEAR(u.createDate) =?1 AND u.status in ?2 AND u.user.id =?3 AND u.type = 1")
	 Float countReduceCo2_ByYearAndUserId(Integer year, Short[] status, Integer userId);
	 
	 @Query("SELECT ROUND(COALESCE(SUM(u.totalReduceCo2),0),2) FROM MileageHistoryEntity u WHERE u.status in ?1 AND u.user.id =?2 AND u.type = 1")
	 Float countReduceCo2_ByUserId(Short[] status, Integer userId);
	 
	 @Query("SELECT SUM(u.totalReduceCo2) FROM MileageHistoryEntity u WHERE u.status in ?1 AND u.type = 1")
	 Float countReduceCo2(Short[] status);
	 
	 @Query("SELECT ROUND(SUM(u.totalReduceCo2),0) FROM MileageHistoryEntity u")
	 Long countReduceCo2();
	 
	 @Query("SELECT SUM(u.point) FROM MileageHistoryEntity u WHERE u.isAdminApproval = false AND u.user.id = ?1 AND u.adminApprovalDate = DATE(?2)")
	 Long countApprovalByUserId(Integer userId, Date date);
	 
	 @Query("SELECT SUM(u.point) FROM MileageHistoryEntity u WHERE  u.user.id = ?1 AND MONTH(u.adminApprovalDate) = MONTH(?2)")
	 Long countPointByUserIdAndMonth(Integer userId, Date date);
	 
	 @Query("SELECT COALESCE(SUM(u.point),0) FROM MileageHistoryEntity u WHERE  u.user.id = ?1 AND u.type in ?2")
	 Long countPointByUserId(Integer userId, Short[] type);
	 
	 @Query("SELECT COALESCE(SUM(u.point),0) FROM MileageHistoryEntity u WHERE  u.user.id = ?1")
	 Long countTotalPointByUserId(Integer userId);
	 
	 @Query("SELECT COALESCE(SUM(u.favouritePoint),0) FROM MileageHistoryEntity u WHERE  u.user.id = ?1")
	 Long countFavouritePointByUserId(Integer userId);
	 
	 @Query("SELECT COUNT(u.id) FROM MileageHistoryEntity u WHERE  u.user.id = ?1 AND u.type in ?2")
	 Long countActiveByUserId(Integer userId, Short[] type);

	 @Query("SELECT ROUND(SUM(u.totalMileage),2) FROM MileageHistoryEntity u WHERE u.user.id = ?1 AND u.type in ?2 ")
	 Float countTotalMileageByUserId(Integer userId, Short[] type);
	 
	 @Query("SELECT ROUND(SUM(u.totalMileage),2) FROM MileageHistoryEntity u WHERE u.type in ?1")
	 Float countTotalMileage(Short[] type);
	 
	 @Query("SELECT ROUND(SUM(u.totalMileage),2) FROM MileageHistoryEntity u WHERE YEAR(u.createDate) =?1 AND u.type in ?3 AND u.user.id =?2")
	 Float countTotalMileage_ByYearAndUserId(Integer year, Integer userId, Short[] type);
}
