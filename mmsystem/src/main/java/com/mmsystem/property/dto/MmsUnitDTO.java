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
public class MmsUnitDTO {
	
	 
	private int unitId;
	private String unitCode;
	private String unitType;
	private String unitName;
	private String unitAddrLine1;
	private String unitAddrLine2;
	private String unitAddrLine3;
	private String unitCity;
	private String unitState;
	private String unitZipcode5;
	private String unitZipcodeExt;
	private MmsPropertyDTO unitBelongToProp ;
	
	

		
}
