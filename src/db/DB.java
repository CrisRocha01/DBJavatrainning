package db;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class DB {
	
	// metodo que usa os dados da conex�o do BD e conecta
	private static Connection conn = null; //instancia uma vari�vel para conex�o do jdbc
	public static Connection getConnection() { //m�todo para conectar
		if(conn == null) {
			try {
				Properties pros = loadProperties(); // instancia vari�vel para carregar os dados de conex�o;
				String url = pros.getProperty("dburl"); //carrega os dados de conec��o
				conn = DriverManager.getConnection(url, pros); // efetua a conex�o
			}
			catch (SQLException e) {
				throw new DbException(e.getMessage());
			}
		}
		return conn;
	}
	
	 
	
	// metodo para baixar os dados de conex�o com o BD
	private static Properties loadProperties() { //metodo para carregar os dados da conex�o
		try (FileInputStream fs = new FileInputStream("db.propeties")) { //l� o arquivo
			Properties pros = new Properties(); //instancia um objeto Properties
			pros.load(fs); // carrega os dados da conex�o no objeto
			return pros;
		}
		catch (IOException e) {
			throw new DbException(e.getMessage());			
		}
	}
	
	public static void closeStatement(Statement st) {
		if (st != null) {
			try {
				st.close();
			} catch (SQLException e) {
				throw new DbException(e.getMessage());
			}
		}
	}
	
	public static void closeResultSet(ResultSet rs) {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				throw new DbException(e.getMessage());
			}
		}
	}
	
	public static void closeConnection(Connection conn) {
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				throw new DbException(e.getMessage());
			}
		}
	}
}
