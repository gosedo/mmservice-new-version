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
public class MmsPropertyDTO {
	
	
	private int propertyId;
	private String proCode;
	private String proName;
	private String proAddrLine1;
	private String proAddrLine2;
	private String proAddrLine3;
	private String proCity;
	private String proState;
	private String proZipcode5;
	private String proZipcodeExt;
	private String proPhone;
	private String proEmail;
	private String proFax;
	private String contact;
	private MmsPropertyManagementDTO proManagement;

		
}
