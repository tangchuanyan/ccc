package cn.itcast.tx.service;

import cn.itcast.tx.dao.OrdersDao;

public class OrdersService {

	private OrdersDao ordersDao;
	public void setOrdersDao(OrdersDao ordersDao) {
		this.ordersDao = ordersDao;
	}
	
	//调用dao的方法
	//业务逻辑层，写转账业务
	public void accountMoney() {
		//小王少1000
		ordersDao.lessMoney();
		
//		//出现异常
//		int i = 10/0;
		
		//小马多1000
		ordersDao.moreMoney();
	}
}
