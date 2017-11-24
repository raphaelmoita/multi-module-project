package org.moita.sbe.aspect;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.moita.sbe.model.Item;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ItemAspect {

	@Before("execution(* org.moita.sbe.persistence.ItemDAO.add(..))")
	public void addTo(JoinPoint joinPoint) {
		System.out.println(Arrays.toString(joinPoint.getArgs()));
		System.out.println("Service method add called");
	}
	
	@AfterReturning(pointcut = "execution(* org.moita.sbe.persistence.ItemDAO.list())", returning = "result")
	public void returnedFrom(JoinPoint joinPoint, Object result) {
		System.out.println("hijacked : " + joinPoint.getSignature().getName());
		System.out.println("Method returned value is : " + result);
	}

	@Around("execution(* org.moita.sbe.persistence..*(..))")
	public Object timeElapsed(ProceedingJoinPoint joinPoint) throws Throwable {
		long start = System.currentTimeMillis();
		Object proceed = joinPoint.proceed();
		long executionTime = System.currentTimeMillis() - start;
		System.out.println(joinPoint.getSignature() + " executed in " + executionTime + "ms");
		return proceed;
	}
	
	@Around("execution(* org.moita.sbe.persistence.ItemDAO.add(..))")
	public Object enrich(ProceedingJoinPoint joinPoint) throws Throwable {
		Object proceed = joinPoint.proceed(this.enrichItem(joinPoint.getArgs()));
		return proceed;
	}
	
	private Object[] enrichItem(Object[] obj) {
		if (obj[0] instanceof Item) {
			Item i = (Item) obj[0];
			i.setDescription("Enriched::" + i.getDescription());
			obj[0] = i;
		}
		return obj;
	}
}
