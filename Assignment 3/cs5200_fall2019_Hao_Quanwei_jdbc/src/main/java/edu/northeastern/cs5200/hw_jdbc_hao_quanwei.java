package edu.northeastern.cs5200;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import edu.northeastern.cs5200.daos.DeveloperDao;
import edu.northeastern.cs5200.daos.PageDao;
import edu.northeastern.cs5200.daos.RoleDao;
import edu.northeastern.cs5200.daos.UserDao;
import edu.northeastern.cs5200.daos.WebsiteDao;
import edu.northeastern.cs5200.daos.WidgetDao;
import edu.northeastern.cs5200.models.Address;
import edu.northeastern.cs5200.models.Developer;
import edu.northeastern.cs5200.models.HeadingWidget;
import edu.northeastern.cs5200.models.HtmlWidget;
import edu.northeastern.cs5200.models.ImageWidget;
import edu.northeastern.cs5200.models.Page;
import edu.northeastern.cs5200.models.Phone;
import edu.northeastern.cs5200.models.User;
import edu.northeastern.cs5200.models.Website;
import edu.northeastern.cs5200.models.Widget;
import edu.northeastern.cs5200.models.YouTubeWidget;

public class hw_jdbc_hao_quanwei {
	public void create() {
		// Insert Developers and Users
		Developer alice = new Developer(12, "Alice", "Wonder", "alice", "alice", "alice@wonder.com", null, "4321rewq");
		createDeveloper(alice);
		
        Developer bob = new Developer(23, "Bob", "Marley", "bob", "bob", "bob@marley.com", null, "5432trew");
        createDeveloper(bob);
        
        Developer charlie = new Developer(34, "Charles", "Garcia", "charlie", "charlie", "chuch@garcia.com", null, "6543ytre");
        createDeveloper(charlie);
        
        User dan = new User(45, "Dan", "Martin", "dan", "dan", "dan@martin.com", null, false);
        createUser(dan);
        
        User ed = new User(56, "Ed", "Karaz", "ed", "ed", "ed@kar.com", null, false);
        createUser(ed);
        
        // Insert Websites
        Date today = new Date();
        Website facebook = new Website(123, "Facebook", "an online social media and social networking service", today, today, 1234234);
        createWebsite(12, facebook);
        
        Website twitter = new Website(234, "Twitter", "an online news and social networking service", today, today, 4321543);
        createWebsite(23, twitter);
        
        Website wikipedia = new Website(345, "Wikipedia", "a free online encyclopedia", today, today, 3456654);
        createWebsite(34, wikipedia);
        
        Website cnn = new Website(456, "CNN", "an American basic cable and satellite television news channel", today, today, 6543345);
        createWebsite(12, cnn);
        
        Website cnet = new Website(567, "CNET", "an American media website that publishes reviews, news, articles, blogs, podcasts and videos on technology and consumer electronics",
        		today, today, 5433455);
        createWebsite(23, cnet);
        
        Website gizmodo = new Website(678, "Gizmodo", "a design, technology, science and science fiction website that also writes articles on polit",
                today, today, 4322345);
        createWebsite(34, gizmodo);
        
        // Insert Website Role
        RoleDao roleDao = RoleDao.getInstance();
        roleDao.assignWebsiteRole(12, 123, 1);
        roleDao.assignWebsiteRole(23, 123, 4);
        roleDao.assignWebsiteRole(34, 123, 2);
        
        roleDao.assignWebsiteRole(23, 234, 1);
        roleDao.assignWebsiteRole(34, 234, 4);
        roleDao.assignWebsiteRole(12, 234, 2);
        
        roleDao.assignWebsiteRole(34, 345, 1);
        roleDao.assignWebsiteRole(12, 345, 4);
        roleDao.assignWebsiteRole(23, 345, 2);
        
        roleDao.assignWebsiteRole(12, 456, 1);
        roleDao.assignWebsiteRole(23, 456, 4);
        roleDao.assignWebsiteRole(34, 456, 2);
        
        roleDao.assignWebsiteRole(23, 567, 1);
        roleDao.assignWebsiteRole(34, 567, 4);
        roleDao.assignWebsiteRole(12, 567, 2);
        
        roleDao.assignWebsiteRole(34, 678, 1);
        roleDao.assignWebsiteRole(12, 678, 4);
        roleDao.assignWebsiteRole(23, 678, 2);
        
        // Insert Page
        Page home = new Page(123, "Home", "Landing page", today, today, 123434);
        createPage(567, home);
        
        Page about = new Page(234, "About", "Website description", today, today, 234545);
        createPage(678, about);
        
        Page contact = new Page(345, "Contact", "Addresses, phones, and contact info", today, today, 345656);
        createPage(345, contact);
        
        Page preferences = new Page(456, "Preferences", "Where users can configure their preferences", today, today, 456776);
        createPage(456, preferences);
        
        Page profile = new Page(567, "Profile", "Users can configure their personal infomation", today, today, 567878);
        createPage(567, profile);
        
        // Insert Page Role
        roleDao.assignPageRole(12, 123, 4);
        roleDao.assignPageRole(23, 123, 5);
        roleDao.assignPageRole(34, 123, 3);
        
        roleDao.assignPageRole(23, 234, 4);
        roleDao.assignPageRole(34, 234, 5);
        roleDao.assignPageRole(12, 234, 3);
        
        roleDao.assignPageRole(34, 345, 4);
        roleDao.assignPageRole(12, 345, 5);
        roleDao.assignPageRole(23, 345, 3);
        
        roleDao.assignPageRole(12, 456, 4);
        roleDao.assignPageRole(23, 456, 5);
        roleDao.assignPageRole(34, 456, 3);
        
        roleDao.assignPageRole(23, 567, 4);
        roleDao.assignPageRole(34, 567, 5);
        roleDao.assignPageRole(12, 567, 3);
        
        // Insert Widgets
        HeadingWidget head123 = new HeadingWidget(1, "head123", 0, 0, null, null, "Welcome", 0, 0);
        createWidget(123, head123);
        createHeadingForPage(head123);
        
        HtmlWidget post234 = new HtmlWidget(2, "post234", 0, 0, null, null, "<p>Lorem</p>", 0, null);
        createWidget(234, post234);
        createHtmlForPage(post234);
        
        HeadingWidget head345 = new HeadingWidget(3, "head345", 0, 0, null, null, "Hi", 1, 0);
        createWidget(345, head345);
        createHeadingForPage(head345);
        
        HtmlWidget intro456 = new HtmlWidget(4, "intro456", 0, 0, null, null, "<h1>Hi</h1>", 2, null);
        createWidget(345, intro456);
        createHtmlForPage(intro456);
        
        ImageWidget image345 = new ImageWidget(5, "image345", 50, 100, null, null, null, 3, "/img/567.png");
        createWidget(345, image345);
        createImageForPage(image345);
        
        YouTubeWidget video456 = new YouTubeWidget(6, "video", 400, 300, null, null, null, 0, "https://youtu.be/h67VX51QXiQ", false, false);
        createWidget(456, video456);
        createYouTubeForPage(video456);
        
  
	}
	
