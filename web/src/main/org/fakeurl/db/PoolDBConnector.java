package main.org.fakeurl.db;

import snaq.db.ConnectionPool;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;


public class PoolDBConnector {
	private final static PoolDBConnector instance = new PoolDBConnector();
	
	static final String JDBC_DRIVER = "org.postgresql.Driver";
	static final String DB_URL = "jdbc:postgresql://ec2-54-213-58-1.us-west-2.compute.amazonaws.com:5432/";
	
	private ConnectionPool pool;
	
	private PoolDBConnector(){
		try {
			Class<?> c = Class.forName(JDBC_DRIVER);
			Driver driver = (Driver)c.newInstance();
			DriverManager.registerDriver(driver);

			pool = new ConnectionPool("pool", //<poolname>
                    5,//<minpool>
                    10,//<maxpool>
                    30,//<maxsize>
                    10000,//<idleTimeout>
                    DB_URL + "gymadviser_db",//<url>
                    "postgres",//<username>
                    "samsung1");//<password>
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static PoolDBConnector getInstance(){
		return instance;
	}
	
	public Connection getConnection() throws SQLException{
		return pool.getConnection();
	}
	
}
