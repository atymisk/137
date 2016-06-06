/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SessionObjects;

import ProductsPage.UserDetails;
import com.mysql.jdbc.Driver;
import java.sql.*;
import java.util.HashMap;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
/**
 *
 * @author ATY
 */
public class Checkout extends HttpServlet 
{
    private void deleteItem(HttpServletRequest request, HttpServletResponse response, HttpSession session, Cart shoppingCart) 
            throws ServletException, IOException
    {
        String name = request.getParameter("name");
        shoppingCart.deleteFromCart(name);
        session.setAttribute(session.getId(), shoppingCart);
        
        if(!shoppingCart.getCartItems().isEmpty())
        {viewCart(request, response, shoppingCart);}
        else
        {
            RequestDispatcher r = request.getRequestDispatcher("ProductsPageServlet");
            r.forward(request,response);
        }
    }
    
    private void updateQTY(HttpServletRequest request, HttpServletResponse response, HttpSession session, Cart shoppingCart) 
            throws ServletException, IOException
    {
        String name = request.getParameter("name");
        Integer qty = Integer.parseInt(request.getParameter("qty"));
        shoppingCart.updateCart(name, qty);
        session.setAttribute(session.getId(), shoppingCart);
        
        if(!shoppingCart.getCartItems().isEmpty())
        {viewCart(request, response, shoppingCart);}
        else
        {
            RequestDispatcher r = request.getRequestDispatcher("ProductsPageServlet");
            r.forward(request,response);
        }
    }
    private void viewCart(HttpServletRequest request, HttpServletResponse response, Cart shoppingCart) 
            throws ServletException, IOException 
    {
        HashMap<String, CartItem> items = shoppingCart.getCartItems();
        try (PrintWriter out = response.getWriter()) 
        {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<link rel='stylesheet' type='text/css' href='CSS/styleCSS.css'>");
            out.println("<link rel='stylesheet' type='text/css' href='CSS/popupCSS.css'>");
            out.println("<script type='text/javascript' src='javascript/CartSiteHandler.js'></script>");
            out.println("<script type='text/javascript' src='javascript/emailfunction.js'></script>");
            out.println("<title>Checkout</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Checkout</h1>");
            out.println("<form action='ProductsPageServlet'><input type='submit' value='Continue browsing'></form>");
            out.println("<hr>");
            out.println("<h1>Review Items</h1>");
            out.println("<table border='1px'>");
            int total = 0;
            for(CartItem key: items.values())
            {
                out.println("<tr><td>" + key.getName() 
                        + "</td><td>" + "$" + key.getPrice() 
                        + "</td>"
                        + "<form id='uptqty' action='Checkout' method='POST'><input type='hidden' name='command' value='update'>"
                        + "<input type='hidden' name='name' value='"+ key.getName() + "'>"
                        + "<td><input type='number' min='0' onchange='updateCart()' name='qty' value='"+ key.getQuantity()+ "'>" 
                        + "</td></form>"
                        
                        + "<form action='Checkout' method='POST'>"
                        + "<input type='hidden' name='command' value='delete'><input type='hidden' name='name' value='"+ key.getName() + "'>"
                        + "<td>"
                        + "<input type='submit' value='delete'></td></form></tr>");
                total+=key.getTotalPrice();
            }
            
            out.println("<form id='confirmpurchase' action='Checkout' method='POST'>");
            out.println("<input type='hidden' name='command' value='buy'>");
            out.println("<input type='hidden' id='totalamt' name='totalamt' value='"+total+"'>");
            out.println("<p>Total Amount Before Shipping: $"+total+"</p>");
            out.println("<table>" 
                    +"<tr><td class='popup'><label for='name' class='textinpt'>Name</label></td>" 
                    + "<td class='popup'>" 
                    + "<input id='name' name='name' placeholder='Example: Peter Anteater' type='text' onblur='checkName()' required><br/></td>"
                    + "<td class='popup'><p id='nameerr' class='err'></p></td></tr>" 
                    
                    + "<tr><td class='popup'><label for='email' class='textinpt'>Email Address</label></td>"
                    + "<td class='popup'>" 
                    + "<input id='email' name='email' placeholder='Example: peteranteater@uci.edu' type='text' onblur='checkEmail()' required><br/></td>" 
                    + "<td class='popup'><p id='emailerr' class='err'></p></td></tr></table>" 
                    
                    + "<table><tr><td class='popup'><label for='creditcard' class='textinpt'>Credit Card Number</label></td>" 
                    
                    + "<td class='popup'>" 
                    + "<input id='creditcard' name='creditcard' placeholder='Example: 1234 5678 1234 5678' type='text' onblur='checkCreditCard()' required/></td>" 
                    + "<td class='popup'><input id='CSC' name='CSC' placeholder='CSC' type='text' style='width:35%;' onblur='checkCSC()' required/></td></tr>" 
                    + "<tr><td class='popup'><p id='crediterr' class='err'></p></td></tr></table>" 
                    
                    + "<h3 class='infoheader'>Shipping Information</h3>" 
                    + "<table><tr>" 
                    + "<td class='popup'><label for='addr1' class='textinpt'>Shipping Address Line 1</label></td>" 
                    + "<td class='popup'><input id='addr1' name='addr1' placeholder='Example: 123 Main St.' type='text' onblur='checkShippingAddress1()' required/></td>" 
                    + "<td class='popup'><p id='shippingerr1' class='err'></p></td></tr>" 
                    + "<tr>" 
                    + "<td class'popup'><label for='addr2' class='textinpt'>Shipping Address Line 2</label></td>" 
                    + "<td class='popup'><input id='addr2' name='addr2' placeholder='optional' onblur='checkShippingAddress2()' type='text'/></td>" 
                    + "<td class'popup'><p id='shippingerr2' class='err'></p></td></tr></table>" 
                    
                    + "<table style='margin-left: 3%;'><tr>" 
                    + "<td class='popup'><label for='city' class='textinpt'>City</label></td>" 
                    + "<td class'popup'><input id='city' name='city' placeholder='Example: Irvine' type='text' onblur='checkCity()' required/></td>" 
                    + "<td class='popup'><label for='state' class='textinpt'>State</label></td>"
                    
                    + "<td class='popup'>" 
                    + "<select id='state' name='state' onchange='checkState()' required>" 
                    + "<option value='' disabled selected>--</option>" 
                    + "<option value='AL'>AL</option><option value='AK'>AK</option>" 
                    + "<option value='AR'>AR</option><option value='AZ'>AZ</option><option value='CA'>CA</option>" 
                    + "<option value='CO'>CO</option><option value='CT'>CT</option><option value='DE'>DE</option>" 
                    + "<option value='FL'>FL</option><option value='GA'>GA</option><option value='HI'>HI</option>" 
                    + "<option value='ID'>ID</option><option value='IL'>IL</option><option value='IN'>IN</option>" 
                    + "<option value='IA'>IA</option><option value='KS'>KS</option><option value='KY'>KY</option>" 
                    + "<option value='LA'>LA</option><option value='ME'>ME</option><option value='MD'>MD</option>" 
                    + "<option value='MA'>MA</option><option value='MI'>MI</option><option value='MN'>MN</option>" 
                    + "<option value='MS'>MS</option><option value='MO'>MO</option><option value='MT'>MT</option>" 
                    + "<option value='NE'>NE</option><option value='NV'>NV</option><option value='NH'>NH</option>" 
                    + "<option value='NJ'>NJ</option><option value='NM'>NM</option><option value='NY'>NY</option>" 
                    + "<option value='NC'>NC</option><option value='ND'>ND</option><option value='OH'>OH</option>" 
                    + "<option value='OK'>OK</option><option value='OR'>OR</option><option value='PA'>PA</option>" 
                    + "<option value='RI'>RI</option><option value='SC'>SC</option><option value='SD'>SD</option>" 
                    + "<option value='TN'>TN</option><option value='TX'>TX</option><option value='UT'>UT</option>" 
                    + "<option value='VT'>VT</option><option value='VA'>VA</option><option value='WA'>WA</option>" 
                    + "<option value='WV'>WV</option><option value='WI'>WI</option><option value='WY'>WY</option>" 
                    + "</select></td>"
                    
                    + "<td class='popup'><label for='zip'>ZIP</label></td>" 
                    + "<td class='popup'>"
                    + "<input id='zip' name='zip' placeholder='ex: 12345' size='5' type='text' onblur='checkZIP()' required/></td></tr>" 
                    + "<tr><td><input type='hidden' name='address' id='address'></td></tr></table>" 
                    + "<p id='addrerr' class='err'></p>" 
                    + "<table style='margin-top: 0px;'>" 
                    + "<tr><td class='popup'><label for='country'>Country</label></td>" 
                    + "<td class='popup'>"
                    + "<input id='country' name='country' type='text' placeholder='ex: United States of America' onblur='checkCountry()' required/></td>" 
                    + "<td class='popup'><p id='countryerr' class='err'></p></td>\n</tr></table>" 
                    
                    + "<h3 class='infoheader'>Shipping Options</h3><table>" 
                    + "<tr><td class='popup'><p class='err' id='shipmethoderr'></p></td></tr>"
                    
                    + "<tr><td class='popup'>" 
                    + "<input type='radio' name='shipping' id='overnight' onclick='checkShippingOptions()' value='overnight'>" 
                    + "<label for='overnight'>Overnight Shipping</label></td></tr>" 
                    
                    + "<tr><td class='popup'>"
                    + "<input type='radio' name='shipping' id='2days' onclick='checkShippingOptions()' value='2day'>"
                    + "<label for='2days'>2 Day Shipping</label>"
                    + "</td></tr>"
                    
                    + "<tr><td class='popup'>"
                    + "<input type='radio' name='shipping' id='1week' onclick='checkShippingOptions()' value='1 Week'>"
                    + "<label for='1week'>1 week Shipping</label></td>"
                    + "</tr></table>"
                    
                    + "<h3 class='infoheader'>Total Amount</h3>" 
                    + "<table style='margin-left: 10%;'>" 
                    + "<tr><td><i>Sales Tax: </i></td>" 
                    + "<td><p class='money' id='salestax'>$0</p></td></tr>" 
                    + "<tr><td><i>Shipping: </i></td>" 
                    + "<td><p class='money' id='shipping'>$0</p></td></tr>" 
                    + "<tr><td><i>Total: </i></td><td><p class='money' id='totalprice'>$"+total+"</p></td></tr></table>");
            
            out.println("<input type='submit' id='submitpurchase' value='Confirm Purchase' disabled>");
            out.println("</form>");
            
            out.println("</table><br>");
            out.println("</body>");
            out.println("</html>");
        }
    }
    
