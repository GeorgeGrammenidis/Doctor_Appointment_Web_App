package patientServlets;

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

public class MakeApp extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MakeApp() {
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
		// TODO Auto-generated method stub\
		HttpSession session1 = request.getSession();
		String user_username = session1.getAttribute("username").toString();
		String user_password = session1.getAttribute("password").toString();
		String month = request.getParameter("month");
		String day = request.getParameter("day");
		String doctor_id = request.getParameter("id");
		String patient_id="";
		String specialty="";
		String url = "jdbc:mysql://localhost:3306/information" ;
		String username = "root";
		String password = "cantfind";
		try {
			String driver = "com.mysql.jdbc.Driver";
			Class.forName(driver);
			Connection connection = DriverManager.getConnection(url , username , password);
			boolean check1 = false;
			boolean check2 = false;
			boolean check3 = false;
			String sql = "SELECT * FROM doctors";
			String sql2 = "SELECT * FROM patients";
			String sql3 = "SELECT * FROM availability";
			String sql4 = "INSERT INTO appointments (id_doctor , id_patient , month , day , specialty) VALUES (? , ? , ? , ?, ?)";
			String sql5 = "DELETE FROM availability WHERE doctor_id = ? && month=? && day=?";
			Statement statement = connection.createStatement();
			ResultSet result = statement.executeQuery(sql);
			//-----------------------------------------------//
			Statement statement2 = connection.createStatement();
			ResultSet result2 = statement2.executeQuery(sql2);
			//-----------------------------------------------//		
			Statement statement3 = connection.createStatement();
			ResultSet result3 = statement3.executeQuery(sql3);
			//-----------------------------------------------
			while(result.next()){
				if (Integer.toString(result.getInt("id")).equals(doctor_id)) {
					specialty = result.getString("specialty");
					check1 = true;
					break;
				}
			}
			
			while(result2.next()) {
				if(result2.getString("username").equals(user_username) && result2.getString("password").equals(user_password)){
					patient_id = result2.getString("id");
					check2 = true;
					break;
				}
			}
			
			while(result3.next()) {
				if(result3.getString("month").equals(month) && result3.getString("day").equals(day) && result3.getString("doctor_id").equals(doctor_id)) {
					check3 = true;
				}
			}
			if (check1 && check2 && check3) {
				PreparedStatement statement4 = connection.prepareStatement(sql4);
			    statement4.setString(1, doctor_id);
			    statement4.setString(2, patient_id);
			    statement4.setString(3, month);
			    statement4.setString(4, day);
			    statement4.setString(5, specialty);
			    statement4.executeUpdate();
			    
			    PreparedStatement statement5 = connection.prepareStatement(sql5);
			    statement5.setString(1, doctor_id);
			    statement5.setString(2, month);
			    statement5.setString(3, day);
			    statement5.executeUpdate();
			    response.sendRedirect("Patient.jsp");
			    
			}
			else{
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
