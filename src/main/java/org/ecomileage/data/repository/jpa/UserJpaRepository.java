package org.ecomileage.data.repository.jpa;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Date;
import java.util.List;

import org.ecomileage.bean.jpa.UserEntity;

/**
 * Repository : User.
 */
public interface UserJpaRepository
		extends PagingAndSortingRepository<UserEntity, Integer>, JpaSpecificationExecutor<UserEntity> {
	@Query("Select u From UserEntity u Where u.isDelete = 0")
	Page<UserEntity> listPaging(Pageable pageable);

	@Modifying
	@Query(value = "UPDATE user SET is_delete = 1 WHERE id = ?1", nativeQuery = true)
	void update_isDelete(Integer id);

	@Query("Select u From UserEntity u Where u.username = ?1 and u.password = ?2 and u.userRole.id in ?3 and u.isDelete = 0")
	UserEntity login(String username, String password, Integer[] roleIds);

	@Query("Select u From UserEntity u Where u.username = ?1 and u.isDelete = 0")
	UserEntity findByUsername(String username);

	UserEntity findBySnsTypeAndSocialIdAndIsDelete(String snsType, String socialId, boolean isDelete);

	List<UserEntity> findByEmailAndPhoneAndIsDelete(String email, String phone, boolean isDelete);

	@Query("Select u From UserEntity u Where u.username = ?1 and u.phone = ?2 and u.isDelete = ?3")
	UserEntity findByUsernameAndPhoneAndIsDelete(String username, String phone, boolean isDelete);

	@Query("Select u From UserEntity u Where u.snsType = ?1 and u.socialId = ?2 and u.userRole.id in ?3 and u.isDelete = 0")
	UserEntity loginSNS(String snsType, String snsToken, Integer[] roleIds);

	List<UserEntity> findByPhoneAndIsDelete(String phone, boolean isDelete);

	@Query("Select COUNT(u.id) From UserEntity u WHERE u.userRole.id !=4 ")
	Long countUserActive();
	
	@Query("Select COUNT(u.id) From UserEntity u WHERE u.userRole.id !=4 AND YEAR(u.createDate) = ?1")
	Long countUserJoinByYear(Integer year);

	@Query(value="Select min(create_date) From user ", nativeQuery=true)
	Date getMinStartTime();
}
