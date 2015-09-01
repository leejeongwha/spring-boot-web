package demo.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class TestAspect {
	final static Logger logger = LoggerFactory.getLogger(TestAspect.class);

	@Pointcut("execution(* demo.bo..*.*(..))")
	public void pointcut() {

	}

	@Before("pointcut()")
	public void before(JoinPoint joinPoint) throws Exception {
		logger.info("AopAspect:before");
		System.out.println("AopAspect:before");
	}

	@After("pointcut()")
	public void after(JoinPoint joinPoint) throws Exception {
		logger.info("AopAspect:after");
		System.out.println("AopAspect:after");
	}
}
