package com.mmsystem.property.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity  
@Table(name="mmspropertymanagements")
public class MmsPropertyManagement {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY) 
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
