package com.mmsystem.property.repo;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
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

import com.mmsystem.property.mapper.MmsUserMapper;
import com.mmsystem.property.model.MmsUser;
import com.mmsystem.property.util.StubData;
import com.mmsystem.property.util.UsersPageConstants;
import com.mmsystem.property.util.YesOrNoConstants;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class MmsUserJPARepositoryTest {

	
	@Autowired
	private MmsUserJPARepository mmsUserJPARepository;
	
	
	
	static StubData allData = new StubData();
	
	@BeforeAll
	static void setupAllDataForTest() {
		allData.setUpAllData();
	}
	
	@BeforeEach
	void setup() {
		
		
		
	}
	
	@Test
	public void givenMmsUser_whenSave_theReturnsSavedUser() {
		
		MmsUser mmsUser = allData.getStubListOfUsers().get(0);
		
		MmsUser newUser = MmsUserMapper.INSTANCE.mapToUserToNoId(mmsUser);
		
		
		
		List<MmsUser> testUsersFromDB = mmsUserJPARepository.findLastTestUserWithtestEmail();
		
		MmsUser testUserFromDB = testUsersFromDB.get(0);
		
		String userEmail = testUserFromDB.getUserEmail();
		
		String newEmail = getNewAppendedEmailFrom(userEmail);
		
		//phenderson@randatmail.com
		//phenderso11@randatmail.co
		newUser.setUserEmail(newEmail);
		
		MmsUser savedUser = mmsUserJPARepository.save(newUser);
		
		assertThat(savedUser).isNotNull();
        assertThat(savedUser.getUserId()).isGreaterThan(0);
		
	}
	
	@Test
	public void givenMmsUser_whenIsVerifiedUpdated_ReturnsUserWithUpdatedVerfiedStatus() {
		
		List<MmsUser> testUsersFromDB = mmsUserJPARepository.findLastTestUserWithtestEmail();
		
		MmsUser testUserFromDB = testUsersFromDB.get(0);
		
		String initialVerFromDB = testUserFromDB.getIsVerified();
		
		String newVerifiedStatus = initialVerFromDB == YesOrNoConstants.YES ? YesOrNoConstants.NO : YesOrNoConstants.YES;
		
		testUserFromDB.setIsVerified(newVerifiedStatus);
		
		MmsUser updatedUser = mmsUserJPARepository.save(testUserFromDB);
		
		
		assertThat(updatedUser).isNotNull();
        assertThat(updatedUser.getIsVerified()).isNotEqualTo(initialVerFromDB);
		
	}
	
	@Test
	public void givenPageSize_whenGetPagedUsers_ReturnsNumberOfUserEqPageSize() {
		
		Sort sort = Sort.by("userId").ascending();
		int pageSize =3;
		int pageNumber = 1;
	           
	    Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);
	    
	    List<MmsUser> pagedListOfusers = mmsUserJPARepository.findUserByNameAndEmail(UsersPageConstants.DEFAULT_FIRST_NAME
														    		,UsersPageConstants.DEFAULT_LAST_NAME
														    		,UsersPageConstants.DEFAULT_USER_EMAIL,pageable).getContent();
		
		
		assertThat(pagedListOfusers).isNotNull();
        assertThat(pagedListOfusers.size()).isEqualTo(pageSize);
		
	}
	
	@ParameterizedTest
	@ValueSource(ints = {3, 4, 5})
	public void givenDifferentPageSize_whenGetPagedUsers_ReturnsNumberOfUserEqPageSize(int pageSizeInput) {
		
		Sort sort = Sort.by("userId").ascending();
		int pageSize =pageSizeInput;
		int pageNumber = 1;
	           
	    Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);
	    
	    List<MmsUser> pagedListOfIssues = mmsUserJPARepository.findUserByNameAndEmail(
	    											 UsersPageConstants.DEFAULT_FIRST_NAME
	    											,UsersPageConstants.DEFAULT_LAST_NAME
	    											,UsersPageConstants.DEFAULT_USER_EMAIL,pageable).getContent();
		
		
		assertThat(pagedListOfIssues).isNotNull();
        assertThat(pagedListOfIssues.size()).isEqualTo(pageSize);
		
	}
	
	private String getNewAppendedEmailFrom(String oldEmail) {
		
		int indexOfLastCharOfEmail = oldEmail.indexOf("@")-1;
		
		String emailDomain = oldEmail.substring(oldEmail.indexOf("@"), oldEmail.length());
		
		Character lastChar = oldEmail.charAt(indexOfLastCharOfEmail);
		
		String userEmailToBeAppended = Character.isDigit(lastChar) == true ? oldEmail.substring(0, oldEmail.indexOf("@")-1): 
																			oldEmail.substring(0, oldEmail.indexOf("@"));
		
//		log.info("from user testing =====");
//		log.info("old email ====" +oldEmail);
//		log.info("index @ -2 " +oldEmail.substring(0, oldEmail.indexOf("@")-2));
//		log.info("index @ -1 " +oldEmail.substring(0, oldEmail.indexOf("@")-1));
//		log.info(""+Character.isDigit(lastChar));
//		log.info("Last Character "+lastChar.toString());
//		log.info("email to append" + userEmailToBeAppended);
		
		String newEmail = Character.isDigit(lastChar) ? userEmailToBeAppended + Integer.toString((lastChar -'0')+1)+emailDomain : 
														userEmailToBeAppended + Integer.toString(1)+emailDomain;
		
		log.info("new email " + newEmail);
		
		return newEmail;
		
	}
	
}
