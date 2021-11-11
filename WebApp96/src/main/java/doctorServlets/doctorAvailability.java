package doctorServlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;


public class doctorAvailability extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public doctorAvailability() {
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
		HttpSession session2 = request.getSession();
		String user_username = session2.getAttribute("username").toString();
		String user_password = session2.getAttribute("password").toString();
		String month = request.getParameter("month");
		String day = request.getParameter("day");
		String url = "jdbc:mysql://localhost:3306/information" ;
		String username = "root";
		String password = "cantfind";
		try {
			boolean check = false;
			int id=0;
			String specialty="";
			String sql = "SELECT * FROM doctors";
			String driver = "com.mysql.jdbc.Driver";
			Class.forName(driver);
			Connection connection = DriverManager.getConnection(url , username , password);
			Statement statement = connection.createStatement();
			ResultSet result = statement.executeQuery(sql);
			
			while(result.next()){
				if (result.getString("username").equals(user_username) || result.getString("password").equals(user_password)) {
					check = true;
					id=result.getInt("id");
					specialty = result.getString("specialty");
					break;
				}
			}
			if (check) {
				sql = "INSERT INTO availability (doctor_id , specialty , month , day) VALUES (? , ? , ? , ?)";
				PreparedStatement statement2 = connection.prepareStatement(sql);
			    statement2.setString(1, Integer.toString(id));
			    statement2.setString(2, specialty);
			    statement2.setString(3, month);
			    statement2.setString(4, day);
			    response.sendRedirect("Doctor.jsp");
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
	}

}
