package com.mmsystem.property.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
@Table(name="mmsunits")
public class MmsUnit {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY) 
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
	
	@ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "belongToPropId" , nullable=false)
	private MmsProperty unitBelongToProp ;
	
	

		
}
