/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package duonght.servlet;

import duonght.dao.accountDao;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Trung Duong
 */
public class VerifyMailServlet extends HttpServlet {

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
            String codeInput = request.getParameter("codeInput");
            String codeCheck = request.getParameter("codeCheck");
            int time = Integer.parseInt(request.getParameter("time"));
            if (codeCheck.equals(codeInput)) {
                String email = request.getParameter("txtEmail");
                String fullName = request.getParameter("txtFullName");
                String password = request.getParameter("txtPassWord");
                String phone = request.getParameter("txtPhone");
                boolean checkInsert = accountDao.insertAccount(email, password, fullName, phone, 1, 0);
                if (checkInsert) {
                    request.setAttribute("MESSAGE", "Your Account has already Created!");
                    request.getRequestDispatcher("index.jsp").forward(request, response);
                } else {
                    request.setAttribute("MESSAGE", "Somethings Wrong with your Action");
                    request.getRequestDispatcher("registration.jsp").forward(request, response);
                }
            } else {
                time = time - 1;
                if (time > 0) {
                    request.setAttribute("time", time);
                    request.setAttribute("MESSAGE", "Wrong Verify's Code");
                    request.getRequestDispatcher("VerifyMaiil.jsp").forward(request, response);
                } else {
                    request.setAttribute("MESSAGE", "Your time is out! Please Try again with more carefully!");
                    request.getRequestDispatcher("index.jsp").forward(request, response);
                }
            }
            String match = (String) request.getAttribute("Match");

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
