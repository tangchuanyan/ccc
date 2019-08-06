package cn.itcast.aop;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

@Aspect
public class MyBook {

	//在方法上面使用注解完成增强配置
	@Before(value="execution(* cn.itcast.aop.Book.*(..))")//也可以后置增强@After或者环绕增强@Around
	public void before1() {
		System.out.println("before..............");
	}
}
