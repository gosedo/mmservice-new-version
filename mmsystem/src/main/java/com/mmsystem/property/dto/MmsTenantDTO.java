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
public class MmsTenantDTO {
	
    private int tenantId;
    private MmsUserDTO tenantInfo;
	private MmsUnitDTO tenantUnit;
	private boolean isCurrent;
	private LocalDateTime createdOnDate;
	private LocalDateTime statusModifiedDate;
	
}


