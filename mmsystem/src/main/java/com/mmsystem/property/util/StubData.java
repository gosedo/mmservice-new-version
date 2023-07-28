package com.mmsystem.property.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import com.mmsystem.property.model.MmsIssueStatus;
import com.mmsystem.property.model.MmsIssueType;
import com.mmsystem.property.model.MmsMaintenanceIssue;
import com.mmsystem.property.model.MmsPost;
import com.mmsystem.property.model.MmsProperty;
import com.mmsystem.property.model.MmsPropertyManagement;
import com.mmsystem.property.model.MmsTechTask;
import com.mmsystem.property.model.MmsTechTaskPost;
import com.mmsystem.property.model.MmsTechTaskStatus;
import com.mmsystem.property.model.MmsTechTeam;
import com.mmsystem.property.model.MmsTechTeamMember;
import com.mmsystem.property.model.MmsTenant;
import com.mmsystem.property.model.MmsUnit;
import com.mmsystem.property.model.MmsUser;
import com.mmsystem.property.model.MmsUserRole;
import com.mmsystem.property.model.MmsUserStatus;

import lombok.Getter;
import lombok.Setter;

/**
 * sample data for testing purpose
 * */

@Getter
@Setter
public class StubData {
	
	
	
	public MmsUserRole getRoleByIdFromStub(int roleId) {
		
		MmsUserRole sampleRole = getStubListOfUserRoles()
											.stream()
											.filter(role -> role.getUsrRoleId() == roleId)
											.collect(Collectors.toList()).get(0);
		
		return sampleRole;
	}
	
	public Page<MmsMaintenanceIssue> getIssuesByUserIdPagedFromStub(int userId){
		
		
		
		MmsUserRole sampleRole = getRoleByIdFromStub(RoleTypeConstants.TENANT);
		
		List<MmsMaintenanceIssue> listOfIssueFromStub = getStubListOfIssues()
															.stream()
															.filter(issue -> issue.getRequestedBy()
																					.getTenantInfo()
																					.getUserRoles().contains(sampleRole) 
																					&&
																					issue.getRequestedBy()
																					.getTenantInfo()
																					.getUserId() == userId)
															.collect(Collectors.toList());
		
		Page<MmsMaintenanceIssue> pagedResponse = new PageImpl<MmsMaintenanceIssue>(listOfIssueFromStub.subList(0, 2));
		
		
		return pagedResponse;
		
	}
	
	
	
