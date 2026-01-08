package com.managemed.managemedapp.dao;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.time.LocalDate;
import java.util.Properties;
import com.managemed.managemedapp.model.Product;
import com.managemed.managemedapp.model.User;
import com.managemed.managemedapp.security.PasswordUtil;

public class DAOImpl {

	private String result;
	private int row_exist = 0;
	private int price;
	private Connection con;
	private PreparedStatement st;
	private static DAOImpl obj;
	private Properties dbProperties;

	public static DAOImpl getInstance() {
		if (obj == null) {
			obj = new DAOImpl();
		}
		return obj;
	}

	private DAOImpl() {
		try (InputStream input = getClass().getClassLoader().getResourceAsStream("db.properties")) {
			dbProperties = new Properties();
			dbProperties.load(input);
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	public String Connection() throws ClassNotFoundException, SQLException {
		Class.forName(dbProperties.getProperty("db.Driver"));
		String dbURL = dbProperties.getProperty("db.URL");
		String user = dbProperties.getProperty("db.User");
		String pwd = dbProperties.getProperty("db.Pwd");
		con = DriverManager.getConnection(dbURL, user, pwd);
		if (con == null) {
			result = "Connection Error";
		} else {
			result = "Connection Established";
		}
		return result;
	}

	// public ResultSet displayAll() throws SQLException {
	// 	String query1 = "select * from stock where quantity>0";
	// 	st = con.prepareStatement(query1);
	// 	ResultSet rs = st.executeQuery();
	// 	return rs;
	// }

	public ResultSet display(String product) throws SQLException {
		String query1 = "select product, quantity, unitprice, description from stock where UPPER(product) like ?";
		st = con.prepareStatement(query1);
		st.setString(1, "%" + product.toUpperCase() + "%");
		ResultSet rs = st.executeQuery();
		return rs;
	}

	public int paymentDetails(String username) throws SQLException {
		String query1 = "select SUM(PRICE) from cart where username = ? and order_id is null and cart_status is null";
		int count = 0;
		st = con.prepareStatement(query1);
		st.setString(1, username);
		ResultSet rs = st.executeQuery();
		if (rs.next())
			count = rs.getInt(1);
		return count;
	}

	// public int proceedPayment(LocalDate date, String username) throws SQLException {
	// 	String query1 = "select order_id from ORDERTB where order_status=? and username=?";
	// 	int result = 0;
	// 	st = con.prepareStatement(query1);
	// 	st.setString(1, "PENDING");
	// 	st.setString(2, username);
	// 	ResultSet rs = st.executeQuery();
	// 	if (rs.next()) {
	// 		int order_id = rs.getInt(1);
	// 		String query2 = "select payment_id from payment where order_id = ? and payment_status=?";
	// 		st = con.prepareStatement(query2);
	// 		st.setInt(1, order_id);
	// 		st.setString(2, "PENDING");
	// 		if (st.executeQuery().next()) {
	// 			String query4 = "update payment set payment_date=? where order_id=?";
	// 			st = con.prepareStatement(query4);
	// 			st.setObject(1, date);
	// 			st.setInt(2, order_id);
	// 			result = st.executeUpdate();
	// 		} else {
	// 			String query3 = "{call add_payment(?,?,?,?)}";
	// 			CallableStatement st = con.prepareCall(query3);
	// 			st.setInt(1, order_id);
	// 			st.setString(2, "CARD");
	// 			st.setString(3, "PENDING");
	// 			st.registerOutParameter(4, java.sql.Types.NUMERIC);
	// 			st.execute();
	// 			result = st.getInt(4);
	// 		}
	// 	}

	// 	return result;
	// }

	public int proceedSale(String username) throws SQLException {
		String query1 = "{call payment_checkout(?,?)}";
		CallableStatement st = con.prepareCall(query1);
		st.setString(1, username);
		st.registerOutParameter(2, java.sql.Types.NUMERIC);
		st.execute();
		return st.getInt(2);
	}

	// public int createOrder(String username, int orderQty, String orderStatus, LocalDate date) throws SQLException {
	// 	int result = 0;
	// 	String query1 = "select * from ordertb where username = ? and order_status=?";
	// 	st = con.prepareStatement(query1);
	// 	st.setString(1, username);
	// 	st.setString(2, "PENDING");
	// 	ResultSet rs = st.executeQuery();
	// 	if (rs.next()) {
	// 		String query2 = "update ORDERTB set order_qty=?, order_date=? where username=?";
	// 		st = con.prepareStatement(query2);
	// 		st.setInt(1, orderQty);
	// 		st.setObject(2, date);
	// 		st.setString(3, username);
	// 		result = st.executeUpdate();
	// 	} else {
	// 		String query2 = "{call add_order(?,?,?,?)}";
	// 		CallableStatement st = con.prepareCall(query2);
	// 		st.setString(1, username);
	// 		st.setInt(2, orderQty);
	// 		st.setString(3, orderStatus);
	// 		st.registerOutParameter(4, java.sql.Types.NUMERIC);
	// 		st.execute();
	// 		result = st.getInt(4);
	// 	}
	// 	return result;
	// }

	// public void reserveCart(String username) throws SQLException {
	// 	String query1 = "select quantity, product from cart where username=? and order_id is null";
	// 	st = con.prepareStatement(query1);
	// 	st.setString(1, username);
	// 	ResultSet rs = st.executeQuery();
	// 	while (rs.next()) {
	// 		int cartQuantity = rs.getInt(1);
	// 		String product = rs.getString(2);
	// 		String query2 = "select quantity from stock where product=?";
	// 		st = con.prepareStatement(query2);
	// 		st.setString(1, product);
	// 		ResultSet rs2 = st.executeQuery();
	// 		if (rs2.next()) {
	// 			int stockQuantity = rs2.getInt(1);
	// 			if (stockQuantity > cartQuantity) {
	// 				String query3 = "update stock set quantity = quantity - ? where product = ?";
	// 				st = con.prepareStatement(query3);
	// 				st.setInt(1, cartQuantity);
	// 				st.setString(2, product);
	// 				st.executeUpdate();
	// 				String query4 = "update cart set cart_status=? where username=? and product=? and cart_status is null";
	// 				st = con.prepareStatement(query4);
	// 				st.setString(1, "RESERVED");
	// 				st.setString(2, username);
	// 				st.setString(3, product);
	// 				st.executeUpdate();
	// 			} else {
	// 				String query5 = "update cart set cart_status=? where username=? and product=? and cart_status is null";
	// 				st = con.prepareStatement(query5);
	// 				st.setString(1, "UNAVAILABLE");
	// 				st.setString(2, username);
	// 				st.setString(3, product);
	// 				st.executeUpdate();
	// 			}
	// 		}
	// 	}
	// }

	// public void restoreStock(String username) throws SQLException {
	// 	String query1 = "select quantity, product from cart where username=? and order_id is null and cart_status=?";
	// 	st = con.prepareStatement(query1);
	// 	st.setString(1, username);
	// 	st.setString(2, "RESERVED");
	// 	ResultSet rs = st.executeQuery();
	// 	while (rs.next()) {
	// 		int cartQuantity = rs.getInt(1);
	// 		String product = rs.getString(2);
	// 		String query2 = "update stock set quantity = quantity + ? where product=?";
	// 		st = con.prepareStatement(query2);
	// 		st.setInt(1, cartQuantity);
	// 		st.setString(2, product);
	// 		st.executeUpdate();

	// 		String query3 = "update cart set cart_status = null where username = ? and product=? and cart_status in (?,?)";
	// 		st = con.prepareStatement(query3);
	// 		st.setString(1, username);
	// 		st.setString(2, product);
	// 		st.setString(3, "RESERVED");
	// 		st.setString(4, "UNAVAILABLE");
	// 		st.executeUpdate();
	// 	}
	// }

	// public ResultSet getRole(String username) throws SQLException {
	// 	String query = "select role_name from roles where role_id = (select role_id from usertb where username = ?)";
	// 	st = con.prepareStatement(query);
	// 	st.setString(1, username);
	// 	return st.executeQuery();
	// }

	public ResultSet getUsers() throws SQLException {
		String query = "select username, firstname, lastname, roles.role_name, password \r\n" + "from usertb \r\n"
				+ "left join roles \r\n" + "on usertb.role_id = roles.role_id \r\n" + "where roles.role_name!=?";
		st = con.prepareStatement(query);
		st.setString(1, "Customer");
		return st.executeQuery();
	}

	public ResultSet getRoles() throws SQLException {
		String query = "select * from roles where role_name!=?";
		st = con.prepareStatement(query);
		st.setString(1, "Customer");
		return st.executeQuery();
	}

	// public void addRole(String role) throws SQLException {
	// 	String query1 = "{call add_role(?)}";
	// 	CallableStatement st = con.prepareCall(query1);
	// 	st.setString(1, role);
	// 	st.execute();
	// }

	// public void editRole(String role, int roleId) throws SQLException {
	// 	String query1 = "update roles set role_name = ? where role_id=? ";
	// 	st = con.prepareStatement(query1);
	// 	st.setString(1, role);
	// 	st.setInt(2, roleId);
	// 	st.execute();
	// }

	// public void deleteRole(int roleId) throws SQLException {
	// 	String query1 = "delete from roles where role_id=? ";
	// 	st = con.prepareStatement(query1);
	// 	st.setInt(1, roleId);
	// 	st.execute();
	// }

	public void editUser(String firstname, String lastname, String username, String roleName) throws SQLException {
		String query1 = "update usertb set firstname = ?, lastname=?, role_id = (select role_id from roles where role_name=?) where username=?";
		st = con.prepareStatement(query1);
		st.setString(1, firstname);
		st.setString(2, lastname);
		st.setString(3, roleName);
		st.setString(4, username);
		st.execute();
	}

	public void deleteUser(String username) throws SQLException {
		String query1 = "delete from usertb where username=? ";
		st = con.prepareStatement(query1);
		st.setString(1, username);
		st.execute();
	}

	// public ResultSet getOrders(String username) throws SQLException {
	// 	String query = "select product, quantity, price, o.order_date from cart c\r\n" + "inner join ordertb o\r\n"
	// 			+ "on c.order_id = o.order_id\r\n" + "and cart_status='PURCHASED' \r\n" + "and c.username=?";
	// 	st = con.prepareStatement(query);
	// 	st.setString(1, username);
	// 	return st.executeQuery();
	// }
	
	// public int getPaymentStatus(String username) throws SQLException {
	// 	int payment_id = 0;
	// 	String query1 = "select order_id from ordertb where username=? and order_status=?";
	// 	st = con.prepareStatement(query1);
	// 	st.setString(1, username);
	// 	st.setString(2, "PENDING");
	// 	ResultSet rs1 = st.executeQuery();
	// 	if (rs1.next()) {
	// 		int order_id = rs1.getInt(1);
	// 		String query2 = "select payment_id from payment where order_id=? and payment_status = ?";
	// 		st = con.prepareStatement(query2);
	// 		st.setInt(1, order_id);
	// 		st.setString(2, "PENDING");
	// 		ResultSet rs = st.executeQuery();
	// 		if(rs.next()) {
	// 			payment_id = rs.getInt(1);
	// 		}
	// 	}
		 
	// 	return payment_id;
	// }
	
	// public void setPaymentStatus(String status, int payment_id) throws SQLException {
	// 	System.out.println("Inside set payment status");
	// 	String query = "update payment set payment_status=? where payment_id=?"; 
	// 	st = con.prepareStatement(query);
	// 	st.setString(1, status);
	// 	st.setInt(2, payment_id);
	// 	st.executeUpdate();
		
	// }
	
	public ResultSet getUser(String username) throws SQLException {
		String query = "select firstname, lastname from usertb where username=?"; 
		st = con.prepareStatement(query);
		st.setString(1, username);
		return st.executeQuery();
	}
	

}

