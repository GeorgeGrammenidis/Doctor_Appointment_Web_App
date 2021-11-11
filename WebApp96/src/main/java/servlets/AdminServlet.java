package servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class AdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String user_username = request.getParameter("admin_username");
		String user_password = request.getParameter("admin_password");
		String url = "jdbc:mysql://localhost:3306/information" ;
		String username = "root";
		String password = "cantfind";
		HttpSession session3 = request.getSession() ;
		session3.setAttribute("username", user_username);
		session3.setAttribute("password", user_password);
		try {
			boolean check = false;
			String sql = "SELECT * FROM admins";
			String driver = "com.mysql.jdbc.Driver";
			Class.forName(driver);
			Connection connection = DriverManager.getConnection(url , username , password);
			Statement statement = connection.createStatement();
			ResultSet result = statement.executeQuery(sql);
			HttpSession session1 = request.getSession();
			session1.invalidate();
			HttpSession session2 = request.getSession();
			session2.invalidate();
			while(result.next()){
				if (result.getString("username").equals(user_username) || result.getString("password").equals(user_password)) {
					check = true;
					break;
				}
			}
			if (check) {
				response.sendRedirect("Admin.jsp");
			}
			else {
				System.out.println("Connected, not found");
				response.sendRedirect("Failure.jsp");
			}
		}
		catch (SQLException e){
				response.sendRedirect("Failure.jsp");
				System.out.println("SQL Error.");
				e.printStackTrace();
		}
		catch (ClassNotFoundException e) {
			response.sendRedirect("Failure.jsp");
			System.out.println("Class not found Error.");
			e.printStackTrace();
		}
		//catch (InstantiationException e) {
			//response.sendRedirect("Failure.jsp");
			//System.out.println("Error.");
			//e.printStackTrace();
		//}
		//catch (IllegalAccessException e) {
			//response.sendRedirect("Failure.jsp");
			//System.out.println("Error.");
			//e.printStackTrace();
		//}
		
	}
}