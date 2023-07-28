package com.mmsystem.property.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.stream.Collectors;

import org.junit.jupiter.api.Assertions;
//import org.assertj.core.api.Assertions;
//import org.junit.jupiter.api.Assertions;
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
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.mmsystem.property.dto.MmsMaintenanceIssueDTO;
import com.mmsystem.property.dto.MmsTechTeamMemberDTO;
import com.mmsystem.property.dto.MmsTenantDTO;
import com.mmsystem.property.dto.MmsUserCreateDTO;
import com.mmsystem.property.dto.MmsUserDTO;
import com.mmsystem.property.exception.ResourceAlreadyExistsException;
import com.mmsystem.property.mapper.MmsTechTeamMemberMapper;
import com.mmsystem.property.mapper.MmsTenantMapper;
import com.mmsystem.property.mapper.MmsUserMapper;
import com.mmsystem.property.model.MmsTechTeamMember;
import com.mmsystem.property.model.MmsTenant;
import com.mmsystem.property.model.MmsUser;
import com.mmsystem.property.model.MmsUserRole;
import com.mmsystem.property.repo.MmsIssuesJPARepository;
import com.mmsystem.property.repo.MmsUserJPARepository;
import com.mmsystem.property.repo.UserRepository;
import com.mmsystem.property.util.EmailUtil;
import com.mmsystem.property.util.IssuesPageConstants;
import com.mmsystem.property.util.StubData;

@ExtendWith(MockitoExtension.class)

@SpringBootTest
public class MmsUserServiceImplTest {
	
	@Autowired
	private MmsUserServiceImpl mmsUserServiceImpl;
	
	@MockBean
	private MmsIssuesJPARepository mmsIssuesJPARepository;
	
	@MockBean  
	 private UserRepository mmspUserRepo;
	  
	@MockBean  
	private MmsUserJPARepository mmspUserJpaRepo; 
	  
	@MockBean
	private BCryptPasswordEncoder passwordEncoder;
	  
	@MockBean
	private MmsUserRoleServiceImpl mmsUserRoleServiceImpl;
	  
	@MockBean
	private MmsUserStatusServiceImpl mmsUserStatusServiceImpl;
	  
	@MockBean
	private MmsTenantService mmsTenantService;
	  
	@MockBean
	private MmsTechTeamMemberService mmsTechTeamMemberService;
	  
	@MockBean
	private EmailUtil emailUtilService;
	  
	  
	
	
	static StubData allData = new StubData();
	
	@BeforeAll
	static void setupAllDataForTest() {
		allData.setUpAllData();
	}
	
	@BeforeEach
	void setup() {
		
		
		Sort sort = Sort.by(IssuesPageConstants.DEFAULT_SORT_BY).descending();
		
		Pageable pageable = PageRequest.of(0, 3, sort);
		
		
		Mockito.when(mmspUserJpaRepo.findByUserEmail(allData.getStubListOfUsers().get(0).getUserEmail())).thenReturn(allData.getStubListOfUsers().get(0));
		
		
		MmsUserRole mmsUserRole = allData.getStubListOfUsers().get(0).getUserRoles().stream().collect(Collectors.toList()).get(0);
		
		Mockito.when(mmsUserRoleServiceImpl.getRoleById(mmsUserRole.getUsrRoleId())).thenReturn(allData.getStubListOfUserRoles().get(0));
		
		
		Mockito.when(mmsUserStatusServiceImpl.getUserStatusById(allData.getStubListOfUsers().get(0).getUserStatus().getUserStatusId()))
														.thenReturn(allData.getStubListOfUserStatuses().get(0));
		
		Mockito.when(mmspUserJpaRepo.save(Mockito.any())).thenReturn(allData.getStubListOfUsers().get(0));
		
		MmsTenant mmsTenant = allData.getStubListOfTenants().get(0);
		MmsTenantDTO mmsTenantDto = MmsTenantMapper.INSTANCE.mapToMmsTenantDto(mmsTenant);
		
		Mockito.when(mmsTenantService.addTenant(allData.getStubListOfUsers().get(0),0)).thenReturn(mmsTenantDto);
		
		MmsTechTeamMember mmsTechTeamMember = allData.getStubListOfTeamMembs().get(0);
		MmsTechTeamMemberDTO mmsTechTeamMemberDTO = MmsTechTeamMemberMapper.INSTANCE.mapToMmsTechTeamMemberDto(mmsTechTeamMember);
		
		Mockito.when(mmsTechTeamMemberService.addTeamMember(allData.getStubListOfUsers().get(0),0)).thenReturn(mmsTechTeamMemberDTO);
		
		Mockito.when(emailUtilService.sendSimpleMail(Mockito.any())).thenReturn("Email Was sent");
		
		
	}
	
