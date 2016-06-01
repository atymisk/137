/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SessionObjects;

import java.util.HashMap;
import java.io.IOException;
import java.io.PrintWriter;
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
        viewCart(request, response, shoppingCart);
    }
    
    private void updateQTY(HttpServletRequest request, HttpServletResponse response, HttpSession session, Cart shoppingCart) 
            throws ServletException, IOException
    {
        String name = request.getParameter("name");
        Integer qty = Integer.parseInt(request.getParameter("qty"));
        shoppingCart.updateCart(name, qty);
        session.setAttribute(session.getId(), shoppingCart);
        viewCart(request, response, shoppingCart);
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
            out.println("<script type='text/javascript'>function updateCart(){document.forms['uptqty'].submit();}</script>");
            out.println("<title>Checkout</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Checkout</h1>");
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
            out.println("<p>Total Amount: $"+total+"</p>");
            out.println("</table><br>");
            out.println("</body>");
            out.println("</html>");
        }
    }
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException 
    {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = (HttpSession)request.getAttribute("Session");
        Cart shoppingCart = (Cart)request.getAttribute("Cart");
        
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
