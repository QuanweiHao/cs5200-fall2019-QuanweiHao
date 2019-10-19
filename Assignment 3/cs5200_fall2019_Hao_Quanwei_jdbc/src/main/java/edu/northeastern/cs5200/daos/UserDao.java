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
import edu.northeastern.cs5200.models.Phone;
import edu.northeastern.cs5200.models.User;

public class UserDao implements UserImpl {
    private static UserDao instance = null;

    private UserDao() {

    }

    public static UserDao getInstance() {
        if (instance == null) {
            instance = new UserDao();
        }
        return instance;
    }

    private Connection connection = null;
    private Statement statement = null;
    private PreparedStatement pStatement = null;
    
	@Override
	public void createUser(User user) {
		 try {
	            connection = edu.northeastern.cs5200.Connection.getConnection();
	            //Person
	            String INSERT_PERSON = "INSERT INTO person (id, username, password, first_name, last_name, email, dob) VALUES (?, ?, ?, ?, ?, ?, ?)";
	            pStatement = connection.prepareStatement(INSERT_PERSON);
	            pStatement.setInt(1, user.getId());
	            pStatement.setString(2, user.getUsername());
	            pStatement.setString(3, user.getPassword());
	            pStatement.setString(4, user.getFirstName());
	            pStatement.setString(5, user.getLastName());
	            pStatement.setString(6, user.getEmail());
	            Date dob = user.getDob();
	            java.sql.Date dobSql;
	            if (dob != null) {
	                dobSql = new java.sql.Date(dob.getTime());
	            } else dobSql = null;
	            pStatement.setDate(7, dobSql);
	            pStatement.executeUpdate();
	            //Phone
	            Collection<Phone> phones = user.getPhone();
	            insertPhone(user, phones);
	            //Address
	            Collection<Address> addresses =  user.getAddress();
	            insertAddress(user, addresses);
	            //User
	            String INSERT_USER = "INSERT INTO user (id, user_agreement) VALUES (?, ?)";
	            pStatement = connection.prepareStatement(INSERT_USER);
	            pStatement.setInt(1, user.getId());
	            pStatement.setBoolean(2, user.getUserAggrement());
	            pStatement.executeUpdate();
	        } catch (ClassNotFoundException e) {
	            e.printStackTrace();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }


	}

