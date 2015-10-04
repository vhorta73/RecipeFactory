package core;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import constants.Global;

public class ConnectDB {
	private static final String DB_URL = "jdbc:mysql://localhost/";
	private static Connection mySql;

	public Connection getConnection(String database) {
		if ( mySql == null ) {
			try {
				Class.forName(Global.JDBC_DRIVER);
				// TODO: err.. what is that?
				mySql = DriverManager.getConnection(DB_URL + database,"root","123456");
			} catch (ClassNotFoundException | SQLException e1) {
				e1.printStackTrace();
			}
		}
		return mySql;
	}
}
