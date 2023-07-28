package com.mmsystem.property.security;

import org.apache.juli.logging.LogFactory;
import org.apache.juli.logging.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Component;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * This bean presents itself as provider so that the authentication manager will use it 
 * as authentication provider. As AuthManger provided by spring boot which will be used by 
 * the custom filter will go through possible candidates of auth provide  which this is one of them.
 * authenticate method uses loadUserByUsername()of user detail service to get user password
 * and matches with the incoming one, if equal, returns Authentication object
 * 
 * */

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

    private final Log logger = LogFactory.getLog(getClass());

    
    @Autowired
    CustomUserDetailsService userService;
    
    @Autowired
	private BCryptPasswordEncoder passwordEncoder;

    
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        UsernamePasswordAuthenticationToken authToken = null;

        if (authentication == null) {
            return null;
        }
        String uname = String.valueOf(authentication.getName());
        String upassw = String.valueOf(authentication.getCredentials());

        logger.info("Username: " + uname + " Password: " + upassw);

        User user = (User) userService.loadUserByUsername(uname);

        if (user == null) return null; 
        
    
        //if (user.getPassword().equals(upassw)) { replaced this 
        if (passwordEncoder.matches(upassw,user.getPassword())) {
            authToken = new UsernamePasswordAuthenticationToken(user.getUsername(), null, user.getAuthorities());
        }    
        return authToken;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
    
}