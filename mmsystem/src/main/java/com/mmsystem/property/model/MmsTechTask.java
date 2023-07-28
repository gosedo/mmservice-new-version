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
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity  
@Table(name="mmstechtasks")
public class MmsTechTask {
		
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY) 
	private int techTaskId;
	private String taskDescr;
	
	@ManyToOne(cascade = { CascadeType.DETACH, CascadeType.REFRESH }, fetch = FetchType.EAGER)
    @JoinColumn(name = "taskIssueId" , nullable=false)
	private MmsMaintenanceIssue issueTaskFor;
	
	@ManyToOne(cascade = { CascadeType.DETACH, CascadeType.REFRESH }, fetch = FetchType.EAGER)
	@JoinColumn(name = "taskStatusId" , nullable=false) 
	private MmsTechTaskStatus taskStatus;
	  
	private LocalDateTime createdOnDate;
	private LocalDateTime closedOnDate; 
	
	@ManyToOne(cascade = { CascadeType.DETACH, CascadeType.REFRESH }, fetch = FetchType.EAGER)
	@JoinColumn(name = "techTeamId" , nullable=false) 
	private MmsTechTeam teamAssigned;
	 
	@ManyToOne(cascade = { CascadeType.DETACH, CascadeType.REFRESH }, fetch = FetchType.EAGER)
	@JoinColumn(name = "createdById" , nullable=false) 
	private MmsUser taskCreatedBy;
	  
	@ManyToOne(cascade = { CascadeType.DETACH, CascadeType.REFRESH }, fetch = FetchType.EAGER)
	@JoinColumn(name = "taskUpdatedById" , nullable=true)
	private MmsUser taskUpdatedBy;
	 
	 
	/*
	 * public TechTeam getTeamAssigned() { return teamAssigned; }
	 * 
	 * public void setTeamAssigned(TechTeam teamAssigned) { this.teamAssigned =
	 * teamAssigned; }
	 * 
	 * public User getTaskCreatedBy() { return taskCreatedBy; }
	 * 
	 * public void setTaskCreatedBy(User taskCreatedBy) { this.taskCreatedBy =
	 * taskCreatedBy; }
	 * 
	 * public User getTaskCloseddBy() { return taskCloseddBy; }
	 * 
	 * public void setTaskCloseddBy(User taskCloseddBy) { this.taskCloseddBy =
	 * taskCloseddBy; }
	 */
	
	
	
}
