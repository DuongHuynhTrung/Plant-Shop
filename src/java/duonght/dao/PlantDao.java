/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package duonght.dao;

import duonght.dto.Plant;
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
public class PlantDao {

    public static ArrayList<Plant> getPlants(String keyword, String searchBy) {
        ArrayList<Plant> plants = new ArrayList<>();
        Connection cn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            cn = DButils.makeConnection();
            if (cn != null) {
                String url = "SELECT PID, PName, price, imgPath, description, status, Plants.CateID as 'CateID', CateName "
                        + "FROM Plants JOIN Categories ON Categories.CateID = Plants.CateID ";
                if (searchBy.equals("byname")) {
                    url = url + "WHERE PName like ? ";
                } else {
                    url = url + "WHERE CateName like ? ";
                }
                pst = cn.prepareStatement(url);
                pst.setString(1, "%" + keyword + "%");
                rs = pst.executeQuery();
                while (rs.next()) {
                    int PID = rs.getInt("PID");
                    String PName = rs.getString("PName");
                    int price = rs.getInt("price");
                    String imgPath = rs.getString("imgPath");
                    String description = rs.getString("description");
                    int status = rs.getInt("status");
                    int CateTD = rs.getInt("CateID");
                    String CateName = rs.getString("CateName");
                    Plant p1 = new Plant(PID, PName, price, imgPath, description, status, CateTD, CateName);
                    plants.add(p1);
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
        return plants;
    }

    public static ArrayList<Plant> getAllPlants() {
        ArrayList<Plant> plants = new ArrayList<>();
        Connection cn = null;
        Statement st = null;
        ResultSet rs = null;
        try {
            cn = DButils.makeConnection();
            String url = "SELECT PID, PName, price, imgPath, description, status, Plants.CateID as 'CateID', CateName "
                    + "FROM Plants JOIN Categories ON Categories.CateID = Plants.CateID";
            st = cn.createStatement();
            rs = st.executeQuery(url);
            while (rs.next()) {
                int PID = rs.getInt("PID");
                String PName = rs.getString("PName");
                int price = rs.getInt("price");
                String imgPath = rs.getString("imgPath");
                String description = rs.getString("description");
                int status = rs.getInt("status");
                int CateTD = rs.getInt("CateID");
                String CateName = rs.getString("CateName");
                Plant p1 = new Plant(PID, PName, price, imgPath, description, status, CateTD, CateName);
                plants.add(p1);
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
        return plants;
    }

    public static Plant getPlant(int pid) {
        Plant plant = null;
        Connection cn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            cn = DButils.makeConnection();
            if (cn != null) {
                String url = "SELECT [PID], [PName], [price], [imgPath], [description], [status], Plants.[CateID], CateName FROM Plants JOIN Categories ON Plants.CateID = Categories.CateID\n"
                        + "WHERE PID = ?";
                pst = cn.prepareStatement(url);
                pst.setInt(1, pid);
                rs = pst.executeQuery();
                if (rs.next()) {
                    int PID = rs.getInt("PID");
                    String PName = rs.getString("PName");
                    int price = rs.getInt("price");
                    String imgPath = rs.getString("imgPath");
                    String description = rs.getString("description");
                    int status = rs.getInt("status");
                    int CateTD = rs.getInt("CateID");
                    String CateName = rs.getString("CateName");
                    plant = new Plant(PID, PName, price, imgPath, description, status, CateTD, CateName);
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
        return plant;
    }

    public static boolean updateStatus(int pid, int status) {
        Connection cn = null;
        PreparedStatement pst = null;
        int rs = 0;
        try {
            cn = DButils.makeConnection();
            if (cn != null) {
                String url = "UPDATE Plants SET status = ?\n"
                        + "WHERE PID = ?";
                pst = cn.prepareStatement(url);
                pst.setInt(2, pid);
                if (status == 1) {
                    pst.setInt(1, 0);
                } else {
                    pst.setInt(1, 1);
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

    public static boolean createPlant(String name, int price, String img, String description, int CateID, int status) {
        Connection cn = null;
        PreparedStatement pst = null;
        int rs = 0;
        try {
            cn = DButils.makeConnection();
            if (cn != null) {
                String url = "INSERT INTO Plants(PName, price, imgPath, status, description, CateID) \n"
                        + "VALUES (?,?,?,?,?,?)";
                pst = cn.prepareStatement(url);
                pst.setString(1, name);
                pst.setInt(2, price);
                pst.setString(3, img);
                pst.setInt(4, status);
                pst.setString(5, description);
                pst.setInt(6, CateID);
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

    public static boolean deletePlant(int pid) {
        Connection cn = null;
        PreparedStatement pst = null;
        int rs = 0;
        try {
            cn = DButils.makeConnection();
            if (cn != null) {
                String url = "DELETE FROM Plants \n"
                        + "WHERE Plants.PID = ?";
                pst = cn.prepareStatement(url);
                pst.setInt(1, pid);
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

    public static ArrayList<Plant> getPlantByStatus(int status) {
        ArrayList<Plant> list = new ArrayList<>();
        Connection cn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            cn = DButils.makeConnection();
            if (cn != null) {
                String url = "SELECT [PID], [PName], [price], [imgPath], [description], [status], Plants.[CateID], CateName FROM Plants JOIN Categories ON Plants.CateID = Categories.CateID\n"
                        + "WHERE status = ?";
                pst = cn.prepareStatement(url);
                pst.setInt(1, status);
                rs = pst.executeQuery();
                while (rs != null && rs.next()) {
                    int PID = rs.getInt("PID");
                    String PName = rs.getString("PName");
                    int price = rs.getInt("price");
                    String imgPath = rs.getString("imgPath");
                    String description = rs.getString("description");
                    int CateTD = rs.getInt("CateID");
                    String CateName = rs.getString("CateName");
                    Plant plant = new Plant(PID, PName, price, imgPath, description, status, CateTD, CateName);
                    list.add(plant);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public static boolean updatePlant(String name, int price, int cateID, int pid) {
        Connection cn = null;
        PreparedStatement pst = null;
        int rs = 0;
        try {
            cn = DButils.makeConnection();
            if (cn != null) {
                String url = "UPDATE Plants \n"
                        + "SET PName = ?, price = ?, CateID = ?\n"
                        + "WHERE PID = ?";
                pst = cn.prepareStatement(url);
                pst.setString(1, name);
                pst.setInt(2, price);
                pst.setInt(3, cateID);
                pst.setInt(4, pid);
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
