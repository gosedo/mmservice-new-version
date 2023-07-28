package com.mmsystem.property.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;  
import org.springframework.transaction.annotation.Transactional;

import com.mmsystem.property.dto.MmsTokenDTO;
import com.mmsystem.property.mapper.MmsPropertyManagementMapper;
import com.mmsystem.property.mapper.MmsTokenMapper;
import com.mmsystem.property.model.MmsPropertyManagement;
import com.mmsystem.property.model.MmsToken;
import com.mmsystem.property.model.MmsUser;

import com.mmsystem.property.repo.TokenRepository;


@Service  
@Transactional  
public class TokenService  {  
 
  @Autowired  
  private TokenRepository mmsTokenRepo; 
  
  @Autowired
  private MmsUserService mmsUserService;
   
  DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"); 
    
  public MmsTokenDTO saveToken(MmsTokenDTO mmsTokenDto) { 
	  
	  MmsToken mmsToken = MmsTokenMapper.INSTANCE.mapToMmsToken(mmsTokenDto);
	  mmsTokenRepo.save(mmsToken);
		
	  return MmsTokenMapper.INSTANCE.mapToMmsTokenDto(mmsToken); 
  }  

   
  public List<MmsToken> getTokens() {  
      return mmsTokenRepo.get();  
  }  

   
  public boolean deleteToken(MmsToken mmsToken) {  
      return mmsTokenRepo.delete(mmsToken);  
  }  

    
  public MmsToken getTokenByID(MmsToken mmsToken) {  
      return mmsTokenRepo.getByID(mmsToken);  
  }  

    
  public boolean updateToken(MmsToken mmsToken) {  
      return mmsTokenRepo.update(mmsToken);  
  }  
  
  public MmsToken generateTokenWithUUID(int userId)
  {
      String token = UUID.randomUUID().toString();
      LocalDateTime issuedOn = LocalDateTime.now();
      LocalDateTime expiresOn = LocalDateTime.now().plusMinutes(60);
      
      MmsUser user = mmsUserService.getUserByID(userId);

      var tokenModel = new MmsToken( user,token,issuedOn,expiresOn);
  			
      mmsTokenRepo.save(tokenModel);
      return tokenModel;
      
  }
  
  public boolean validateAuthTokenUUDI(String authToken)
  {
      var token = mmsTokenRepo.getByAuthTokenStr(authToken);
      
      if (token != null && !(LocalDateTime.now().isAfter(token.getTokenExpiresOn())))
      {
          token.setTokenExpiresOn(token.getTokenExpiresOn().plusMinutes(60));
          
          mmsTokenRepo.update(token);

          return true;
      }
      return false;
  }
  
  public Optional<User> getUserByTokenUUDI(String token) {
	  
	  Optional<MmsUser> user = Optional.of(mmsTokenRepo.getByAuthTokenStr(token).getTokenIssuedTo());
	  
	  
	  if(user.isPresent()) {
		  MmsUser userFromOption = user.get();
		  User detailuser = new User(userFromOption.getUserEmail()
									,userFromOption.getUserPassword()
									, true, true, true, true
									, AuthorityUtils.createAuthorityList("USER")) ;
		  return Optional.of(detailuser);
		  
	  }
		  
	  
	  return Optional.empty();
  }

} 
