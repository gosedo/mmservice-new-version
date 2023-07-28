package com.mmsystem.property.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
//@Builder
public class MmsPropertyManagementDTO {
	
	private int pMgmtId;
	private String pMgmtCode;
	private String pMgmtName;
	private String pMgmtAddrLine1;
	private String pMgmtAddrLine2;
	private String pMgmtAddrLine3;
	private String pMgmtCity;
	private String pMgmtState;
	private String pMgmtZipcode5;
	private String pMgmtZipcodeExt;
	private String pMgmtPhone;
	private String pMgmtEmail;
	private String pMgmtFax;
	private String pMgmtContact;
	
}







