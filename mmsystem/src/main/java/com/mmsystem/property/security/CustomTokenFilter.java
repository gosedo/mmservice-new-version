package com.mmsystem.property.security;

import java.io.IOException;
import java.util.Base64;


import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.log.LogMessage;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import  org.springframework.security.core.Authentication;

/**
 * This custom filter when added to the filter list it will be responsible for security
 * related actions.
 * It handles basic and bearer token authentication. If basic authentication it extract user information base64
 * token and do authentication. If bearer token it checks the validity of the token. And then set the security context 
 * with authenticate. The security context will be used for the authorization of access to the resources.
 * */

@Component
public class CustomTokenFilter extends OncePerRequestFilter {
	 
	  public static final String BASIC_TOKEN_PREFIX =  "Basic ";
	  public static final String AUTH_HEADER = "Authorization";  
	  private final Log logger = LogFactory.getLog(getClass());
	  
	  //@Autowired
	  private AuthenticationManager authManager;
	  
	  @Autowired
	  CustomUserDetailsService userService;

	  public CustomTokenFilter(AuthenticationManager authenticationManager) {
	    
	    this.authManager = authenticationManager;
	  }


	  @Override
	  protected void doFilterInternal(HttpServletRequest request,
	                                  HttpServletResponse response,
	                                  FilterChain filterChain) throws ServletException, IOException {

		  String uname ="";
		  String upassw ="";
		  UsernamePasswordAuthenticationToken authentication;
		  
	      String uri = request.getRequestURI();
	      logger.info("Request URI: " + uri);

	      try {
	          String headerToken = "";
	          headerToken = request.getHeader(AUTH_HEADER);
	          
	          if (headerToken == null 
	        		  || (!headerToken.startsWith(SecurityConstants.BASIC_TOKEN_PREFIX) 
	        		  		&& !headerToken.startsWith(SecurityConstants.BEARER_TOKEN_PREFIX))) {
	            filterChain.doFilter(request, response);
	            return;
	          }

	          if (headerToken.startsWith(SecurityConstants.BASIC_TOKEN_PREFIX) && uri.endsWith(SecurityConstants.SIGN_IN_URI_ENDING)) {
		          
	        	  headerToken = StringUtils.delete(headerToken, BASIC_TOKEN_PREFIX).trim();
		          uname = TokenUtils.decodedBase64(headerToken)[0];
		          upassw = TokenUtils.decodedBase64(headerToken)[1];
	
		          
		          authentication = new UsernamePasswordAuthenticationToken(uname, upassw); // getAuthentication(request);
		          Authentication authResult = this.authManager.authenticate(authentication);
		          SecurityContextHolder.getContext().setAuthentication(authResult);
		          
	          }else if ( headerToken.startsWith(SecurityConstants.BEARER_TOKEN_PREFIX) 
	        		  && !uri.endsWith(SecurityConstants.SIGN_IN_URI_ENDING)) {
	        	  
	        	  headerToken = StringUtils.delete(headerToken, SecurityConstants.BEARER_TOKEN_PREFIX).trim();
	        	  if (TokenUtils.isJWTTokenValid(headerToken)) {
	                  uname = TokenUtils.getUsernameFromJWTUserToken(headerToken);
	                  
	                  
	                  //User is an org.springframework.security.core.userdetails.User object
	                  User user = (User) userService.loadUserByUsername(uname);
	                  if (user != null) {
	                      authentication = new UsernamePasswordAuthenticationToken(uname, null, user.getAuthorities());
	                      SecurityContextHolder.getContext().setAuthentication(authentication);

	                      logger.info("(Authorized) Authentication: " + authentication.toString());

	                    }
	        	  }
	          
	          }
	          
		          
	        } catch (Exception ex) {
	            this.logger.info("Failed to process authentication request: " + ex.getMessage());
	        }

	      filterChain.doFilter(request, response);

	  }

	  

	}