package com.mmsystem.property.service;

import java.util.List;  
import org.springframework.beans.factory.annotation.Autowired;  
import org.springframework.stereotype.Service;  
import org.springframework.transaction.annotation.Transactional;

import com.mmsystem.property.model.MmsToken;

import com.mmsystem.property.repo.TokenRepository;



@Service  
public class AuthenticationService  {  
 
  @Autowired  
  private TokenService mmsTokenService; 
  
  @Autowired  
  private MmsUserService mmsUsersService; 
    
  
  public String loginToUUDI(String email, String password) {
	  
	  
	  if (mmsUsersService.isValidUserCrediential(email, password)) {
		  
		  int userId = mmsUsersService.getByEmail(email).getUserId();
		  String authToken = mmsTokenService.generateTokenWithUUID(userId).getAuthToken();
		  
		  return authToken;
	  }
	  
	  return null;
	  	  
  }
 

} 
