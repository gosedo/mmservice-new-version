package com.mmsystem.property.controller;



import static org.assertj.core.api.Assertions.assertThat;


import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.jwt;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mmsystem.property.dto.MmsUserCreateDTO;
import com.mmsystem.property.dto.MmsUserDTO;
import com.mmsystem.property.dto.MmsUserUpdateDTO;
import com.mmsystem.property.mapper.MmsUserMapper;
import com.mmsystem.property.model.MmsUser;
import com.mmsystem.property.service.MmsUserService;
import com.mmsystem.property.util.StubData;



@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTests {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private MmsUserService mmsUserService;
	
	static StubData allData = new StubData();
	
	@BeforeAll
	static void setupAllDataForTest() {
		allData.setUpAllData();
	}
	
	@BeforeEach
	void setup() {
		
		
		
	}
	
	@Test
	public void testCreateUser_ReturnsTheSavedMmsUser() throws Exception {
		
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
		
		
		MmsUserDTO mmsuserDTO = MmsUserMapper.INSTANCE.mapToUserDto(mmsUser);
		
		String expectedJson = this.mapToJson(mmsuserDTO);
		String userToBeCreated = this.mapToJson(mmsUserCreateDTO);
		
		Mockito.when(mmsUserService.createUser(mmsUserCreateDTO)).thenReturn(mmsuserDTO);
		
		String url = "/api/mmsuser/mmsuser-create";
		MvcResult mvcResult = mockMvc.perform(post(url)
									.accept(MediaType.APPLICATION_JSON)
									.contentType(MediaType.APPLICATION_JSON)
									.content(userToBeCreated)
									.with(jwt()))
									.andReturn();
		String response = mvcResult.getResponse().getContentAsString();
		
		assertThat(response).isEqualTo(expectedJson);
		
	}
	
	
	@Test
	public void testUpdateUser_ReturnsTheUpdatedMmsUser() throws Exception {
		
		MmsUser mmsUser= allData.getStubListOfUsers().stream()
													.filter(user -> user.getUserId() ==1)
													.collect(Collectors.toList()).get(0);
		
		MmsUserUpdateDTO mmsUserUpdateDTO = new MmsUserUpdateDTO();
		mmsUserUpdateDTO.setUserId(mmsUser.getUserId());
		mmsUserUpdateDTO.setUserEmail(mmsUser.getUserEmail());
		mmsUserUpdateDTO.setUserFirstname(mmsUser.getUserFirstname());
		mmsUserUpdateDTO.setUserLastname(mmsUser.getUserLastname());
		mmsUserUpdateDTO.setUserPassword(mmsUser.getUserPassword());
		mmsUserUpdateDTO.setUserPhone(mmsUser.getUserPhone());
		mmsUserUpdateDTO.setUserRoles(mmsUser.getUserRoles().stream()
															.map(role -> role.getUsrRoleId())
															.collect(Collectors.toSet()));
		mmsUserUpdateDTO.setUserStatus(mmsUser.getUserStatus().getUserStatusId());
		
		
		
		MmsUserDTO mmsuserDTO = MmsUserMapper.INSTANCE.mapToUserDto(mmsUser);
		
		String expectedJson = this.mapToJson(mmsuserDTO);
		String userToBeUpdated = this.mapToJson(mmsUserUpdateDTO);
		
		Mockito.when(mmsUserService.updateUser(mmsUserUpdateDTO)).thenReturn(mmsuserDTO);
		
		String url = "/api/mmsuser/mmsuser-update";
		MvcResult mvcResult = mockMvc.perform(post(url)
									.accept(MediaType.APPLICATION_JSON)
									.contentType(MediaType.APPLICATION_JSON)
									.content(userToBeUpdated)
									.with(jwt()))
									.andReturn();
		String response = mvcResult.getResponse().getContentAsString();
		
		assertThat(response).isEqualTo(expectedJson);
		
	}
	
	@Test
	public void testGetUsers_CheckStatusSuccess() throws Exception {
		
		MmsUserDTO mmsUserDto= MmsUserMapper.INSTANCE.mapToUserDto(allData.getStubListOfUsers().get(0));
		
		
		List<MmsUserDTO> mmsUserDtoList = allData.getStubListOfUsers()
													.stream()
													.map( mmsuser -> MmsUserMapper.INSTANCE.mapToUserDto(mmsuser)															
																).collect(Collectors.toList());
		
		Mockito.when(mmsUserService.getUsers()).thenReturn(mmsUserDtoList);
		
		String url = "/api/mmsuser/mmsuser-list";
		MvcResult mvcResult = mockMvc.perform(get(url).with(jwt())).andExpect(status().isOk()).andReturn();
		//var response = mvcResult.getResponse().getStatus();
		
	}
	
	private String mapToJson(Object object) throws JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.writeValueAsString(object);
	}
	
	
	
	
	
	
	
	//	assertThat(jwt.getTokenValue()).isEqualTo("token");
	//	assertThat(jwt.getHeaders().get("alg")).isEqualTo("none");
	//	assertThat(jwt.getSubject()).isEqualTo("sub");
	
	//HttpStatus.UNAUTHORIZED
	//assertThat(response, equalTo(401));
	//String token = "eyJhbGciOiJIUzM4NCJ9.eyJzdWIiOiJoZm9zdGVyd2VkQHJhbmRhdG1haWwuY29tIiwiaWF0IjoxNjkwMzc4NjIyLCJleHAiOjE2OTA0MTQ2MjJ9.1Ttg_BNNKM3_w0iFzjMg6za1-JOtDx2ovy0PmU5vAOETprIR6AtFut9gmzITnetP";
	//aGZvc3RlcndlZEByYW5kYXRtYWlsLmNvbTplZGJzZzY=
	//.header("Authorization", "Bearer " + token)

}
