/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package duonght.dao;

import duonght.dto.Category;
import duonght.utils.DButils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Trung Duong
 */
public class CategoryDao {

    public static ArrayList<Category> getCategories() {
        ArrayList<Category> result = new ArrayList<>();
        Connection cn = null;
        Statement st = null;
        ResultSet rs = null;
        try {
            cn = DButils.makeConnection();
            if (cn != null) {
                String url = "SELECT CateID, CateName FROM Categories";
                st = cn.createStatement();
                rs = st.executeQuery(url);
                while (rs != null && rs.next()) {
                    int CateID = rs.getInt("CateID");
                    String CateName = rs.getString("CateName");
                    Category cate = new Category(CateID, CateName);
                    result.add(cate);
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
        return result;
    }

    public static int getCateID(String CateName) {
        Connection cn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            cn = DButils.makeConnection();
            if (cn != null) {
                String url = "SELECT CateID FROM Categories\n"
                        + "WHERE CateName = ?";
                pst = cn.prepareStatement(url);
                pst.setString(1, CateName);
                rs = pst.executeQuery();
                if (rs != null && rs.next()) {
                    return rs.getInt("CateID");
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
        return 0;
    }

    public static boolean createCategory(String name) {
        Connection cn = null;
        PreparedStatement pst = null;
        int rs = 0;
        try {
            cn = DButils.makeConnection();
            if (cn != null) {
                String url = "INSERT INTO Categories(CateName)\n"
                        + "VALUES (?)";
                pst = cn.prepareStatement(url);
                pst.setString(1, name);
                rs = pst.executeUpdate();
                if (rs != 0) {
                    return true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean updateCategory(String name, int cateID) {
        Connection cn = null;
        PreparedStatement pst = null;
        int rs = 0;
        try {
            cn = DButils.makeConnection();
            if (cn != null) {
                String url = "UPDATE Categories \n"
                        + "SET CateName = ?\n"
                        + "WHERE CateID = ?";
                pst = cn.prepareStatement(url);
                pst.setString(1, name);
                pst.setInt(2, cateID);
                rs = pst.executeUpdate();
                if (rs != 0) {
                    return true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean deleteCategory(int cateID) {
        Connection cn = null;
        PreparedStatement pst = null;
        int rs = 0;
        try {
            cn = DButils.makeConnection();
            if (cn != null) {
                String url = "DELETE FROM Categories\n"
                        + "WHERE CateID = ?";
                pst = cn.prepareStatement(url);
                pst.setInt(1, cateID);
                rs = pst.executeUpdate();
                if (rs != 0) {
                    return true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
