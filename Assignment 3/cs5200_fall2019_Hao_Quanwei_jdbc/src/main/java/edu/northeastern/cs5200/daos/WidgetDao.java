package edu.northeastern.cs5200.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;

import edu.northeastern.cs5200.models.Page;
import edu.northeastern.cs5200.models.Widget;

public class WidgetDao implements WidgetImpl {
	
    private static WidgetDao instance = null;

    private WidgetDao() {

    }

    public static WidgetDao getInstance() {
        if (instance == null) {
            instance = new WidgetDao();
        }
        return instance;
    }

    private Connection connection = null;
    private Statement statement = null;
    private PreparedStatement pStatement = null;


	@Override
	public int createWidgetForPage(int pageId, Widget widget) {
        try {
            connection = edu.northeastern.cs5200.Connection.getConnection();
            String INSERT_WIDGET = "INSERT INTO widget (dtype, id, name, width, height, css_class, css_style, text, `order`, pageId) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            pStatement = connection.prepareStatement(INSERT_WIDGET);
            pStatement.setString(1, "Basic Widget");
            pStatement.setInt(2, widget.getId());
            pStatement.setString(3, widget.getName());
            pStatement.setInt(4, widget.getWidth());
            pStatement.setInt(5, widget.getHeight());
            pStatement.setString(6, widget.getCssClass());
            pStatement.setString(7, widget.getCssStyle());
            pStatement.setString(8, widget.getText());
            pStatement.setInt(9, widget.getOrder());
            pStatement.setInt(10, pageId);
            pStatement.executeUpdate();
            return 1;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
		return 0;
	}

	@Override
	public Collection<Widget> findAllWidgets() {
		Collection<Widget> widgets = new ArrayList<>();
        try {
            connection = edu.northeastern.cs5200.Connection.getConnection();
            statement = connection.createStatement();
            String FIND_ALL_WIDGETS = "SELECT * FROM widget";
            ResultSet results = statement.executeQuery(FIND_ALL_WIDGETS);
            while (results.next()) {
                int id = results.getInt("id");
                String name = results.getString("name");
                int width = results.getInt("width");
                int height = results.getInt("height");
                String cssClass = results.getString("cssClass");
                String cssStyle = results.getString("cssStyle");
                String text = results.getString("text");
                int order = results.getInt("order");
                int pageId = results.getInt("pageId");
                Widget widget = new Widget(id, name, width, height, cssClass, cssStyle, text, order);
                PageDao dao = PageDao.getInstance();
                Page page = dao.findPageById(pageId);
                widget.setPage(page);
                widgets.add(widget);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return widgets;
	}

	@Override
	public Widget findWidgetById(int widgetId) {
		Widget widgetById = null;
		try {
            connection = edu.northeastern.cs5200.Connection.getConnection();
            String FIND_WIDGET_BY_ID = "SELECT * FROM widget WHERE id = ?";
            pStatement = connection.prepareStatement(FIND_WIDGET_BY_ID);
            pStatement.setInt(1, widgetId);
            ResultSet results = pStatement.executeQuery();
            if (results.next()) {
                int id = results.getInt("id");
                String name = results.getString("name");
                int width = results.getInt("width");
                int height = results.getInt("height");
                String cssClass = results.getString("cssClass");
                String cssStyle = results.getString("cssStyle");
                String text = results.getString("text");
                int order = results.getInt("order");
                int pageId = results.getInt("pageId");
                widgetById = new Widget(id, name, width, height, cssClass, cssStyle, text, order);
                PageDao dao = PageDao.getInstance();
                Page page = dao.findPageById(pageId);
                widgetById.setPage(page);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return widgetById;
	}

	@Override
	public Collection<Widget> findWidgetsForPage(int pageId) {
		Collection<Widget> widgets = new ArrayList<>();
        try {
            connection = edu.northeastern.cs5200.Connection.getConnection();
            String FIND_WIDGETS_FOR_PAGE = "SELECT * FROM widget WHERE pageId = ?";
            pStatement = connection.prepareStatement(FIND_WIDGETS_FOR_PAGE);
            pStatement.setInt(1, pageId);
            ResultSet results = pStatement.executeQuery();
            while (results.next()) {
                int id = results.getInt("id");
                String name = results.getString("name");
                int width = results.getInt("width");
                int height = results.getInt("height");
                String cssClass = results.getString("css_class");
                String cssStyle = results.getString("css_style");
                String text = results.getString("text");
                int order = results.getInt("order");
                Widget widget = new Widget(id, name, width, height, cssClass, cssStyle, text, order);
                PageDao dao = PageDao.getInstance();
                Page page = dao.findPageById(pageId);
                widget.setPage(page);
                widgets.add(widget);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return widgets;
	}

	@Override
	public int updateWidget(int widgetId, Widget widget) {
        try {
            connection = edu.northeastern.cs5200.Connection.getConnection();
            String UPDATE_WIDGET = "UPDATE widget SET id = ?, name = ?, width = ?, height = ?, css_class = ?, css_style = ?, text = ?, `order` = ?, pageId = ? WHERE id = ?";
            pStatement = connection.prepareStatement(UPDATE_WIDGET);
            pStatement.setInt(1, widget.getId());
            pStatement.setString(2, widget.getName());
            pStatement.setInt(3, widget.getWidth());
            pStatement.setInt(4, widget.getHeight());
            pStatement.setString(5, widget.getCssClass());
            pStatement.setString(6, widget.getCssStyle());
            pStatement.setString(7, widget.getText());
            pStatement.setInt(8, widget.getOrder());
            pStatement.setInt(9, widget.getPage().getId());
            pStatement.setInt(10, widgetId);
            pStatement.executeUpdate();
            return 1;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
		return 0;
	}

	@Override
	public int deleteWidget(int widgetId) {
       try {
            connection = edu.northeastern.cs5200.Connection.getConnection();
            String DELETE_WIDGET = "DELETE FROM widget WHERE id = ?";
            pStatement = connection.prepareStatement(DELETE_WIDGET);
            pStatement.setInt(1, widgetId);
            System.out.print(pStatement);
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
