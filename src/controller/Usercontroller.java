package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

/**
 * Servlet implementation class Usercontroller
 */
@WebServlet("/User")
public class Usercontroller extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Usercontroller() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		// TODO Auto-generated method stub
		Connection con = null;
		Statement statement = null;
		ResultSet rs = null;
		try {
			// find a question
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			
			con  = DriverManager.getConnection("jdbc:mysql://localhost:3306/sample?"+"user=root&password=Satyam@12345");
			statement = con.createStatement();
			rs = statement.executeQuery("Select * from userlist;");
		
			Gson gson = new Gson();
			List<User> list = new ArrayList<User>();
			int i = 0;
			while(rs.next()) {
				User user = new User();
				user.setEmail(rs.getString("email"));
				user.setFname(rs.getString("fname"));
				user.setLname(rs.getString("lname"));
				list.add(user);
			}
			
			String jsonto  = gson.toJson(list.toArray());
			System.out.println(jsonto);
			
			response.setContentType("application/json");
			PrintWriter out = response.getWriter();
			out.print(jsonto);
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			if(rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(statement != null ) {
				try {
					statement.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// Add method
		BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream()));
		String input = br.readLine();
		System.out.println(input);
		Gson gson = new Gson();
		User user = gson.fromJson(input, User.class);
		Connection con = null;
		PreparedStatement ps = null;
		response.setContentType("application/json");
		PrintWriter out = response.getWriter();
		ResponseMessage responsemsg ;
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			con  = DriverManager.getConnection("jdbc:mysql://localhost:3306/sample?"+"user=root&password=Satyam@12345");
			ps = con.prepareStatement("insert into userlist(email , fname ,lname) values (?,?,?) ;");
			ps.setString(1, user.getEmail());
			ps.setString(2, user.getFname());
			ps.setString(3, user.getLname());
			int i = ps.executeUpdate();
			
			if( i == 1) {
				responsemsg = new ResponseMessage(0, "success");
			}else {
				responsemsg = new ResponseMessage(1, "Something went Wrong");
			}
			String jsonto  = gson.toJson(responsemsg);
			System.out.println(jsonto);
			
			out.print(jsonto);
			out.flush();
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException  e) {
			// TODO Auto-generated catch block
			responsemsg = new ResponseMessage(1, "Something went Wrong");
			String jsonto  = gson.toJson(responsemsg);
			System.out.println(jsonto);
			out.print(jsonto);
			out.flush();
			e.printStackTrace();
			
		}finally {
			if(ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(out != null) {
				out.close();
			}
		} 
		
		
		
	}

}
