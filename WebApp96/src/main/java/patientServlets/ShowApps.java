package patientServlets;

import java.io.PrintWriter ;
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

/**
 * Servlet implementation class ShowApps
 */

public class ShowApps extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShowApps() {
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
		HttpSession session1 = request.getSession() ;
		String user_username = session1.getAttribute("username").toString();
		String user_password = session1.getAttribute("password").toString();
		String user = session1.getAttribute("username").toString();
		System.out.println(user);
		String url = "jdbc:mysql://localhost:3306/information" ;
		String username = "root";
		String password = "cantfind";
		try {
			boolean check = false;
			int id=0;
			String first="";
			String last="";
			String sql = "SELECT * FROM patients";
			String driver = "com.mysql.jdbc.Driver";
			Class.forName(driver);
			Connection connection = DriverManager.getConnection(url , username , password);
			Statement statement = connection.createStatement();
			ResultSet result = statement.executeQuery(sql);
			
			while(result.next()){
				if (result.getString("username").equals(user_username) || result.getString("password").equals(user_password)) {
					check = true;
					id=result.getInt("id");
					first = result.getString("first_name");
					last = result.getString("last_name");
					break;
				}
			}
			if (check) {
				StringBuilder sb = new StringBuilder();
				sql = "SELECT * FROM appointments";
				statement = connection.createStatement();
				result = statement.executeQuery(sql);
				while(result.next()){
					if (result.getInt("id_patient")==id ) {
						int month = result.getInt("month");
						int day = result.getInt("day");
						sb.append("Month: " + Integer.toString(month) + " Day: " + Integer.toString(day) + "| \n"   );
					}
				}
				response.setCharacterEncoding("ISO-8859-1");
				PrintWriter out = response.getWriter();
				out.println("<html>");
				out.println("<head>");
				out.println("<title> Result </title>");
				out.println("</head>");
				out.println("<body style='background-color:powderblue;'>");
				out.println(first + " " + last + "<p> Your arranged appointments: </p>");
				out.println("<p>" + sb + "</p>");
				out.println("</body>");
				out.println("</html>");
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
