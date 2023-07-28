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
@Table(name="mmsproperties")
public class MmsProperty {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY) 
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
	
	@ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "proMgmtID" , nullable=false)
	private MmsPropertyManagement proManagement;

		
}
