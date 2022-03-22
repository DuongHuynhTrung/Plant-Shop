/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package duonght.servlet;

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
public class mainController extends HttpServlet {

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

        String url = "index.jsp";
        String action = request.getParameter("btAction");
        try {
            if (action == null || action.equals("")) {

            } else if (action.equals("search")) {
                url = "SearchServlet";
            } else if (action.equals("login")) {
                url = "LoginServlet";
            } else if (action.equals("Register")) {
                url = "RegistrationServlet";
            } else if (action.equals("Save")) {
                url = "UpdateServlet";
            } else if (action.equals("addtocart")) {
                url = "AddToCartServlet";
            } else if (action.equals("viewcart")) {
                url = "viewCart.jsp";
            } else if (action.equals("Update Cart")) {
                url = "UpdateCartServlet";
            } else if (action.equals("Delete Cart")) {
                url = "DeleteCartServlet";
            } else if (action.equals("Save Order")) {
                url = "CheckOutServlet";
            } else if (action.equals("logout")) {
                url = "Logout";
            } else if (action.equals("viewdetail")) {
                url = "GetPlantServlet";
            } else if (action.equals("manageAccounts")) {
                url = "manageAccountsServlet";
            } else if (action.equals("updateStatusAccount")) {
                url = "UpdateStatusAccountServlet";
            } else if (action.equals("SearchAccount")) {
                url = "SearchAccountServlet";
            } else if (action.equals("SearchOrders")) {
                url = "SearchByDateServlet";
            } else if (action.equals("getCompleteOrders")) {
                url = "GetCompletedOrdersServlet";
            } else if (action.equals("getCanceledOrders")) {
                url = "GetCanceledOrdersServlet";
            } else if (action.equals("getProcessingOrders")) {
                url = "GetProcessingOrdersServlet";
            } else if (action.equals("manageOrders")) {
                url = "SearchByDateServlet";
            } else if (action.equals("SearchOrder")) {
                url = "SearchOrderServlet";
            } else if (action.equals("managePlants")) {
                url = "GetCategoryServlet";
            } else if (action.equals("updateStatusPlant")) {
                url = "UpdateStatusPlantSerlvet";
            } else if (action.equals("Create")) {
                url = "CreatePlantServlet";
            } else if (action.equals("deletePlant")) {
                url = "DeletePlantServlet";
            } else if (action.equals("SavePlant")) {
                url = "UpdatePlantSerlvet";
            } else if (action.equals("manageCategory")) {
                url = "GetCategoryServlet";
            } else if (action.equals("CreateCategory")) {
                url = "CreateCategoryServlet";
            } else if (action.equals("UpdateCategory")) {
                url = "UpdateCategoryServlet";
            } else if (action.equals("DeleteCategory")) {
                url = "DeleteCategoryServlet";
            } else if (action.equals("deleteAccount")) {
                url = "DelelteAccountServlet";
            } else if (action.equals("Verify")) {
                url = "VerifyMailServlet";
            } else if (action.equals("deleteOrder")) {
                url = "DeleteOrderServlet";
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
