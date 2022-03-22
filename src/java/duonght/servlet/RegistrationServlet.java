/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package duonght.servlet;

import duonght.dao.accountDao;
import duonght.utils.JavaMailUntil;
import java.io.IOException;
import java.util.Random;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Trung Duong
 */
public class RegistrationServlet extends HttpServlet {

    private static String REGISTRATION_PAGE = "registration.jsp";
    private static String VERIFY_PAGE = "verify.jsp";

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

        String url = REGISTRATION_PAGE;
        String email = request.getParameter("txtEmail");
        request.setAttribute("txtEmail", email);
        String fullName = request.getParameter("txtFullName");
        request.setAttribute("txtFullName", fullName);
        String password = request.getParameter("txtPassWord");
        request.setAttribute("txtPassWord", password);
        String passwordConfirm = request.getParameter("txtPassWordConfirm");
        String phone = request.getParameter("txtPhone");
        request.setAttribute("txtPhone", phone);
        boolean check = true;

        if (!phone.matches("[0-9]+")) {
            request.setAttribute("txtEmail", email);
            request.setAttribute("txtFullName", fullName);
            request.setAttribute("txtPassWord", password);
            request.setAttribute("txtPassWordConfirm", passwordConfirm);
            request.setAttribute("txtPhone", phone);
            request.setAttribute("ERROR_PHONE", "Phone must be number!");
            check = false;
        } else if (phone.length() != 10) {
            request.setAttribute("txtEmail", email);
            request.setAttribute("txtFullName", fullName);
            request.setAttribute("txtPassWord", password);
            request.setAttribute("txtPassWordConfirm", passwordConfirm);
            request.setAttribute("txtPhone", phone);
            request.setAttribute("ERROR_PHONE", "Phone must be 10 number!");
        }
        if (fullName.length() < 5) {
            request.setAttribute("txtEmail", email);
            request.setAttribute("txtFullName", fullName);
            request.setAttribute("txtPassWord", password);
            request.setAttribute("txtPassWordConfirm", passwordConfirm);
            request.setAttribute("txtPhone", phone);
            request.setAttribute("ERROR_FULLNAME", "Name must more than 5 character!");
            check = false;
        }
        if (password.length() < 5) {
            request.setAttribute("txtEmail", email);
            request.setAttribute("txtFullName", fullName);
            request.setAttribute("txtPassWord", password);
            request.setAttribute("txtPassWordConfirm", passwordConfirm);
            request.setAttribute("txtPhone", phone);
            request.setAttribute("ERROR_PASSWORD", "Password is too short!");
            check = false;
        }

        if (!email.matches("\\b[A-Z0-9._%-]+@[A-Z0-9.-]+\\.[A-Z]{2,4}\\b")) {
            request.setAttribute("txtEmail", email);
            request.setAttribute("txtFullName", fullName);
            request.setAttribute("txtPassWord", password);
            request.setAttribute("txtPassWordConfirm", passwordConfirm);
            request.setAttribute("txtPhone", phone);
            request.setAttribute("ERROR_EMAIL", "Wrong email format!");
            check = false;
        }
        if (!password.equals(passwordConfirm)) {
            request.setAttribute("txtEmail", email);
            request.setAttribute("txtFullName", fullName);
            request.setAttribute("txtPassWord", password);
            request.setAttribute("txtPassWordConfirm", passwordConfirm);
            request.setAttribute("txtPhone", phone);
            request.setAttribute("ERROR_CONFIRM", "The Password does not Match!");
            check = false;
        }
        if (!check) {
            request.getRequestDispatcher(url).forward(request, response);
        }
        try {
            if (email == "" || fullName == "" || password == "" || phone == "") {
                url = REGISTRATION_PAGE;
                request.setAttribute("ERROR", "NOT EMPTY FLIED ALLOWS!");
            } else {
                boolean isDuplicate = accountDao.isDuplicateAccount(email);
                if (!isDuplicate) {

                    // random code verify
                    int leftLimit = 48; // numeral '0'
                    int rightLimit = 122; // letter 'z'
                    int len = 10;
                    Random random = new Random();
                    String code = random.ints(leftLimit, rightLimit + 1)
                            .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                            .limit(len)
                            .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                            .toString();
                    JavaMailUntil.SendMail(email, code);
                    request.setAttribute("SendOTP", email);
                    request.setAttribute("code", code);
                    request.setAttribute("time", 3);
                    url = "VerifyMaiil.jsp";
                } else {
                    request.setAttribute("ERROR", "ACCOUNT HAS ALREADY EXIST");
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
