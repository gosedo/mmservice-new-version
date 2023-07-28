package com.mmsystem.property.repo;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.mmsystem.property.model.MmsMaintenanceIssue;


public interface MmsIssuesJPARepository extends JpaRepository<MmsMaintenanceIssue, Long>  {
	
	List<MmsMaintenanceIssue> findByRequestedByTenantInfoUserEmail(String userEmail);
	List<MmsMaintenanceIssue> findByRequestedByTenantInfoUserId(Long userId);
	Page<MmsMaintenanceIssue> findByCreatedOnDateGreaterThanEqualAndCreatedOnDateLessThanEqualAndRequestedByTenantInfoUserId(
			LocalDateTime startDate,LocalDateTime endData,Long userId,
			 Pageable pageable);
	
	@Query("FROM MmsMaintenanceIssue c JOIN c.requestedBy t JOIN t.tenantInfo u  WHERE ((:startDate is null and :endData is null) "
														+ "or (c.createdOnDate between :startDate and :endData)) "
														+ "and (:userId is null"
														+ " or u.userId = :userId) and"
														+ "(:issueId = 0 or c.issueId =:issueId)")
	Page<MmsMaintenanceIssue> findIssueByDateAndUserIdAndIssueId(@Param("startDate") LocalDateTime startDate
												,@Param("endData") LocalDateTime endData
												,@Param("userId") Long userId
												,@Param("issueId") Long issueId
												,Pageable pageable);
	Page<MmsMaintenanceIssue> findByCreatedOnDateGreaterThanEqualAndCreatedOnDateLessThanEqual(
			LocalDateTime startDate,LocalDateTime endData,
			 Pageable pageable);
	
}

