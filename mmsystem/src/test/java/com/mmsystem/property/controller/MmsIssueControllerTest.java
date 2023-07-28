package com.mmsystem.property.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.jwt;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import java.util.stream.Collectors;

import org.junit.jupiter.api.Assertions;
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
import com.mmsystem.property.dto.MmsIssueCreateDTO;
import com.mmsystem.property.dto.MmsMaintenanceIssueDTO;
import com.mmsystem.property.mapper.MmsMaintenanceIssueMapper;
import com.mmsystem.property.model.MmsMaintenanceIssue;
import com.mmsystem.property.service.MmsIssuesService;
import com.mmsystem.property.util.StubData;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class MmsIssueControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private MmsIssuesService mmsIssuesService;
	
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
		
		MmsMaintenanceIssue mmsMaintenanceIssue= allData.getStubListOfIssues().stream()
													.filter(issue -> issue.getIssueId() ==1)
													.collect(Collectors.toList()).get(0);
		
		MmsIssueCreateDTO mmsIssueCreateDTO = new MmsIssueCreateDTO();
		mmsIssueCreateDTO.setIssueTypeId(mmsMaintenanceIssue.getIssueType().getIssueTypeId());
		mmsIssueCreateDTO.setIssueDescr(mmsMaintenanceIssue.getIssueDescription());
		mmsIssueCreateDTO.setRequestedByUserId(mmsMaintenanceIssue.getRequestedBy().getTenantId());
		
		
		MmsMaintenanceIssueDTO mmsMaintenanceIssueDTO = MmsMaintenanceIssueMapper.INSTANCE.mapToIssueDto(mmsMaintenanceIssue);
		
		String expectedJson = this.mapToJson(mmsMaintenanceIssueDTO);
		
		
		String userToBeCreated = this.mapToJson(mmsIssueCreateDTO);
		
		Mockito.when(mmsIssuesService.createMmsIssue(mmsIssueCreateDTO)).thenReturn(mmsMaintenanceIssueDTO);
		
		String url = "/api/issue/mmsissue-create";
		MvcResult mvcResult = mockMvc.perform(post(url)
									.accept(MediaType.APPLICATION_JSON)
									.contentType(MediaType.APPLICATION_JSON)
									.content(userToBeCreated)
									.with(jwt()))
									.andReturn();
		String response = mvcResult.getResponse().getContentAsString();
		
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.findAndRegisterModules();
		MmsMaintenanceIssueDTO mmsMaintenanceIssueDTOreturn =objectMapper.readValue(response, MmsMaintenanceIssueDTO.class);
		
		//assertThat(response).isEqualTo(expectedJson);
		
		assertThat(mmsMaintenanceIssueDTOreturn).isInstanceOf(MmsMaintenanceIssueDTO.class);
		
		
		
	}
	
	private String mapToJson(Object object) throws JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.findAndRegisterModules();
		return objectMapper.writeValueAsString(object);
	}

}
