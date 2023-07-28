package com.mmsystem.property.util;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * It is just acting as dto but for just parameters.
 * Rather  than sending multiple parameter, putting them in one object and pass them.
 * 
 * */


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MmsPageParam {
	private int pageNo;
	private int pageSize;
	private String sortBy;
	private String sortDir;

}
