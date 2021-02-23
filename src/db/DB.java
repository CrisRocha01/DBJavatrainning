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
	private static Connection conn = null; //instancia uma variável para conexão
	public static Connection getConnection() { //método para conectar
		if(conn == null) {
			try {
				Properties pros = loadProperties(); // instancia variável para carregar os dados de conexão;
				String url = pros.getProperty("dburl"); //carrega os dados de conecção
				conn = DriverManager.getConnection(url, pros); // efetua a conexão
			}
			catch (SQLException e) {
				throw new DbException(e.getMessage());
			}
		}
		return conn;
	}
	
	 
	
	
	private static Properties loadProperties() { //metodo para carregar os dados da conexão
		try (FileInputStream fs = new FileInputStream("db.propeties")) { //lê o arquivo
			Properties pros = new Properties(); //instancia um objeto Properties
			pros.load(fs); // carrega os dados da conexão no objeto
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
