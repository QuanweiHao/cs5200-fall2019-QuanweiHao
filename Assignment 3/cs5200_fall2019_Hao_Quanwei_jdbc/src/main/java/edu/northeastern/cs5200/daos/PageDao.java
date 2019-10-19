package edu.northeastern.cs5200.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import edu.northeastern.cs5200.models.Page;
import edu.northeastern.cs5200.models.Website;

public class PageDao implements PageImpl {

    private static PageDao instance = null;

    private PageDao() {

    }

    public static PageDao getInstance() {
        if (instance == null) {
            instance = new PageDao();
        }
        return instance;
    }

    private Connection connection = null;
    private Statement statement = null;
    private PreparedStatement pStatement = null;
    
	@Override
	public void createPageForWebsite(int websiteId, Page page) {
        try {
            connection = edu.northeastern.cs5200.Connection.getConnection();
            String INSERT_PAGE = "INSERT INTO page (id, title, description, created, updated, views, websiteId) VALUES (?, ?, ?, ?, ?, ?, ?)";
            pStatement = connection.prepareStatement(INSERT_PAGE);
            pStatement.setInt(1, page.getId());
            pStatement.setString(2, page.getTitle());
            pStatement.setString(3, page.getDescription());
            java.util.Date created = page.getCreated();
            java.sql.Date createdSql;
            if (created != null) {
                createdSql = new java.sql.Date(created.getTime());
            } else createdSql = null;
            pStatement.setDate(4, createdSql);
            java.util.Date updated = page.getUpdated();
            java.sql.Date updatedSql;
            if (updated != null) {
                updatedSql = new java.sql.Date(updated.getTime());
            } else updatedSql = null;
            pStatement.setDate(5, updatedSql);
            pStatement.setInt(6, page.getViews());
            pStatement.setInt(7, websiteId);
            pStatement.executeUpdate();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

	}

	@Override
	public Collection<Page> findAllPages() {
        Collection<Page> pages = new ArrayList<>();
        try {
            connection = edu.northeastern.cs5200.Connection.getConnection();
            statement = connection.createStatement();
            String FIND_ALL_PAGES = "SELECT * FROM page";
            ResultSet results = statement.executeQuery(FIND_ALL_PAGES);
            while (results.next()) {
            	int id = results.getInt("id");
                String title = results.getString("title");
                String description = results.getString("description");
                Date created = results.getDate("created");
                Date updated = results.getDate("updated");
                int views = results.getInt("views");
                int websiteId = results.getInt("websiteId");
                Page page = new Page(id, title, description, created, updated, views);
                WebsiteDao dao = WebsiteDao.getInstance();
                Website website = dao.findWebsiteById(websiteId);
                page.setWebsite(website);
                pages.add(page);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return pages;
	}

	@Override
	public Page findPageById(int pageId) {
        Page page = null;
        try {
            connection = edu.northeastern.cs5200.Connection.getConnection();
            String FIND_PAGE_BY_ID = "SELECT * FROM page WHERE id = ?";
            pStatement = connection.prepareStatement(FIND_PAGE_BY_ID);
            pStatement.setInt(1, pageId);
            ResultSet result = pStatement.executeQuery();
            if (result.next()) {
            	int id = result.getInt("id");
                String title = result.getString("title");
                String description = result.getString("description");
                Date created = result.getDate("created");
                Date updated = result.getDate("updated");
                int views = result.getInt("views");
                int websiteId = result.getInt("websiteId");
                page = new Page(id, title, description, created, updated, views);
                WebsiteDao dao = WebsiteDao.getInstance();
                Website website = dao.findWebsiteById(websiteId);
                page.setWebsite(website);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return page;
	}

	@Override
	public Collection<Page> findPagesForWebsite(int websiteId) {
		Collection<Page> pages = new ArrayList<>();
		try {
			connection = edu.northeastern.cs5200.Connection.getConnection();
			String FIND_PAGES_FOR_WEBSITE = "SELECT * FROM page WHERE websiteId = ?";
			pStatement = connection.prepareStatement(FIND_PAGES_FOR_WEBSITE);
			pStatement.setInt(1, websiteId);
			ResultSet results = pStatement.executeQuery();
            while (results.next()) {
            	int id = results.getInt("id");
                String title = results.getString("title");
                String description = results.getString("description");
                Date created = results.getDate("created");
                Date updated = results.getDate("updated");
                int views = results.getInt("views");
                Page page = new Page(id, title, description, created, updated, views);
                WebsiteDao dao = WebsiteDao.getInstance();
                Website website = dao.findWebsiteById(websiteId);
                page.setWebsite(website);
                pages.add(page);
            }
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return pages;
	}

	@Override
	public int updatePage(int pageId, Page page) {
		try {
			connection = edu.northeastern.cs5200.Connection.getConnection();
			String UPDATE_PAGE = "UPDATE page SET id = ?, title = ?, description = ?, created = ?, updated = ?, views = ?, websiteId = ? WHERE id = ?";
			pStatement = connection.prepareStatement(UPDATE_PAGE);
			pStatement.setInt(1, page.getId());
	        pStatement.setString(2, page.getTitle());
            pStatement.setString(3, page.getDescription());
            java.util.Date created = page.getCreated();
            java.sql.Date createdSql;
            if (created != null) {
                createdSql = new java.sql.Date(created.getTime());
            } else createdSql = null;
            pStatement.setDate(4, createdSql);
            java.util.Date updated = page.getUpdated();
            java.sql.Date updatedSql;
            if (updated != null) {
                updatedSql = new java.sql.Date(updated.getTime());
            } else updatedSql = null;
            pStatement.setDate(5, updatedSql);
            pStatement.setInt(6, page.getViews());
            pStatement.setInt(7, page.getWebsite().getId());
            pStatement.setInt(8, pageId);
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
	public int deletePage(int pageId) {
		try {
			connection = edu.northeastern.cs5200.Connection.getConnection();
			String DELETE_PAGE = "DELETE FROM page WHERE id = ?";
			pStatement = connection.prepareStatement(DELETE_PAGE);
			pStatement.setInt(1, pageId);
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

}