	public void update() {
		// Update developer - Update Charlie's primary phone number to 333-444-5555
		DeveloperDao developerDao = DeveloperDao.getInstance();	
		Developer charlie = developerDao.findDeveloperByUsername("charlie");
		Collection<Phone> charlie_phones = charlie.getPhone();
		charlie.setPhone(new ArrayList<>());
		if (charlie_phones.isEmpty()) {
			Phone newPhone = new Phone("333-444-5555", true, charlie);
			charlie.addPhone(newPhone);
		} else {
			for (Phone phone : charlie_phones) {
				if (phone.getPrimary()) {
					phone.setPhone("333-444-5555");
				}
				charlie.addPhone(phone);
			}
		}
		developerDao.updateDeveloper(charlie.getId(), charlie);
		
		
		// Update widget - Update the relative order of widget head345 on the page so that
		// it's new order is 3
		WidgetDao widgetDao = WidgetDao.getInstance();
		int pageId = findPageIdforWidgetByName("head345");
		Collection<Widget> widgets = widgetDao.findWidgetsForPage(pageId);
		for (Widget widget : widgets) {
			int current_order = widget.getOrder();
			widget.setOrder(current_order + 2);
			widgetDao.updateWidget(widget.getId(), widget);
		}
		
		// Update page - Append 'CNET - ' to the beginning of all CNET's page titles
		PageDao pageDao = PageDao.getInstance();
		int websiteId = findWebsiteIdByName("CNET");
		Collection<Page> pagesCNET = pageDao.findPagesForWebsite(websiteId);
		for (Page page : pagesCNET) {
			String current_title = page.getTitle();
			page.setTitle("CNET - " + current_title);
			pageDao.updatePage(page.getId(), page);
		}
		
		// Update role - Swap Charlies's and Bob's role in CNET's home page
		RoleDao roleDao = RoleDao.getInstance();
		websiteId = findWebsiteIdByName("CNET");
		pageId = findPageIdByTile("Home");
		Developer bob = developerDao.findDeveloperByUsername("bob");
		int charlieId = charlie.getId();
		int bobId = bob.getId();
		int charlieRoleId = findRoleIdBydIdAndpId(pageId, charlieId);
		int bobRoleId = findRoleIdBydIdAndpId(pageId, bobId);
		roleDao.deletePageRole(charlieId, pageId, charlieRoleId);
		roleDao.assignPageRole(charlieId, pageId, bobRoleId);
		roleDao.deletePageRole(bobId, pageId, bobRoleId);
		roleDao.assignPageRole(bobId, pageId, charlieRoleId);
	}
	
