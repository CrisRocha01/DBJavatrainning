package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import db.DB;
import db.DbException;

public class ProgramRecoveUpdaterDepartment {

	public static void main(String[] args) {

		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		PreparedStatement ps = null;
		
		System.out.println("\n++++++++++++ data recover test ++++++++++++++++++++\n");
		
		try {
			conn = DB.getConnection();
			st = conn.createStatement();
			rs = st.executeQuery("SELECT * FROM department");
		
		while(rs.next()) {
			System.out.println(rs.getInt("ID") + ", "  + rs.getString("Name"));
		}
		
		System.out.println("\n++++++++++++ data insert test ++++++++++++++++++++\n");
		
		/*
		ps = conn.prepareStatement("INSERT INTO department "
				+ "(Name) "
				+ "VALUES "				
				+ "(?)",
				Statement.RETURN_GENERATED_KEYS);
		
		ps.setString(1, "Games");
		*/
		
		ps = conn.prepareStatement("INSERT INTO department "
				+ "(Name) "
				+ "VALUES "
				+ "('Tools'), ('Toys')",
				Statement.RETURN_GENERATED_KEYS);
		
		
		int rows = ps.executeUpdate();
		
		if(rows > 0) {
			ResultSet rs2 = ps.getGeneratedKeys();
			while(rs2.next()) {
				System.out.println("Done! new ID = " + rs2.getInt(1));
				
			}
			System.out.println("rows affected: " + rows);
		}
		else {
			System.out.println("no rows affected!");
		}
		
		
		}
		catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
			DB.closeStatement(ps);
			DB.closeResultSet(rs);
			DB.getConnection();
		}
	}

}
