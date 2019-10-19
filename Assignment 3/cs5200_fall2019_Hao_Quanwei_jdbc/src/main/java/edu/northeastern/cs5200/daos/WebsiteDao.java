package edu.northeastern.cs5200.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import edu.northeastern.cs5200.models.Website;

public class WebsiteDao implements WebsiteImpl {
    private static WebsiteDao instance = null;

    private WebsiteDao() {

    }

    public static WebsiteDao getInstance() {
        if (instance == null) {
            instance = new WebsiteDao();
        }
        return instance;
    }

    private Connection connection = null;
    private Statement statement = null;
    private PreparedStatement pStatement = null;

	@Override
	public void createWebsiteForDeveloper(int developerId, Website website) {
		try {
			connection = edu.northeastern.cs5200.Connection.getConnection();
			
			// Insert into Website
			String INSERT_WEBSITE = "INSERT INTO website (id, name, description, created, updated, visits, developerId) VALUES (?, ?, ?, ?, ?, ?, ?)";
			pStatement = connection.prepareStatement(INSERT_WEBSITE);
			pStatement.setInt(1, website.getId());
			pStatement.setString(2, website.getName());
			pStatement.setString(3, website.getDescription());
			Date created = website.getCreated();
			java.sql.Date createdSql;
			if (created != null) {
				createdSql = new java.sql.Date(created.getTime());
            } else createdSql = null;
			pStatement.setDate(4, createdSql);
			
			Date updated = website.getUpdated();
			java.sql.Date updatedSql;
			if (updated != null) {
				updatedSql = new java.sql.Date(updated.getTime());
            } else updatedSql = null;
			pStatement.setDate(5, updatedSql);
			
			pStatement.setInt(6, website.getVisits());
			pStatement.setInt(7, developerId);
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
	public Collection<Website> findAllWebsites() {
        Collection<Website> websites = new ArrayList<>();
        try {
            connection = edu.northeastern.cs5200.Connection.getConnection();
            statement = connection.createStatement();
            String FIND_ALL_WEBSITES = "SELECT * FROM website";
            ResultSet results = statement.executeQuery(FIND_ALL_WEBSITES);
            while (results.next()) {
                int id = results.getInt("id");
                String name = results.getString("name");
                String description = results.getString("description");
                Date created = results.getDate("created");
                Date updated = results.getDate("updated");
                int visits = results.getInt("visits");
                Website website = new Website(id, name, description, created, updated, visits);
                websites.add(website);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return websites;
	}

	@Override
	public Collection<Website> findWebsitesForDeveloper(int developerId) {
        Collection<Website> websites = new ArrayList<>();
        try {
            connection = edu.northeastern.cs5200.Connection.getConnection();
            String FIND_WEBSITES_FOR_DEVELOPER = "SELECT * FROM website WHERE developerId = ?"; 
            pStatement = connection.prepareStatement(FIND_WEBSITES_FOR_DEVELOPER);
            pStatement.setInt(1, developerId);
            ResultSet results = pStatement.executeQuery();
            while (results.next()) {
                int id = results.getInt("id");
                String name = results.getString("name");
                String description = results.getString("description");
                Date created = results.getDate("created");
                Date updated = results.getDate("updated");
                int visits = results.getInt("visits");
                Website website = new Website(id, name, description, created, updated, visits);
                websites.add(website);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return websites;
	}

	@Override
	public Website findWebsiteById(int websiteId) {
		Website websiteById = null;
		try {
			connection = edu.northeastern.cs5200.Connection.getConnection();
			String FIND_WEBSITE_BY_ID = "SELECT * FROM website WHERE id = ?";
			pStatement = connection.prepareStatement(FIND_WEBSITE_BY_ID);
			pStatement.setInt(1, websiteId);
			ResultSet result = pStatement.executeQuery();
			if (result.next()) {
                int id = result.getInt("id");
                String name = result.getString("name");
                String description = result.getString("description");
                Date created = result.getDate("created");
                Date updated = result.getDate("updated");
                int visits = result.getInt("visits");
                websiteById = new Website(id, name, description, created, updated, visits);
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return websiteById;
	}

	@Override
	public int updateWebsite(int websiteId, Website website) {
		try {
			connection = edu.northeastern.cs5200.Connection.getConnection();
			String UPDATE_WEBSITE = "UPDATE website SET id = ?, name = ?, description = ?, created = ?, updated = ?, visits = ? WHERE id = ?";
			pStatement = connection.prepareStatement(UPDATE_WEBSITE);
			pStatement.setInt(1, website.getId());
			pStatement.setString(2, website.getName());
			pStatement.setString(3, website.getDescription());
			Date created = website.getCreated();
			java.sql.Date createdSql;
			if (created != null) {
				createdSql = new java.sql.Date(created.getTime());
            } else createdSql = null;
			pStatement.setDate(4, createdSql);
			
			Date updated = website.getUpdated();
			java.sql.Date updatedSql;
			if (updated != null) {
				updatedSql = new java.sql.Date(updated.getTime());
            } else updatedSql = null;
			pStatement.setDate(5, updatedSql);
			pStatement.setInt(6, website.getVisits());
			pStatement.setInt(7, websiteId);
			pStatement.executeUpdate();
			return 1;
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int deleteWebsite(int websiteId) {
        try {
            connection = edu.northeastern.cs5200.Connection.getConnection();
            String DELETE_WEBSITE = "DELETE FROM website WHERE id = ?";
            pStatement = connection.prepareStatement(DELETE_WEBSITE);
            pStatement.setInt(1, websiteId);
            pStatement.executeUpdate();
            return 1;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
		return 0;
	}

}
