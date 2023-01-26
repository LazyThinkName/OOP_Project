/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.utem.groupproject;


/**
 *
 * @author mangy
 */
public abstract class Item{
    protected String itemID;
    protected String itemType;
    private String itemName;
    private double itemPrice;
    private int itemQuantity;
    private String arrivalDate;
    private String expiredDate;

    public Item() {
    }

    public abstract void setItemID(int itemID);

    public abstract void setItemType();

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }
    
    public void setItemPrice(double itemPrice) {
        this.itemPrice = itemPrice;
    }

    public void setItemQuantity(int itemQuantity) {
        this.itemQuantity = itemQuantity;
    }

    public void setArrivalDate(String arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    public void setExpiredDate(String expiredDate) {
        this.expiredDate = expiredDate;
    }

    public String getItemID() {
        return itemID;
    }

    public String getItemType() {
        return itemType;
    }

    public String getItemName() {
        return itemName;
    }

    public double getItemPrice() {
        return itemPrice;
    }

    public int getItemQuantity() {
        return itemQuantity;
    }

    public String getArrivalDate() {
        return arrivalDate;
    }

    public String getExpiredDate() {
        return expiredDate;
    }
}
