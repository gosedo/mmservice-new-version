package com.mmsystem.property.model;

import java.time.LocalDateTime;


import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
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
@Table(name="mmsmaintenanceIssues")
public class MmsMaintenanceIssue{
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY) 
	private int issueId;
	
	@ManyToOne(cascade = { CascadeType.DETACH, CascadeType.REFRESH }, fetch = FetchType.EAGER)
    @JoinColumn(name = "issueTypeId" , nullable=false)
    private MmsIssueType issueType;
	
	private String issueDescription;
	
	@ManyToOne(cascade = { CascadeType.DETACH, CascadeType.REFRESH }, fetch = FetchType.EAGER)
    @JoinColumn(name = "issueStatusId" , nullable=false)
	private MmsIssueStatus issueStatus;
	
	@ManyToOne(cascade = { CascadeType.DETACH, CascadeType.REFRESH }, fetch = FetchType.EAGER)
    @JoinColumn(name = "issueReqTenantId" , nullable=false)
	private MmsTenant requestedBy;
	
	
	//@OneToMany(cascade = CascadeType.ALL, mappedBy = "postToIssue") 
	//private List<Post> posts = new ArrayList<>();
	
	
	private LocalDateTime createdOnDate;
	private LocalDateTime completedOnDate;
	
	
			
	//public List<Post> getPosts() { return posts; } public void
	//setPosts(List<Post> posts) { this.posts = posts; }
	 
		
}
