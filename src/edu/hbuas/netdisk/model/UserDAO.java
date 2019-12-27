package edu.hbuas.netdisk.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import edu.hbuas.netdisk.config.SocketConfig;

public class UserDAO {

	/**
	 * ����ע�����û������ݿ��������
	 * @param u
	 * @return
	 */
	public  boolean    registerUser(Users u) {
		boolean result=false;
		try {
			Class.forName(SocketConfig.jdbcDriverClass);
			Connection con=DriverManager.getConnection(SocketConfig.jdbcURL, SocketConfig.jdbcUsername,SocketConfig.jdbcPassword);
			PreparedStatement  pre=con.prepareStatement("insert into users values(?,?,?,?,?,?)");
			
			pre.setString(1, u.getUsername());
			pre.setString(2, u.getPassword());
			pre.setString(3, u.getSex());
			pre.setInt(4, u.getAge());
			pre.setString(5, u.getEmail());
			pre.setString(6, u.getTel());
			
			int count=pre.executeUpdate();
			result=count>0?true:false;
			pre.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	/**
	 * �����û����������ѯ�û�����ķ���
	 * @param username
	 * @param password
	 * @return
	 */
	public Users    login(String username,String password) {
		Users user=null;
		try {
			Class.forName(SocketConfig.jdbcDriverClass);
			Connection con=DriverManager.getConnection(SocketConfig.jdbcURL, SocketConfig.jdbcUsername,SocketConfig.jdbcPassword);
			PreparedStatement  pre=con.prepareStatement("select * from users where username=? and password=?");
			pre.setString(1, username);
			pre.setString(2, password);
			ResultSet rs=pre.executeQuery();
			
			if(rs.next()) {
				user=new Users();
				user.setAge(rs.getInt("age"));
				user.setUsername(rs.getString("username"));
				user.setPassword(rs.getString("password"));
				user.setSex(rs.getString("sex"));
				user.setEmail(rs.getString("email"));
				user.setTel(rs.getString("tel"));
			}
			rs.close();
			pre.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return user;
	}
}
