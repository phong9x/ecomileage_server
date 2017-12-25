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

import org.ecomileage.bean.jpa.TeamEntity;
/**
 * Repository : Team.
 */
public interface TeamJpaRepository extends PagingAndSortingRepository<TeamEntity, Integer>,
		JpaSpecificationExecutor<TeamEntity> {
	@Query(
			"Select u From TeamEntity u Where u.isDelete = false"
			)
	Page<TeamEntity> listPaging(Pageable pageable);
	
	List<TeamEntity> findByName(String name);

	@Modifying
	@Query(
			value="UPDATE team SET is_delete = true WHERE id = ?1",nativeQuery=true
			)
	void update_isDelete(Integer id);

	@Query(
			"Select u From TeamEntity u Where DATE(u.deleteDate) <= DATE(?1) AND u.isDelete = false"
			)
	List<TeamEntity> listByDeleteDate(Date date);
	
}