	public void delete() {
		// Delete developer - Delete Alice's primary address
		DeveloperDao developerDao = DeveloperDao.getInstance();	
		Developer alice = developerDao.findDeveloperByUsername("alice");
		Collection<Address> addressesAlice = alice.getAddress();
		if (!addressesAlice.isEmpty()) {
			for (Address address : addressesAlice) {
				if (address.getPrimary()) {
					alice.removeAddress(address);
				}
			}
		}
		developerDao.updateDeveloper(alice.getId(), alice);
		
		
		// Delete widget - Remove the last widget in the Contact page
		WidgetDao widgetDao = WidgetDao.getInstance();
		int contactId = findPageIdByTile("Contact");
		Collection<Widget> widgetsOfContact = widgetDao.findWidgetsForPage(contactId);
		int maxOrder = 0;
		for (Widget widget : widgetsOfContact) {
			int currOrder = widget.getOrder();
			if (currOrder > maxOrder) {
				maxOrder = currOrder;
			}
		}
		for (Widget widget : widgetsOfContact) {
			if (widget.getOrder() == maxOrder) {
				widgetDao.deleteWidget(widget.getId());
				break;
			}
		}
		
		// Delete page - Remove the last updated page in Wikipedia
		PageDao pageDao = PageDao.getInstance();
		int wikiId = findWebsiteIdByName("Wikipedia");
		Collection<Page> pagesOfWiki = pageDao.findPagesForWebsite(wikiId);
		Date lastUpdate = new Date(2323223232L);
		for (Page page : pagesOfWiki) {
			Date currUpdate = page.getUpdated();
			if (currUpdate.compareTo(lastUpdate) > 0){
				lastUpdate = currUpdate;
			}
		}
		for (Page page : pagesOfWiki) {
			Date currUpdate = page.getUpdated();
			if (currUpdate.compareTo(lastUpdate) == 0){
				Collection<Widget> widgetsInPage = widgetDao.findWidgetsForPage(page.getId());
				if (!widgetsInPage.isEmpty()) {
					for (Widget widgetInPage : widgetsInPage) {
						widgetDao.deleteWidget(widgetInPage.getId());
					}
				}
				deletePageRoleByPageid(page.getId());
				pageDao.deletePage(page.getId());
				break;
			}
		}
		
		// Delete website - Remove the CNET website, as well as all related roles and privileges 
		// relating developers to the Website and Page;
		WebsiteDao websiteDao = WebsiteDao.getInstance();
		int CNETId = findWebsiteIdByName("CNET");
		Collection<Page> pagesOfCNET = pageDao.findPagesForWebsite(CNETId);
		for (Page page : pagesOfCNET) {
			Collection<Widget> widgetsInPage = widgetDao.findWidgetsForPage(page.getId());
			if (!widgetsInPage.isEmpty()) {
				for (Widget widgetInPage : widgetsInPage) {
					widgetDao.deleteWidget(widgetInPage.getId());
				}
			}
			deletePageRoleByPageid(page.getId());
			pageDao.deletePage(page.getId());
		} 
		deleteWebsiteRoleByWebsiteid(CNETId);
		websiteDao.deleteWebsite(CNETId);
		
	}
	
	
	
