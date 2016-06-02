package ProductsPage;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.jdbc.Driver;
import java.sql.*;

/**
 *
 * @author Jessica Parhusip - ish
 */
public class ConnectionServlet extends HttpServlet {

    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ConnectionTestServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Failed to establish servlet JDBC connection</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            // Load the MYSQL JDBC driver.
            Class.forName("com.mysql.jdbc.Driver");
            
            
            // Create a connection to the Database with a specific user and 
            // password. Remember users have access to a specific tabel in the 
            // database.
            // URL: jdbc:mysql://<host_name>/<database_name>
            Connection con = DriverManager.getConnection(
                "jdbc:mysql://sylvester-mccoy-v3.ics.uci.edu/inf124grp03",
                UserDetails.USERNAME,
                UserDetails.PASSWORD);
            
            // If the connection was successful, create a result set object
            Statement stmt = null;
            ResultSet rs = null;
            //SQL query command
            String SQL = "SELECT * FROM products";
            stmt = con.createStatement();
            rs = stmt.executeQuery(SQL);
            
            
            // Creating a response object only if the connection was establihsed
            // successfully.
            response.setContentType("text/html;charset=UTF-8");
            try (PrintWriter out = response.getWriter()) {
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("<title>Servlet ConnectionServlet</title>");            
                out.println("</head>");
                out.println("<body>");
                out.println("<h1>Servlet JDBC connection established successfully.</h1>");
                while (rs.next()) {
                    out.println(rs.getString("name") + " : " + rs.getInt("id") + ":" + rs.getString("email"));
                    out.println("</br>");
                }
                if(getServletContext().getAttribute("access_count") == null) {
                    getServletContext().setAttribute("access_count", 0);
                }
                int accessCount = (int) getServletContext().getAttribute("access_count");
                accessCount++;
                getServletContext().setAttribute("access_count", accessCount);
            
                out.println("Access Count:" + accessCount);
                out.println("</body>");
                out.println("</html>");
            }
            con.close();
        
        } catch(Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            processRequest(request, response);
        }
    }

   
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