	@Test
	public void testCreateUser_Throws_ResourceAlreadyExistsException_WhenEmailExist() {
		
		MmsUser mmsUser= allData.getStubListOfUsers().stream()
															.filter(user -> user.getUserId() ==1)
															.collect(Collectors.toList()).get(0);

		MmsUserCreateDTO mmsUserCreateDTO = new MmsUserCreateDTO();
		mmsUserCreateDTO.setUserEmail(mmsUser.getUserEmail());
		mmsUserCreateDTO.setUserFirstname(mmsUser.getUserFirstname());
		mmsUserCreateDTO.setUserLastname(mmsUser.getUserLastname());
		mmsUserCreateDTO.setUserPassword(mmsUser.getUserPassword());
		mmsUserCreateDTO.setUserPhone(mmsUser.getUserPhone());
		mmsUserCreateDTO.setUserRoles(mmsUser.getUserRoles().stream().findFirst().get().getUsrRoleId());
		mmsUserCreateDTO.setUserTeam(0);
		mmsUserCreateDTO.setUserUnit(0);
		
		//MmsUserDTO userCreated = mmsUserServiceImpl.createUser(mmsUserCreateDTO);
		
		final String invalid = String.format( "User with %s email already exist. ",mmsUserCreateDTO.getUserEmail() );

	     
	    		
		ResourceAlreadyExistsException exceptionThrown = 	Assertions.assertThrows(ResourceAlreadyExistsException.class, 
	    				() ->{
	    					mmsUserServiceImpl.createUser(mmsUserCreateDTO);
	    				}
	    				);
	    		

		Assertions.assertEquals(invalid, exceptionThrown.getMessage());
		
		
		//assertThat(userCreated).isInstanceOf(MmsUserDTO.class);
		
	}
	
	@Test
	public void testCreateUser_ReturnTypeOfMmsUserDTO() {
		
		MmsUser mmsUser= allData.getStubListOfUsers().stream()
															.filter(user -> user.getUserId() ==1)
															.collect(Collectors.toList()).get(0);

		MmsUserCreateDTO mmsUserCreateDTO = new MmsUserCreateDTO();
		mmsUserCreateDTO.setUserEmail("neverbeenusedemail@gmail.com");
		mmsUserCreateDTO.setUserFirstname(mmsUser.getUserFirstname());
		mmsUserCreateDTO.setUserLastname(mmsUser.getUserLastname());
		mmsUserCreateDTO.setUserPassword(mmsUser.getUserPassword());
		mmsUserCreateDTO.setUserPhone(mmsUser.getUserPhone());
		mmsUserCreateDTO.setUserRoles(mmsUser.getUserRoles().stream().findFirst().get().getUsrRoleId());
		mmsUserCreateDTO.setUserTeam(0);
		mmsUserCreateDTO.setUserUnit(0);
		
		MmsUserDTO userCreated = mmsUserServiceImpl.createUser(mmsUserCreateDTO);
		
		assertThat(userCreated).isInstanceOf(MmsUserDTO.class);
		
	}
	
	
	
	
	
	
	

}