	private void createDeveloper(Developer developer) {
		DeveloperDao developerDao = DeveloperDao.getInstance();	
		developerDao.createDeveloper(developer);
	}
	
	private void createUser(User user) {
		UserDao userDao = UserDao.getInstance();
		userDao.createUser(user);
	}
	
	private void createWebsite(int developerId, Website website) {
		WebsiteDao websiteDao = WebsiteDao.getInstance();
		websiteDao.createWebsiteForDeveloper(developerId, website);
	}
	
	private void createPage(int websiteId, Page page) {
		PageDao pageDao = PageDao.getInstance();
		pageDao.createPageForWebsite(websiteId, page);
	}
	
	private void createWidget(int pageId, Widget widget) {
		WidgetDao widgetDao = WidgetDao.getInstance();
		widgetDao.createWidgetForPage(pageId, widget);
	}
	
	private Connection connection = null;
	private PreparedStatement pStatement = null;
	
    private void createHeadingForPage(HeadingWidget headingWidget) {
        try {
            connection = edu.northeastern.cs5200.Connection.getConnection();
            String INSERT_HEADING = "UPDATE widget SET dtype = ?, size = ? WHERE id = ?";
            pStatement = connection.prepareStatement(INSERT_HEADING);
            pStatement.setString(1, "Heading");
            pStatement.setInt(2, headingWidget.getSize());
            pStatement.setInt(3, headingWidget.getId());
            pStatement.executeUpdate();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    private void createHtmlForPage(HtmlWidget htmlWidget) {
        try {
            connection = edu.northeastern.cs5200.Connection.getConnection();
            String INSERT_HTML = "UPDATE widget SET dtype = ?, html = ? WHERE id = ?";
            pStatement = connection.prepareStatement(INSERT_HTML);
            pStatement.setString(1, "Html");
            pStatement.setString(2, htmlWidget.getHtml());
            pStatement.setInt(3, htmlWidget.getId());
            pStatement.executeUpdate();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    private void createImageForPage(ImageWidget imageWidget) {
        try {
            connection = edu.northeastern.cs5200.Connection.getConnection();
            String INSERT_IMAGE = "UPDATE widget SET dtype = ?, src = ? WHERE id = ?";
            pStatement = connection.prepareStatement(INSERT_IMAGE);
            pStatement.setString(1, "Image");
            pStatement.setString(2, imageWidget.getSrc());
            pStatement.setInt(3, imageWidget.getId());
            pStatement.executeUpdate();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    private void createYouTubeForPage(YouTubeWidget youTubeWidget) {
        try {
            connection = edu.northeastern.cs5200.Connection.getConnection();
            String INSERT_YOUTUBE = "UPDATE widget SET dtype = ?, url = ?, shareble = ?, expandable = ? WHERE id = ?";
            pStatement = connection.prepareStatement(INSERT_YOUTUBE);
            pStatement.setString(1, "YouTube");
            pStatement.setString(2, youTubeWidget.getUrl());
            pStatement.setBoolean(3, youTubeWidget.getShareble());
            pStatement.setBoolean(4, youTubeWidget.getExpandable());
            pStatement.setInt(5, youTubeWidget.getId());
            pStatement.executeUpdate();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    private int findPageIdforWidgetByName(String name) {
    	int pageId = 0;
    	try {
			connection = edu.northeastern.cs5200.Connection.getConnection();
			String FIND_PAGE_ID_FOR_WIDGET_BY_NAME = "SELECT p.id FROM page p, widget w WHERE p.id = w.pageId AND w.name = ?";
			pStatement = connection.prepareStatement(FIND_PAGE_ID_FOR_WIDGET_BY_NAME);
			pStatement.setString(1, name);
			ResultSet result = pStatement.executeQuery();
			if (result.next()) {
				pageId = result.getInt("id");
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return pageId;
    }
    
    private int findWebsiteIdByName(String name) {
    	int websiteIdWithName = 0;
    	try {
			connection = edu.northeastern.cs5200.Connection.getConnection();
			String FIND_WEBSITE_ID_BY_NAME = "SELECT w.id FROM website w WHERE name = ?";
			pStatement = connection.prepareStatement(FIND_WEBSITE_ID_BY_NAME);
			pStatement.setString(1, name);
			ResultSet result = pStatement.executeQuery();
			if (result.next()) {
				websiteIdWithName = result.getInt("id");
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return websiteIdWithName;
    }
    
    private int findPageIdByTile(String title) {
    	int pageIdWithTitle = 0;
    	try {
			connection = edu.northeastern.cs5200.Connection.getConnection();
			String FIND_PAGE_ID_BY_TITLE = "SELECT p.id FROM page p WHERE title = ?";
			pStatement = connection.prepareStatement(FIND_PAGE_ID_BY_TITLE);
			pStatement.setString(1, title);
			ResultSet result = pStatement.executeQuery();
			if (result.next()) {
				pageIdWithTitle = result.getInt("id");
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return pageIdWithTitle;
    }
    
    private int findRoleIdBydIdAndpId(int pageId, int developerId) {
    	int roleId = 0;
    	try {
			connection = edu.northeastern.cs5200.Connection.getConnection();
			String FIND_ROLE_ID = "SELECT role FROM page_role pr WHERE pr.pageId = ? AND pr.developerId = ?";
			pStatement = connection.prepareStatement(FIND_ROLE_ID);
			pStatement.setInt(1, pageId);
			pStatement.setInt(2, developerId);
			ResultSet result = pStatement.executeQuery();
			if (result.next()) {
				String role = result.getString("role");
				switch (role) {
					case "owner":
						roleId = 1;
						break;
					case "admin":
						roleId = 2;
						break;
					case "writer":
						roleId = 3;
						break;
					case "editor":
						roleId = 4;
						break;
					case "reviewer":
						roleId = 5;
						break;
					default:
						roleId = 0;
				}
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return roleId;
    }
    
    private void deletePageRoleByPageid(int pageId) {
    	try {
			connection = edu.northeastern.cs5200.Connection.getConnection();
			String DELETE_PAGE_ROLE_BY_TITLE = "DELETE FROM page_role WHERE pageId = ?";
			pStatement = connection.prepareStatement(DELETE_PAGE_ROLE_BY_TITLE);
			pStatement.setInt(1, pageId);
			pStatement.executeUpdate();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    private void deleteWebsiteRoleByWebsiteid(int websiteId) {
    	try {
			connection = edu.northeastern.cs5200.Connection.getConnection();
			String DELETE_WEBSITE_ROLE_BY_TITLE = "DELETE FROM website_role WHERE websiteId = ?";
			pStatement = connection.prepareStatement(DELETE_WEBSITE_ROLE_BY_TITLE);
			pStatement.setInt(1, websiteId);
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
