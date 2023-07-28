package com.mmsystem.property.service;

import static org.assertj.core.api.Assertions.assertThat;
//import static org.assertj.core.api.Assertions.*;

import static org.junit.jupiter.api.Assertions.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.mmsystem.property.dto.MmsIssueResponse;
import com.mmsystem.property.dto.MmsMaintenanceIssueDTO;
import com.mmsystem.property.model.MmsMaintenanceIssue;
import com.mmsystem.property.repo.MmsIssuesJPARepository;
import com.mmsystem.property.util.IssuesPageConstants;
import com.mmsystem.property.util.MmsPageParam;
import com.mmsystem.property.util.StubData;

@ExtendWith(MockitoExtension.class)

@SpringBootTest
class MmsIssuesServiceImplTest {

	@Autowired
	private MmsIssuesService mmsIssueService;
	
	@MockBean
	private MmsIssuesJPARepository mmsIssuesJPARepository;
	
	
	static StubData allData = new StubData();
	
	@BeforeAll
	static void setupAllDataForTest() {
		allData.setUpAllData();
	}
	
	@BeforeEach
	void setup() {
		
		Sort sort = Sort.by(IssuesPageConstants.DEFAULT_SORT_BY).descending();
		
		Pageable pageable = PageRequest.of(0, 3, sort);
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSX");
		//DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
		LocalDateTime startDateParam = LocalDateTime.parse("2023-07-05T01:07:19.967Z",formatter);
		LocalDateTime endDateParam = LocalDateTime.parse("2023-07-20T01:07:19.967Z", formatter);
		
		//Issues for Tenant UserId --1
		Mockito.when(mmsIssuesJPARepository
			  			.findByCreatedOnDateGreaterThanEqualAndCreatedOnDateLessThanEqualAndRequestedByTenantInfoUserId(
			  					startDateParam	
					  			,endDateParam
					  			,(long)1
					  			,pageable)).thenReturn(allData.getIssuesByUserIdPagedFromStub(1));
		
		//Issues For Manager userId --9
		Mockito.when(mmsIssuesJPARepository
	  						.findAll(
	  								pageable
			  			)).thenReturn(allData.getAllIssuePagedFromStub());
		
		Mockito.when(mmsIssuesJPARepository.findByRequestedByTenantInfoUserId((long)1)).thenReturn(allData.getStubListOfIssues());
		
		int issueId;
		//marked
		Mockito.when( 
				  mmsIssuesJPARepository
				  	.findIssueByDateAndUserIdAndIssueId(
				  			startDateParam
				  			,endDateParam
				  			,(long) 1
				  			,(long) 0
				  			,pageable)).thenReturn(allData.getIssuesByUserIdPagedFromStub(1));
		
		Mockito.when(  mmsIssuesJPARepository
			  		.findIssueByDateAndUserIdAndIssueId(
			  			startDateParam
			  			,endDateParam
			  			,null
			  			,(long) 0
			  			,pageable)).thenReturn(allData.getAllIssuePagedFromStub());
		
		
	}
	
	@Test
	public void testGetAllMmsIssuesPagedByUserId_ReturnsAllIssuesForManager_Success() {
		
		MmsPageParam pageParam = new MmsPageParam();
		pageParam.setPageNo(0);
		pageParam.setPageSize(3);
		pageParam.setSortBy(IssuesPageConstants.DEFAULT_SORT_BY);
		pageParam.setSortDir(IssuesPageConstants.DEFAULT_SORT_DIRECTION);
		
//		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSX");
//		LocalDateTime startDateParam = LocalDateTime.parse("2023-07-19T20:39:14.000X",formatter);
//		LocalDateTime endDateParam = LocalDateTime.parse("2023-07-19T20:39:14.000X", formatter);
		
		MmsIssueResponse responseForIssues =  mmsIssueService.getAllMmsIssuesPagedByUserId(9,0
																			,"2023-07-05T01:07:19.967Z"
																			,"2023-07-20T01:07:19.967Z"
																			, pageParam);
		
		assertThat( allData.getIssuesByUserIdPagedFromStub(1)).hasAtLeastOneElementOfType(MmsMaintenanceIssue.class);
		
		assertThat(responseForIssues.getContent().get(0)).isInstanceOf(MmsMaintenanceIssueDTO.class);
		
		
	}
	
	@Test
	public void testGetIssueById_Success() {
				
		List<MmsMaintenanceIssueDTO> issuesList = mmsIssueService.getMmsIssueByUserId(1);
		assertEquals(3,issuesList.size());
	}
	
	@Test
	public void testGetIssueByUserId_ReturnIssueDTO_Success() {
		
				
		List<MmsMaintenanceIssueDTO> issuesList = mmsIssueService.getMmsIssueByUserId(1);
		
		//jupiter.api
		assertEquals(3,issuesList.size());
		
//		//hamcrest
//		assertThat(3,equalTo(issuesList.size()));
//		assertThat(issuesList.get(0), instanceOf(MmsMaintenanceIssueDTO.class));
		
		//assertj
		assertThat(3).isEqualTo(issuesList.size());
		assertThat(issuesList.get(0)).isInstanceOf(MmsMaintenanceIssueDTO.class);
		assertThat(issuesList).isNotInstanceOf(MmsMaintenanceIssueDTO.class);
		
	}
	
	
	
	
	 
//	List<MmsMaintenanceIssue> listOfIssues = new ArrayList<>();
//	MmsMaintenanceIssue mmsIssueOne= new MmsMaintenanceIssue();
//	mmsIssueOne.setIssueId(1);
//	MmsMaintenanceIssue mmsIssueTwo= new MmsMaintenanceIssue();
//	mmsIssueOne.setIssueId(1);
//	
//	listOfIssues.add(mmsIssueOne);
//	listOfIssues.add(mmsIssueTwo);
//	//StubData fromStubData = new StubData();

}