	@Override
	public Collection<User> findAllUsers() {
		Collection<User> users = new ArrayList<>();
        try {
            connection = edu.northeastern.cs5200.Connection.getConnection();
            statement = connection.createStatement();
            //User
            String FIND_ALL_USERS = "SELECT * FROM user";
            ResultSet userResults = statement.executeQuery(FIND_ALL_USERS);
            while (userResults.next()) {
                int id = userResults.getInt("personId");
                Boolean userAgreement = userResults.getInt("user_agreement") == 1;
                //Person
                String FIND_PERSON_BY_ID = "SELECT * FROM person WHERE id=?";
                pStatement = connection.prepareStatement(FIND_PERSON_BY_ID);
                pStatement.setInt(1, id);
                ResultSet personResults = pStatement.executeQuery();
                if (personResults.next()) {
                    String firstName = personResults.getString("first_name");
                    String lastName = personResults.getString("last_name");
                    String username = personResults.getString("username");
                    String password = personResults.getString("password");
                    String email = personResults.getString("email");
                    Date dob = personResults.getDate("dob");
                    User user = new User(id, firstName, lastName, username, password, email, dob, userAgreement);
                    //Phone
                    String FIND_ALL_PHONE_BY_PERSON_ID = "SELECT * FROM phone WHERE personId=?";
                    pStatement = connection.prepareStatement(FIND_ALL_PHONE_BY_PERSON_ID);
                    pStatement.setInt(1, id);
                    ResultSet tempResults = pStatement.executeQuery();
                    addPhone(user, tempResults);
                    //Address
                    String FIND_ALL_ADDRESS_BY_PERSON_ID = "SELECT * FROM address WHERE personId=?";
                    pStatement = connection.prepareStatement(FIND_ALL_ADDRESS_BY_PERSON_ID);
                    pStatement.setInt(1, id);
                    tempResults = pStatement.executeQuery();
                    addAddress(user, tempResults);
                    users.add(user);
                }
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return users;
	}

	@Override
	public User findUserById(int userId) {
       try {
            connection = edu.northeastern.cs5200.Connection.getConnection();
            String FIND_USER_BY_ID = "SELECT * FROM user WHERE id=?";
            pStatement = connection.prepareStatement(FIND_USER_BY_ID);
            pStatement.setInt(1, userId);
            //User
            ResultSet userResults = pStatement.executeQuery();
            if (userResults.next()) {
                int id = userResults.getInt("personId");
                Boolean userAgreement = userResults.getInt("user_agreement") == 1;
                //Person
                String FIND_PERSON_BY_ID = "SELECT * FROM person WHERE id=?";
                pStatement = connection.prepareStatement(FIND_PERSON_BY_ID);
                pStatement.setInt(1, id);
                ResultSet personResults = pStatement.executeQuery();
                if (personResults.next()) {
                    String firstName = personResults.getString("first_name");
                    String lastName = personResults.getString("last_name");
                    String username = personResults.getString("username");
                    String password = personResults.getString("password");
                    String email = personResults.getString("email");
                    Date dob = personResults.getDate("dob");
                    User user = new User(id, firstName, lastName, username, password, email, dob, userAgreement);
                    //Phone
                    String FIND_ALL_PHONE_BY_PERSON_ID = "SELECT * FROM phone WHERE personId=?";
                    pStatement = connection.prepareStatement(FIND_ALL_PHONE_BY_PERSON_ID);
                    pStatement.setInt(1, id);
                    ResultSet tempResults = pStatement.executeQuery();
                    addPhone(user, tempResults);
                    //Address
                    String FIND_ALL_ADDRESS_BY_PERSON_ID = "SELECT * FROM address WHERE personId=?";
                    pStatement = connection.prepareStatement(FIND_ALL_ADDRESS_BY_PERSON_ID);
                    pStatement.setInt(1, id);
                    tempResults = pStatement.executeQuery();
                    addAddress(user, tempResults);
                    return user;
                }
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
	}

	@Override
	public User findUserByUsername(String username) {
		try {
            connection = edu.northeastern.cs5200.Connection.getConnection();
            String FIND_USER_BY_USERNAME = "SELECT * FROM person p , user u WHERE p.id = u.id AND username = ?";
            pStatement = connection.prepareStatement(FIND_USER_BY_USERNAME);
            pStatement.setString( 1, username);
            ResultSet results = pStatement.executeQuery();
            if (results.next()) {
                int id = results.getInt("id");
                String firstName = results.getString("first_name");
                String lastName = results.getString("last_name");
                String password = results.getString("password");
                String email = results.getString("email");
                Date dob = results.getDate("dob");
                Boolean userAgreement = results.getInt("user_agreement") == 1;
                User user = new User(id, firstName, lastName, username, password, email, dob, userAgreement);
                //Phone
                String FIND_ALL_PHONE_BY_PERSON_ID = "SELECT * FROM phone WHERE personId=?";
                pStatement = connection.prepareStatement(FIND_ALL_PHONE_BY_PERSON_ID);
                pStatement.setInt(1, id);
                ResultSet tempResults = pStatement.executeQuery();
                addPhone(user, tempResults);
                //Address
                String FIND_ALL_ADDRESS_BY_PERSON_ID = "SELECT * FROM address WHERE personId=?";
                pStatement = connection.prepareStatement(FIND_ALL_ADDRESS_BY_PERSON_ID);
                pStatement.setInt(1, id);
                tempResults = pStatement.executeQuery();
                addAddress(user, tempResults);
                return user;
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
}

	@Override
	public User findUserByCredentials(String username, String password) {
        try {
            connection = edu.northeastern.cs5200.Connection.getConnection();
            String FIND_USER_BY_CREDENTIALS = "SELECT * FROM person p, user u WHERE p.id = u.personId AND username = ? AND password = ?";
            pStatement = connection.prepareStatement(FIND_USER_BY_CREDENTIALS);
            pStatement.setString( 1, username);
            pStatement.setString(2,password);
            ResultSet results = pStatement.executeQuery();
            if (results.next()) {
                int id = results.getInt("id");
                String firstName = results.getString("first_name");
                String lastName = results.getString("last_name");
                String email = results.getString("email");
                Date dob = results.getDate("dob");
                Boolean userAgreement = results.getInt("user_agreement") == 1;
                User user = new User(id, firstName, lastName, username, password, email, dob, userAgreement);
                //Phone
                String FIND_ALL_PHONE_BY_PERSON_ID = "SELECT * FROM phone WHERE personId=?";
                pStatement = connection.prepareStatement(FIND_ALL_PHONE_BY_PERSON_ID);
                pStatement.setInt(1, id);
                ResultSet tempResults = pStatement.executeQuery();
                addPhone(user, tempResults);
                //Address
                String FIND_ALL_ADDRESS_BY_PERSON_ID = "SELECT * FROM address WHERE personId=?";
                pStatement = connection.prepareStatement(FIND_ALL_ADDRESS_BY_PERSON_ID);
                pStatement.setInt(1, id);
                tempResults = pStatement.executeQuery();
                addAddress(user, tempResults);
                return user;
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
	}

	@Override
	public int updateUser(int userId, User user) {
        try {
            connection = edu.northeastern.cs5200.Connection.getConnection();
            String UPDATE_PERSON = "UPDATE person SET first_name=?, last_name=?, username=?, password=?, email=?, dob=? WHERE id=?";
            pStatement = connection.prepareStatement(UPDATE_PERSON);
            pStatement.setString(1, user.getFirstName());
            pStatement.setString(2, user.getLastName());
            pStatement.setString(3, user.getUsername());
            pStatement.setString(4, user.getPassword());
            pStatement.setString(5, user.getEmail());
            Date dob = user.getDob();
            java.sql.Date dobSql;
            if (dob != null) {
                dobSql = new java.sql.Date(dob.getTime());
            } else dobSql = null;
            pStatement.setDate(6, dobSql);
            pStatement.setInt(7, userId);
            pStatement.executeUpdate();
            String UPDATE_USER = "UPDATE user SET user_agreement=? WHERE id = ?";
            pStatement = connection.prepareStatement(UPDATE_USER);
            pStatement.setBoolean(1, user.getUserAggrement());
            pStatement.setInt(2, userId);
            pStatement.executeUpdate();
            //Phone
            String DELETE_PHONE = "DELETE FROM phone WHERE personId=?";
            pStatement = connection.prepareStatement(DELETE_PHONE);
            pStatement.setInt(1, user.getId());
            pStatement.executeUpdate();
            Collection<Phone> phones = user.getPhone();
            insertPhone(user, phones);
            //Address
            String DELETE_ADDRESS = "DELETE FROM address WHERE personId=?";
            pStatement = connection.prepareStatement(DELETE_ADDRESS);
            pStatement.setInt(1, user.getId());
            pStatement.executeUpdate();
            Collection<Address> addresses =  user.getAddress();
            insertAddress(user, addresses);
            return 1;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 0;
	}

	@Override
	public int deleteUser(int userId) {
        try {
            connection = edu.northeastern.cs5200.Connection.getConnection();
            String DELETE_PHONE = "DELETE FROM phone WHERE personId=?";
            pStatement = connection.prepareStatement(DELETE_PHONE);
            pStatement.setInt(1, userId);
            pStatement.executeUpdate();
            String DELETE_ADDRESS = "DELETE FROM address WHERE personId=?";
            pStatement = connection.prepareStatement(DELETE_ADDRESS);
            pStatement.setInt(1, userId);
            pStatement.executeUpdate();
            String DELETE_USER = "DELETE FROM user WHERE id=?";
            pStatement = connection.prepareStatement(DELETE_USER);
            pStatement.setInt(1, userId);
            pStatement.executeUpdate();
            String DELETE_PERSON = "DELETE FROM person WHERE id=?";
            pStatement = connection.prepareStatement(DELETE_PERSON);
            pStatement.setInt(1, userId);
            pStatement.executeUpdate();
            return 1;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
	}
	
    private void addAddress(User user, ResultSet tempResults) throws SQLException {
        while (tempResults.next()) {
            String street1 = tempResults.getString("street1");
            String street2 = tempResults.getString("street2");
            String city = tempResults.getString("city");
            String state = tempResults.getString("state");
            String zip = tempResults.getString("zip");
            Boolean isPrimary = (tempResults.getInt("primary") == 0 ? false : true);
            Address address = new Address(street1, street2, city, state, zip, isPrimary, user);
            user.addAddress(address);
        }
    }

    private void addPhone(User user, ResultSet tempResults) throws SQLException {
        while (tempResults.next()) {
            String phone = tempResults.getString("phone");
            Boolean isPrimary = (tempResults.getInt("primary") == 0) ? false : true;
            Phone phoneObj = new Phone(phone, isPrimary, user);
            user.addPhone(phoneObj);
        }
    }

    private void insertAddress(User user, Collection<Address> addresses) throws SQLException {
        for (Address address : addresses) {
        	String INSERT_ADDRESS = "INSERT INTO address (street1, street2 ,city , state, zip, primary, personId) VALUES (?, ?, ?, ?, ?, ?, ?)";
            pStatement = connection.prepareStatement(INSERT_ADDRESS);
            pStatement.setString(1, address.getStreet1());
            pStatement.setString(2, address.getStreet2());
            pStatement.setString(3, address.getCity());
            pStatement.setString(4, address.getState());
            pStatement.setString(5, address.getZip());
            pStatement.setBoolean(6, address.getPrimary());
            pStatement.setInt(7, user.getId());
            pStatement.executeUpdate();
        }
    }

    private void insertPhone(User user, Collection<Phone> phones) throws SQLException {
        for (Phone phone : phones) {
        	String INSERT_PHONE = "INSERT INTO phone (phone, primary, personId) VALUES (?, ?, ?)";
            pStatement = connection.prepareStatement(INSERT_PHONE);
            pStatement.setString(1, phone.getPhone());
            pStatement.setBoolean(2, phone.getPrimary());
            pStatement.setInt(3, user.getId());
            pStatement.executeUpdate();
        }
    }

}
