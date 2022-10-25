package com.javaproject.admin.paging;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface PagingParam {
	int id() default 0;
	
	String path() default "";
	
	int page() default 1;
	
	int pageSize() default 8;
	
	String orderBy() default "id";
	
	String orderType() default "desc";
}
