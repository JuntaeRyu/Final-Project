package com.spring.biz.common;

import org.aspectj.lang.annotation.Pointcut;

public class PointCutCommon {
	
	@Pointcut("execution(* com.spring.biz..*Impl.*(..))")
	public void aPointcut() {}
	
	@Pointcut("execution(* com.spring.biz..*Impl.select*(..))")
	public void bPointcut() {}
	
	@Pointcut("execution(* com.spring.biz..*Impl.selectAll(..))")
	public void cPointcut() {}
	
	@Pointcut("@annotation(org.springframework.web.bind.annotation.RequestMapping)")
	public void dPointcut() {}

}
