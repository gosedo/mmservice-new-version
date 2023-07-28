package com.mmsystem.property.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mmsystem.property.dto.MmsUserActivationDTO;
import com.mmsystem.property.dto.MmsUserCreateDTO;
import com.mmsystem.property.dto.MmsUserDTO;
import com.mmsystem.property.dto.MmsUserUpdateDTO;
import com.mmsystem.property.dto.MmsUsersResponse;
import com.mmsystem.property.model.MmsUser;
import com.mmsystem.property.service.MmsUserService;
import com.mmsystem.property.util.MmsPageParam;
import com.mmsystem.property.util.UsersPageConstants;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

/**
 * User related request and response are handled here.
 * create, select, update and delete can be handled her.
 * */
@Slf4j
@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(value = "/api/mmsuser")
public class UserController {

	@Autowired
	private MmsUserService mmsUserService;

	@GetMapping("test")
	public String test() {
		return "The result from Gosa";

	}
	
	@PostMapping(value="/mmsuser-create") 
	public MmsUserDTO createUser(@RequestBody MmsUserCreateDTO mmsUserCreateDto){ 
		
		 return mmsUserService.createUser(mmsUserCreateDto);
	}
	
	@PostMapping("mmsuser-update") 
	public MmsUserDTO updateUser(@Valid @RequestBody MmsUserUpdateDTO mmsUserUpdateDto){ 
		 return mmsUserService.updateUser(mmsUserUpdateDto);
	}
	
	@PostMapping("mmsuser-activation") 
	public boolean updateUserActivation(@Valid @RequestBody MmsUserActivationDTO mmsUserActivationDTO){ 
		 return mmsUserService.updateUserActivation(mmsUserActivationDTO);
	}

	
	@GetMapping("mmsuser-list-paged")
	public MmsUsersResponse getMmsUserByNameAndEmailPaged(
					@RequestParam(value = "userFirstname",defaultValue = UsersPageConstants.DEFAULT_FIRST_NAME,  required = false) String userFirstname,
					@RequestParam(value = "userLastname",defaultValue = UsersPageConstants.DEFAULT_LAST_NAME,  required = false) String userLastname,
		            @RequestParam(value = "userEmail",defaultValue = UsersPageConstants.DEFAULT_USER_EMAIL, required = false) String userEmail,
					@RequestParam(value = "pageNo", defaultValue = UsersPageConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
		            @RequestParam(value = "pageSize", defaultValue = UsersPageConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize,
		            @RequestParam(value = "sortBy", defaultValue = UsersPageConstants.DEFAULT_SORT_BY, required = false) String sortBy,
		            @RequestParam(value = "sortDir", defaultValue = UsersPageConstants.DEFAULT_SORT_DIRECTION, required = false) String sortDir
			) {
		MmsPageParam pageParam = new MmsPageParam(pageNo,pageSize,sortBy,sortDir);
		
		
		return mmsUserService.getAllMmsUsersPagedByNameOrEmail(userFirstname,userLastname,userEmail,pageParam);

	}
	
	@GetMapping("mmsuser-list")
	public List<MmsUserDTO> allUsers() {
		return mmsUserService.getUsers();

	}

	@DeleteMapping("delete-user/{user_id}")
	public boolean deleteUser(@PathVariable("user_id") int user_id, MmsUser mmsUser) {
		mmsUser.setUserId(user_id);
		return mmsUserService.deleteUser(mmsUser);
	}

	@GetMapping("user/{user_id}")
	public MmsUser allUserByID(@PathVariable("user_id") int user_id, MmsUser mmsUser) {
		mmsUser.setUserId(user_id);
		return mmsUserService.getUserByID(mmsUser);

	}

	@PostMapping("update-user/{user_id}")
	public boolean updateUser(@RequestBody MmsUser user, @PathVariable("user_id") int user_id) {
		user.setUserId(user_id);
		return mmsUserService.updateUser(user);
	}
}
