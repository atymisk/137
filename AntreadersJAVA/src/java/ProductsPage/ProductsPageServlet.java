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
public class ProductsPageServlet extends HttpServlet {

    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ProductsPageServlet</title>");            
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
                String SQL = "SELECT * FROM products";
                stmt = con.createStatement();
                rs = stmt.executeQuery(SQL);
                
                
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                    out.println("<meta charset=\"utf-8\">");
                    out.println("<link rel=\"stylesheet\" type=\"text/css\" href='CSS/styleCSS.css'>");
                    out.println("<title>Products Page</title>");            
                out.println("</head>");
                out.println("<body>");
                
                // DEBUGGING
                //out.println("<h1>Servlet JDBC connection established successfully.</h1>");
                
                out.println("<nav id=\"navbar\">");
                    out.println("<a href=\"/AntreadersJAVA/index.html\" id=\"title\">Antreader Bookstore</a>");
                    out.println("<a href=\"/AntreadersJAVA/PHP/products.php\" class=\"menu\">Products</a>");
                out.println("<a href=\"/AntreadersJAVA/about.html\" class=\"menu\">About</a>");
                out.println("</nav>");

                out.println("<h1 id=\"products_title\">Products</h1>");
                
                
                out.println("<table id=\"products\" align=\"center\">");
                out.println("<tr>");
                    out.println("<td class=\"product_info\">Image</td>");
                    out.println("<td class=\"product_info\">Author(s)</td>");
                    out.println("<td class=\"product_info\">Book Title</td>");
                    out.println("<td class=\"product_info\">Genre</td>");
                    out.println("<td class=\"product_info\">Year</td>");
                    out.println("<td class=\"product_info\">Price</td>");
                out.println("</tr>");
                
                while (rs.next()) 
                {
                    // '<tr style="cursor:pointer" onclick="document.location.href=\'/Antreaders/PHP/one_product.php?title='.$row['title'].'\';">';
                    out.println("<tr style=\"cursor:pointer\" onclick=\"document.location.href='/Antreaders/PHP/one_product.php?title=" + rs.getString("title") + "';\">");
                    out.println("<td height=300 width=250 align=center class='product_info'> <img src='" + rs.getString("image_link") + "' alt='Picture will be coming soon' height=200 width=150></td>");
  
                    out.println("<td class=\"product_info\">" + rs.getString("author") + "</td>");
                    out.println("<td class=\"product_info\">");
                    out.println("<a href=\"/AntreadersJAVA/PHP/one_product.php?title=" + rs.getString("title") + "\" class=\"product_info\">");
                    out.println(rs.getString("title") + "</a></td>");
                    
                    
                    out.println("<td class=\"product_info\">" + rs.getString("genre") + "</td>");
                    out.println("<td class=\"product_info\">"  + rs.getString("published_date") + "</td>");
                    out.println("<td class=\"product_info\">$"  + rs.getString("price") + "</td></tr>");
                    
                    // PRELIMINARY
                    //out.println(rs.getString("title") + " : " + rs.getString("author") + ":" + rs.getString("genre"));
                    //out.println("</br>");
                }
                if(getServletContext().getAttribute("access_count") == null) 
                {
                    getServletContext().setAttribute("access_count", 0);
                }
                int accessCount = (int) getServletContext().getAttribute("access_count");
                accessCount++;
                getServletContext().setAttribute("access_count", accessCount);

                
                out.println("</table>");
                
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
        return "This Servlet displays the products list";
    }

}
