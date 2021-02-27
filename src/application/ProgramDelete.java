package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import db.DB;
import db.DbException;

public class ProgramDelete {

	public static void main(String[] args) {

		Connection conn = null;
		PreparedStatement st = null;
		
		try {
			conn = DB.getConnection();
			st = conn.prepareStatement("DELETE FROM seller "
					+ "where Id = ?");
			
			st.setInt(1, 9);
			
			int rows = st.executeUpdate();
			System.out.println("Rows affected: " + rows);
			
		}
		catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		
		finally {
			DB.closeStatement(st);
			DB.closeConnection(conn);
			
		}

	}

}
