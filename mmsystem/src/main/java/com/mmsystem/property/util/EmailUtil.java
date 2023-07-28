package com.mmsystem.property.util;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

/**
 * Uses javaMailSender to send email when email detail provided.
 * */

@Service
@Slf4j 
public class EmailUtil {
	 
	@Autowired
	private JavaMailSender javaMailSender;
	 
    
 
    
    public  String sendSimpleMail(EmailDetails details)
    {
 
        try {
 
        	SimpleMailMessage msg = new SimpleMailMessage();
        	msg.setFrom("ethioteste@gmail.com");
            msg.setTo(details.getRecipient());
            msg.setSubject(details.getSubject());
            msg.setText(details.getMsgBody());
            javaMailSender.send(msg);
            
            return "Mail Sent Successfully...";
        }
 
        
        catch (Exception e) {
        	log.error(e.getMessage());
            return "Error while Sending Mail";
        }
    }

}
