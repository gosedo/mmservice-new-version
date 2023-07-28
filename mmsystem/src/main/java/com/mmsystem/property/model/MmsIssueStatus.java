package com.mmsystem.property.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity  
@Table(name="mmsissuestatus")
public class MmsIssueStatus {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY) 
	private int issueStatusId;
	private String issueStatusCode;
	private String issueStatusDescr;
	
}
