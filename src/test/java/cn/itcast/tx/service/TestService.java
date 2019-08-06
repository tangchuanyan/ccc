package cn.itcast.tx.service;

import cn.itcast.tx.service.OrdersService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestService {

	@Test
	public void testDemo() {
		ApplicationContext context = 
				new ClassPathXmlApplicationContext("bean3.xml");
		OrdersService service = (OrdersService) context.getBean("ordersService");
		service.accountMoney();
	}
}
