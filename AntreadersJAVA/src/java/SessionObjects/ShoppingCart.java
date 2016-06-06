/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SessionObjects;

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
public class ShoppingCart extends HttpServlet 
{

    private void addItem(HttpServletRequest request, HttpServletResponse response, HttpSession session, Cart shoppingCart) 
            throws ServletException, IOException
    {
        Integer PID = Integer.parseInt(request.getParameter("PID"));
        String name = request.getParameter("name");
        Integer price = Integer.parseInt(request.getParameter("price"));
        Integer quantity = Integer.parseInt(request.getParameter("quantity"));
        
        shoppingCart.addToCart(PID, name, price, quantity);
        session.setAttribute(session.getId(), shoppingCart);
        //response.sendRedirect("ProductsPageServlet");
        viewCart(request,response, shoppingCart);
    }
    
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
    
    private void clearCart(HttpServletRequest request, HttpServletResponse response, HttpSession session, Cart shoppingCart)
            throws ServletException, IOException
    {
        shoppingCart.clearCart();
        session.setAttribute(session.getId(), shoppingCart);
        viewCart(request, response, shoppingCart);
    }
    
    private void viewCart(HttpServletRequest request, HttpServletResponse response, Cart shoppingCart) 
            throws ServletException, IOException 
    {
        try (PrintWriter out = response.getWriter()) 
        {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<link rel='stylesheet' type='text/css' href='CSS/styleCSS.css'>");
            out.println("<script type='text/javascript' src='javascript/CartSiteHandler.js'></script>");
//           out.println("<script type='text/javascript'>function updateCart(){document.forms['uptqty'].submit();}"
//                    + "function checkCart()"
//                    + "{var rows = document.getElementById('carttable').getElementsByTagName('tr').length;"
//                    + "if(rows !== 0){document.getElementById('submitCheckout').disabled = false;}}"
//                    + "</script>");
            out.println("<title>View Shopping Cart</title>");
            out.println("</head>");
            out.println("<body onload='checkCart()'>");
            out.println("<nav id='navbar'>");
            out.println("<a href='index.html' id='title'>Antreader Bookstore</a>\n" +
                        "<a href='ProductsPageServlet' class='menu'>Products</a>\n" +
                        "<a href='about.html' class='menu'>About</a></nav>");
            out.println("<h1>Shopping Cart</h1>");
            out.println("<hr>");
            out.println("<form action='ProductsPageServlet'><input type='submit' value='Back to browsing'></form>");
            out.println("<hr>");
            out.println("<h2>Shopping Cart</h2>");
            HashMap<String, CartItem> items = shoppingCart.getCartItems();
            out.println("<table border='1px' id='carttable'>");
             
            for(CartItem key: items.values())
            {
                out.println("<tr><td>" + key.getName() 
                        + "</td><td>" + "$" + key.getPrice() 
                        + "</td>"
                        + "<form id='uptqty' action='ShoppingCart' method='POST'><input type='hidden' name='command' value='update'>"
                        + "<input type='hidden' name='name' value='"+ key.getName() + "'>"
                        + "<td><input type='number' min='0' onchange='updateCart()' name='qty' value='"+ key.getQuantity()+ "'>" 
                        + "</td></form>"
                        
                        + "<form action='ShoppingCart' method='POST'>"
                        + "<input type='hidden' name='command' value='delete'><input type='hidden' name='name' value='"+ key.getName() + "'>"
                        + "<td>"
                        + "<input type='submit' value='delete'></td></form></tr>");
            }
            out.println("</table><br>"
                    + "<form action='ShoppingCart' method='POST'>" 
                    + "<input type='hidden' name='command' value='clear'>" 
                    + "<input type='submit' value='Clear Cart'>" 
                    + "</form>");
            out.println("<hr>");
            out.println("<form action='ShoppingCart' method='POST'><input type='hidden' name='command' value='checkout'>"
                    + "<input id='submitCheckout' type='submit' disabled value='Checkout'></form>");
            out.println("</body>");
            out.println("</html>");
        }
    }
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
    {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession(true);
        Cart shoppingCart = (Cart) session.getAttribute(session.getId());
        if(shoppingCart == null)
        {
            shoppingCart = new Cart();
            session.setAttribute(session.getId(), shoppingCart);
        }
        
        String command = request.getParameter("command");
        switch(command)
        {
            case "add":
                addItem(request, response, session, shoppingCart);
                break;
            case "delete":
                deleteItem(request, response, session, shoppingCart);
                break;
            case "update":
                updateQTY(request,response,session, shoppingCart);
                break;
            case "clear":
                clearCart(request, response, session, shoppingCart);
                break;
            case "view":
                viewCart(request, response, shoppingCart);
                break;
            case "checkout":
                request.setAttribute("Cart", shoppingCart);
                request.getSession().setAttribute("Session",session);
                RequestDispatcher r = request.getRequestDispatcher("Checkout");
                r.forward(request, response);
                break;
            default:
                viewCart(request, response, shoppingCart);
                break;
        }
    }
    
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
