/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package duonght.servlet;

import duonght.dao.OrderDao;
import duonght.dto.Order;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Trung Duong
 */
public class SearchByDateServlet extends HttpServlet {

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
            throws ServletException, IOException, ParseException, Exception {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            String txtDateFrom = request.getParameter("from");
            String txtDateTo = request.getParameter("to");
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-mm-dd");
            Date today = new Date();
            Date dateFrom = null;
            Date dateTo = null;
            if (txtDateFrom != null && !txtDateFrom.equals("") && txtDateTo != null && !txtDateTo.equals("")) {
                dateFrom = formatter.parse(txtDateFrom);
                dateTo = formatter.parse(txtDateTo);
            } else {
                dateFrom = new SimpleDateFormat("yyyy-mm-dd").parse("1000-10-10");
                dateTo = today;
            }
            ArrayList<Order> orders = new ArrayList<>();
            ArrayList<Order> result = new ArrayList<>();
            ArrayList<Order> completed = new ArrayList<>();
            ArrayList<Order> processing = new ArrayList<>();
            ArrayList<Order> canceled = new ArrayList<>();
            String check = request.getParameter("check");
            if (check == null) {
                check = "customer";
            }
            switch (check) {
                case "customer":
                    HttpSession session = request.getSession(false);
                    String email = (String) session.getAttribute("email");
                    orders = OrderDao.getOrders(email);
                    for (Order order : orders) {
                        Date date = new SimpleDateFormat("yyyy-mm-dd").parse(order.getOrderDate());
                        if (date.after(dateFrom) && date.before(dateTo)) {
                            result.add(order);
                        }
                    }
                    request.setAttribute("result", result);
                    request.getRequestDispatcher("customerPage.jsp").forward(request, response);
                    break;
                case "admin":
                    orders = OrderDao.getOrders();
                    for (Order order : orders) {
                        Date date = new SimpleDateFormat("yyyy-mm-dd").parse(order.getOrderDate());
                        if (date.after(dateFrom) && date.before(dateTo)) {
                            result.add(order);
                            if (order.getStatus() == 1) {
                                processing.add(order);
                            }
                            if (order.getStatus() == 2) {
                                completed.add(order);
                            }
                            if (order.getStatus() == 3) {
                                canceled.add(order);
                            }
                            
                        }
                    }
                    request.setAttribute("orders", orders);
                    request.setAttribute("completed", completed);
                    request.setAttribute("processing", processing);
                    request.setAttribute("canceled", canceled);
                    request.setAttribute("result", result);
                    request.getRequestDispatcher("ManageOrders.jsp").forward(request, response);
                    break;
                default:
                    response.sendRedirect("index.jsp");
                    break;
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
        try {
            processRequest(request, response);
        } catch (ParseException ex) {
            Logger.getLogger(SearchByDateServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(SearchByDateServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        try {
            processRequest(request, response);
        } catch (ParseException ex) {
            Logger.getLogger(SearchByDateServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(SearchByDateServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
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
