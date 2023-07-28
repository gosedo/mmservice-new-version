package com.mmsystem.property.controller;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.mmsystem.property.dto.MmsUserDTO;
import com.mmsystem.property.mapper.MmsUserMapper;
import com.mmsystem.property.service.MmsUserService;
import com.mmsystem.property.util.StubData;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class AuthenticationControllerTest {
	
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
	public void testAuthenticateUser() throws Exception {
		
		MmsUserDTO mmsUserDto= MmsUserMapper.INSTANCE.mapToUserDto(allData.getStubListOfUsers().get(0));
		
		
		List<MmsUserDTO> mmsUserDtoList = allData.getStubListOfUsers()
													.stream()
													.map( mmsuser -> MmsUserMapper.INSTANCE.mapToUserDto(mmsuser)															
																).collect(Collectors.toList());
		
		Mockito.when(mmsUserService.getUsers()).thenReturn(mmsUserDtoList);
		
		String url = "/api/auth/signin";
		
		MvcResult mvcResult = mockMvc.perform(get(url)
								.header("Authorization", "Basic aGZvc3RlcndlZEByYW5kYXRtYWlsLmNvbTplZGJzZzY=")
								//.with(httpBasic("hfosterwed@randatmail.com","123qwe")
						)
						.andExpect(status().isUnauthorized()).andReturn();
		var response = mvcResult.getResponse().getStatus();//HttpStatus.UNAUTHORIZED
		
		//assertThat(response, equalTo(401));
		
	}

}
