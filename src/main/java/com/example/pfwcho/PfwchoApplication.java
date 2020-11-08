package com.example.pfwcho;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.*;

@SpringBootApplication
public class PfwchoApplication {

	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://Full2020-86381:3306/pfwcho?useSSL=false&serverTimezone=UTC";
	//static final String DB_URL = "jdbc:mysql://localhost:3306/pwcho?useSSL=false&serverTimezone=UTC";

	static final String USER = "ppaluch";
	static final String PASS = "ppaluch";

	public static void main(String[] args) {
		Connection conn = null;
		Statement stmt = null;
		try{
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			stmt = conn.createStatement();

			ResultSet resultSet = conn.getMetaData().getCatalogs();
			boolean exists = false;
			while (resultSet.next()) {
				String databaseName = resultSet.getString(1);
				if(databaseName.equals("pfwcho")) {
					exists = true;
				}
			}
			resultSet.close();
			if(!exists) {
				String sql = "CREATE DATABASE PFWCHO";
				stmt.execute(sql);
			}

			DatabaseMetaData dbm = conn.getMetaData();
			ResultSet tables = dbm.getTables(null, null, "user", null);
			if (tables.next()) {
			}
			else {
				String sql2 = "CREATE TABLE pfwcho.user (`id` int(11) NOT NULL AUTO_INCREMENT, `name` varchar(45) DEFAULT NULL, `surname` varchar(45) DEFAULT NULL, PRIMARY KEY (`id`)) ";
				stmt.execute(sql2);
			}

		}catch(SQLException se){
			se.printStackTrace();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{
				if(stmt!=null)
					stmt.close();
			}catch(SQLException se2){
			}
			try{
				if(conn!=null)
					conn.close();
			}catch(SQLException se){
				se.printStackTrace();
			}
		}

		SpringApplication.run(PfwchoApplication.class, args);

	}

}
