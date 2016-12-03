package com.esteeminfo.proauto.dao;
//package com.esteeminfo.prouto.dao;
//
//import java.sql.Connection;
//import java.sql.Date;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.sql.Statement;
//import java.text.SimpleDateFormat;
//import java.util.ArrayList;
//import java.util.Calendar;
//import java.util.List;
//import java.util.Map;
//
//import com.esteeminfo.proauto.entity.CustomerDTO;
//import com.esteeminfo.proauto.entity.EmployeeDTO;
//import com.esteeminfo.proauto.entity.VendorDTO;
//
//public class CommonDAO extends BaseDAO {
//	public static SimpleDateFormat ui_date_format =  new SimpleDateFormat("MM/dd/yyyy");
//
//	public static String authenticateUser(String uname, String pwd) throws SQLException {
//
//		String retString = null;
//		Connection conn = getConnection();
//		Statement st = conn.createStatement();
//		ResultSet res = st
//				.executeQuery("SELECT * FROM EMPLOYEE WHERE employee_name='" + uname + "' and password='" + pwd + "'");
//		while (res.next()) {
//			String role = res.getString("role");
//			retString = role;
//		}
//		conn.close();
//		return retString;
//	}
//
//	public static boolean userExist(String uname) throws SQLException {
//
//		boolean userExist = false;
//		Connection conn = getConnection();
//		Statement st = conn.createStatement();
//		ResultSet res = st.executeQuery("SELECT * FROM EMPLOYEE WHERE employee_name='" + uname + "'");
//		while (res.next()) {
//			userExist = true;
//		}
//		conn.close();
//		return userExist;
//	}
//
//	public static boolean vendorExist(String vName) throws SQLException {
//
//		boolean vendorExist = false;
//		Connection conn = getConnection();
//		Statement st = conn.createStatement();
//		ResultSet res = st.executeQuery("SELECT * FROM vendor WHERE vendor_name='" + vName + "'");
//		while (res.next()) {
//			vendorExist = true;
//		}
//		conn.close();
//		return vendorExist;
//	}
//
//	public static VendorDTO retrieveVendor(String vName) {
//		Connection conn;
//		Statement stmt = null;
//
//		VendorDTO vendor = null;
//		try {
//
//			if (!vendorExist(vName))
//				return null;
//
//			conn = getConnection();
//			stmt = conn.createStatement();
//			String query = "SELECT vendor_id,vendor_name,address,city,state,zip_code,create_date,name_one,phone_one,email_one,name_two,phone_two,email_two,name_three,"
//					+ "phone_three,email_three,name_four,phone_four,email_four,name_five,phone_five,email_five,name_six,phone_six,email_six,name_seven,phone_seven,email_seven,"
//					+ "name_eight,phone_eight,email_eight,name_nine,phone_nine,email_nine,name_ten,phone_ten,email_ten from vendor where vendor_name='"
//					+ vName + "'";
//			ResultSet rs = stmt.executeQuery(query);
//			while (rs.next()) {
//				// Retrieve by column name
//				vendor = new VendorDTO();
//				int id = rs.getInt("vendor_id");
//				String vendorName = rs.getString("vendor_name");
//				String address = rs.getString("address");
//
//				String nameOne = rs.getString("name_one");
//				String phoneOne = rs.getString("phone_one");
//				String emailOne = rs.getString("email_one");
//
//				String nametwo = rs.getString("name_two");
//				String phonetwo = rs.getString("phone_two");
//				String emailtwo = rs.getString("email_two");
//
//				String namethree = rs.getString("name_three");
//				String phonethree = rs.getString("phone_three");
//				String emailthree = rs.getString("email_three");
//
//				String namefour = rs.getString("name_four");
//				String phonefour = rs.getString("phone_four");
//				String emailfour = rs.getString("email_four");
//
//				String namefive = rs.getString("name_five");
//				String phonefive = rs.getString("phone_five");
//				String emailfive = rs.getString("email_five");
//
//				String namesix = rs.getString("name_six");
//				String phonesix = rs.getString("phone_six");
//				String emailsix = rs.getString("email_six");
//
//				String nameseven = rs.getString("name_seven");
//				String phoneseven = rs.getString("phone_seven");
//				String emailseven = rs.getString("email_seven");
//
//				String nameeight = rs.getString("name_eight");
//				String phoneeight = rs.getString("phone_eight");
//				String emaileight = rs.getString("email_eight");
//
//				String namenine = rs.getString("name_nine");
//				String phonenine = rs.getString("phone_nine");
//				String emailnine = rs.getString("email_nine");
//
//				String nameten = rs.getString("name_ten");
//				String phoneten = rs.getString("phone_ten");
//				String emailten = rs.getString("email_ten");
//
//				// Display values
//				System.out.print("ID: " + id);
//				System.out.print("name: " + vendorName);
//				System.out.print("addres: " + address);
//
//				vendor.setVendorId(id);
//				vendor.setVendorName(vendorName);
//				vendor.setAddress(address);
//
//				vendor.setName_one(nameOne);
//				vendor.setPhone_one(phoneOne);
//				vendor.setEmail_one(emailOne);
//
//				vendor.setName_two(nametwo);
//				vendor.setPhone_two(phonetwo);
//				vendor.setEmail_two(emailtwo);
//
//				vendor.setName_three(namethree);
//				vendor.setPhone_three(phonethree);
//				vendor.setEmail_three(emailthree);
//
//				vendor.setName_four(namefour);
//				vendor.setPhone_four(phonefour);
//				vendor.setEmail_four(emailfour);
//
//				vendor.setName_five(namefive);
//				vendor.setPhone_five(phonefive);
//				vendor.setEmail_five(emailfive);
//
//				vendor.setName_six(namesix);
//				vendor.setPhone_six(phonesix);
//				vendor.setEmail_six(emailsix);
//
//				vendor.setName_seven(nameseven);
//				vendor.setPhone_seven(phoneseven);
//				vendor.setEmail_seven(emailseven);
//
//				vendor.setName_eight(nameeight);
//				vendor.setPhone_eight(phoneeight);
//				vendor.setEmail_eight(emaileight);
//
//				vendor.setName_nine(namenine);
//				vendor.setPhone_nine(phonenine);
//				vendor.setEmail_nine(emailnine);
//
//				vendor.setName_ten(nameten);
//				vendor.setPhone_ten(phoneten);
//				vendor.setEmail_ten(emailten);
//
//			}
//			rs.close();
//			conn.close();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		return vendor;
//	}
//
//	public static List<VendorDTO> retrieveAllVendors(String vendorSearched) {
//		Connection conn;
//		Statement stmt = null;
//
//		List<VendorDTO> vendors = new ArrayList<VendorDTO>();
//		try {
//
//			conn = getConnection();
//			stmt = conn.createStatement();
//			String query = "SELECT vendor_id,vendor_name,address,name_one,phone_one,email_one from vendor";
//			if (vendorSearched != null && vendorSearched.length() > 0) {
//				query += " where vendor_name LIKE '" + vendorSearched + "%'";
//			}
//			ResultSet rs = stmt.executeQuery(query);
//			while (rs.next()) {
//				// Retrieve by column name
//				VendorDTO eachVendor = new VendorDTO();
//				int id = rs.getInt("vendor_id");
//				String vendorName = rs.getString("vendor_name");
//				String address = rs.getString("address");
//
//				String nameOne = rs.getString("name_one");
//				String phoneOne = rs.getString("phone_one");
//				String emailOne = rs.getString("email_one");
//
//				eachVendor.setVendorId(id);
//				eachVendor.setVendorName(vendorName);
//				eachVendor.setAddress(address);
//
//				eachVendor.setName_one(nameOne);
//				eachVendor.setPhone_one(phoneOne);
//				eachVendor.setEmail_one(emailOne);
//
//				vendors.add(eachVendor);
//
//			}
//			rs.close();
//			conn.close();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		return vendors;
//	}
//
//	private static boolean customerExist(String cName) throws SQLException {
//		boolean customerExist = false;
//		Connection conn = getConnection();
//		Statement st = conn.createStatement();
//		ResultSet res = st.executeQuery("SELECT * FROM customer WHERE customer_name='" + cName + "'");
//		while (res.next()) {
//			customerExist = true;
//		}
//		conn.close();
//		return customerExist;
//	}
//
//	public static void registerVendor(String create, String vid, String vName, String vAddress, String nameOne,
//			String phoneOne, String emailOne, String nameTwo, String phonTwo, String emailTwo, String namethree,
//			String phonethree, String emailthree, String namefour, String phonefour, String emailfour, String namefive,
//			String phonefive, String emailfive, String namesix, String phonesix, String emailsix, String nameseven,
//			String phoneseven, String emailseven, String nameeight, String phoneeight, String emaileight,
//			String namenine, String phonenine, String emailnine, String nameten, String phoneten, String emailten)
//			throws Exception {
//		Connection conn;
//		boolean registered = false;
//		try {
//
//			Calendar calendar = Calendar.getInstance();
//			java.sql.Date currentTime = new java.sql.Date(calendar.getTime().getTime());
//			conn = getConnection();
//			boolean vendorExist = vendorExist(vName);
//			if (create.equalsIgnoreCase("true") && vendorExist) {
//				throw new Exception("Vendor already exist. Please select from existing Vendors and update");
//			}
//			if (create.equalsIgnoreCase("false") && vid != null) {
//
//				String query = "UPDATE VENDOR set vendor_name=?,address=?, create_date=?, name_one=?,phone_one=?,email_one=?,name_two=?,phone_two=?,email_two=?,name_three=?,"
//						+ "phone_three=?,email_three=?,name_four=?,phone_four=?,email_four=?,name_five=?,phone_five=?,email_five=?,name_six=?,phone_six=?,email_six=?,name_seven=?,phone_seven=?,email_seven=?,"
//						+ "name_eight=?,phone_eight=?,email_eight=?,name_nine=?,phone_nine=?,email_nine=?,name_ten=?,phone_ten=?,email_ten=? where vendor_id="
//						+ vid;
//				PreparedStatement preparedStmt = conn.prepareStatement(query);
//				preparedStmt.setString(1, vName);
//				preparedStmt.setString(2, vAddress);
//				preparedStmt.setDate(3, currentTime);
//
//				preparedStmt.setString(4, nameOne);
//				preparedStmt.setString(5, phoneOne);
//				preparedStmt.setString(6, emailOne);
//
//				preparedStmt.setString(7, nameTwo);
//				preparedStmt.setString(8, phonTwo);
//				preparedStmt.setString(9, emailTwo);
//
//				preparedStmt.setString(10, namethree);
//				preparedStmt.setString(11, phonethree);
//				preparedStmt.setString(12, emailthree);
//
//				preparedStmt.setString(13, namefour);
//				preparedStmt.setString(14, phonefour);
//				preparedStmt.setString(15, emailfour);
//
//				preparedStmt.setString(16, namefive);
//				preparedStmt.setString(17, phonefive);
//				preparedStmt.setString(18, emailfive);
//
//				preparedStmt.setString(19, namesix);
//				preparedStmt.setString(20, phonesix);
//				preparedStmt.setString(21, emailsix);
//
//				preparedStmt.setString(22, nameseven);
//				preparedStmt.setString(23, phoneseven);
//				preparedStmt.setString(24, emailseven);
//
//				preparedStmt.setString(25, nameeight);
//				preparedStmt.setString(26, phoneeight);
//				preparedStmt.setString(27, emaileight);
//
//				preparedStmt.setString(28, namenine);
//				preparedStmt.setString(29, phonenine);
//				preparedStmt.setString(30, emailnine);
//
//				preparedStmt.setString(31, nameten);
//				preparedStmt.setString(32, phoneten);
//				preparedStmt.setString(33, emailten);
//
//				int count = preparedStmt.executeUpdate();
//				registered = (count > 0);
//
//			} else {
//				String query = "INSERT INTO VENDOR(vendor_name,address, create_date, name_one,phone_one,email_one,name_two,phone_two,email_two,name_three,"
//						+ "phone_three,email_three,name_four,phone_four,email_four,name_five,phone_five,email_five,name_six,phone_six,email_six,name_seven,phone_seven,email_seven,"
//						+ "name_eight,phone_eight,email_eight,name_nine,phone_nine,email_nine,name_ten,phone_ten,email_ten) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
//				PreparedStatement preparedStmt = conn.prepareStatement(query);
//				preparedStmt.setString(1, vName);
//				preparedStmt.setString(2, vAddress);
//				preparedStmt.setDate(3, currentTime);
//
//				preparedStmt.setString(4, nameOne);
//				preparedStmt.setString(5, phoneOne);
//				preparedStmt.setString(6, emailOne);
//
//				preparedStmt.setString(7, nameTwo);
//				preparedStmt.setString(8, phonTwo);
//				preparedStmt.setString(9, emailTwo);
//
//				preparedStmt.setString(10, namethree);
//				preparedStmt.setString(11, phonethree);
//				preparedStmt.setString(12, emailthree);
//
//				preparedStmt.setString(13, namefour);
//				preparedStmt.setString(14, phonefour);
//				preparedStmt.setString(15, emailfour);
//
//				preparedStmt.setString(16, namefive);
//				preparedStmt.setString(17, phonefive);
//				preparedStmt.setString(18, emailfive);
//
//				preparedStmt.setString(19, namesix);
//				preparedStmt.setString(20, phonesix);
//				preparedStmt.setString(21, emailsix);
//
//				preparedStmt.setString(22, nameseven);
//				preparedStmt.setString(23, phoneseven);
//				preparedStmt.setString(24, emailseven);
//
//				preparedStmt.setString(25, nameeight);
//				preparedStmt.setString(26, phoneeight);
//				preparedStmt.setString(27, emaileight);
//
//				preparedStmt.setString(28, namenine);
//				preparedStmt.setString(29, phonenine);
//				preparedStmt.setString(30, emailnine);
//
//				preparedStmt.setString(31, nameten);
//				preparedStmt.setString(32, phoneten);
//				preparedStmt.setString(33, emailten);
//
//				int count = preparedStmt.executeUpdate();
//				registered = (count > 0);
//			}
//
//			conn.close();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//	}
//
//	public static CustomerDTO retrieveCustomer(String cName) {
//
//		Connection conn;
//		Statement stmt = null;
//
//		CustomerDTO customer = null;
//		try {
//
//			if (!customerExist(cName))
//				return null;
//
//			conn = getConnection();
//			stmt = conn.createStatement();
//			String query = "SELECT customer_id,customer_name,address,city,state,zip_code,create_date,name_one,phone_one,email_one,name_two,phone_two,email_two,name_three,"
//					+ "phone_three,email_three,name_four,phone_four,email_four,name_five,phone_five,email_five,name_six,phone_six,email_six,name_seven,phone_seven,email_seven,"
//					+ "name_eight,phone_eight,email_eight,name_nine,phone_nine,email_nine,name_ten,phone_ten,email_ten from customer where customer_name='"
//					+ cName + "'";
//			ResultSet rs = stmt.executeQuery(query);
//			while (rs.next()) {
//				// Retrieve by column name
//				customer = new CustomerDTO();
//				int id = rs.getInt("customer_id");
//				String customerName = rs.getString("customer_name");
//				String address = rs.getString("address");
//
//				String nameOne = rs.getString("name_one");
//				String phoneOne = rs.getString("phone_one");
//				String emailOne = rs.getString("email_one");
//
//				String nametwo = rs.getString("name_two");
//				String phonetwo = rs.getString("phone_two");
//				String emailtwo = rs.getString("email_two");
//
//				String namethree = rs.getString("name_three");
//				String phonethree = rs.getString("phone_three");
//				String emailthree = rs.getString("email_three");
//
//				String namefour = rs.getString("name_four");
//				String phonefour = rs.getString("phone_four");
//				String emailfour = rs.getString("email_four");
//
//				String namefive = rs.getString("name_five");
//				String phonefive = rs.getString("phone_five");
//				String emailfive = rs.getString("email_five");
//
//				String namesix = rs.getString("name_six");
//				String phonesix = rs.getString("phone_six");
//				String emailsix = rs.getString("email_six");
//
//				String nameseven = rs.getString("name_seven");
//				String phoneseven = rs.getString("phone_seven");
//				String emailseven = rs.getString("email_seven");
//
//				String nameeight = rs.getString("name_eight");
//				String phoneeight = rs.getString("phone_eight");
//				String emaileight = rs.getString("email_eight");
//
//				String namenine = rs.getString("name_nine");
//				String phonenine = rs.getString("phone_nine");
//				String emailnine = rs.getString("email_nine");
//
//				String nameten = rs.getString("name_ten");
//				String phoneten = rs.getString("phone_ten");
//				String emailten = rs.getString("email_ten");
//
//				// Display values
//				System.out.print("ID: " + id);
//				System.out.print("name: " + customerName);
//				System.out.print("addres: " + address);
//
//				customer.setCustomerId(id);
//				customer.setCustomerName(customerName);
//				customer.setAddress(address);
//
//				customer.setName_one(nameOne);
//				customer.setPhone_one(phoneOne);
//				customer.setEmail_one(emailOne);
//
//				customer.setName_two(nametwo);
//				customer.setPhone_two(phonetwo);
//				customer.setEmail_two(emailtwo);
//
//				customer.setName_three(namethree);
//				customer.setPhone_three(phonethree);
//				customer.setEmail_three(emailthree);
//
//				customer.setName_four(namefour);
//				customer.setPhone_four(phonefour);
//				customer.setEmail_four(emailfour);
//
//				customer.setName_five(namefive);
//				customer.setPhone_five(phonefive);
//				customer.setEmail_five(emailfive);
//
//				customer.setName_six(namesix);
//				customer.setPhone_six(phonesix);
//				customer.setEmail_six(emailsix);
//
//				customer.setName_seven(nameseven);
//				customer.setPhone_seven(phoneseven);
//				customer.setEmail_seven(emailseven);
//
//				customer.setName_eight(nameeight);
//				customer.setPhone_eight(phoneeight);
//				customer.setEmail_eight(emaileight);
//
//				customer.setName_nine(namenine);
//				customer.setPhone_nine(phonenine);
//				customer.setEmail_nine(emailnine);
//
//				customer.setName_ten(nameten);
//				customer.setPhone_ten(phoneten);
//				customer.setEmail_ten(emailten);
//
//			}
//			rs.close();
//			conn.close();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		return customer;
//
//	}
//
//	public static List<CustomerDTO> retrieveAllCustomers(String customerSearched) {
//
//		Connection conn;
//		Statement stmt = null;
//
//		List<CustomerDTO> customers = new ArrayList<CustomerDTO>();
//		try {
//
//			conn = getConnection();
//			stmt = conn.createStatement();
//			String query = "SELECT customer_id,customer_name,address,name_one,phone_one,email_one from customer";
//			if (customerSearched != null && customerSearched.length() > 0) {
//				query += " where customer_name LIKE '" + customerSearched + "%'";
//			}
//			ResultSet rs = stmt.executeQuery(query);
//			while (rs.next()) {
//				// Retrieve by column name
//				CustomerDTO eachCustomer = new CustomerDTO();
//				int id = rs.getInt("customer_id");
//				String customerName = rs.getString("customer_name");
//				String address = rs.getString("address");
//
//				String nameOne = rs.getString("name_one");
//				String phoneOne = rs.getString("phone_one");
//				String emailOne = rs.getString("email_one");
//
//				eachCustomer.setCustomerId(id);
//				eachCustomer.setCustomerName(customerName);
//				eachCustomer.setAddress(address);
//
//				eachCustomer.setName_one(nameOne);
//				eachCustomer.setPhone_one(phoneOne);
//				eachCustomer.setEmail_one(emailOne);
//
//				customers.add(eachCustomer);
//
//			}
//			rs.close();
//			conn.close();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		return customers;
//
//	}
//
//	public static void registerCustomer(String create, String cid, String cName, String cAddress, String nameOne,
//			String phoneOne, String emailOne, String nameTwo, String phonTwo, String emailTwo, String namethree,
//			String phonethree, String emailthree, String namefour, String phonefour, String emailfour, String namefive,
//			String phonefive, String emailfive, String namesix, String phonesix, String emailsix, String nameseven,
//			String phoneseven, String emailseven, String nameeight, String phoneeight, String emaileight,
//			String namenine, String phonenine, String emailnine, String nameten, String phoneten, String emailten)
//			throws Exception {
//
//		Connection conn;
//		boolean registered = false;
//		try {
//
//			Calendar calendar = Calendar.getInstance();
//			java.sql.Date currentTime = new java.sql.Date(calendar.getTime().getTime());
//			conn = getConnection();
//			boolean customerExist = customerExist(cName);
//			if (create.equalsIgnoreCase("true") && customerExist) {
//				throw new Exception("Customer already exist. Please select from existing Customers and update");
//			}
//			if (create.equalsIgnoreCase("false") && cid != null) {
//				String query = "UPDATE Customer set customer_name=?,address=?, create_date=?, name_one=?,phone_one=?,email_one=?,name_two=?,phone_two=?,email_two=?,name_three=?,"
//						+ "phone_three=?,email_three=?,name_four=?,phone_four=?,email_four=?,name_five=?,phone_five=?,email_five=?,name_six=?,phone_six=?,email_six=?,name_seven=?,phone_seven=?,email_seven=?,"
//						+ "name_eight=?,phone_eight=?,email_eight=?,name_nine=?,phone_nine=?,email_nine=?,name_ten=?,phone_ten=?,email_ten=? where customer_id="
//						+ cid;
//				PreparedStatement preparedStmt = conn.prepareStatement(query);
//				preparedStmt.setString(1, cName);
//				preparedStmt.setString(2, cAddress);
//				preparedStmt.setDate(3, currentTime);
//
//				preparedStmt.setString(4, nameOne);
//				preparedStmt.setString(5, phoneOne);
//				preparedStmt.setString(6, emailOne);
//
//				preparedStmt.setString(7, nameTwo);
//				preparedStmt.setString(8, phonTwo);
//				preparedStmt.setString(9, emailTwo);
//
//				preparedStmt.setString(10, namethree);
//				preparedStmt.setString(11, phonethree);
//				preparedStmt.setString(12, emailthree);
//
//				preparedStmt.setString(13, namefour);
//				preparedStmt.setString(14, phonefour);
//				preparedStmt.setString(15, emailfour);
//
//				preparedStmt.setString(16, namefive);
//				preparedStmt.setString(17, phonefive);
//				preparedStmt.setString(18, emailfive);
//
//				preparedStmt.setString(19, namesix);
//				preparedStmt.setString(20, phonesix);
//				preparedStmt.setString(21, emailsix);
//
//				preparedStmt.setString(22, nameseven);
//				preparedStmt.setString(23, phoneseven);
//				preparedStmt.setString(24, emailseven);
//
//				preparedStmt.setString(25, nameeight);
//				preparedStmt.setString(26, phoneeight);
//				preparedStmt.setString(27, emaileight);
//
//				preparedStmt.setString(28, namenine);
//				preparedStmt.setString(29, phonenine);
//				preparedStmt.setString(30, emailnine);
//
//				preparedStmt.setString(31, nameten);
//				preparedStmt.setString(32, phoneten);
//				preparedStmt.setString(33, emailten);
//
//				int count = preparedStmt.executeUpdate();
//				registered = (count > 0);
//
//			} else {
//				String query = "INSERT INTO CUSTOMER(customer_name, address, create_date, name_one,phone_one,email_one,name_two,phone_two,email_two,name_three,"
//						+ "phone_three,email_three,name_four,phone_four,email_four,name_five,phone_five,email_five,name_six,phone_six,email_six,name_seven,phone_seven,email_seven,"
//						+ "name_eight,phone_eight,email_eight,name_nine,phone_nine,email_nine,name_ten,phone_ten,email_ten) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
//				PreparedStatement preparedStmt = conn.prepareStatement(query);
//				preparedStmt.setString(1, cName);
//				preparedStmt.setString(2, cAddress);
//				preparedStmt.setDate(3, currentTime);
//
//				preparedStmt.setString(4, nameOne);
//				preparedStmt.setString(5, phoneOne);
//				preparedStmt.setString(6, emailOne);
//
//				preparedStmt.setString(7, nameTwo);
//				preparedStmt.setString(8, phonTwo);
//				preparedStmt.setString(9, emailTwo);
//
//				preparedStmt.setString(10, namethree);
//				preparedStmt.setString(11, phonethree);
//				preparedStmt.setString(12, emailthree);
//
//				preparedStmt.setString(13, namefour);
//				preparedStmt.setString(14, phonefour);
//				preparedStmt.setString(15, emailfour);
//
//				preparedStmt.setString(16, namefive);
//				preparedStmt.setString(17, phonefive);
//				preparedStmt.setString(18, emailfive);
//
//				preparedStmt.setString(19, namesix);
//				preparedStmt.setString(20, phonesix);
//				preparedStmt.setString(21, emailsix);
//
//				preparedStmt.setString(22, nameseven);
//				preparedStmt.setString(23, phoneseven);
//				preparedStmt.setString(24, emailseven);
//
//				preparedStmt.setString(25, nameeight);
//				preparedStmt.setString(26, phoneeight);
//				preparedStmt.setString(27, emaileight);
//
//				preparedStmt.setString(28, namenine);
//				preparedStmt.setString(29, phonenine);
//				preparedStmt.setString(30, emailnine);
//
//				preparedStmt.setString(31, nameten);
//				preparedStmt.setString(32, phoneten);
//				preparedStmt.setString(33, emailten);
//
//				int count = preparedStmt.executeUpdate();
//				registered = (count > 0);
//			}
//
//			conn.close();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//
//	}
//
//	public static void loadRoleMap(Map<String, String> roleMap) {
//
//		Connection conn;
//		Statement stmt = null;
//
//		try {
//
//			conn = getConnection();
//			stmt = conn.createStatement();
//			String query = "select role,role_desc from roles order by role_desc asc";
//			ResultSet rs = stmt.executeQuery(query);
//			while (rs.next()) {
//				String role = rs.getString("role");
//				String roleDescription = rs.getString("role_desc");
//				roleMap.put(role, roleDescription);
//			}
//			rs.close();
//			conn.close();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//	}
//
//	public static EmployeeDTO retrieveEmployee(String employeeSelected) {
//
//		Connection conn;
//		Statement stmt = null;
//		Statement stmt2 = null;
//		EmployeeDTO employee = null;
//		try {
//			conn = getConnection();
//			stmt = conn.createStatement();
//			stmt2 = conn.createStatement();
//			String query = "select employee_id, first_name,last_name,gender, designation, dob,	doj, qualification, experience,	married, passport ,"
//					+ "	emergency_contact ,	user_id ,password ,current_address,	permanent_address,	city ,phone ,email , state ,zip_code, create_date ,"
//					+ "	notes from employee where employee_id="+ employeeSelected;
//			
//			ResultSet rs = stmt.executeQuery(query);
//			while (rs.next()) {
//				// Retrieve by column name
//				employee = new EmployeeDTO();
//				int id = rs.getInt("employee_id");
//				String firstName = rs.getString("first_name");
//				String lastName = rs.getString("last_name");
//				String gender = rs.getString("gender");
//				String designation = rs.getString("designation");
//				Date dob = rs.getDate("dob");
//				Date doj = rs.getDate("doj");
//				String qualification = rs.getString("qualification");
//				String experience = rs.getString("experience");
//				String married = rs.getString("married");
//				String passport = rs.getString("passport");
//				String emergencyContact = rs.getString("emergency_contact");
//				String userId = rs.getString("user_id");
//				String password = rs.getString("password");
//				String currentAddress = rs.getString("current_address");
//				String permanentAddress = rs.getString("permanent_address");
//				String phone = rs.getString("phone");
//				String email = rs.getString("email");
//				String notes = rs.getString("notes");
//
//				String role = "ROLE_norole";
//				if(userId!=null && userId.length()>0){
//					String query1 = "select role from employee_role where user_id='"
//							+ userId + "'";
//					ResultSet rs1 = stmt2.executeQuery(query1);
//					while (rs1.next()) {
//						role = rs1.getString("role");
//					}
//				}
//				// Display values
//
//				employee.setEmployeeId(id);
//				employee.setFirstName(firstName);
//				employee.setLastName(lastName);
//				employee.setGender(gender);
//				employee.setDesignation(designation);
//				employee.setDob(ui_date_format.format(dob));
//				employee.setDoj(ui_date_format.format(doj));
//				employee.setQualification(qualification);
//				employee.setExperience(experience);
//				employee.setMarried(married);
//				employee.setPassport(passport);
//				employee.setEmergencyContact(emergencyContact);
//				employee.setUserId(userId);
//				employee.setPassword(password);
//				employee.setPhone(phone);
//				employee.setEmail(email);
//				employee.setRole(role);
//				employee.setCurrentAddress(currentAddress);
//				employee.setPermanentAddress(permanentAddress);
//				employee.setNotes(notes);
//			}
//			rs.close();
//			conn.close();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		return employee;
//
//
//	}
//
//	public static List<EmployeeDTO> retrieveAllEmployees(String employeeSearched) {
//
//		Connection conn;
//		Statement stmt = null;
//
//		List<EmployeeDTO> employees = new ArrayList<EmployeeDTO>();
//		try {
//
//			conn = getConnection();
//			stmt = conn.createStatement();
//			
//			String query = "select employee_id, first_name,last_name,gender, designation, phone, user_id, emergency_contact from employee";
//			if (employeeSearched != null && employeeSearched.length() > 0) {
//				query += " where first_name LIKE '" + employeeSearched + "%'";
//			}
//			ResultSet rs = stmt.executeQuery(query);
//			while (rs.next()) {
//				// Retrieve by column name
//				EmployeeDTO emp = new EmployeeDTO();
//				int id = rs.getInt("employee_id");
//				String firstName = rs.getString("first_name");
//				String lastName = rs.getString("last_name");
//				String gender = rs.getString("gender");
//				String designation = rs.getString("designation");
//				String phone = rs.getString("phone");
//				String userId = rs.getString("user_id");
//				String emergencyContact = rs.getString("emergency_contact");
//
//				emp.setEmployeeId(id);
//				emp.setFirstName(firstName);
//				emp.setLastName(lastName);
//				emp.setGender(gender);
//				emp.setDesignation(designation);
//				emp.setPhone(phone);
//				emp.setUserId(userId);
//				emp.setEmergencyContact(emergencyContact);
//				
//				employees.add(emp);
//
//
//			}
//			rs.close();
//			conn.close();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		return employees;
//
//
//	}
//
//	
//
//	private static void insertEmployeeRole(String eUserId, String eRole) throws SQLException {
//		Connection conn = getConnection();
//		String query = "INSERT INTO employee_role(user_id,role) values(?,?)";
//		PreparedStatement preparedStmt = conn.prepareStatement(query);
//		preparedStmt.setString(1, eUserId);
//		preparedStmt.setString(2, eRole);
//		int count = preparedStmt.executeUpdate();
//		conn.close();
//
//	}
//
//	private static void deleteEmployeeRole(String eUserId) throws SQLException{
//		Connection conn = getConnection();
//		Statement st = conn.createStatement();
//		String sql = "DELETE FROM employee_role WHERE user_id='" + eUserId + "'";
//		st.executeUpdate(sql);
//	}
//	
//	private static boolean empoyeeExist(String userId) throws SQLException {
//		if(userId==null || userId.trim().length()==0) return false;
//		boolean employeeExist = false;
//		Connection conn = getConnection();
//		Statement st = conn.createStatement();
//		ResultSet res = st.executeQuery("SELECT * FROM employee WHERE user_id='" + userId + "'");
//		while (res.next()) {
//			employeeExist = true;
//		}
//		conn.close();
//		return employeeExist;
//
//	}
//
//	public static void registerEmployee(String create, String eid, String efirstName, String eLastName, String gender,
//			String eQualification, String eExperience, String married, String eDesignation, String eDob, String eDoj,
//			String eRole, String eUserId, String password, String ePhone, String eEmail, String ePassport,
//			String eEmergencyContact, String eCAddress, String ePAddress, String eNotes) throws Exception{
//		Connection conn;
//		boolean registered = false;
//		try {
//
//			Calendar calendar = Calendar.getInstance();
//			java.sql.Date currentTime = new java.sql.Date(calendar.getTime().getTime());
//			conn = getConnection();
//			boolean empoyeeExist = empoyeeExist(eUserId);
//			if (create.equalsIgnoreCase("true") && empoyeeExist) {
//				throw new Exception("Employee with given UserId already exist. Please select other UserId");
//			}
//			java.util.Date javaDateDob = ui_date_format.parse(eDob) ;
//			java.util.Date javaDateDoj = ui_date_format.parse(eDoj) ;
//
//			if (create.equalsIgnoreCase("false") && eid != null) {
//				
//				System.out.println("********************************************** updating");
//				String query = "UPDATE EMPLOYEE set first_name=?,last_name=?,gender=?, designation=?, dob=?,doj=?, qualification=?, experience=?,married=?, passport=? ,"
//					+ "	emergency_contact=? ,user_id=? ,password=? ,current_address=?,	permanent_address=?,phone =?,email=? ,create_date=? ,"
//					+ "	notes=? where employee_id="
//						+ eid;
//				PreparedStatement preparedStmt = conn.prepareStatement(query);
//				preparedStmt.setString(1, efirstName);
//				preparedStmt.setString(2, eLastName);
//				preparedStmt.setString(3, gender);
//
//				preparedStmt.setString(4, eDesignation);
//				preparedStmt.setDate(5, new java.sql.Date(javaDateDob.getTime()));
//				preparedStmt.setDate(6, new java.sql.Date(javaDateDoj.getTime()));
//				preparedStmt.setString(7, eQualification);
//
//				preparedStmt.setString(8, eExperience);
//				preparedStmt.setString(9, married);
//				preparedStmt.setString(10, ePassport);
//				preparedStmt.setString(11, eEmergencyContact);
//				
//				preparedStmt.setString(12, eUserId);
//				preparedStmt.setString(13, password);
//				preparedStmt.setString(14, eCAddress);
//				preparedStmt.setString(15, ePAddress);
//				
//				preparedStmt.setString(16, ePhone);
//				preparedStmt.setString(17, eEmail);
//				preparedStmt.setDate(18, currentTime);
//				preparedStmt.setString(19, eNotes);
//
//				int count = preparedStmt.executeUpdate();
//
//				deleteEmployeeRole(eUserId);
//				
//				if(eUserId!=null && eUserId.length()>0 && eRole!=null && eRole!="ROLE_norole") {
//					insertEmployeeRole(eUserId, eRole);
//				}
//				
//				registered = (count > 0);
//
//			} else {
//				System.out.println("********************************************** inserting");
//	
//				String query = "INSERT INTO EMPLOYEE(first_name,last_name,gender, designation, dob,doj, qualification, experience,married, passport ,"
//					+ "	emergency_contact ,user_id ,password ,current_address,	permanent_address,phone ,email ,create_date ,"
//					+ "	notes) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
//				PreparedStatement preparedStmt = conn.prepareStatement(query);
//
//				preparedStmt.setString(1, efirstName);
//				preparedStmt.setString(2, eLastName);
//				preparedStmt.setString(3, gender);
//
//				preparedStmt.setString(4, eDesignation);
//				preparedStmt.setDate(5, new java.sql.Date(javaDateDob.getTime()));
//				preparedStmt.setDate(6, new java.sql.Date(javaDateDoj.getTime()));
//				preparedStmt.setString(7, eQualification);
//
//				preparedStmt.setString(8, eExperience);
//				preparedStmt.setString(9, married);
//				preparedStmt.setString(10, ePassport);
//				preparedStmt.setString(11, eEmergencyContact);
//				
//				preparedStmt.setString(12, eUserId);
//				preparedStmt.setString(13, password);
//				preparedStmt.setString(14, eCAddress);
//				preparedStmt.setString(15, ePAddress);
//				
//				preparedStmt.setString(16, ePhone);
//				preparedStmt.setString(17, eEmail);
//				preparedStmt.setDate(18, currentTime);
//				preparedStmt.setString(19, eNotes);
//
//
//				int count = preparedStmt.executeUpdate();
//
//				if(eUserId!=null && eUserId.length()>0 && eRole!=null && eRole!="ROLE_norole") {
//					insertEmployeeRole(eUserId, eRole);
//				}	
//				registered = (count > 0);
//			}
//
//			conn.close();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}catch(Exception gen){
//			gen.printStackTrace();
//		}
//
//	}
//}
