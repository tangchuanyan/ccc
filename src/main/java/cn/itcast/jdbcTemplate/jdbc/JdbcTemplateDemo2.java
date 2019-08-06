package cn.itcast.jdbcTemplate.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class JdbcTemplateDemo2 {
	
	/*
	 * QueryRunner runner = new QueryRuner(datasource);
	 * 返回对象
	 * runner.query(sql,new BeanHandler<User>(User.class));
	 * 
	 * 返回list集合
	 * runner.query(sql,new BeanListHander<User>(User.class))
	 * 
	 * 1 在dbutils时候，有接口 ResultSetHandler
	 * dbutils提供了针对不同的结果实现类
	 * 
	 * 2 jdbcTemplate实现查询，有接口 RowMapper，
	 * jdbcTemplate针对这个接口没有提供实现类，得到不同的类型数据需要自己进行数据封装
	 * 
	 * */
	

	
	//2 jdbc实现代码
	@Test
	public void testJDBC() {
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		//加载驱动
		try {
			Class.forName("com.mysql.jdbc.Driver");
			//创建连接
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "tcy03181030");
			//编写sql语句
			String sql = "select * from user where username=?";
			//预编译sql
			psmt = conn.prepareStatement(sql);
			//设置参数值
			psmt.setString(1, "lucy");
			//执行sql
			rs = psmt.executeQuery();
			//遍历结果集
			while(rs.next()) {
				//得到返回结果值
				String username = rs.getString("username");
				String password = rs.getString("password");
				//放到user对象里面
				User user = new User();
				user.setUsername(username);
				user.setPassword(password);
				
				System.out.println(user);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				psmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	//1 查询表有多少条记录
	@Test
	public void testCount() {
		//设置数据库信息
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql://localhost:3306/test");
		dataSource.setUsername("root");
		dataSource.setPassword("tcy03181030");
		
		//创建jdbcTemplate对象
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		
		//调用方法得到记录数
		String sql = "select count(*) from user";
		//调用jdbcTemplate的方法
		int count = jdbcTemplate.queryForObject(sql, Integer.class);
		System.out.println(count);
	}
	
	//3 查询返回对象
	@Test
	public void testObject() {
		//设置数据库信息
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql://localhost:3306/test");
		dataSource.setUsername("root");
		dataSource.setPassword("tcy03181030");
		
		//创建jdbcTemplate对象
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		
		//写sql语句，根据username查询
		String sql = "select * from user where username=?";
		//调用jdbcTemplate的方法实现
		//第二个参数是接口 RowMapper，需要自己写类实现接口，自己做数据封装
		User user = jdbcTemplate.queryForObject(sql, new MyRowMapper(), "mary");
		System.out.println(user);
	}
	
	//4 查询返回对象
	@Test
	public void testList() {
		
//		ComboPooledDataSource dataSource = new ComboPooledDataSource();
//		dataSource.setDriverClass("com.mysql.jdbc.Driver");
//		dataSource.setJdbcUrl("jdbc:mysql:///spring_day03");
//		dataSource.setUser("root");
//		dataSource.setPassword("root");
		
		//设置数据库信息
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql://localhost:3306/test");
		dataSource.setUsername("root");
		dataSource.setPassword("tcy03181030");
		
		//创建jdbcTemplate对象
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		
		//写sql语句
		String sql = "select * from user";
		//调用jdbcTemplate的方法实现
		List<User> list = jdbcTemplate.query(sql,new MyRowMapper());
		
		System.out.println(list);
		
	}
}


class MyRowMapper implements RowMapper<User> {

	@Override  //报错,必须jdk1.5以上，file-project structure-language level选择8-lambdas
	//file-setting-java compiler-target bytecode version-1.8
	public User mapRow(ResultSet rs, int num) throws SQLException {
		// 1 从结果集里面把数据得到
		String username = rs.getString("username");
		String password = rs.getString("password");
		
		// 2 把得到数据封装到对象里面
		User user = new User();
		user.setUsername(username);
		user.setPassword(password);
		
		return user;
	}
	
}






