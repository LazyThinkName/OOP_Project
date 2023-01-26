/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.utem.groupproject;

import java.io.Serializable;


/**
 *
 * @author mangy
 */
public abstract class Drink extends Item implements Serializable{
    public Drink(){}
    
    @Override
    public void setItemID(int num){
        super.itemID = "D-" + String.valueOf(num);
    }
    
    public void setItemID(String itemID){
        super.itemID = itemID;
    }
    
    @Override
    public void setItemType(){
        super.itemType = "Drink";
    }
    
    public void setItemType(String type){
        super.itemType = type;
    }
}
