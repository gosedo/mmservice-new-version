package com.mmsystem.property.model;

import java.time.LocalDateTime;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity  
@Table(name="mmsposts")
public class MmsPost {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY) 
	private int postId;
	
	@ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "issueId" , nullable=false)
    private MmsMaintenanceIssue postToIssue;
	
	private String comment;
	private LocalDateTime createdOn;
	
	@ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "postUserId" , nullable=false)
    private MmsUser postedBy;

	
}
