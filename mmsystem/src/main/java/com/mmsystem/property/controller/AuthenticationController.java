package com.mmsystem.property.controller;

import java.util.Date;

import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mmsystem.property.dto.AuthInformationDTO;
import com.mmsystem.property.dto.MmsUserDTO;
import com.mmsystem.property.mapper.MmsUserMapper;
import com.mmsystem.property.model.MmsUser;
import com.mmsystem.property.security.SecurityConstants;
import com.mmsystem.property.security.TokenUtils;
import com.mmsystem.property.service.MmsUserService;

import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

/**
 * In this the API, jwt bearer token will be generated based on the 
 * username/email and security key, and a response will be composed as
 *  AuthInformationDTO which has user information and token and token expiration date. 
 * 
 * */
@Slf4j
@RestController
@RequestMapping(path = "api/auth")
public class AuthenticationController {
	
	
	@Autowired
	private MmsUserService mmsUserService;
	
	//private final Log logger = LogFactory.getLog(getClass());

	    @GetMapping(value = "/signin")
	    public ResponseEntity<AuthInformationDTO> signIn(HttpServletResponse response)  {

	        try {

	            String uname = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	    
	            String token = TokenUtils.generateJWTUserToken(uname);  
	            log.info("Generated JWT Token: " + token);
	            log.warn("Generated JWT Token for: " + uname);

	            response.addHeader(SecurityConstants.AUTH_HEADER, SecurityConstants.BEARER_TOKEN_PREFIX + token);
	            
	            MmsUser userFromDB = mmsUserService.getByEmail(uname);
	            MmsUserDTO mmsUserDTO = MmsUserMapper.INSTANCE.mapToUserDto(userFromDB);
	            
	            Date tokenExpiration = TokenUtils.getJWTTokenExpiration(token);
	            
	            AuthInformationDTO responseBody = mmsUserService.createAuthInfoDTO(token,tokenExpiration, mmsUserDTO);
	            
	            return new ResponseEntity<>(responseBody, HttpStatus.OK);    

	        } catch (Exception ex) {
	            log.info("Exception error: " + ex.getMessage()); 
	            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
	        }
	    }


}
