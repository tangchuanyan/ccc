package cn.itcast.txanno.dao;

import org.springframework.jdbc.core.JdbcTemplate;

public class OrdersDao {

	//注入jdbcTemplate
	private JdbcTemplate jdbcTemplate;
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	/*
	 * 做对数据库操作的方法，不写业务操作
	 * */
	//小王少钱的方法
	public void lessMoney() {
		String sql = "update account set salary=salary-? where username=?";
		jdbcTemplate.update(sql, 1000,"小王");
	}
	
	//小马多钱的方法
	public void moreMoney() {
		String sql = "update account set salary=salary+? where username=?";
		jdbcTemplate.update(sql, 1000,"小马");
	}
	
}
