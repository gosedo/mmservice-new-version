package com.mmsystem.property.repo;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;

import com.mmsystem.property.mapper.MmsMaintenanceIssueMapper;
import com.mmsystem.property.model.MmsIssueStatus;
import com.mmsystem.property.model.MmsMaintenanceIssue;
import com.mmsystem.property.util.StubData;


@RunWith(SpringRunner.class)
@SpringBootTest
public class MmsIssuesRepositoryJPATest {
	
	@Autowired
	private MmsIssuesJPARepository mmsIssuesJPARepository;
	
	@Autowired
	private MmsIssueStausJPARepository mmsIssueStausJPARepository;
	
	static StubData allData = new StubData();
	
	@BeforeAll
	static void setupAllDataForTest() {
		allData.setUpAllData();
	}
	
	@BeforeEach
	void setup() {
		
		
		
	}
	
	@Test
	public void givenMmsMaintenaceIssue_whenSave_theReturnsSavedIssue() {
		
		MmsMaintenanceIssue mmsMaintenanceIssue = allData.getStubListOfIssues().get(0);
		
		MmsMaintenanceIssue newIssue = MmsMaintenanceIssueMapper.INSTANCE.mapToNoIdIssueObj(mmsMaintenanceIssue);
		
		MmsMaintenanceIssue savedIssue = mmsIssuesJPARepository.save(newIssue);
		
		assertThat(savedIssue).isNotNull();
        assertThat(savedIssue.getIssueId()).isGreaterThan(0);
		
	}
	
	@Test
	public void givenMmsMaintenaceIssue_whenStatusUpdated_ReturnsIssueWithUpdatedStatus() {
		
		MmsMaintenanceIssue mmsIssueForUpdate = mmsIssuesJPARepository.findAll().get(0);
		MmsIssueStatus initialStatusFromDB = mmsIssueForUpdate.getIssueStatus();
		
		List<MmsIssueStatus> allIssueExceptTheInitial = mmsIssueStausJPARepository.findAll().stream()
		 						.filter(issueStatus -> issueStatus.getIssueStatusId() != initialStatusFromDB.getIssueStatusId())
		 						.collect(Collectors.toList());
		
		mmsIssueForUpdate.setIssueStatus(allIssueExceptTheInitial.get(0));
		
		MmsMaintenanceIssue updatedIssue = mmsIssuesJPARepository.save(mmsIssueForUpdate);
		
		
		assertThat(updatedIssue).isNotNull();
        assertThat(updatedIssue.getIssueStatus().getIssueStatusId()).isNotEqualTo(initialStatusFromDB.getIssueStatusId());
		
	}
	
	@Test
	public void givenPageSize_whenGetPagedIssues_ReturnsNumberOfIssueEqPageSize() {
		
		Sort sort = Sort.by("issueId").ascending();
		int pageSize =3;
		int pageNumber = 1;
	           
	    Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);
	    
	    List<MmsMaintenanceIssue> pagedListOfIssues = mmsIssuesJPARepository.findIssueByDateAndUserIdAndIssueId(null,null,null,(long)0,pageable).getContent();
		
		
		assertThat(pagedListOfIssues).isNotNull();
        assertThat(pagedListOfIssues.size()).isEqualTo(pageSize);
		
	}
	@ParameterizedTest
	@ValueSource(ints = {3, 4, 5})
	public void givenDifferentPageSize_whenGetPagedIssues_ReturnsNumberOfIssueEqPageSize(int pageSizeInput) {
		
		Sort sort = Sort.by("issueId").ascending();
		int pageSize =pageSizeInput;
		int pageNumber = 1;
	           
	    Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);
	    
	    List<MmsMaintenanceIssue> pagedListOfIssues = mmsIssuesJPARepository.findIssueByDateAndUserIdAndIssueId(null,null,null,(long)0,pageable).getContent();
		
		
		assertThat(pagedListOfIssues).isNotNull();
        assertThat(pagedListOfIssues.size()).isEqualTo(pageSize);
		
	}
	
	
	

}
