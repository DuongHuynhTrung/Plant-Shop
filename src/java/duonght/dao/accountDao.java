/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package duonght.dao;

import duonght.dto.account;
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
public class accountDao {

    //ham nay de lay cac dong trong bang account
    //tra ve: ds cac object account
    public static ArrayList<account> getAccounts() {
        ArrayList<account> list = new ArrayList<>();
        Connection cn = null;
        Statement st = null;
        ResultSet rs = null;
        try {
            //buoc 1: mo ket noi
            cn = DButils.makeConnection();
            //buoc 2: cau query
            if (cn != null) {
                String sql = "SELECT accID, email, password, fullname, phone, status, role FROM Accounts";
                st = cn.createStatement();
                rs = st.executeQuery(sql);
                //buoc 3: xu li ket qua cua buoc 2
                while (rs.next()) {
                    int id = rs.getInt("accID");
                    String email = rs.getString("email");
                    String password = rs.getString("password");
                    String name = rs.getString("fullname");
                    String phone = rs.getString("phone");
                    int role = rs.getInt("role");
                    int status = rs.getInt("status");
                    account acc = new account(id, email, password, name, status, phone, role);
                    list.add(acc);
                }
            }
            //buoc 4: dong ket noi
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
        return list;
    }

    public static ArrayList<account> getAccountsExcept(String txtemail) {
        ArrayList<account> list = new ArrayList<>();
        Connection cn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            //buoc 1: mo ket noi
            cn = DButils.makeConnection();
            //buoc 2: cau query
            if (cn != null) {
                String sql = "SELECT accID, email, password, fullname, phone, status, role FROM Accounts\n"
                        + "EXCEPT \n"
                        + "SELECT accID, email, password, fullname, phone, status, role FROM Accounts\n"
                        + "WHERE email = ?";
                pst = cn.prepareStatement(sql);
                pst.setString(1, txtemail);
                rs = pst.executeQuery();
                //buoc 3: xu li ket qua cua buoc 2
                while (rs.next()) {
                    int id = rs.getInt("accID");
                    String password = rs.getString("password");
                    String email = rs.getString("email");
                    String name = rs.getString("fullname");
                    String phone = rs.getString("phone");
                    int role = rs.getInt("role");
                    int status = rs.getInt("status");
                    account acc = new account(id, email, password, name, status, phone, role);
                    list.add(acc);
                }
            }
            //buoc 4: dong ket noi
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
        return list;
    }

    //ham nahy de lay account dua vao emaill
    //tra ve: account trung voi email da chon
    public static account getAccount(String email, String password) {
        account acc = null;
        //buoc 1: mo ket noi
        Connection cn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            cn = DButils.makeConnection();
            if (cn != null) {
                String url = "Select accID, email, password, fullname, phone, status, role "
                        + "from Accounts "
                        + "where email = ? and password = ? ";
                pst = cn.prepareStatement(url);
                pst.setString(1, email);
                pst.setString(2, password);
                rs = pst.executeQuery();
                if (rs.next()) {
                    int id = rs.getInt("AccId");
                    String name = rs.getString("fullname");
                    String phone = rs.getString("phone");
                    int role = rs.getInt("role");
                    int status = rs.getInt("status");
                    acc = new account(id, email, password, name, status, phone, role);
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
        return acc;
    }

    public static account getAccount(int AccID) {
        account acc = null;
        //buoc 1: mo ket noi
        Connection cn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            cn = DButils.makeConnection();
            if (cn != null) {
                String url = "Select accID, email, password, fullname, phone, status, role "
                        + "from Accounts "
                        + "where accID = ?";
                pst = cn.prepareStatement(url);
                pst.setInt(1, AccID);
                rs = pst.executeQuery();
                if (rs.next()) {
                    int id = rs.getInt("AccId");
                    String name = rs.getString("fullname");
                    String phone = rs.getString("phone");
                    String email = rs.getString("email");
                    String password = rs.getString("password");
                    int role = rs.getInt("role");
                    int status = rs.getInt("status");
                    acc = new account(id, email, password, name, status, phone, role);
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
        return acc;
    }

    public static account getAccountByEmail(String email) {
        account acc = null;
        //buoc 1: mo ket noi
        Connection cn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            cn = DButils.makeConnection();
            if (cn != null) {
                String url = "Select accID, email, password, fullname, phone, status, role "
                        + "from Accounts "
                        + "where email = ?";
                pst = cn.prepareStatement(url);
                pst.setString(1, email);
                rs = pst.executeQuery();
                if (rs.next()) {
                    int id = rs.getInt("AccId");
                    String name = rs.getString("fullname");
                    String phone = rs.getString("phone");
                    String password = rs.getString("password");
                    int role = rs.getInt("role");
                    int status = rs.getInt("status");
                    acc = new account(id, email, password, name, status, phone, role);
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
        return acc;
    }

    public static account getAccount(String token) {
        account acc = null;
        //buoc 1: mo ket noi
        Connection cn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            cn = DButils.makeConnection();
            if (cn != null) {
                String url = "SELECT accID, email, password, fullname, phone, status, role FROM Accounts\n"
                        + "WHERE token = ?";
                pst = cn.prepareStatement(url);
                pst.setString(1, token);
                rs = pst.executeQuery();
                if (rs.next()) {
                    int id = rs.getInt("accID");
                    String email = rs.getString("email");
                    String password = rs.getString("password");
                    String name = rs.getString("fullname");
                    String phone = rs.getString("phone");
                    int role = rs.getInt("role");
                    int status = rs.getInt("status");
                    acc = new account(id, email, password, name, status, phone, role);
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
        return acc;
    }

    //Ham nay de update status dua tren email
    public static boolean updateAccountStatus(String email, int status) {
        Connection cn = null;
        PreparedStatement pst = null;
        int rs = 0;
        try {
            cn = DButils.makeConnection();
            if (cn != null) {
                String url = "UPDATE Accounts \n"
                        + "SET status = ?\n"
                        + "WHERE email = ?";
                pst = cn.prepareStatement(url);
                pst.setInt(1, status);
                pst.setString(2, email);
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

    //Ham nay de update password, fullname, phone dua tren email
    public static boolean updateAccount(String email, String newPassowrd, String newFullname, String newPhone) {
        Connection cn = null;
        PreparedStatement pst = null;
        int rs = 0;
        try {
            cn = DButils.makeConnection();
            if (cn != null) {
                String url = "UPDATE Accounts \n"
                        + "SET password = ?, fullname = ?, phone = ?\n"
                        + "WHERE email = ?";
                pst = cn.prepareStatement(url);
                pst.setString(1, newPassowrd);
                pst.setString(2, newFullname);
                pst.setString(3, newPhone);
                pst.setString(4, email);
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

    //Ham nay de insert Account
    public static boolean insertAccount(String newEmail, String newPassword, String newFullname, String newPhone, int newStatus, int newRole) {
        Connection cn = null;
        int rs = 0;
        PreparedStatement pst = null;
        try {
            cn = DButils.makeConnection();
            if (cn != null) {
                String url = "INSERT INTO Accounts (email,fullname,password,phone,role,status) "
                        + "VALUES (?,?,?,?,?,?)";
                pst = cn.prepareStatement(url);
                pst.setString(1, newEmail);
                pst.setString(2, newFullname);
                pst.setString(3, newPassword);
                pst.setString(4, newPhone);
                pst.setInt(5, newRole);
                pst.setInt(6, newStatus);
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

    //Ham nay de check Duplicate account by email
    public static boolean isDuplicateAccount(String email) {
        Connection cn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            cn = DButils.makeConnection();
            if (cn != null) {
                String url = "SELECT accID, email, password, fullname, phone, status, role "
                        + "FROM Accounts "
                        + "WHERE email = ? ";
                pst = cn.prepareStatement(url);
                pst.setString(1, email);
                rs = pst.executeQuery();
                if (rs.next()) {
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
            if (rs != null) {
                try {
                    rs.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return false;
    }

    public static boolean updateAccount(String email, String fullname, String phone) {
        Connection cn = null;
        PreparedStatement pst = null;
        int rs = 0;
        try {
            cn = DButils.makeConnection();
            if (cn != null) {
                String url = "UPDATE Accounts SET fullname = ?, phone = ?\n"
                        + "WHERE email = ?";
                pst = cn.prepareStatement(url);
                pst.setString(1, fullname);
                pst.setString(2, phone);
                pst.setString(3, email);
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

    public static boolean updateToken(String token, String email) {
        Connection cn = null;
        PreparedStatement pst = null;
        int rs = 0;
        try {
            cn = DButils.makeConnection();
            if (cn != null) {
                String url = "UPDATE [dbo].[Accounts] SET token = ?\n"
                        + "WHERE email = ?";
                pst = cn.prepareStatement(url);
                pst.setString(1, token);
                pst.setString(2, email);
                rs = pst.executeUpdate();
                if (rs > 0) {
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

    public static boolean isTokenExist(String token) {
        Connection cn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            cn = DButils.makeConnection();
            if (cn != null) {
                String url = "SELECT * FROM Accounts \n"
                        + "WHERE token = ?";
                pst = cn.prepareStatement(url);
                pst.setString(1, token);
                rs = pst.executeQuery();
                if (rs != null & rs.next()) {
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

    public static ArrayList<account> searchAccountByName(String txtname, String txtemail) {
        ArrayList<account> list = new ArrayList<>();
        Connection cn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            cn = DButils.makeConnection();
            if (cn != null) {
                String url = "SELECT accID, email, password, fullname, phone, status, role FROM Accounts \n"
                        + "WHERE Accounts.fullname like ?\n"
                        + "EXCEPT \n"
                        + "SELECT accID, email, password, fullname, phone, status, role FROM Accounts \n"
                        + "WHERE email = ?";
                pst = cn.prepareStatement(url);
                pst.setString(1, "%" + txtname + "%");
                pst.setString(2, txtemail);
                rs = pst.executeQuery();
                while (rs != null && rs.next()) {
                    int id = rs.getInt("accID");
                    String email = rs.getString("email");
                    String password = rs.getString("password");
                    String name = rs.getString("fullname");
                    String phone = rs.getString("phone");
                    int role = rs.getInt("role");
                    int status = rs.getInt("status");
                    account acc = new account(id, email, password, name, status, phone, role);
                    list.add(acc);
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
        return list;
    }

    public static ArrayList<account> getAccountByRole(int role) {
        ArrayList<account> list = new ArrayList<>();
        Connection cn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            cn = DButils.makeConnection();
            if (cn != null) {
                String url = "SELECT accID, email, password, fullname, phone, status, role FROM Accounts \n"
                        + "WHERE Accounts.role = ?";
                pst = cn.prepareStatement(url);
                pst.setInt(1, role);
                rs = pst.executeQuery();
                while (rs != null && rs.next()) {
                    int id = rs.getInt("accID");
                    String email = rs.getString("email");
                    String password = rs.getString("password");
                    String name = rs.getString("fullname");
                    String phone = rs.getString("phone");
                    int status = rs.getInt("status");
                    account acc = new account(id, email, password, name, status, phone, role);
                    list.add(acc);
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
        return list;
    }

    public static boolean deleteAccount(int AccID) {
        Connection cn = null;
        PreparedStatement pst = null;
        int rs = 0;
        try {
            cn = DButils.makeConnection();
            if (cn != null) {
                String url = "DELETE FROM Accounts\n"
                        + "WHERE accID = ?";
                pst = cn.prepareStatement(url);
                pst.setInt(1, AccID);
                rs = pst.executeUpdate();
                if (rs > 0) {
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

    public static boolean isExistOrder(int AccID) {
        ArrayList<account> list = new ArrayList<>();
        Connection cn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            cn = DButils.makeConnection();
            if (cn != null) {
                String url = "SELECT * FROM Orders\n"
                        + "WHERE AccID = ?";
                pst = cn.prepareStatement(url);
                pst.setInt(1, AccID);
                rs = pst.executeQuery();
                if (rs != null && rs.next()) {
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
            if (rs != null) {
                try {
                    rs.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return false;
    }
}