    private void confirmpurchase(HttpServletRequest request, HttpServletResponse response, Cart shoppingCart, HttpSession session) 
            throws ServletException, IOException 
    {
        //validate the fields via js: disabled submit
        //access db
        //write to db about products purchsed
        //get order ID
        //empty the cart
        //destroy session object
        //forward to jsp with order ID
        try(PrintWriter out = response.getWriter())
        {
            try
            {
                Class.forName("com.mysql.jdbc.Driver");

                Connection con = DriverManager.getConnection(
                    "jdbc:mysql://sylvester-mccoy-v3.ics.uci.edu/inf124grp03",
                    UserDetails.USERNAME,
                    UserDetails.PASSWORD);

                //SQL query command
                String SQL = "INSERT INTO orders (name, email, creditcard, address, shipping, paid) VALUES (?,?,?,?,?,?)";
                PreparedStatement stmt = con.prepareStatement(SQL,Statement.RETURN_GENERATED_KEYS);

                stmt.setString(1, request.getParameter("name"));
                stmt.setString(2, request.getParameter("email"));
                stmt.setString(3, request.getParameter("creditcard"));
                stmt.setString(4, request.getParameter("address"));
                stmt.setString(5, request.getParameter("shipping"));
                stmt.setInt(6, Integer.parseInt(request.getParameter("totalamt")));

                stmt.execute();
                ResultSet rs = stmt.getGeneratedKeys();
                rs.next();
                int order_id = rs.getInt(1);

                out.println(order_id);

                for(CartItem c: shoppingCart.getCartItems().values())
                {
                    SQL = "INSERT INTO product_order (OID, PID, QTY) VALUES (?,?,?)";
                    stmt = con.prepareStatement(SQL);
                    stmt.setInt(1, order_id);
                    stmt.setInt(2, c.getPID());
                    stmt.setInt(3, c.getQuantity());
                    stmt.executeUpdate();
                }
                
                stmt.close();
                
                shoppingCart.clearCart();//clear the cart
                if(session != null){session.invalidate();}//destroy session object
                request.setAttribute("OID", order_id);//save the order id
                RequestDispatcher rq = request.getRequestDispatcher("orderdetails.jsp");
                rq.forward(request, response);
            }
            catch(SQLException e)
            {
                out.println("SQL: "+e.getMessage());
            }
            catch(ClassNotFoundException e)
            {
                out.println(e.getMessage());
            }
        }
    }
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException 
    {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = (HttpSession)request.getSession().getAttribute("Session");
        //Cart shoppingCart = (Cart)request.getAttribute("Cart");
        Cart shoppingCart = (Cart) session.getAttribute(session.getId());
        
        String command = request.getParameter("command");
        switch(command)
        {
            case "delete":
                deleteItem(request, response, session, shoppingCart);
                break;
            case "update":
                updateQTY(request,response,session, shoppingCart);
                break;
            case "view":
                viewCart(request, response, shoppingCart);
                break;
            case "buy":
                confirmpurchase(request,response,shoppingCart, session);
                break;
            /*
                Input fields
                form validation
            */
            default:
                viewCart(request, response, shoppingCart);
                break;
        }
        
    }

    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException 
    {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException 
    {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() 
    {
        return "Short description";
    }

}
