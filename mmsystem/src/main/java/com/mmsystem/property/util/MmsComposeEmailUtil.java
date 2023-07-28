package com.mmsystem.property.util;

/**
 * Used to compose different email content. E.g New Account Registration
 * Updated Account notification.
 * 
 * */

public class MmsComposeEmailUtil {
	
	public static EmailDetails composerNewAccountEmail( String email, String verificationId) {
		
		String emailBody = String.format("Dear, Mmservice account has been create for you. "
				+ "Plsease use the following link to verify and change your password."
				+ "http://localhost:4200/newuser/activation?id=%s", verificationId);
		
		
		EmailDetails emailDetail = new EmailDetails();
		emailDetail.setRecipient(email);
		emailDetail.setSubject("New Mmsservice Account");
		emailDetail.setMsgBody(emailBody);
		
		
		return emailDetail;
	}
	
	public static EmailDetails composerUpdatedAccountEmail( String email, String verificationId) {
		
		String emailBody = String.format("Dear, Your Mmservice account has been updated for you. "
				+ "Plsease use the following link to change your password."
				+ "http://localhost:4200/newuser/activation?id=%s", verificationId);
		
		
		EmailDetails emailDetail = new EmailDetails();
		emailDetail.setRecipient(email);
		emailDetail.setSubject("Mmsservice Account updated");
		emailDetail.setMsgBody(emailBody);
		
		
		return emailDetail;
	}

}
