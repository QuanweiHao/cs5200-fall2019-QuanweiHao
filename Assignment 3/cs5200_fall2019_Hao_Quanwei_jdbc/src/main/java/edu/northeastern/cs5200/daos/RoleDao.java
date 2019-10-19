package edu.northeastern.cs5200.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import edu.northeastern.cs5200.models.Role;

public class RoleDao implements RoleImpl {
	private static RoleDao instance = null;
	
	RoleDao(){
		
	}
	
	public static RoleDao getInstance() {
		if (instance == null) {
			instance = new RoleDao();
		}
		return instance;
	}
	
	private Connection connection = null;
    private PreparedStatement pStatement = null;
	
	@Override
	public void assignWebsiteRole(int developerId, int websiteId, int roleId) {
		try {
			connection = edu.northeastern.cs5200.Connection.getConnection();
			String ASSIGN_WEBSITE_ROLE = "INSERT INTO website_role (`role`, developerId, websiteId) VALUES (?, ?, ?)";
			pStatement = connection.prepareStatement(ASSIGN_WEBSITE_ROLE);
			pStatement.setString(1, Role.fromInt(roleId).toString().toLowerCase());
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
	public void assignPageRole(int developerId, int pageId, int roleId) {
		try {
			connection = edu.northeastern.cs5200.Connection.getConnection();
			String ASSIGN_PAGE_ROLE = "INSERT INTO page_role (`role`, developerId, pageId) VALUES (?, ?, ?)";
			pStatement = connection.prepareStatement(ASSIGN_PAGE_ROLE);
			pStatement.setString(1, Role.fromInt(roleId).toString().toLowerCase());
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
	public void deleteWebsiteRole(int developerId, int websiteId, int roleId) {
		try {
			connection = edu.northeastern.cs5200.Connection.getConnection();
			String DELETE_WEBSITE_ROLE = "DELETE FROM website_role WHERE developerId = ? AND websiteId = ? AND role = ?";
			pStatement = connection.prepareStatement(DELETE_WEBSITE_ROLE);
			pStatement.setInt(1, developerId);
			pStatement.setInt(2, websiteId);
			pStatement.setString(3, Role.fromInt(roleId).toString().toLowerCase());
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
	public void deletePageRole(int developerId, int pageId, int roleId) {
		try {
			connection = edu.northeastern.cs5200.Connection.getConnection();
			String DELETE_PAGE_ROLE = "DELETE FROM page_role WHERE developerId = ? AND `pageId` = ? AND role = ?";
			pStatement = connection.prepareStatement(DELETE_PAGE_ROLE);
			pStatement.setInt(1, developerId);
			pStatement.setInt(2, pageId);
			pStatement.setString(3, Role.fromInt(roleId).toString().toLowerCase());
			System.out.println(pStatement);
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
