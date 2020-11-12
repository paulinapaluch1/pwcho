package com.example.pfwcho;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import java.sql.*;
@EnableAutoConfiguration
@SpringBootApplication
@ComponentScan

public class PfwchoApplication {

	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://Full2020-86381:3306/pfwcho?useSSL=false&serverTimezone=UTC";
	//static final String DB_URL = "jdbc:mysql://localhost:3306/pwcho?useSSL=false&serverTimezone=UTC";

	static final String USER = "ppaluch";
	static final String PASS = "ppaluch";

	public static void main(String[] args) {
		Connection connection = null;
		Statement statement = null;
		try{
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection(DB_URL, USER, PASS);
			statement = connection.createStatement();

			ResultSet resultSet = connection.getMetaData().getCatalogs();
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
				statement.execute(sql);
			}

			DatabaseMetaData dbm = connection.getMetaData();
			ResultSet tables = dbm.getTables(null, null, "user", null);
			if (tables.next()) {
			}
			else {
				String sql2 = "CREATE TABLE pfwcho.user (`id` int(11) NOT NULL AUTO_INCREMENT, `name` varchar(45) DEFAULT NULL, `surname` varchar(45) DEFAULT NULL, PRIMARY KEY (`id`)) ";
				statement.execute(sql2);
			}

		}catch(SQLException se){
			se.printStackTrace();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{
				if(statement!=null)
					statement.close();
			}catch(SQLException se2){
			}
			try{
				if(connection!=null)
					connection.close();
			}catch(SQLException se){
				se.printStackTrace();
			}
		}

		SpringApplication.run(PfwchoApplication.class, args);

	}

}
