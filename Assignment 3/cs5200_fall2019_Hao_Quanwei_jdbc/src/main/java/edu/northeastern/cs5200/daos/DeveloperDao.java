package edu.northeastern.cs5200.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import edu.northeastern.cs5200.models.Address;
import edu.northeastern.cs5200.models.Developer;
import edu.northeastern.cs5200.models.Phone;

public class DeveloperDao implements DeveloperImpl {
	public static DeveloperDao instance = null;
	public static DeveloperDao getInstance() {
		if (instance == null) {
			instance = new DeveloperDao();
		}
		return instance;
	}
	
	private DeveloperDao() {}
	
	private Connection connection = null;
	private PreparedStatement pStatement = null;
	private Statement statement = null;
	
	
	@Override
	public void createDeveloper(Developer developer) {
		try {
			connection = edu.northeastern.cs5200.Connection.getConnection();
			String INSERT_PERSON = "INSERT INTO person (id, first_name, last_name, username, password, email, dob) VALUES (?, ?, ?, ?, ?, ?, ?)";
			pStatement = connection.prepareStatement(INSERT_PERSON);
			pStatement.setInt(1, developer.getId());
			pStatement.setString(2, developer.getFirstName());
			pStatement.setString(3, developer.getLastName());
			pStatement.setString(4, developer.getUsername());
			pStatement.setString(5, developer.getPassword());
			pStatement.setString(6, developer.getEmail());;
            Date dob = developer.getDob();
            java.sql.Date dobSql;
            if (dob != null) {
                dobSql = new java.sql.Date(dob.getTime());
            } else dobSql = null;
            pStatement.setDate(7, dobSql);
            pStatement.executeUpdate();
            
            // Insert into Phone
            Collection<Phone> phones = developer.getPhone();
            insertPhone(phones, developer);
            
            // Insert into Address
            Collection<Address> addresses = developer.getAddress();
            insertAddress(addresses, developer);
            
            String INSERT_DEVELOPER = "INSERT INTO developer (id, developer_key) VALUES (?, ?)";
            pStatement = connection.prepareStatement(INSERT_DEVELOPER);
            pStatement.setInt(1, developer.getId());
            pStatement.setString(2, developer.getDeveloperKey());
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
	public Collection<Developer> findAllDevelopers() {
		Collection<Developer> AllDevelopers = new ArrayList<>();
		try {
			connection = edu.northeastern.cs5200.Connection.getConnection();
			statement = connection.createStatement();
			String FIND_ALL_DEVELOPERS = "SELECT * FROM developer";
			ResultSet developers = statement.executeQuery(FIND_ALL_DEVELOPERS);
			while(developers.next()) {
				int id = developers.getInt("id");
				String developerKey = developers.getString("developer_key");
				
				// Find Person with same Id with Developer
				String FIND_PERSON_BY_ID = "SELECT * FROM person WHERE id = ?";
				pStatement = connection.prepareStatement(FIND_PERSON_BY_ID);
				pStatement.setInt(1, id);
				ResultSet person_byId = pStatement.executeQuery();
				if (person_byId.next()) {
					String firstName = person_byId.getString("first_name");
					String lastName = person_byId.getString("last_name");
					String username = person_byId.getString("username");
					String password = person_byId.getString("password");
					String email = person_byId.getString("email");
					Date dob = person_byId.getDate("dob");
					Developer developer = new Developer(id, firstName, lastName, username, password, email, dob, developerKey);
					
					// Find Phone and Address of Person
					String FIND_PHONE_BY_ID = "SELECT * FROM phone WHERE personId = ?";
					pStatement = connection.prepareStatement(FIND_PHONE_BY_ID);
					pStatement.setInt(1, id);
					ResultSet phones_byId = pStatement.executeQuery();
					while (phones_byId.next()) {
						String phone = phones_byId.getString("phone");
						Boolean primary_phone = phones_byId.getBoolean("primary");
						Phone phoneObj = new Phone(phone, primary_phone, developer);
						developer.addPhone(phoneObj);
					}
					
					
					String FIND_ADDRESS_BY_ID = "SELECT * FROM address WHERE personId = ?";
					pStatement = connection.prepareStatement(FIND_ADDRESS_BY_ID);
					pStatement.setInt(1, id);
					ResultSet addresses_byId = pStatement.executeQuery();
					while (addresses_byId.next()) {
						String street1 = addresses_byId.getString("street1");
						String street2 = addresses_byId.getString("street2");
						String city = addresses_byId.getString("city");
						String state = addresses_byId.getString("state");
						String zip = addresses_byId.getString("zip");
						Boolean primary_address = addresses_byId.getBoolean("primary");
						Address addressObj = new Address(street1, street2, city, state, zip, primary_address, developer);
						developer.addAddress(addressObj);
					}
					
					AllDevelopers.add(developer);
				}
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return AllDevelopers;
		
	}

	@Override
	public Developer findDeveloperById(int developerId) {
		Developer developerById = null;
		try {
			connection = edu.northeastern.cs5200.Connection.getConnection();
			String FIND_DEVELOPER_BY_ID = "SELECT * FROM developer WHERE id = ?";
			pStatement = connection.prepareStatement(FIND_DEVELOPER_BY_ID);
			pStatement.setInt(1, developerId);
			ResultSet developer = pStatement.executeQuery();
			if (developer.next()) {
				int id = developer.getInt("id");
				String developerKey = developer.getString("developer_key");
				// Find Person with same id with Developer
				String FIND_PERSON_BY_ID = "SELECT * FROM person WHERE id = ?";
				pStatement = connection.prepareStatement(FIND_PERSON_BY_ID);
				pStatement.setInt(1, id);
				ResultSet person_byId = pStatement.executeQuery();
				if (person_byId.next()) {
					String firstName = person_byId.getString("first_name");
					String lastName = person_byId.getString("last_name");
					String username = person_byId.getString("username");
					String password = person_byId.getString("password");
					String email = person_byId.getString("email");
					Date dob = person_byId.getDate("dob");
					developerById = new Developer(id, firstName, lastName, username, password, email, dob, developerKey);
					
					// find phone and address of person and add to developer
					addPhone(developerById, id);
					
					addAddress(developerById, id);
				}
					
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return developerById;
	}

	@Override
	public Developer findDeveloperByUsername(String username) {
		Developer developerByUsername = null;
		try {
			connection = edu.northeastern.cs5200.Connection.getConnection();
			String FIND_DEVELOPER_BY_USERNAME = "SELECT p.*, d.developer_key FROM person p, developer d WHERE p.id = d.id AND username = ?";
            pStatement = connection.prepareStatement(FIND_DEVELOPER_BY_USERNAME);
            pStatement.setString(1, username);
            ResultSet results = pStatement.executeQuery();
            if (results.next()) {
                int id = results.getInt("id");
                String firstName = results.getString("first_name");
                String lastName = results.getString("last_name");
                String password = results.getString("password");
                String email = results.getString("email");
                Date dob = results.getDate("dob");
                String developerKey = results.getString("developer_key");
                developerByUsername = new Developer(id, firstName, lastName, username, password, email, dob, developerKey);
					
				// Find Phone and Address of Person and add to Developer
				addPhone(developerByUsername, id);
					
				addAddress(developerByUsername, id);
			}
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return developerByUsername;
	}

	@Override
	public Developer findDeveloperByCredentials(String username, String password) {
		Developer developerByCredential = null;
		try {
            connection = edu.northeastern.cs5200.Connection.getConnection();
            String FIND_DEVELOPER_BY_CREDENTIALS = "SELECT p.*, d.developer_key FROM person p, developer d WHERE p.id = d.id AND username = ? AND password = ?";
            pStatement = connection.prepareStatement(FIND_DEVELOPER_BY_CREDENTIALS);
            pStatement.setString( 1, username);
            pStatement.setString(2,password);
            ResultSet results = pStatement.executeQuery();
            if (results.next()) {
                int id = results.getInt("id");
                String firstName = results.getString("firstN_nme");
                String lastName = results.getString("last_name");
                String email = results.getString("email");
                Date dob = results.getDate("dob");
                String developerKey = results.getString("developer_key");
                developerByCredential = new Developer(id, firstName, lastName, username, password, email, dob, developerKey);
                // Find Phone and Address of Person and add to Developer
                addPhone(developerByCredential, id);
                
                addAddress(developerByCredential, id);
            }
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return developerByCredential;
	}

	@Override
	public int updateDeveloper(int developerId, Developer developer) {
		try {
			connection = edu.northeastern.cs5200.Connection.getConnection();
			String UPDATE_PERSON = "UPDATE person SET id = ?, first_name = ?, last_name = ?, username = ?, password = ?, email = ?, dob = ? WHERE id = ?";
			pStatement = connection.prepareStatement(UPDATE_PERSON);
			pStatement.setInt(1, developer.getId());;
			pStatement.setString(2, developer.getFirstName());
			pStatement.setString(3, developer.getLastName());
			pStatement.setString(4, developer.getUsername());
			pStatement.setString(5, developer.getPassword());
			pStatement.setString(6, developer.getEmail());
            Date dob = developer.getDob();
            java.sql.Date dobSql;
            if (dob != null) {
                dobSql = new java.sql.Date(dob.getTime());
            } else dobSql = null;
            pStatement.setDate(7, dobSql);
            pStatement.setInt(8, developerId);
			pStatement.executeUpdate();
			
			String UPDATE_DEVELOPER = "UPDATE developer SET id = ?, developer_key = ? WHERE id = ?";
			pStatement = connection.prepareStatement(UPDATE_DEVELOPER);
			pStatement.setInt(1, developer.getId());;
			pStatement.setString(2, developer.getDeveloperKey());
			pStatement.setInt(3, developerId);
			pStatement.executeUpdate();
			
            // Update Phone
			String DELETE_PHONE = "DELETE FROM phone WHERE personId = ?";
            pStatement = connection.prepareStatement(DELETE_PHONE);
            pStatement.setInt(1, developer.getId());
            pStatement.executeUpdate();
            Collection<Phone> phones = developer.getPhone();
            insertPhone(phones, developer);
            
            
            // Update Address
            String DELETE_ADDRESS = "DELETE FROM address WHERE personId = ?";
            pStatement = connection.prepareStatement(DELETE_ADDRESS);
            pStatement.setInt(1, developer.getId());
            pStatement.executeUpdate();
            Collection<Address> addresses =  developer.getAddress();
            insertAddress(addresses, developer);
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
	public int deleteDeveloper(int developerId) {
		try {
			connection = edu.northeastern.cs5200.Connection.getConnection();
			
			String DELETE_PHONE = "DELETE FROM phone WHERE personId = ?";
            pStatement = connection.prepareStatement(DELETE_PHONE);
            pStatement.setInt(1, developerId);
            pStatement.executeUpdate();
            
            String DELETE_ADDRESS = "DELETE FROM address WHERE personId = ?";
            pStatement = connection.prepareStatement(DELETE_ADDRESS);
            pStatement.setInt(1, developerId);
            pStatement.executeUpdate();
            
            String DELETE_DEVELOPER = "DELETE FROM developer WHERE id = ?";
            pStatement = connection.prepareStatement(DELETE_DEVELOPER);
            pStatement.setInt(1, developerId);
            pStatement.executeUpdate();
            
            String DELETE_PERSON = "DELETE FROM person WHERE id = ?";
            pStatement = connection.prepareStatement(DELETE_PERSON);
            pStatement.setInt(1, developerId);
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
	
	private void insertPhone(Collection<Phone> phones, Developer developer) throws SQLException {
        for (Phone phone : phones) {
        	String INSERT_PHONE = "INSERT INTO phone (id, phone, `primary`, personId) VALUES (?, ?, ?, ?)";
        	pStatement = connection.prepareStatement(INSERT_PHONE);
        	pStatement.setInt(1, phone.getId());
        	pStatement.setString(2, phone.getPhone());
        	pStatement.setBoolean(3, phone.getPrimary());
        	pStatement.setInt(4, developer.getId());
        	pStatement.executeUpdate();
        }
	}
	
	private void insertAddress(Collection<Address> addresses, Developer developer) throws SQLException {
        for (Address address : addresses) {
        	String INSERT_ADDRESS = "INSERT INTO address (id, street1, street2, city, state, zip, `primary`, personId) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        	pStatement = connection.prepareStatement(INSERT_ADDRESS);
        	pStatement.setInt(1, address.getId());
        	pStatement.setString(2, address.getStreet1());
        	pStatement.setString(3, address.getStreet2());
        	pStatement.setString(4, address.getCity());
        	pStatement.setString(5, address.getState());
        	pStatement.setString(6, address.getZip());
        	pStatement.setBoolean(7, address.getPrimary());
        	pStatement.setInt(8, developer.getId());
        	pStatement.executeUpdate();
        }
	}
	
	private void addPhone(Developer developer, int id) throws SQLException {
		String FIND_PHONE_BY_ID = "SELECT * FROM phone WHERE personId = ?";
		pStatement = connection.prepareStatement(FIND_PHONE_BY_ID);
		pStatement.setInt(1, id);
		ResultSet phones_byId = pStatement.executeQuery();
		while (phones_byId.next()) {
			String phone = phones_byId.getString("phone");
			Boolean primary_phone = phones_byId.getBoolean("primary");
			Phone phoneObj = new Phone(phone, primary_phone, developer);
			developer.addPhone(phoneObj);
		}
	}
	
	private void addAddress(Developer developer, int id) throws SQLException {
		String FIND_ADDRESS_BY_ID = "SELECT * FROM address WHERE personId = ?";
		pStatement = connection.prepareStatement(FIND_ADDRESS_BY_ID);
		pStatement.setInt(1, id);
		ResultSet addresses_byId = pStatement.executeQuery();
		while (addresses_byId.next()) {
			String street1 = addresses_byId.getString("street1");
			String street2 = addresses_byId.getString("street2");
			String city = addresses_byId.getString("city");
			String state = addresses_byId.getString("state");
			String zip = addresses_byId.getString("zip");
			Boolean primary_address = addresses_byId.getBoolean("primary");
			Address addressObj = new Address(street1, street2, city, state, zip, primary_address, developer);
			developer.addAddress(addressObj);
		}
	}

}
