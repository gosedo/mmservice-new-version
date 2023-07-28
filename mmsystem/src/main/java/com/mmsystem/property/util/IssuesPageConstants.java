package com.mmsystem.property.util;

import java.time.LocalDateTime;

/**
 * When issues paged request sent, if values are not provided, these values will be the default.
 * */

public class IssuesPageConstants {
	
//	public static final String DEFAULT_START_DATE = LocalDateTime.now().minusDays(15).toString();
//    public static final String DEFAULT_END_DATE = LocalDateTime.now().toString();
 	public static final String DEFAULT_PAGE_NUMBER = "0";
    public static final String DEFAULT_PAGE_SIZE = "10";
    public static final String DEFAULT_SORT_BY = "createdOnDate";
    public static final String DEFAULT_SORT_DIRECTION = "desc";

}
