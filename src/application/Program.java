package application;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import db.DB;

public class Program {

	public static void main(String[] args) {

		Connection conn = null; //instanciar a conex�o atribui a vari�vel
		Statement st = null; // instancia uma classe para fazer uma query e atribui a vari�vel
		ResultSet rs = null;// instancia uma classe para o resultado da consulta e atribui a vari�vel
		// ResultSet recebe uma tabela e possui as opera��es fist(), beforeFirst(), nex() e absolute(int)
		
		try {
			conn = DB.getConnection(); //conecta ao BD
			st = conn.createStatement(); // a partir da conex�o, cria o Statement
			rs = st.executeQuery("SELECT * FROM seller");
			
			while (rs.next()) { // retorna falso se n�o houver dados na linha
				System.out.println(rs.getInt("ID") + ", " + rs.getString("Name"));
			} //rs.type(columm name)
				
			}
		catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
			DB.getConnection();
			
			
		}

	}

}
