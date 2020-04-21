package db.sqlite;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import pojos.Dissability;
import pojos.MedicalProfessional;

public class SQLiteDissability {
	
	private Connection c;

	public SQLiteDissability(Connection n) {
		this.c = n;
	}
	
	public void add(Dissability dissability) {
		 
		String sql = "INSERT INTO dissability(name, type, severity, extra_info, affected_area)"
				+ "(VALUES (?,?,?,?,?);)";
		
		PreparedStatement prep;
		try {
			prep = c.prepareStatement(sql);
			
			prep.setString(1, dissability.getName());
			prep.setString(2, dissability.getType());
			prep.setString(3,dissability.getSeverity());
			prep.setString(4, dissability.getExtra_info());
			prep.setString(5,dissability.getAffected_area());
		
			prep.executeUpdate();
			prep.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	public List<Dissability> searchByName(String name) {
		List<Dissability> dissabilityList = new ArrayList<Dissability>();
		
		try {
			String sql = "SELECT * FROM dissability WHERE name LIKE ?";
			PreparedStatement prep = c.prepareStatement(sql);
			prep.setString(1, "%" + name + "%");
			ResultSet rs = prep.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("id");
				String dissabilityName = rs.getString("name");
				String type = rs.getString("type");
				String severity = rs.getString("severity");
				String extra_info = rs.getString("extra_info");
				String affected_area = rs.getString("affected_area");
				
				Dissability newDissability = new Dissability(id, dissabilityName, type, severity, extra_info, affected_area);
				dissabilityList.add(newDissability);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dissabilityList;
	}

	public Dissability searchById(Integer id) {
		
		Dissability newdissability = null;
		
		try {
			String sql = "SELECT * FROM dissability WHERE id LIKE ?";
			PreparedStatement prep = c.prepareStatement(sql);
			prep.setInt(1, id);
			ResultSet rs = prep.executeQuery();
			
			int dissability_id = rs.getInt("id");
			String dissabilityName = rs.getString("name");
			String type = rs.getString("type");
			String severity = rs.getString("severity");
			String extra_info = rs.getString("extra_info");
			String affected_area = rs.getString("affected_area");
			
			newdissability = new Dissability(id, dissabilityName, type, severity, extra_info, affected_area);
			
		} catch (Exception e) {
			e.printStackTrace();
			
		}
	 return newdissability;
	}

	public void deleteById(Integer id) {
		
		try {
		String sql = "DELETE FROM dissability WHERE id= ?";
		PreparedStatement prep = c.prepareStatement(sql);
		prep.setInt(1, id);
		prep.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	public void updateDissability(Dissability dissability) {
		
		String sql = "UPDATE disability SET name = ?, type = ?, severity = ?, extra_info = ?, affected_area = ?";
		PreparedStatement prep;
		try {
			prep = c.prepareStatement(sql);
			
			prep.setString(1, dissability.getName());
			prep.setString(2, dissability.getType());
			prep.setString(3,dissability.getSeverity());
			prep.setString(4, dissability.getExtra_info());
			prep.setString(5, dissability.getAffected_area());
			prep.executeUpdate();
			System.out.println("Update finished.");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
