package adminServlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class adminDelete extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public adminDelete() {
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
		
		String doctor_username = request.getParameter("doctor_username0");
		String doctor_password = request.getParameter("doctor_password0");
		String url = "jdbc:mysql://localhost:3306/information" ;
		String username = "root";
		String password = "cantfind";
		try {
			String driver = "com.mysql.jdbc.Driver";
			Class.forName(driver);
			Connection connection = DriverManager.getConnection(url , username , password);
			String sql = "DELETE FROM doctors WHERE username = ? && password = ? ;";
			PreparedStatement statement = connection.prepareStatement(sql);
		    statement.setString(1, doctor_username);
		    statement.setString(2, doctor_password);
			statement.executeUpdate();
			System.out.println("Database updated");
			response.sendRedirect("Admin.jsp");
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
		
	}

}
