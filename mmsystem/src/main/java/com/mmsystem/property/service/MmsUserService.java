package com.mmsystem.property.service;

import java.util.Date;
import java.util.List;

import com.mmsystem.property.dto.AuthInformationDTO;
import com.mmsystem.property.dto.MmsUserActivationDTO;
import com.mmsystem.property.dto.MmsUserCreateDTO;
import com.mmsystem.property.dto.MmsUserDTO;
import com.mmsystem.property.dto.MmsUserUpdateDTO;
import com.mmsystem.property.dto.MmsUsersResponse;
import com.mmsystem.property.exception.ResourceNotFoundException;
import com.mmsystem.property.model.MmsUser;
import com.mmsystem.property.util.MmsPageParam;


public interface MmsUserService {
	
	//Start JPA
	MmsUserDTO createUser(MmsUserCreateDTO mmsUserCreateDto);
	
	List<MmsUserDTO> getUsers();
	//End JPA
	
	MmsUserDTO saveUser(MmsUserDTO mmsUserDto);
	
	List<MmsUser> getUsersOld();

    boolean deleteUser(MmsUser mmsUser);

    MmsUser getUserByID(MmsUser mmsUser);
  
    boolean updateUser(MmsUser mmsUser);
  
    MmsUser getUserByID(int mmsUser);
  
    MmsUser getByEmail(String email);
  
    boolean isValidUserCrediential(String email,String password);
  
    AuthInformationDTO createAuthInfoDTO(String jwtToken,Date tokenExpiration, MmsUserDTO userDTO);

	MmsUserDTO updateUser(MmsUserUpdateDTO mmsUserUpdateDto);

	boolean updateUserActivation(MmsUserActivationDTO mmsUserActivationDTO) throws ResourceNotFoundException;

	MmsUsersResponse getAllMmsUsersPagedByNameOrEmail(String userFirstname, String userLastname, String userEmail,
			MmsPageParam pageParam);
    

} 