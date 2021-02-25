package application;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import db.DB;

public class ProgramRecoveUpdaterSeller {

	public static void main(String[] args) {
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		Connection conn = null; //instanciar a conexão atribui a variável
		Statement st = null; // instancia uma classe para fazer uma query e atribui a variável
		ResultSet rs = null;// instancia uma classe para o resultado da consulta e atribui a variável
		// ResultSet recebe uma tabela e possui as operações fist(), beforeFirst(), nex() e absolute(int)
		PreparedStatement ps = null;// prepara para receber uma query. troca '?' (place holder)
		
		try {
			conn = DB.getConnection(); //conecta ao BD
			st = conn.createStatement(); // a partir da conexão, cria o Statement
			rs = st.executeQuery("SELECT * FROM seller");
			
			
			System.out.println("\n++++++++++++ data recover test ++++++++++++++++++++\n");
			
			while (rs.next()) { // retorna falso se não houver dados na linha
				System.out.println(rs.getInt("ID")
						+ ", "
						+ rs.getString("Name")
						+ ", "
						+ rs.getString("Email")
						+ ", "
						+ String.format("%.2f", rs.getDouble("BaseSalary")));
				
			} //rs.type(columm name)
			
			System.out.println("\n++++++++++++ data insert test ++++++++++++++++++++\n");
			
			
			ps = conn.prepareStatement("INSERT INTO SELLER "
					+ "(Name, Email, BirthDate, BaseSalary, DepartmentId) "
					+ "VALUES "
					+ "(?, ?, ? ,? , ?)",
					Statement.RETURN_GENERATED_KEYS); // retorno tbm as chaves geradas;
			
			ps.setString(1, "Cris");
			ps.setString(2, "cris@gmail.com");
			// date sql, instanciação para datas
			ps.setDate(3, new java.sql.Date(sdf.parse("17/10/2000").getTime()));
			ps.setDouble(4, 4000.0);
			ps.setInt(5, 3);
			
			
			
			int rows = ps.executeUpdate();// executa a query e retorna um int(nr de linhas afetadas)
			
			if(rows > 0) {
				ResultSet rs2 = ps.getGeneratedKeys();//instancia um ResultSet com as chaves criadas.
				while(rs2.next()) {
					int id = rs2.getInt(1); //atribui o valor da coluna "1" para a variável
					System.out.println("Done! new ID = " + id);
				}
			}
			else {
				System.out.println("no rows affected!");
			}
			
				
			}
		catch (SQLException | ParseException e) {
			e.printStackTrace();
		}
		finally {
			
			DB.closeStatement(ps);
			DB.closeStatement(st);
			DB.closeResultSet(rs);
			DB.getConnection();
			
			
		}

	}

}
