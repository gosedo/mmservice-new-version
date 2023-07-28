package com.mmsystem.property.service;


import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;  
import org.springframework.transaction.annotation.Transactional;

import com.mmsystem.property.dto.AuthInformationDTO;

import com.mmsystem.property.dto.MmsTechTeamMemberDTO;
import com.mmsystem.property.dto.MmsTenantDTO;
import com.mmsystem.property.dto.MmsUserActivationDTO;
import com.mmsystem.property.dto.MmsUserCreateDTO;
import com.mmsystem.property.dto.MmsUserDTO;
import com.mmsystem.property.dto.MmsUserUpdateDTO;
import com.mmsystem.property.dto.MmsUsersResponse;
import com.mmsystem.property.exception.ResourceAlreadyExistsException;
import com.mmsystem.property.exception.ResourceNotFoundException;


import com.mmsystem.property.mapper.MmsUserMapper;
import com.mmsystem.property.model.MmsUser;
import com.mmsystem.property.model.MmsUserRole;
import com.mmsystem.property.model.MmsUserStatus;
import com.mmsystem.property.repo.MmsUserJPARepository;
import com.mmsystem.property.repo.UserRepository;
import com.mmsystem.property.util.EmailUtil;
import com.mmsystem.property.util.MmsComposeEmailUtil;
import com.mmsystem.property.util.MmsPageParam;
import com.mmsystem.property.util.RoleTypeConstants;
import com.mmsystem.property.util.UserStatusesConstants;
import com.mmsystem.property.util.YesOrNoConstants;

import lombok.extern.slf4j.Slf4j;

/**
 * User related operation. 
 * Registration, updating user information, calling other services to make user a tenant
 * , team member, email notification when new registration or update on existing user. 
 * Create, find, update
 * 
 * */


@Slf4j 
@Service  
@Transactional  
public class MmsUserServiceImpl implements MmsUserService {  
 
  @Autowired  
  private UserRepository mmspUserRepo;
  
  @Autowired  
  private MmsUserJPARepository mmspUserJpaRepo; 
  
  @Autowired
  private BCryptPasswordEncoder passwordEncoder;
  
  @Autowired
  private MmsUserRoleServiceImpl mmsUserRoleServiceImpl;
  
  @Autowired
  private MmsUserStatusServiceImpl mmsUserStatusServiceImpl;
  
  @Autowired
  private MmsTenantService mmsTenantService;
  
  @Autowired
  private MmsTechTeamMemberService mmsTechTeamMemberService;
  
  
  @Autowired
  private EmailUtil emailUtilService;
  
  //Start using JPA Repo
  
  @Override
  public MmsUserDTO createUser(MmsUserCreateDTO mmsUserCreateDto) throws ResourceAlreadyExistsException { 
	  
	  
	  MmsUser checkUserExists = mmspUserJpaRepo.findByUserEmail(mmsUserCreateDto.getUserEmail());
	  
	  if(checkUserExists != null) {
		  String message = String.format("User with %s email already exist. ", mmsUserCreateDto.getUserEmail());
		  throw new ResourceAlreadyExistsException(message);
	  }
	  
	  MmsUserRole userRole = mmsUserRoleServiceImpl.getRoleById(mmsUserCreateDto.getUserRoles());
	  Set<MmsUserRole> setOfUserRoles = new HashSet<>();
	  setOfUserRoles.add(userRole);
	  
	  MmsUser userToSave = new MmsUser();
	  
	  
	  MmsUserStatus mmsUserStatus =mmsUserStatusServiceImpl.getUserStatusById(UserStatusesConstants.Active);
	  
	  userToSave.setUserEmail(mmsUserCreateDto.getUserEmail());
	  userToSave.setUserPassword(mmsUserCreateDto.getUserPassword());
	  userToSave.setUserFirstname(mmsUserCreateDto.getUserFirstname());
	  userToSave.setUserLastname(mmsUserCreateDto.getUserLastname());
	  userToSave.setUserPhone(mmsUserCreateDto.getUserPhone());
	  userToSave.setUserRoles(setOfUserRoles);
	  userToSave.setUserStatus(mmsUserStatus);
	  userToSave.setIsVerified("N");
	  
	  UUID uuid = UUID.randomUUID();
	  userToSave.setActivationId(uuid.toString());
	  
	  String passwd= userToSave.getUserPassword();
	  String encodedPasswod = passwordEncoder.encode(passwd);
	  userToSave.setUserPassword(encodedPasswod);
	  
	  MmsUser mmsUserSaved = mmspUserJpaRepo.save(userToSave);
	  
	  MmsTenantDTO mmsUserTenancy;
	  
	  MmsTechTeamMemberDTO mmsUserMemebrOf;
	  
	  if(mmsUserCreateDto.getUserRoles() == RoleTypeConstants.TENANT) {
		  mmsUserTenancy = mmsTenantService.addTenant(mmsUserSaved, mmsUserCreateDto.getUserUnit());
	  }
	  
	  if(mmsUserCreateDto.getUserRoles() == RoleTypeConstants.TECHNICIAN) {
		  mmsUserMemebrOf = mmsTechTeamMemberService.addTeamMember(mmsUserSaved, mmsUserCreateDto.getUserTeam());
	  }
	  
	  
	  String iSMessegaeSent = emailUtilService.sendSimpleMail(MmsComposeEmailUtil.composerNewAccountEmail("ethioteste@gmail.com", mmsUserSaved.getActivationId()));
      
      return MmsUserMapper.INSTANCE.mapToUserDto(mmsUserSaved);
  }
  
  
  
