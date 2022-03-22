/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package duonght.dao;

import duonght.dto.Order;
import duonght.dto.OrderDetails;
import duonght.utils.DButils;
import java.sql.Array;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author Trung Duong
 */
public class OrderDao {

    public static ArrayList<Order> getOrders(String email) throws Exception {
        ArrayList<Order> orders = new ArrayList<>();
        Connection cn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            cn = DButils.makeConnection();
            if (cn != null) {
                String url = "SELECT Orders.OrderID, OrdDate, shipdate, Orders.status, Accounts.AccID "
                        + "FROM Orders JOIN Accounts ON Orders.AccID = Accounts.accID "
                        + "WHERE Accounts.email = ?";
                pst = cn.prepareStatement(url);
                pst.setString(1, email);
                rs = pst.executeQuery();
                if (rs != null) {
                    while (rs.next()) {
                        int OrderID = rs.getInt("OrderID");
                        String OrdDate = rs.getString("OrdDate");
                        String shipdate = rs.getString("shipdate");
                        int status = rs.getInt("status");
                        int AccID = rs.getInt("AccID");
                        Order order = new Order(OrderID, OrdDate, shipdate, status, AccID);
                        orders.add(order);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (cn != null) {
                try {
                    cn.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (pst != null) {
                try {
                    pst.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (rs != null) {
                try {
                    rs.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        return orders;
    }

    public static ArrayList<Order> getOrders() {
        ArrayList<Order> orders = new ArrayList<>();
        Connection cn = null;
        Statement st = null;
        ResultSet rs = null;
        try {
            cn = DButils.makeConnection();
            if (cn != null) {
                String url = "SELECT OrderID, OrdDate, shipdate, status, AccID FROM Orders";
                st = cn.createStatement();
                rs = st.executeQuery(url);
                while (rs != null && rs.next()) {
                    int OrderID = rs.getInt("OrderID");
                    String OrdDate = rs.getString("OrdDate");
                    String shipdate = rs.getString("shipdate");
                    int status = rs.getInt("status");
                    int accID = rs.getInt("AccID");
                    Order order = new Order(OrderID, OrdDate, shipdate, status, accID);
                    orders.add(order);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (cn != null) {
                try {
                    cn.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (st != null) {
                try {
                    st.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (rs != null) {
                try {
                    rs.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return orders;
    }

    public static ArrayList<OrderDetails> getOrderDetail(int orderID) throws Exception {
        ArrayList<OrderDetails> orderDetails = new ArrayList<>();
        Connection cn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;

        try {
            cn = DButils.makeConnection();
            if (cn != null) {
                String url = "SELECT DetailId, OrderID, PID, PName, price, imgPath, quantity FROM OrderDetails JOIN Plants ON OrderDetails.FID = Plants.PID\n"
                        + "WHERE OrderID = ? ";
                pst = cn.prepareStatement(url);
                pst.setInt(1, orderID);
                rs = pst.executeQuery();
                if (rs != null) {
                    while (rs.next()) {
                        int detailID = rs.getInt("DetailId");
                        int OrderID = rs.getInt("OrderID");
                        int PID = rs.getInt("PID");
                        String PName = rs.getString("PName");
                        int price = rs.getInt("price");
                        String imgPath = rs.getString("imgPath");
                        int quanlity = rs.getInt("quantity");
                        OrderDetails orderdetail = new OrderDetails(detailID, OrderID, PID, PName, price, imgPath, quanlity);
                        orderDetails.add(orderdetail);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (cn != null) {
                try {
                    cn.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (pst != null) {
                try {
                    pst.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (rs != null) {
                try {
                    rs.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        return orderDetails;
    }

    public static boolean ReOrder(int status, int orderId) {
        Connection cn = null;
        PreparedStatement pst = null;
        int rs = 0;
        try {
            cn = DButils.makeConnection();
            if (cn != null) {
                String url = "UPDATE Orders SET status = ? WHERE OrderID = ?";
                pst = cn.prepareStatement(url);
                if (status == 1) {
                    pst.setInt(1, 3);
                    pst.setInt(2, orderId);

                } else {
                    pst.setInt(1, 1);
                    pst.setInt(2, orderId);
                }
                rs = pst.executeUpdate();
                if (rs != 0) {
                    return true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (cn != null) {
                try {
                    cn.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (pst != null) {
                try {
                    pst.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return false;
    }

    //Hma nay de chhen vao bang order va orderdetail
    public static int insertOrder(Date orderdate, int status, int accid, HashMap<String, Integer> cart) {
        int result = 0;
        try {
            Connection cn = DButils.makeConnection();
            if (cn != null) {
                // De tat tinh nang set du lieu vao bang vat li - Chi luu du lieu tren bang tam cua database
                cn.setAutoCommit(false);
                //insert vao bang order
                String url = "INSERT Orders(OrdDate,status,AccID) \n"
                        + "VALUES (?,?,?)";
                PreparedStatement pst = cn.prepareStatement(url);
                pst.setDate(1, orderdate);
                pst.setInt(2, status);
                pst.setInt(3, accid);
                result = pst.executeUpdate();
                if (result > 0) {
                    //lay orderID vua chen o tren
                    url = "SELECT TOP 1 OrderID "
                            + "FROM Orders "
                            + "ORDER BY OrderID desc";
                    pst = cn.prepareStatement(url);
                    ResultSet rs = pst.executeQuery();
                    if (rs != null && rs.next()) {
                        int orderid = rs.getInt("OrderID");
                        // chen order detaiil
                        for (String pid : cart.keySet()) {
                            int quantity = cart.get(pid);
                            url = "INSERT OrderDetails (OrderID,FID,quantity) "
                                    + "VALUES (?,?,?)";
                            pst = cn.prepareStatement(url);
                            pst.setInt(2, Integer.parseInt(pid.trim()));
                            pst.setInt(1, orderid);
                            pst.setInt(3, quantity);
                            result = pst.executeUpdate();
                        }
                        cn.commit();
                        cn.setAutoCommit(true);
                    } else {
                        System.out.println("K chen duoc order");
                    }
                }
                cn.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {

        }
        return result;
    }

    public static ArrayList<Order> searchOrderByAccID(int accID) {
        ArrayList<Order> result = new ArrayList<>();
        Connection cn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            cn = DButils.makeConnection();
            if (cn != null) {
                String url = "SELECT OrderID, OrdDate, shipdate, AccID, status FROM Orders\n"
                        + "WHERE AccID = ?";
                pst = cn.prepareStatement(url);
                pst.setInt(1, accID);
                rs = pst.executeQuery();
                while (rs != null && rs.next()) {
                    int OrderID = rs.getInt("OrderID");
                    String OrdDate = rs.getString("OrdDate");
                    String shipdate = rs.getString("shipdate");
                    int status = rs.getInt("status");
                    Order order = new Order(OrderID, OrdDate, shipdate, status, accID);
                    result.add(order);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
    
    public static ArrayList<Order> getOrderByStatus(int status) {
        ArrayList<Order> result = new ArrayList<>();
        Connection cn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            cn = DButils.makeConnection();
            if (cn != null) {
                String url = "SELECT OrderID, OrdDate, shipdate, AccID, status FROM Orders\n"
                        + "WHERE status = ?";
                pst = cn.prepareStatement(url);
                pst.setInt(1, status);
                rs = pst.executeQuery();
                while (rs != null && rs.next()) {
                    int OrderID = rs.getInt("OrderID");
                    String OrdDate = rs.getString("OrdDate");
                    String shipdate = rs.getString("shipdate");
                    int accID = rs.getInt("AccID");
                    Order order = new Order(OrderID, OrdDate, shipdate, status, accID);
                    result.add(order);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
