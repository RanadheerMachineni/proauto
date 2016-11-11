package com.esteeminfo.prouto.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import com.esteeminfo.proauto.entity.Vendor;

public class CommonDAO extends BaseDAO {

	public static String authenticateUser(String uname, String pwd)
			throws SQLException {

		String retString = null;
		Connection conn = getConnection();
		Statement st = conn.createStatement();
		ResultSet res = st.executeQuery("SELECT * FROM EMPLOYEE WHERE employee_name='"+uname+"' and password='"+pwd+"'");
		while (res.next()) {
			String role = res.getString("role");
			retString = role;
		}
		conn.close();
		return retString;
	}

	public static boolean registerEmployee(String empName, String empPassword, String empEmail, String empRole) {
		Connection conn;
		boolean registered = false;
		try {
			
			if(userExist(empName)) return registered;
			Calendar calendar = Calendar.getInstance();
			java.sql.Date currentTime = new java.sql.Date(calendar.getTime().getTime());

			conn = getConnection();
			String query= "INSERT INTO EMPLOYEE(employee_name,password,role,create_date) values(?,?,?,?)";
			PreparedStatement preparedStmt = conn.prepareStatement(query);
	        preparedStmt.setString(1, empName);
	        preparedStmt.setString(2, empPassword);
	        preparedStmt.setString(3, empRole);
	        preparedStmt.setDate(4, currentTime	);
	      
	        int count = preparedStmt.executeUpdate();
	        registered = (count > 0);
	        conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return registered;
	}
	
	public static boolean userExist(String uname)
			throws SQLException {

		boolean userExist = false;
		Connection conn = getConnection();
		Statement st = conn.createStatement();
		ResultSet res = st.executeQuery("SELECT * FROM EMPLOYEE WHERE employee_name='"+uname+"'");
		while (res.next()) {
			userExist = true;
		}
		conn.close();
		return userExist;
	}

	public static boolean vendorExist(String vName)
			throws SQLException {

		boolean vendorExist = false;
		Connection conn = getConnection();
		Statement st = conn.createStatement();
		ResultSet res = st.executeQuery("SELECT * FROM vendor WHERE vendor_name='"+vName+"'");
		while (res.next()) {
			vendorExist = true;
		}
		conn.close();
		return vendorExist;
	}
	
	public static boolean registerVendor(String vName, String vAddress, String vEmail, String vFirstContact,
			String vSecondContact, String vThirdContact) {
		Connection conn;
		boolean registered = false;
		try {
			
			if(vendorExist(vName)) return registered;
			Calendar calendar = Calendar.getInstance();
			java.sql.Date currentTime = new java.sql.Date(calendar.getTime().getTime());

			conn = getConnection();
			String query= "INSERT INTO VENDOR(vendor_name,address,vendor_email,vendor_first_contact,vendor_second_contact,vendor_third_contact,create_date) values(?,?,?,?,?,?,?)";
			PreparedStatement preparedStmt = conn.prepareStatement(query);
	        preparedStmt.setString(1, vName);
	        preparedStmt.setString(2, vAddress);
	        preparedStmt.setString(3, vEmail);
	        preparedStmt.setString(4, vFirstContact);
	        preparedStmt.setString(5, vSecondContact);
	        preparedStmt.setString(6, vThirdContact);
	        preparedStmt.setDate(7, currentTime	);
	        int count = preparedStmt.executeUpdate();
	        registered = (count > 0);
	        conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return registered;
	}

	public static boolean registerCustomer(String cName, String cAddress, String cEmail, String cFirstContact,
			String cSecondContact, String cThirdContact) {
		Connection conn;
		boolean registered = false;
		try {
			
			if(customerExist(cName)) return registered;
			Calendar calendar = Calendar.getInstance();
			java.sql.Date currentTime = new java.sql.Date(calendar.getTime().getTime());

			conn = getConnection();
			String query= "INSERT INTO CUSTOMER(customer_name,address,customer_email,customer_first_contact,customer_second_contact,customer_third_contact,create_date) values(?,?,?,?,?,?,?)";
			PreparedStatement preparedStmt = conn.prepareStatement(query);
	        preparedStmt.setString(1, cName);
	        preparedStmt.setString(2, cAddress);
	        preparedStmt.setString(3, cEmail);
	        preparedStmt.setString(4, cFirstContact);
	        preparedStmt.setString(5, cSecondContact);
	        preparedStmt.setString(6, cThirdContact);
	        preparedStmt.setDate(7, currentTime	);
	        int count = preparedStmt.executeUpdate();
	        registered = (count > 0);
	        conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return registered;
	}

	public static Vendor retrieveVendor(String vName) {
		Connection conn;
		Statement stmt = null;

		Vendor vendor = null;
		try {
			
			if(!vendorExist(vName)) return null;

			conn = getConnection();
			stmt = conn.createStatement();
			String query= "SELECT vendor_id,vendor_name,address,city,state,zip_code,create_date,name_one,phone_one,email_one,name_two,phone_two,email_two,name_three,"
					+ "phone_three,email_three,name_four,phone_four,email_four,name_five,phone_five,email_five,name_six,phone_six,email_six,name_seven,phone_seven,email_seven,"
					+ "name_eight,phone_eight,email_eight,name_nine,phone_nine,email_nine,name_ten,phone_ten,email_ten from vendor where vendor_name='"+vName+"'";
		    ResultSet rs = stmt.executeQuery(query);
			while(rs.next()){
		         //Retrieve by column name
				 vendor = new Vendor();
		         int id  = rs.getInt("vendor_id");
		         String vendorName = rs.getString("vendor_name");
		         String address = rs.getString("address");
	
		         String nameOne = rs.getString("name_one");
		         String phoneOne = rs.getString("phone_one");
		         String emailOne = rs.getString("email_one");

		         String nametwo = rs.getString("name_two");
		         String phonetwo = rs.getString("phone_two");
		         String emailtwo = rs.getString("email_two");

		         String namethree = rs.getString("name_three");
		         String phonethree = rs.getString("phone_three");
		         String emailthree = rs.getString("email_three");

		         String namefour = rs.getString("name_four");
		         String phonefour = rs.getString("phone_four");
		         String emailfour = rs.getString("email_four");

		         String namefive = rs.getString("name_five");
		         String phonefive = rs.getString("phone_five");
		         String emailfive = rs.getString("email_five");

		         String namesix = rs.getString("name_six");
		         String phonesix = rs.getString("phone_six");
		         String emailsix = rs.getString("email_six");

		         String nameseven = rs.getString("name_seven");
		         String phoneseven = rs.getString("phone_seven");
		         String emailseven = rs.getString("email_seven");

		         String nameeight = rs.getString("name_eight");
		         String phoneeight = rs.getString("phone_eight");
		         String emaileight = rs.getString("email_eight");

		         String namenine = rs.getString("name_nine");
		         String phonenine = rs.getString("phone_nine");
		         String emailnine = rs.getString("email_nine");

		         String nameten = rs.getString("name_ten");
		         String phoneten = rs.getString("phone_ten");
		         String emailten = rs.getString("email_ten");

		         //Display values
		         System.out.print("ID: " + id);
		         System.out.print("name: " + vendorName);
		         System.out.print("addres: " + address);

		         vendor.setVendorId(id);
		         vendor.setVendorName(vendorName);
		         vendor.setAddress(address);
		         
		         vendor.setName_one(nameOne);
		         vendor.setPhone_one(phoneOne);
		         vendor.setEmail_one(emailOne);
		         
		         vendor.setName_two(nametwo);
		         vendor.setPhone_two(phonetwo);
		         vendor.setEmail_two(emailtwo);
		         
		         vendor.setName_three(namethree);
		         vendor.setPhone_three(phonethree);
		         vendor.setEmail_three(emailthree);
		         
		         vendor.setName_four(namefour);
		         vendor.setPhone_four(phonefour);
		         vendor.setEmail_four(emailfour);
		         
		         vendor.setName_five(namefive);
		         vendor.setPhone_five(phonefive);
		         vendor.setEmail_five(emailfive);
		         
		         vendor.setName_six(namesix);
		         vendor.setPhone_six(phonesix);
		         vendor.setEmail_six(emailsix);
		         
		         
		         vendor.setName_seven(nameseven);
		         vendor.setPhone_seven(phoneseven);
		         vendor.setEmail_seven(emailseven);
		         
		         vendor.setName_eight(nameeight);
		         vendor.setPhone_eight(phoneeight);
		         vendor.setEmail_eight(emaileight);
		         
		         vendor.setName_nine(namenine);
		         vendor.setPhone_nine(phonenine);
		         vendor.setEmail_nine(emailnine);
		         
		         vendor.setName_ten(nameten);
		         vendor.setPhone_ten(phoneten);
		         vendor.setEmail_ten(emailten);

		    }
		    rs.close();
		    conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return vendor;
	}

	public static List<Vendor> retrieveAllVendors(String vendorSearched) {
		Connection conn;
		Statement stmt = null;

		List<Vendor> vendors = new ArrayList<Vendor>();
		try {
			

			conn = getConnection();
			stmt = conn.createStatement();
			String query= "SELECT vendor_id,vendor_name,address,name_one,phone_one,email_one from vendor";
			if(vendorSearched!=null && vendorSearched.length()>0){
				query+= " where vendor_name LIKE '"+vendorSearched+"%'";
			}
		    ResultSet rs = stmt.executeQuery(query);
			while(rs.next()){
		         //Retrieve by column name
				 Vendor eachVendor = new Vendor();
		         int id  = rs.getInt("vendor_id");
		         String vendorName = rs.getString("vendor_name");
		         String address = rs.getString("address");
	
		         String nameOne = rs.getString("name_one");
		         String phoneOne = rs.getString("phone_one");
		         String emailOne = rs.getString("email_one");

		        
		         eachVendor.setVendorId(id);
		         eachVendor.setVendorName(vendorName);
		         eachVendor.setAddress(address);
		         
		         eachVendor.setName_one(nameOne);
		         eachVendor.setPhone_one(phoneOne);
		         eachVendor.setEmail_one(emailOne);
		         
		         vendors.add(eachVendor);

		    }
		    rs.close();
		    conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return vendors;
	}
	
	private static boolean customerExist(String cName) throws SQLException {
		boolean customerExist = false;
		Connection conn = getConnection();
		Statement st = conn.createStatement();
		ResultSet res = st.executeQuery("SELECT * FROM customer WHERE customer_name='"+cName+"'");
		while (res.next()) {
			customerExist = true;
		}
		conn.close();
		return customerExist;
	}

	public static void registerVendor(String create, String vName, String vAddress, String nameOne, String phoneOne, String emailOne,
			String nameTwo, String phonTwo, String emailTwo, String namethree, String phonethree, String emailthree,
			String namefour, String phonefour, String emailfour, String namefive, String phonefive, String emailfive,
			String namesix, String phonesix, String emailsix, String nameseven, String phoneseven, String emailseven,
			String nameeight, String phoneeight, String emaileight, String namenine, String phonenine, String emailnine,
			String nameten, String phoneten, String emailten) throws Exception {
		Connection conn;
		boolean registered = false;
		try {

			Calendar calendar = Calendar.getInstance();
			java.sql.Date currentTime = new java.sql.Date(calendar.getTime().getTime());
			conn = getConnection();
			boolean vendorExist = vendorExist(vName);
			if(create.equalsIgnoreCase("true") && vendorExist){
				throw new Exception("Vendor already exist. Please select from existing Vendors and update");
			}
			if(create.equalsIgnoreCase("false") && vendorExist){

				String query= "UPDATE VENDOR set vendor_name=?,address=?, create_date=?, name_one=?,phone_one=?,email_one=?,name_two=?,phone_two=?,email_two=?,name_three=?,"
					+ "phone_three=?,email_three=?,name_four=?,phone_four=?,email_four=?,name_five=?,phone_five=?,email_five=?,name_six=?,phone_six=?,email_six=?,name_seven=?,phone_seven=?,email_seven=?,"
					+ "name_eight=?,phone_eight=?,email_eight=?,name_nine=?,phone_nine=?,email_nine=?,name_ten=?,phone_ten=?,email_ten=? where vendor_name='"+vName+"'";
				PreparedStatement preparedStmt = conn.prepareStatement(query);
		        preparedStmt.setString(1, vName);
		        preparedStmt.setString(2, vAddress);
		        preparedStmt.setDate(3, currentTime);
		        
		        preparedStmt.setString(4, nameOne);
		        preparedStmt.setString(5, phoneOne);
		        preparedStmt.setString(6, emailOne);
		        
		        preparedStmt.setString(7, nameTwo);
		        preparedStmt.setString(8, phonTwo);
		        preparedStmt.setString(9, emailTwo);

		        preparedStmt.setString(10, namethree);
		        preparedStmt.setString(11, phonethree);
		        preparedStmt.setString(12, emailthree);

		        preparedStmt.setString(13, namefour);
		        preparedStmt.setString(14, phonefour);
		        preparedStmt.setString(15, emailfour);

		        preparedStmt.setString(16, namefive);
		        preparedStmt.setString(17, phonefive);
		        preparedStmt.setString(18, emailfive);

		        preparedStmt.setString(19, namesix);
		        preparedStmt.setString(20, phonesix);
		        preparedStmt.setString(21, emailsix);

		        preparedStmt.setString(22, nameseven);
		        preparedStmt.setString(23, phoneseven);
		        preparedStmt.setString(24, emailseven);

		        preparedStmt.setString(25, nameeight);
		        preparedStmt.setString(26, phoneeight);
		        preparedStmt.setString(27, emaileight);

		        preparedStmt.setString(28, namenine);
		        preparedStmt.setString(29, phonenine);
		        preparedStmt.setString(30, emailnine);

		        preparedStmt.setString(31, nameten);
		        preparedStmt.setString(32, phoneten);
		        preparedStmt.setString(33, emailten);

		        int count = preparedStmt.executeUpdate();
		        registered = (count > 0);
			
			}else{
				String query= "INSERT INTO VENDOR(vendor_name,address, create_date, name_one,phone_one,email_one,name_two,phone_two,email_two,name_three,"
					+ "phone_three,email_three,name_four,phone_four,email_four,name_five,phone_five,email_five,name_six,phone_six,email_six,name_seven,phone_seven,email_seven,"
					+ "name_eight,phone_eight,email_eight,name_nine,phone_nine,email_nine,name_ten,phone_ten,email_ten) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
				PreparedStatement preparedStmt = conn.prepareStatement(query);
		        preparedStmt.setString(1, vName);
		        preparedStmt.setString(2, vAddress);
		        preparedStmt.setDate(3, currentTime);
		        
		        preparedStmt.setString(4, nameOne);
		        preparedStmt.setString(5, phoneOne);
		        preparedStmt.setString(6, emailOne);
		        
		        preparedStmt.setString(7, nameTwo);
		        preparedStmt.setString(8, phonTwo);
		        preparedStmt.setString(9, emailTwo);

		        preparedStmt.setString(10, namethree);
		        preparedStmt.setString(11, phonethree);
		        preparedStmt.setString(12, emailthree);

		        preparedStmt.setString(13, namefour);
		        preparedStmt.setString(14, phonefour);
		        preparedStmt.setString(15, emailfour);

		        preparedStmt.setString(16, namefive);
		        preparedStmt.setString(17, phonefive);
		        preparedStmt.setString(18, emailfive);

		        preparedStmt.setString(19, namesix);
		        preparedStmt.setString(20, phonesix);
		        preparedStmt.setString(21, emailsix);

		        preparedStmt.setString(22, nameseven);
		        preparedStmt.setString(23, phoneseven);
		        preparedStmt.setString(24, emailseven);

		        preparedStmt.setString(25, nameeight);
		        preparedStmt.setString(26, phoneeight);
		        preparedStmt.setString(27, emaileight);

		        preparedStmt.setString(28, namenine);
		        preparedStmt.setString(29, phonenine);
		        preparedStmt.setString(30, emailnine);

		        preparedStmt.setString(31, nameten);
		        preparedStmt.setString(32, phoneten);
		        preparedStmt.setString(33, emailten);

		        int count = preparedStmt.executeUpdate();
		        registered = (count > 0);
			}

	        conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
