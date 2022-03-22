/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package duonght.servlet;

import duonght.dao.accountDao;
import duonght.dto.account;
import java.io.IOException;
import java.util.HashMap;
import java.util.Random;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Trung Duong
 */
public class LoginServlet extends HttpServlet {

    private static String LOGIN_PAGE = "login.jsp";
    private static String CUSTOMER_PAGE = "mainController?btAction=SearchOrders&check=customer";
    private static String ADMIN_PAGE = "AdminIndex.jsp";
    private static String VIEWCART_PAGE = "viewCart.jsp";

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

        String email = request.getParameter("txtEmail");
        String passWord = request.getParameter("txtPassWork");
        String save = request.getParameter("savelogin");
        account acc = new account();
        String url = LOGIN_PAGE;
        try {
            String erorr_Message = "Email or Passwork Invalid";
            if (email.equals("") || email == null || passWord == null || passWord.equals("")) {
                Cookie[] c = request.getCookies();
                String token = "";
                if (c != null) {
                    for (Cookie cookie : c) {
                        if (cookie.getName().equals("selector")) {
                            token = cookie.getValue();
                        }
                    }
                }
                if (!token.equals("") && accountDao.isTokenExist(token)) {
                    acc = accountDao.getAccount(token);
                    HttpSession session = request.getSession();
                    if (acc.getRole() == 1) {
                        url = ADMIN_PAGE;
                        session.setAttribute("ADMIN_NAME", acc.getFullname());
                        session.setAttribute("email", acc.getEmail());
                    } else {
                        url = CUSTOMER_PAGE;
                        session.setAttribute("USER_NAME", acc.getFullname());
                        session.setAttribute("email", acc.getEmail());
                    }
                } else {
                    request.setAttribute("MESSAGE", erorr_Message);
                }
            } else {
                acc = accountDao.getAccount(email, passWord);
                if (acc != null) {
                    if (acc.getStatus() == 1) {
                        if (acc.getRole() == 1) {
                            // chuyen qua admin page
                            HttpSession session = request.getSession();
                            if (session != null) {
                                session.setAttribute("role", acc.getRole());
                                session.setAttribute("ADMIN_NAME", acc.getFullname());
                                session.setAttribute("email", email);
                                int leftLimit = 48; // numeral '0'
                                int rightLimit = 122; // letter 'z'
                                int len = 10;
                                Random random = new Random();
                                String generatedString = random.ints(leftLimit, rightLimit + 1)
                                        .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                                        .limit(len)
                                        .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                                        .toString();
                                //create a cookie and attach it to respone object
                                if (save != null) {
                                    String token = generatedString;
                                    boolean isUpdateToken = accountDao.updateToken(token, email);
                                    if (isUpdateToken) {
                                        Cookie cookie = new Cookie("selector", token);
                                        cookie.setMaxAge(60 * 30);
                                        response.addCookie(cookie);
                                    }
                                }
                                url = ADMIN_PAGE;
                            }
                        } else {
                            // chuyen qua customer page
                            HttpSession session = request.getSession(true);
                            if (session != null) {
                                session.setAttribute("USER_NAME", acc.getFullname());
                                HashMap<String, Integer> cart = (HashMap<String, Integer>) session.getAttribute("cart");
                                session.setAttribute("email", email);
                                session.setAttribute("passwork", passWord);
                                session.setAttribute("user", acc);
                                //create a random token
                                int leftLimit = 48; // numeral '0'
                                int rightLimit = 122; // letter 'z'
                                int len = 10;
                                Random random = new Random();
                                String generatedString = random.ints(leftLimit, rightLimit + 1)
                                        .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                                        .limit(len)
                                        .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                                        .toString();
                                //create a cookie and attach it to respone object
                                if (save != null) {
                                    String token = generatedString;
                                    boolean isUpdateToken = accountDao.updateToken(token, email);
                                    if (isUpdateToken) {
                                        Cookie cookie = new Cookie("selector", token);
                                        cookie.setMaxAge(60 * 30);
                                        response.addCookie(cookie);
                                    }
                                }
                                if (cart != null && !cart.isEmpty()) {
                                    url = VIEWCART_PAGE;
                                } else {
                                    url = CUSTOMER_PAGE;
                                }
                            }
                        }
                    } else {
                        request.setAttribute("MESSAGE", "Your account was InActive to shopping!");
                    }
                } else {
                    request.setAttribute("MESSAGE", "Wrong Password");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        request.getRequestDispatcher(url).forward(request, response);
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