  @Override
  public MmsUserDTO updateUser(MmsUserUpdateDTO mmsUserUpdateDTO) throws ResourceNotFoundException { 
	  
	  MmsUser checkUserExists = mmspUserJpaRepo.findById(mmsUserUpdateDTO.getUserId()).get();
	  
	  if(checkUserExists == null) {
		  String message = String.format("User with %s userId doesn't exist. ", mmsUserUpdateDTO.getUserId());
		  throw new ResourceNotFoundException(message);
	  }
	  
	  
	  MmsUser userToUpdate = mmspUserJpaRepo.findById(mmsUserUpdateDTO.getUserId()).get();
	  Set<MmsUserRole> userRoles = mmsUserRoleServiceImpl.getUserRoles(mmsUserUpdateDTO.getUserRoles());
	  MmsUserStatus mmsUserStatus =mmsUserStatusServiceImpl.getUserStatusById(mmsUserUpdateDTO.getUserStatus());
	  
	  
	  userToUpdate.setUserEmail(mmsUserUpdateDTO.getUserEmail());
	  userToUpdate.setUserFirstname(mmsUserUpdateDTO.getUserFirstname());
	  userToUpdate.setUserLastname(mmsUserUpdateDTO.getUserLastname());
	  userToUpdate.setUserPhone(mmsUserUpdateDTO.getUserPhone());
	  userToUpdate.setUserRoles(userRoles);
	  userToUpdate.setUserStatus(mmsUserStatus);
	  
	  //Password change business logic Don't change if length less than 5 and encrypt length > 5 
	  if(mmsUserUpdateDTO.getUserPassword() !=null ) {
		  
		  if(mmsUserUpdateDTO.getUserPassword().length() > 5) {
			  
			  userToUpdate.setUserPassword(mmsUserUpdateDTO.getUserPassword());
			  
			  String passwd= userToUpdate.getUserPassword();
			  String encodedPasswod = passwordEncoder.encode(passwd);
			  userToUpdate.setUserPassword(encodedPasswod);
			  
		  }
		 
	  }
	  
	  UUID uuid = UUID.randomUUID();
	  userToUpdate.setActivationId(uuid.toString());
	  
	  
	  MmsUser mmsUserUpdated = mmspUserJpaRepo.save(userToUpdate);
	  
	  
	  String iSMessegaeSent = emailUtilService.sendSimpleMail(MmsComposeEmailUtil.composerNewAccountEmail("ethioteste@gmail.com", mmsUserUpdated.getActivationId()));
	  
	  MmsUserDTO returnUserDTO = MmsUserMapper.INSTANCE.mapToUserDto(mmsUserUpdated);
	  
      
      return returnUserDTO;
  }
  
  @Override
  public boolean updateUserActivation(MmsUserActivationDTO mmsUserActivationDTO) throws ResourceNotFoundException { 
	  
	  MmsUser checkUserExists = mmspUserJpaRepo.findByActivationId(mmsUserActivationDTO.getActivationId());
	  
	  if(checkUserExists == null) {
		  String message = String.format("No with the provided info exists.", mmsUserActivationDTO.getActivationId());
		  throw new ResourceNotFoundException(message);
	  }
	  
	  MmsUser userToUpdate = checkUserExists;
	  userToUpdate.setUserPassword(mmsUserActivationDTO.getNewPassword());
	  userToUpdate.setIsVerified(YesOrNoConstants.YES);
	  
	  UUID uuid = UUID.randomUUID();
	  userToUpdate.setActivationId(uuid.toString());
	  
	  String passwd= userToUpdate.getUserPassword();
	  String encodedPasswod = passwordEncoder.encode(passwd);
	  userToUpdate.setUserPassword(encodedPasswod);
	  
	  MmsUser mmsUserUpdated = mmspUserJpaRepo.save(userToUpdate);
	  
	  MmsUserDTO returnUserDTO = MmsUserMapper.INSTANCE.mapToUserDto(mmsUserUpdated);
	  
      
      return true;
  }
  
  
  