	public Page<MmsMaintenanceIssue> getAllIssuePagedFromStub(){
		
		Page<MmsMaintenanceIssue> pagedResponse = new PageImpl<MmsMaintenanceIssue>(getStubListOfIssues().subList(0, 2));
		
		return pagedResponse ;
	}
	
	
	public void setUpAllData() {
		
		MmsPropertyManagement propMgmtOne = new MmsPropertyManagement(1,"SMGMT","Southern Managment","220 S Eden st","","","Columbia","MD","55401","2312","4103459867","info@southerMngmt.com","4103459868","Ashley");
		MmsPropertyManagement propMgmtTwo = new MmsPropertyManagement(2,"PPMGT","Professional Managment","230 S Eden st","","","Columbia","MD","55401","2312","4103459827","info2@southerMngmt.com","4103459869","Nashley");
		
		stubListOfPropMgmts.add(propMgmtOne);
		stubListOfPropMgmts.add(propMgmtTwo);
		
		MmsProperty propOne = new MmsProperty(1,"PKCH","Park Trees","435 N Metro blvd", "suit 100","","Baltimore","MD","34534","","4109874565","parkt@parttress.com","4109874563","Brown",propMgmtOne);
		MmsProperty propTwo = new MmsProperty(2,"QYCH","Queens York","435 N Luther blvd", "suit 101","","Baltimore","MD","34534","","4109874567","queeny@parttress.com","4109874562","Saron",propMgmtTwo);
		
		stubListOfProps.add(propOne);
		stubListOfProps.add(propTwo);
		
		MmsUnit unitOne = new MmsUnit(1,"220-QWTR-1102","APT","220 Light Street 1102","220 Light st", "Apt 1102","","Baltimore", "MD", "34534", "",propOne);
		MmsUnit unitTwo = new MmsUnit(2,"220-QWTR-1103","APT","220 Light Street 1103","220 Light st", "Apt 1103","","Baltimore", "MD", "34534", "",propOne);
		MmsUnit unitThree = new MmsUnit(3,"220-QWTR-1104","APT","220 Light Street 1104","220 Light st", "Apt 1104","","Baltimore", "MD", "34534", "",propOne);
		MmsUnit unitFour = new MmsUnit(4,"220-QWTR-1105","APT","220 Light Street 1105","220 Light st", "Apt 1105","","Baltimore", "MD", "34534", "",propOne);
		MmsUnit unitFive = new MmsUnit(5,"220-QWTR-1106","APT","220 Light Street 1106","220 Light st", "Apt 1106","","Baltimore", "MD", "34534", "",propOne);
		MmsUnit unitSix = new MmsUnit(6,"220-QWTR-1107","APT","220 Light Street 1107","220 Light st", "Apt 1107","","Baltimore", "MD", "34534", "",propOne);
		MmsUnit unitSeven = new MmsUnit(7,"220-QWTR-1108","APT","220 Light Street 1108","220 Light st", "Apt 1108","","Baltimore", "MD", "34534", "",propOne);
		
		stubListOfUnits.add(unitOne);
		stubListOfUnits.add(unitTwo);
		stubListOfUnits.add(unitThree);
		stubListOfUnits.add(unitFour);
		stubListOfUnits.add(unitFive);
		stubListOfUnits.add(unitSix);
		stubListOfUnits.add(unitSeven);
		
		MmsUserStatus userStatusOne = new MmsUserStatus(1,"A","Active");
		MmsUserStatus userStatusTwo = new MmsUserStatus(2,"T","Terminated");
		
		stubListOfUserStatuses.add(userStatusOne);
		stubListOfUserStatuses.add(userStatusTwo);
		
		MmsUserRole userRoleOne = new MmsUserRole(1,"TNNT","Tenant");
		MmsUserRole userRoleTwo = new MmsUserRole(2,"TECH","Technician");
		MmsUserRole userRoleThree = new MmsUserRole(3,"MGMT","Management");
		
		stubListOfUserRoles.add(userRoleOne);
		stubListOfUserRoles.add(userRoleTwo);
		stubListOfUserRoles.add(userRoleThree);
		
		Set<MmsUserRole> roleForTenant = new HashSet<>();
		roleForTenant.add(userRoleOne);
		
		Set<MmsUserRole> roleForTech = new HashSet<>();
		roleForTenant.add(userRoleTwo);
		
		Set<MmsUserRole> roleForMgmt = new HashSet<>();
		roleForTenant.add(userRoleThree);
		
		MmsUser userOne = new MmsUser(1,"phenderson@randatmail.com" , "7b09hy", "Penelope" 	, "Henderson"	,	"259-6277-1868",roleForTenant,userStatusOne,"N",null);
		MmsUser userTwo = new MmsUser(2,"akelley@randatmail.com"		, "lew9m5", "Amber"		, "Kelley"		,   "692-6874-5059",roleForTenant,userStatusOne,"N",null);
		MmsUser userThree = new MmsUser(3,"lbarnes@randatmail.com"		, "lw2w1x", "Luke"		, "Barnes"		,   "142-5179-6543",roleForTenant,userStatusOne,"N",null);
		MmsUser userFour = new MmsUser(4,"tdavis@randatmail.com"		, "k6x16i", "Tess"		, "Davis"		,   "661-8520-4942",roleForTenant,userStatusOne,"N",null);
		MmsUser userFive = new MmsUser(5,"hfoster@randatmail.com"		, "ywbsg6", "Honey"		, "Foster"		,   "123-1837-1641",roleForTenant,userStatusOne,"N",null);
		MmsUser userSix = new MmsUser(6,"hbrown@randatmail.com"		, "erbsg6", "Henery"	, "Brown"		,   "434-1847-1241",roleForTenant,userStatusOne,"N",null);
		MmsUser userSeven = new MmsUser(7,"lbarnesere@randatmail.com"		, "ws2w1x", "Tuke"		, "Boarnes"		,   "176-5179-6543",roleForTech,userStatusOne,"N",null);
		MmsUser userEight = new MmsUser(8,"tdavissec@randatmail.com"		, "rfx16i", "Less"		, "Travis"		,   "698-8520-4942",roleForTech,userStatusOne,"N",null);
		MmsUser userNine = new MmsUser(9,"hfosterwed@randatmail.com"		, "edbsg6", "Koney"		, "Monster"		,   "165-1837-1641",roleForMgmt,userStatusOne,"N",null);
		MmsUser userTen = new MmsUser(10,"hbrownwsde@randatmail.com"		, "websg6", "Nenery"	, "Aswown"		,   "423-1847-1241",roleForMgmt,userStatusOne,"N",null);
		
		stubListOfUsers.add(userOne);
		stubListOfUsers.add(userTwo);
		stubListOfUsers.add(userThree);
		stubListOfUsers.add(userFour);
		stubListOfUsers.add(userFive);
		stubListOfUsers.add(userSix);
		stubListOfUsers.add(userSeven);
		stubListOfUsers.add(userEight);
		stubListOfUsers.add(userNine);
		stubListOfUsers.add(userTen);
		
		//DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSX");
		
		MmsTenant tenantOne = new MmsTenant(1,userOne,unitOne ,true, LocalDateTime.now(), LocalDateTime.now());
		MmsTenant tenantTwo = new MmsTenant(1,userTwo,unitTwo ,true, LocalDateTime.now(), LocalDateTime.now());
		MmsTenant tenantThree = new MmsTenant(1,userThree,unitThree ,true, LocalDateTime.now(), LocalDateTime.now());
		MmsTenant tenantFour = new MmsTenant(1,userFour,unitFour ,true, LocalDateTime.now(), LocalDateTime.now());
		
		stubListOfTenants.add(tenantOne);
		stubListOfTenants.add(tenantTwo);
		stubListOfTenants.add(tenantThree);
		stubListOfTenants.add(tenantFour);
		
		MmsIssueType issueTypeOne = new MmsIssueType(1,"HVAC","Heating or Ventilation or AC");
		MmsIssueType issueTypeTwo = new MmsIssueType(2,"PLUM","Plumbing systems");
		MmsIssueType issueTypeThree = new MmsIssueType(3,"ELEC","Electrical systems");
		MmsIssueType issueTypeFour = new MmsIssueType(4,"HMAP","Home appliances");
		MmsIssueType issueTypeFive = new MmsIssueType(5,"WNDR","Windows and Doors");
		MmsIssueType issueTypeSix = new MmsIssueType(6,"FLCR","Flooring and Carpets");
		MmsIssueType issueTypeSeven = new MmsIssueType(7,"OUTD","Outdoor amenities");
		MmsIssueType issueTypeEight = new MmsIssueType(8,"SMKD","Smoke detectors");
		MmsIssueType issueTypeNine = new MmsIssueType(9,"OTHR","Others");
		
		stubListOfIssueTypes.add(issueTypeOne);
		stubListOfIssueTypes.add(issueTypeTwo);
		stubListOfIssueTypes.add(issueTypeThree);
		stubListOfIssueTypes.add(issueTypeFour);
		stubListOfIssueTypes.add(issueTypeFive);
		stubListOfIssueTypes.add(issueTypeSix);
		stubListOfIssueTypes.add(issueTypeSeven);
		stubListOfIssueTypes.add(issueTypeEight);
		stubListOfIssueTypes.add(issueTypeNine);

		
		MmsIssueStatus issueStatusOne = new MmsIssueStatus(1,"NEWR","New Request");
		MmsIssueStatus issueStatusTwo = new MmsIssueStatus(2,"UDRV","Under Review");
		MmsIssueStatus issueStatusThree = new MmsIssueStatus(3,"INPR","In Progress");
		MmsIssueStatus issueStatusFour = new MmsIssueStatus(4,"ONHL","On Hold");
		MmsIssueStatus issueStatusFive = new MmsIssueStatus(5,"CLSD","Closed");
		MmsIssueStatus issueStatusSix = new MmsIssueStatus(6,"CMPL","Completed");
		
		stubListOfIssueStatuses.add(issueStatusOne);
		stubListOfIssueStatuses.add(issueStatusTwo);
		stubListOfIssueStatuses.add(issueStatusThree);
		stubListOfIssueStatuses.add(issueStatusFour);
		stubListOfIssueStatuses.add(issueStatusFive);
		stubListOfIssueStatuses.add(issueStatusSix);
		
		MmsMaintenanceIssue issueOne = new MmsMaintenanceIssue(1,issueTypeOne,"AC not working. Too hot!", issueStatusOne,tenantOne , LocalDateTime.now(), null);
		MmsMaintenanceIssue issueTwo = new MmsMaintenanceIssue(1,issueTypeTwo,"Toilet not flushing!", issueStatusOne,tenantOne , LocalDateTime.now(), null);
		MmsMaintenanceIssue issueThree = new MmsMaintenanceIssue(1,issueTypeThree,"Kitchen light out!", issueStatusOne,tenantOne , LocalDateTime.now(), null);
		
		stubListOfIssues.add(issueOne);
		stubListOfIssues.add(issueTwo);
		stubListOfIssues.add(issueThree);
		
		MmsPost issuePostOne = new MmsPost(1,issueOne,"Unable to access unit. Please move blocking items.",LocalDateTime.now(),userEight);
		MmsPost issuePostTwo = new MmsPost(1,issueOne,"Items moved.",LocalDateTime.now(),userOne);
		
		stubListOfIssuePosts.add(issuePostOne);
		stubListOfIssuePosts.add(issuePostTwo);
		
		
		MmsTechTeam techTeamOne = new MmsTechTeam(1,"HVAC Team");
		MmsTechTeam techTeamTwo = new MmsTechTeam(2,"Electricians");
		MmsTechTeam techTeamThree = new MmsTechTeam(3,"Plumbing Team");
		
		stubListOfTeams.add(techTeamOne);
		stubListOfTeams.add(techTeamTwo);
		stubListOfTeams.add(techTeamThree);
		
		
		Set<MmsTechTeam> techMemberOfOne = new HashSet<>();
		techMemberOfOne.add(techTeamOne);
		
		Set<MmsTechTeam> techMemberOfTwo = new HashSet<>();
		techMemberOfTwo.add(techTeamTwo);
		
		Set<MmsTechTeam> techMemberOfThree = new HashSet<>();
		techMemberOfThree.add(techTeamThree);
		
		MmsTechTeamMember teamMemberOne = new MmsTechTeamMember(1,userSeven,techMemberOfOne,"N");
		MmsTechTeamMember teamMemberTwo = new MmsTechTeamMember(1,userEight,techMemberOfTwo,"N");
		
		stubListOfTeamMembs.add(teamMemberOne);
		stubListOfTeamMembs.add(teamMemberTwo);
		

		
		MmsTechTaskStatus techTaskStatusOne = new MmsTechTaskStatus(1,"NEWR","New Request");
		MmsTechTaskStatus techTaskStatusTwo = new MmsTechTaskStatus(2,"UDRV","Under Review");
		MmsTechTaskStatus techTaskStatusThree = new MmsTechTaskStatus(3,"INPR","In Progress");
		MmsTechTaskStatus techTaskStatusFour = new MmsTechTaskStatus(4,"ONHL","On Hold");
		MmsTechTaskStatus techTaskStatusFive = new MmsTechTaskStatus(5,"CLSD","Closed");
		MmsTechTaskStatus techTaskStatusSix = new MmsTechTaskStatus(6,"CMPL","Completed");
		
		stubListOfTaskStatuses.add(techTaskStatusOne);
		stubListOfTaskStatuses.add(techTaskStatusTwo);
		stubListOfTaskStatuses.add(techTaskStatusThree);
		stubListOfTaskStatuses.add(techTaskStatusFour);
		stubListOfTaskStatuses.add(techTaskStatusFive);
		stubListOfTaskStatuses.add(techTaskStatusSix);
		
		
		MmsTechTask techTaskOne = new MmsTechTask(1,"Add freon",issueOne,techTaskStatusOne,LocalDateTime.now(),null,techTeamOne,userNine,userSeven);
		
		stubListOfTasks.add(techTaskOne);
		
		MmsTechTaskPost techTaskPostOne = new MmsTechTaskPost(1,techTaskOne,"Will be done in an hour",LocalDateTime.now(),userSeven);
		
		stubListOfTaskPosts.add(techTaskPostOne);
		
		
	}
	
	
	List<MmsTechTaskPost> stubListOfTaskPosts = new ArrayList<>();
	List<MmsPost> stubListOfIssuePosts = new ArrayList<>();
	List<MmsTechTask> stubListOfTasks = new ArrayList<>();
	List<MmsTechTaskStatus> stubListOfTaskStatuses = new ArrayList<>();
	List<MmsTechTeamMember> stubListOfTeamMembs = new ArrayList<>();
	List<MmsTechTeam> stubListOfTeams = new ArrayList<>();
	List<MmsMaintenanceIssue> stubListOfIssues = new ArrayList<>();
	List<MmsTenant> stubListOfTenants = new ArrayList<>();
	List<MmsUnit> stubListOfUnits = new ArrayList<>();
	List<MmsProperty> stubListOfProps = new ArrayList<>();
	List<MmsPropertyManagement> stubListOfPropMgmts = new ArrayList<>();
	List<MmsUser> stubListOfUsers = new ArrayList<>();
	List<MmsUserRole> stubListOfUserRoles = new ArrayList<>();
	List<MmsUserStatus> stubListOfUserStatuses = new ArrayList<>();
	List<MmsIssueType> stubListOfIssueTypes = new ArrayList<>();
	List<MmsIssueStatus> stubListOfIssueStatuses = new ArrayList<>();

}
