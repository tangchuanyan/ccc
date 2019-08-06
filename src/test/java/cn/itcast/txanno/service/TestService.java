package cn.itcast.txanno.service;

import cn.itcast.txanno.service.OrdersService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestService {

	@Test
	public void testDemo() {
		ApplicationContext context = 
				new ClassPathXmlApplicationContext("bean4.xml");
		OrdersService service = (OrdersService) context.getBean("ordersService");
		service.accountMoney();
	}
}
