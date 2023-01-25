/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.utem.groupproject;

import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author mangy
 */
class DrinkManager implements ItemManager{
    private Drink drink = new Drink() {};
    private ArrayList<Drink> drinkList;
    private Database db = new Database();
    
    public ArrayList<Drink> readAllDrink() throws ClassNotFoundException{
        drinkList = new ArrayList<Drink>();
        try{
            String SQL = "SELECT * FROM drink";
            
            PreparedStatement ps = db.openConnection().prepareStatement(SQL);
            
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                drink = new Drink(){};
                
                drink.setItemID(rs.getString("DRINKID"));
                drink.setItemName(rs.getString("NAME"));
                drink.setItemType(rs.getString("TYPE"));
                drink.setArrivalDate(rs.getString("ARRIVALDATE"));
                drink.setExpiredDate(rs.getString("EXPIREDDATE"));
                drink.setItemPrice(rs.getDouble("PRICE"));
                drink.setItemQuantity(rs.getInt("QUANTITY"));
                
                drinkList.add(drink);
            }
        }
        catch (SQLException err)
        {
            JOptionPane.showMessageDialog(null,err.getMessage());
        }
         JOptionPane.showMessageDialog(null,"Connected");
        return drinkList;
    }
    
    public void edit(String editDrinkID, Drink drink) throws ClassNotFoundException{
        String arrival = drink.getArrivalDate();
        String expired = drink.getExpireDate();
        double price = drink.getItemPrice();
        int qty = drink.getItemQuantity();
        
        try{
            String SQL = "UPDATE drink SET ARRIVALDATE=?, EXPIREDDATE=?, PRICE = ?, QUANTITY = ?, WHERE DRINKID = ?";
            //Declare object to execute parameterized query
            PreparedStatement ps = db.openConnection().prepareStatement(SQL);
            ps.setString(1, arrival);
            ps.setString(2, expired);
            ps.setDouble(3, price);
            ps.setInt(4,qty);
            ps.setString(5, editDrinkID);
            ps.executeUpdate();
        }
        catch(SQLException err){
            JOptionPane.showMessageDialog(null, err.getMessage());
        }
    }
    
    public void saveDrink(Drink drink)throws ClassNotFoundException{
        
        String drinkID = drink.getItemID();
        String type = drink.getItemType();
        String name = drink.getItemName();
        String arrival = drink.getArrivalDate();
        String expired = drink.getExpireDate();
        double price = drink.getItemPrice();
        int qty = drink.getItemQuantity();
        
        try {
                        
            String SQL = "INSERT INTO drink VALUES (?,?,?,?,?,?,?)";
            
            PreparedStatement ps = db.openConnection().prepareStatement(SQL);
            
            ps.setString(1, drinkID);
            ps.setString(2, name);
            ps.setString(3, type);
            ps.setString(4, arrival);
            ps.setString(5, expired);
            ps.setDouble(6, price);
            ps.setInt(7,qty);
            
            ps.executeUpdate();
        }
        catch (SQLException err){
            JOptionPane.showMessageDialog(null,err.getMessage());
        }
    }
    
    @Override
    public void delete(String deleteDrinkID){
        try{           
            String SQL = "DELETE FROM drink where DRINKID=?";
            PreparedStatement ps = db.openConnection().prepareStatement(SQL);
            ps.setString(1, deleteDrinkID);
            ps.executeUpdate();       
        }
        catch(SQLException err){
            JOptionPane.showMessageDialog(null, err.getMessage());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DrinkManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public Drink searchDrink(String searchID) throws ClassNotFoundException{
        try{
                        
            String SQL = "SELECT * FROM Drink where DRINKID=?";
            
            PreparedStatement ps = db.openConnection().prepareStatement(SQL);
            
            ps.setString(1,searchID);
            
            ResultSet rs = ps.executeQuery();
            
            if(rs.next()){
                
                drink.setItemID(rs.getString("DRINKID"));
                drink.setItemName(rs.getString("NAME"));
                drink.setItemType(rs.getString("TYPE"));
                drink.setArrivalDate(rs.getString("ARRIVALDATE"));
                drink.setArrivalDate(rs.getString("EXPIREDDATE"));
                drink.setItemPrice(rs.getDouble("PRICE"));
                drink.setItemQuantity(rs.getInt("QUANTITY"));
                
            }
        }
        catch(SQLException err){
            JOptionPane.showMessageDialog(null,err.getMessage());
        }
        return drink;
    }
    
    public ArrayList<Drink> searchDrink(String search, int searchBy)throws ClassNotFoundException{
        ArrayList<Drink> result=new ArrayList<>();
        ArrayList<Drink> list;
        list=readAllDrink();
        
        switch (searchBy) {
            case 0:
                for (int i=0;i<list.size();i++)
                {
                    if ("Drink".equals(list.get(i).getItemType()) &&(list.get(i).getItemID() == null ? search == null : list.get(i).getItemID().equals(search)))
                    {
                        result.add(list.get(i));
                    }
                }       break;
            case 1:
                for (int i=0;i<list.size();i++)
                {
                    if ("Drink".equals(list.get(i).getItemType()) &&(list.get(i).getItemName() == null ? search == null : list.get(i).getItemName().equals(search)))
                    {
                        result.add(list.get(i));
                    }
                }       break;
            case 2:
                for (int i=0;i<list.size();i++)
                {
                    if ("Drink".equals(list.get(i).getItemType()) &&(list.get(i).getArrivalDate() == null ? search == null : list.get(i).getArrivalDate().equals(search)))
                    {
                        result.add(list.get(i));
                    }
                }       break;
            case 3:
                for (int i=0;i<list.size();i++)
                {
                    if ("Drink".equals(list.get(i).getItemType()) && (list.get(i).getExpireDate() == null ? search == null : list.get(i).getExpireDate().equals(search)))
                    {
                        result.add(list.get(i));
                    }
                }       break;
            default:
                break;
        }
        return result;
    }
    
    public ArrayList<Drink> searchDrink(double max, double min)throws ClassNotFoundException{
        ArrayList<Drink> result=new ArrayList<>();
        ArrayList<Drink> list;
        list=readAllDrink();
        for (int i=0;i<list.size();i++)
        {
            if ("Drink".equals(list.get(i).getItemType()) && (list.get(i).getItemPrice() >= min && list.get(i).getItemPrice() <= max))
            {
                result.add(list.get(i));
            }
        }
        return result;
    }
    
    public ArrayList<Drink> searchDrink(int max, int min)throws ClassNotFoundException{
        ArrayList<Drink> result=new ArrayList<>();
        ArrayList<Drink> list;
        list=readAllDrink();
        for (int i=0;i<list.size();i++)
        {
            if ("Drink".equals(list.get(i).getItemType()) && (list.get(i).getItemQuantity() >= min && list.get(i).getItemQuantity() <= max))
            {
                result.add(list.get(i));
            }
        }
        return result;
    }

    @Override
    public void restock(String restockID,int restockNum) {
        try {
            drink = searchDrink(restockID);
            if (drink.getItemID() == null){
                JOptionPane.showMessageDialog(null, "No Item Found! Please insert correct ID!");
                return;
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(FoodManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            String SQL = "UPDATE Drink set QUANTITY = ? WHERE DRINKID = ?";
            PreparedStatement ps = db.openConnection().prepareStatement(SQL);
            
            drink.setItemQuantity(restockNum + drink.getItemQuantity());
            int newQty = drink.getItemQuantity();
            JOptionPane.showMessageDialog(null, "The new Quantity for " + drink.getItemID() + "/" +
                   drink.getItemName() + " is " + drink.getItemQuantity());
            
            ps.setInt(1,newQty);
            ps.setString(2, restockID);
            ps.executeUpdate();
            
            
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(FoodManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
