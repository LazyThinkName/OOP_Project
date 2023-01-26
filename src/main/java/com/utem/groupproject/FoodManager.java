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
public class FoodManager implements Deletable,Restockable{
    private ArrayList<Food> foodList;
    private Database db = new Database();
    private Food food = new Food() {};
    
    public ArrayList<Food> readAllFood() throws ClassNotFoundException {
        foodList = new ArrayList<Food>();
        
        try{
            String SQL = "SELECT * FROM food";
            
            PreparedStatement ps = db.openConnection().prepareStatement(SQL);
            
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                food = new Food(){};
                
                food.setItemID(rs.getString("FOODID"));
                food.setItemName(rs.getString("NAME"));
                food.setItemType(rs.getString("TYPE"));
                food.setArrivalDate(rs.getString("ARRIVALDATE"));
                food.setExpiredDate(rs.getString("EXPIREDDATE"));
                food.setItemPrice(rs.getDouble("PRICE"));
                food.setItemQuantity(rs.getInt("QUANTITY"));
                
                foodList.add(food);   
            }
        }
        catch (SQLException err)
        {
            JOptionPane.showMessageDialog(null,err.getMessage());
            JOptionPane.showMessageDialog(null,"No Food Found!");
        }
        return foodList;
    }
    
    public void saveFood(Food food) throws ClassNotFoundException{
        
        String foodID = food.getItemID();
        String type = food.getItemType();
        String name = food.getItemName();
        String arrival = food.getArrivalDate();
        String expired = food.getExpiredDate();
        double price = food.getItemPrice();
        int qty = food.getItemQuantity();
        
        try {
                        
            String SQL = "INSERT INTO food VALUES (?,?,?,?,?,?,?)";
            
            PreparedStatement ps = db.openConnection().prepareStatement(SQL);
            
            ps.setString(1, foodID);
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
    
    public void editFood(String editFoodID, Food food) throws ClassNotFoundException{
        String arrival = food.getArrivalDate();
        String expired = food.getExpiredDate();
        double price = food.getItemPrice();
        int qty = food.getItemQuantity();
        
        try{
            String SQL = "UPDATE food SET ARRIVALDATE = ?, EXPIREDDATE = ?, PRICE = ?, QUANTITY = ? WHERE FOODID = ?";
            //Declare object to execute parameterized query
            PreparedStatement ps = db.openConnection().prepareStatement(SQL);
            ps.setString(1, arrival);
            ps.setString(2, expired);
            ps.setDouble(3, price);
            ps.setInt(4,qty);
            ps.setString(5, editFoodID);
            ps.executeUpdate();
        }
        catch(SQLException err){
            JOptionPane.showMessageDialog(null, err.getMessage());
        }
    }
    
    public Food searchFood(String searchID) throws ClassNotFoundException{
        try{
                        
            String SQL = "SELECT * FROM Food where FOODID=?";
            
            PreparedStatement ps = db.openConnection().prepareStatement(SQL);
            
            ps.setString(1,searchID);
            
            ResultSet rs = ps.executeQuery();
            
            if(rs.next()){
                
                food.setItemID(rs.getString("FOODID"));
                food.setItemName(rs.getString("NAME"));
                food.setItemType(rs.getString("TYPE"));
                food.setArrivalDate(rs.getString("ARRIVALDATE"));
                food.setArrivalDate(rs.getString("EXPIREDDATE"));
                food.setItemPrice(rs.getDouble("PRICE"));
                food.setItemQuantity(rs.getInt("QUANTITY"));
                
                if (food.getItemID() == null){
                JOptionPane.showMessageDialog(null, "No Item Found! Please insert correct ID!");
                }
            }
        }
        catch(SQLException err){
            JOptionPane.showMessageDialog(null,err.getMessage());
        }
        
        return food;
    }
    
    public ArrayList<Food> searchFood(String search, int searchBy)throws ClassNotFoundException{
        ArrayList<Food> result=new ArrayList<>();
        ArrayList<Food> list;
        list=readAllFood();
        
        switch (searchBy) {
            case 0:
                for (int i=0;i<list.size();i++)
                {
                    if ("Food".equals(list.get(i).getItemType()) &&(list.get(i).getItemID() == null ? search == null : list.get(i).getItemID().equals(search)))
                    {
                        result.add(list.get(i));
                    }
                }       break;
            case 1:
                for (int i=0;i<list.size();i++)
                {
                    if ("Food".equals(list.get(i).getItemType()) &&(list.get(i).getItemName() == null ? search == null : list.get(i).getItemName().equals(search)))
                    {
                        result.add(list.get(i));
                    }
                }       break;
            case 2:
                for (int i=0;i<list.size();i++)
                {
                    if ("Food".equals(list.get(i).getItemType()) &&(list.get(i).getArrivalDate() == null ? search == null : list.get(i).getArrivalDate().equals(search)))
                    {
                        result.add(list.get(i));
                    }
                }       break;
            case 3:
                for (int i=0;i<list.size();i++)
                {
                    if ("Food".equals(list.get(i).getItemType()) && (list.get(i).getExpiredDate() == null ? search == null : list.get(i).getExpiredDate().equals(search)))
                    {
                        result.add(list.get(i));
                    }
                }       break;
            default:
                break;
        }
        return result;
    }
    
    public ArrayList<Food> searchFood(double max, double min)throws ClassNotFoundException{
        ArrayList<Food> result=new ArrayList<>();
        ArrayList<Food> list;
        list=readAllFood();
        for (int i=0;i<list.size();i++)
        {
            if ("Food".equals(list.get(i).getItemType()) && (list.get(i).getItemPrice() >= min && list.get(i).getItemPrice() <= max))
            {
                result.add(list.get(i));
            }
        }
        return result;
    }
    
    public ArrayList<Food> searchFood(int max, int min)throws ClassNotFoundException{
        ArrayList<Food> result=new ArrayList<>();
        ArrayList<Food> list;
        list=readAllFood();
        for (int i=0;i<list.size();i++)
        {
            if ("Food".equals(list.get(i).getItemType()) && (list.get(i).getItemQuantity() >= min && list.get(i).getItemQuantity() <= max))
            {
                result.add(list.get(i));
            }
        }
        return result;
    }
    
    @Override
    public void delete(String deleteFoodID) {
        try{           
            String SQL = "DELETE FROM food where FOODID=?";
            PreparedStatement ps = db.openConnection().prepareStatement(SQL);
            ps.setString(1, deleteFoodID);
            ps.executeUpdate();       
        }
        catch(SQLException err){
            JOptionPane.showMessageDialog(null, err.getMessage());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(FoodManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void restock(String restockID,int restockNum) {
        try {
            food = searchFood(restockID);
            if (food.getItemID() == null){
                JOptionPane.showMessageDialog(null, "No Item Found! Please insert correct ID!");
                return;
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(FoodManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            String SQL = "UPDATE Food set QUANTITY = ? WHERE FOODID = ?";
            PreparedStatement ps = db.openConnection().prepareStatement(SQL);
            
            food.setItemQuantity(restockNum + food.getItemQuantity());
            int newQty = food.getItemQuantity();
            
            JOptionPane.showMessageDialog(null, "The new Quantity for " + food.getItemID() + "/" +
                   food.getItemName() + " is " + food.getItemQuantity());
            
            ps.setInt(1,newQty);
            ps.setString(2, restockID);
            ps.executeUpdate();
            
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(FoodManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
}
