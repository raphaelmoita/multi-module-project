package org.moita.sbe.aspect;

import java.util.Arrays;
import java.util.Optional;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.moita.sbe.model.Item;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Profile("Test")
public class ItemAspect {
	
	private final static String SOME_PACKAGES_ALL_CLASSES = "execution(* com.abc.xyz..controller..*(..)) || "
														  + "execution(* com.abc.xyz..service..*(..)) || "
														  + "execution(* com.abc.xyz..dao..*(..))";

	private final static String ALL_PACKAGES_ALL_CLASSES = "execution(* org.moita.sbe..*(..))";
	
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

	@Around(ALL_PACKAGES_ALL_CLASSES)
	public Object timeElapsed(ProceedingJoinPoint joinPoint) throws Throwable {
		long start = System.currentTimeMillis();
		Object proceed = joinPoint.proceed();
		long executionTime = System.currentTimeMillis() - start;
		System.out.println("[executed in " + executionTime + "ms] " + joinPoint.getSignature());
		return proceed;
	}
	
	@Around("execution(* org.moita.sbe.persistence.ItemDAO.add(..))")
	public Object enrich(ProceedingJoinPoint joinPoint) throws Throwable {
		Object proceed = joinPoint.proceed(this.enrichItemDescription(joinPoint.getArgs(), 0));
		return proceed;
	}
	
	private Object[] enrichItemDescription(Object[] obj, int argPosition) {
		Item item = Optional.ofNullable(obj)
							.filter(o -> o[argPosition] instanceof Item)
							.map(o -> (Item) o[argPosition])
							.orElseThrow(() -> new IllegalStateException("Invalid input argument."));
		item.setDescription("Enriched::" + item.getDescription());
		return new Object[] {item};
	}
}
