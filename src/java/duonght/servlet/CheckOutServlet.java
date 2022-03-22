/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package duonght.servlet;

import duonght.dao.OrderDao;
import duonght.dao.accountDao;
import duonght.dto.account;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.HashMap;
import java.util.Random;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Trung Duong
 */
public class CheckOutServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            //lay orderDate la ngay hien hanh
            Date date = new Date(System.currentTimeMillis());
            int status = 1; //processing
            // lay account id tu session
            HttpSession session = request.getSession();
            account acc = (account) session.getAttribute("user");
            String txtFullName = request.getParameter("txtFullName");
            String txtPhone = request.getParameter("txtPhone");
            if ((txtFullName.equals("") || txtPhone.equals(""))) {
                if ((acc == null)) {
                    request.setAttribute("WARNING", "You must Login to finish the shopping");
                    request.getRequestDispatcher("login.jsp").forward(request, response);
                }
            } else {
                HashMap<String, Integer> cart = (HashMap<String, Integer>) session.getAttribute("cart");
                if (cart != null && !cart.isEmpty()) {
                    //random email
                    int leftLimit = 48; // numeral '0'
                    int rightLimit = 122; // letter 'z'
                    int len = 10;
                    Random random = new Random();
                    String generatedString = random.ints(leftLimit, rightLimit + 1)
                            .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                            .limit(len)
                            .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                            .toString();
                    boolean isCreate = accountDao.insertAccount(generatedString + "@gmail.com", "1", txtFullName, txtPhone, 1, 0);
                    if (isCreate) {
                        acc = accountDao.getAccount(generatedString + "@gmail.com", "1");
                    } else {
                        request.setAttribute("WARNING", "The System has something wrong! Please Try again!");
                        request.getRequestDispatcher("mainController?btAction=viewcart").forward(request, response);
                    }
                } else {
                    request.setAttribute("WARNING", "Cart is Empty");
                    request.getRequestDispatcher("mainController?btAction=viewcart").forward(request, response);
                }
            }
            if (acc != null) {
                int accId = acc.getAacId();
                // lay cart tu session
                HashMap<String, Integer> cart = (HashMap<String, Integer>) session.getAttribute("cart");
                if (cart != null && !cart.isEmpty()) {
                    int result = OrderDao.insertOrder(date, status, accId, cart);
                    if (result > 0) {
                        if (acc.getPassword().equals("1")) {
                            session.removeAttribute("cart");
                            request.setAttribute("WARNING", "Save your cart successfully");
                            request.getRequestDispatcher("mainController?btAction=viewcart").forward(request, response);
                        } else {
                            session.removeAttribute("cart");
                            request.setAttribute("WARNING", "Save your cart successfully");
                            request.getRequestDispatcher("mainController?btAction=SearchOrders").forward(request, response);
                        }
                    } else {
                        request.setAttribute("WARNING", "These products are out of stock");
                        request.getRequestDispatcher("mainController?btAction=viewcart").forward(request, response);
                    }
                } else {
                    request.setAttribute("WARNING", "Cart is Empty");
                    request.getRequestDispatcher("mainController?btAction=viewcart").forward(request, response);
                }
            }
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
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
            throws ServletException, IOException {
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
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
