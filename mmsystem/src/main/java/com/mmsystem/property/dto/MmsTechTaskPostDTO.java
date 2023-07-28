package com.mmsystem.property.dto;

import java.time.LocalDateTime;

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
public class MmsTechTaskPostDTO {
	
	private int taskPostId;
	private MmsTechTaskDTO postToTask;
	private String comment;
	private LocalDateTime createdOn;
	private MmsUserDTO postedBy;

}
