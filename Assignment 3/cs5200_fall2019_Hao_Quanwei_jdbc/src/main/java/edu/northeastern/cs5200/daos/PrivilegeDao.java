package edu.northeastern.cs5200.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import edu.northeastern.cs5200.models.Privilege;

public class PrivilegeDao implements PrivilegeImpl {
	
	private static PrivilegeDao instance = null;
	
	PrivilegeDao(){
		
	}
	
	public static PrivilegeDao getInstance() {
		if (instance == null) {
			instance = new PrivilegeDao();
		}
		return instance;
	}
	
	private Connection connection = null;
    private PreparedStatement pStatement = null;

	@Override
	public void assignWebsitePrivilege(int developerId, int websiteId, String privilege) {
		try {
			connection = edu.northeastern.cs5200.Connection.getConnection();
			String ASSIGN_WEBSITE_PRIVILEGE = "INSERT INTO website_privilege (privilege, developerId, websiteId) VALUES (?, ?, ?)";
			pStatement = connection.prepareStatement(ASSIGN_WEBSITE_PRIVILEGE);
			pStatement.setString(1, Privilege.fromString(privilege).toString().toLowerCase());
			pStatement.setInt(2, developerId);
			pStatement.setInt(3, websiteId);
			pStatement.executeUpdate();
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void assignPagePrivilege(int developerId, int pageId, String privilege) {
		try {
			connection = edu.northeastern.cs5200.Connection.getConnection();
			String ASSIGN_PAGE_PRIVILEGE = "INSERT INTO page_privilege (priviege, developerId, pageId) VALUES (?, ?, ?)";
			pStatement = connection.prepareStatement(ASSIGN_PAGE_PRIVILEGE);
			pStatement.setString(1, Privilege.fromString(privilege).toString().toLowerCase());
			pStatement.setInt(2, developerId);
			pStatement.setInt(3, pageId);
			pStatement.executeUpdate();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


	}

	@Override
	public void deleteWebsitePrivilege(int developerId, int websiteId, String privilege) {
		try {
			connection = edu.northeastern.cs5200.Connection.getConnection();
			String DELETE_WEBSITE_PRIVILEGE = "DELETE FROM website_privilege WHERE developerId = ?, websiteId = ?, privilege = ?";
			pStatement = connection.prepareStatement(DELETE_WEBSITE_PRIVILEGE);
			pStatement.setInt(1, developerId);
			pStatement.setInt(2, websiteId);
			pStatement.setString(3, Privilege.fromString(privilege).toString().toLowerCase());
			pStatement.executeUpdate();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void deletePagePrivilege(int developerId, int pageId, String privilege) {
		try {
			connection = edu.northeastern.cs5200.Connection.getConnection();
			String DELETE_PAGE_PRIVILEGE = "DELETE FROM page_privilege WHERE developerId = ?, pageId = ?, privilege = ?";
			pStatement = connection.prepareStatement(DELETE_PAGE_PRIVILEGE);
			pStatement.setInt(1, developerId);
			pStatement.setInt(2, pageId);
			pStatement.setString(3, Privilege.fromString(privilege).toString().toLowerCase());
			pStatement.executeUpdate();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
