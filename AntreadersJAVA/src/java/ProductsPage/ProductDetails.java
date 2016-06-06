package ProductsPage;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.jdbc.Driver;
import java.sql.*;


public class ProductDetails extends HttpServlet {

    
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
            out.println("<h1>Something went wrong...</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException 
    {
        try (PrintWriter out = response.getWriter()) 
        {
            response.setContentType("text/html;charset=UTF-8");
            try 
            {
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
                Statement stmt;
                ResultSet rs;
                //SQL query command
                String book_id = request.getParameter("PID");
                String SQL = "SELECT * from products where PID = '" + book_id + "'";
                //String SQL = "SELECT * FROM products";
                stmt = con.createStatement();
                rs = stmt.executeQuery(SQL);
                
                
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("<meta charset='utf-8'>");
                out.println("<link rel='stylesheet' type='text/css' href='CSS/styleCSS.css'>");
                out.println("<title>Servlet ConnectionServlet</title>");            
                out.println("</head>");
                out.println("<body>");
                
                
                out.println("<nav id='navbar'>");
                out.println("<a href='/AntreadersJAVA/index.html' id='title'>Antreader Bookstore</a>");
                out.println("<a href='ProductsPageServlet' class='menu'>Products</a>");
                out.println("<a href='/AntreadersJAVA/about.html' class='menu'>About</a>");
                out.println("</nav>");

                out.println("<h1 class='about'>Big Empty Files</h1><br>");
                
                
                while (rs.next()) 
                {
                    out.println("<h1 class='about'>" + rs.getString("title") + " by " + rs.getString("author") + "</h1>");
                    
                    out.println("<img class='inlinepic' src='" + rs.getString("image_link") + "' alt='" + rs.getString("title") + " cover'/>");
                    
                    out.println("<h2 class='about'>Year Published</h2>");
                    out.println("<p class='about'>" + rs.getString("published_date") + "</p>");
                    out.println("<h2 class='about'>Genre</h2>");
                    out.println("<p class='about'>" + rs.getString("genre") + "</p>");
                    out.println("<h2 class='about'>Synopsis</h2>");
                    out.println("<p class='about'>" + rs.getString("synopsis") + "</p>");
                    out.println("<h2 class='about'>Price</h2>");
                    out.println("<p class='about'>" + rs.getString("price") + "</p>");
                    
                    
                    //Add to Cart button
                    //TO DO: Add the correct link ****************************
                    out.println("<form action='ShoppingCart' method='POST'>"
                            + "<input type='hidden' name='command' value='add'>" 
                            + "<input type='hidden' name='name' value='"+rs.getString("title")+"'>" 
                            + "<input type='hidden' name='price' value='"+rs.getString("price")+"'>" 
                            + "<input type='hidden' name='PID' value='"+rs.getInt("PID")+"'>"
                            + "<input type='hidden' name='quantity' value='1'>" 
                            + "<input type='submit' value='Add to Cart'></form><br>");
                    
                    out.println("<form action='ShoppingCart' method='POST'>"
                            + "<input type='hidden' name='command' value='view'>"
                            + "<input type='submit' value='View Cart'></form>");
                                
                }
                if(getServletContext().getAttribute("access_count") == null) 
                {
                    getServletContext().setAttribute("access_count", 0);
                }
                int accessCount = (int) getServletContext().getAttribute("access_count");
                accessCount++;
                getServletContext().setAttribute("access_count", accessCount);

                
                out.println("</table><br>");
                out.println("");
                
                // out.println("Access Count:" + accessCount);
                out.println("</body>");
                out.println("</html>");

                con.close();
            } 
            catch(Exception e) 
            {
                out.println(e.getMessage());
                e.printStackTrace();
                processRequest(request, response);
            }
        }
    }

   
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "This Servlet displays individual products";
    }

}