  @Override
  public List<MmsUserDTO> getUsers() { 
	  
	List<MmsUser> listOfUsers = mmspUserJpaRepo.findAll();
	List<MmsUserDTO> listOfUsersDto = listOfUsers
			  							.stream()
			  							.map(user -> MmsUserMapper.INSTANCE.mapToUserDto(user))
			  							.collect(Collectors.toList());
			  

      return listOfUsersDto;  
  } 
  
  @Override
  public MmsUsersResponse getAllMmsUsersPagedByNameOrEmail(String userFirstname,String userLastname
														, String userEmail, MmsPageParam pageParam) {
																											
						
				Sort sort = pageParam.getSortDir().equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(pageParam.getSortBy()).ascending()
											: Sort.by(pageParam.getSortBy()).descending();
				
				Pageable pageable = PageRequest.of(pageParam.getPageNo(), pageParam.getPageSize(), sort);
				
				Page<MmsUser> mmsissuesPage = mmspUserJpaRepo.findUserByNameAndEmail(userFirstname, userLastname
																						,userEmail, pageable);
				
				List<MmsUser> listOfMmsUsers = mmsissuesPage.getContent();
				List<MmsUserDTO> content= listOfMmsUsers.stream()
														.map((user) ->  MmsUserMapper.INSTANCE.mapToUserDto(user))
														.collect(Collectors.toList());
				
				MmsUsersResponse mmsUsersResponse = new MmsUsersResponse();
				mmsUsersResponse.setContent(content);
				mmsUsersResponse.setPageNo(mmsissuesPage.getNumber());
				mmsUsersResponse.setPageSize(mmsissuesPage.getSize());
				mmsUsersResponse.setTotalElements(mmsissuesPage.getTotalElements());
				mmsUsersResponse.setTotalPages(mmsissuesPage.getTotalPages());
				mmsUsersResponse.setLast(mmsissuesPage.isLast());
				
				return mmsUsersResponse;
						

  }
  
  //End using JPA Repo
  
    
  @Override
  public MmsUserDTO saveUser(MmsUserDTO mmsUserDto) { 
	  
	  MmsUser mmsUser = MmsUserMapper.INSTANCE.mapToUser(mmsUserDto);
	  
      mmspUserRepo.save(mmsUser); 
      
      return MmsUserMapper.INSTANCE.mapToUserDto(mmsUser);
  }  

  @Override
  public List<MmsUser> getUsersOld() {  
      return mmspUserRepo.get();  
  }  

  @Override 
  public boolean deleteUser(MmsUser mmsUser) {  
      return mmspUserRepo.delete(mmsUser);  
  }  

  @Override 
  public MmsUser getUserByID(MmsUser mmsUser) {  
      return mmspUserRepo.getByID(mmsUser);  
  } 
  
  @Override 
  public boolean updateUser(MmsUser mmsUser) {  
      return mmspUserRepo.update(mmsUser);  
  } 
  //Below codes are additional service method from what is defined in the Interface IMMspService
  @Override
  public MmsUser getUserByID(int mmsUser) {  
      return mmspUserRepo.getByID(mmsUser);  
  }
  @Override
  public MmsUser getByEmail(String email) {
	  
	  return mmspUserRepo.getByEmail(email);
  }
  @Override
  public boolean isValidUserCrediential(String email,String password) {
	  
	  return mmspUserRepo.getByCredential(email,password) != null;
  }
  @Override
  public AuthInformationDTO createAuthInfoDTO(String jwtToken,Date tokenExpiration, MmsUserDTO userDTO) {
	  
	  Set<Integer> listOfUserRoles = new HashSet<>();
	  for(var role : userDTO.getUserRoles()) {
		  listOfUserRoles.add(role.getUsrRoleId());
	  }
		
	  AuthInformationDTO responseAuthInfo = new AuthInformationDTO();
	  responseAuthInfo.setJwtToken(jwtToken);
	  responseAuthInfo.setUserId(userDTO.getUserId());
	  responseAuthInfo.setUserEmail(userDTO.getUserEmail());
	  responseAuthInfo.setUserFirstName(userDTO.getUserFirstname());
	  responseAuthInfo.setUserLastName(userDTO.getUserLastname());
	  responseAuthInfo.setTokenExpiration(tokenExpiration);
	  responseAuthInfo.setUserRoleIds(listOfUserRoles);
	  
	  return responseAuthInfo;
  }

} 
