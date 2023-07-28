package com.mmsystem.property.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *Used to take information necessary to update maintenance issue request 
 * 
 * */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MmsIssueUpdateDTO {
	private int issueId;
	private int issueTypeId;
	private int issueStatusId;
}


