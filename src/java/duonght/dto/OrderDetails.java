/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package duonght.dto;

/**
 *
 * @author Trung Duong
 */
public class OrderDetails {
    private int orderDetailID;
    private int orderID;
    private int plantID;
    private String plantName;
    private int price;
    private String imgPath;
    private int quanlity;

    public OrderDetails() {
    }

    public OrderDetails(int orderDetailID, int orderID, int plantID, String plantName, int price, String imgPath, int quanlity) {
        this.orderDetailID = orderDetailID;
        this.orderID = orderID;
        this.plantID = plantID;
        this.plantName = plantName;
        this.price = price;
        this.imgPath = imgPath;
        this.quanlity = quanlity;
    }

    public int getQuanlity() {
        return quanlity;
    }

    public void setQuanlity(int quanlity) {
        this.quanlity = quanlity;
    }

    public int getOrderDetailID() {
        return orderDetailID;
    }

    public void setOrderDetailID(int orderDetailID) {
        this.orderDetailID = orderDetailID;
    }

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public int getPlantID() {
        return plantID;
    }

    public void setPlantID(int plantID) {
        this.plantID = plantID;
    }

    public String getPlantName() {
        return plantName;
    }

    public void setPlantName(String plantName) {
        this.plantName = plantName;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }
    
}
