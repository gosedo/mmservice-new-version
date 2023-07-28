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
@Table(name="mmsuserstatus") 
public class MmsUserStatus {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY) 
	private int userStatusId;
	private String userStatusCode;
	private String userStatusDescr;
		
}
