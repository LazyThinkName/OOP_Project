/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.utem.groupproject;

import java.sql.*;
import java.util.*;
import javax.swing.JOptionPane;

/**
 *
 * @author mangy
 */
public class ItemManager{
    private ArrayList<Food> foodList;
    private ArrayList<Drink> drinkList;
    
    private Food food = new Food() {};
    private Drink drink = new Drink() {};
    
    private Database db = new Database();

    public ItemManager() {
    }
    
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
        }
        return foodList;
    }
    
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
    
    public void saveFood(Food food) throws ClassNotFoundException{
        
        String foodID = food.getItemID();
        String type = food.getItemType();
        String name = food.getItemName();
        String arrival = food.getArrivalDate();
        String expired = food.getExpireDate();
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
    
    public void restockItem(String itemID,int selectedType,int num) throws ClassNotFoundException{
        if (selectedType == 0){
            //searchFood();
        }
        else{
            //searchDrink();
        }
    }
    
    public void editDrink(String editDrinkID, Drink drink) throws ClassNotFoundException{
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
    
     public void editFood(String editFoodID, Food food) throws ClassNotFoundException{
        String arrival = food.getArrivalDate();
        String expired = food.getExpireDate();
        double price = food.getItemPrice();
        int qty = food.getItemQuantity();
        
        try{
            String SQL = "UPDATE food SET ARRIVALDATE=?, EXPIREDDATE=?, PRICE = ?, QUANTITY = ?, WHERE FOODID = ?";
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
    
    
    public void deleteDrink(String deleteDrinkID) throws ClassNotFoundException{
        try{           
            String SQL = "DELETE FROM drink where DRINKID=?";
            PreparedStatement ps = db.openConnection().prepareStatement(SQL);
            ps.setString(1, deleteDrinkID);
            ps.executeUpdate();       
        }
        catch(SQLException err){
            JOptionPane.showMessageDialog(null, err.getMessage());
        }
    }

    public void deleteFood(String deleteFoodID) throws ClassNotFoundException{
        try{           
            String SQL = "DELETE FROM food where FOODID=?";
            PreparedStatement ps = db.openConnection().prepareStatement(SQL);
            ps.setString(1, deleteFoodID);
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
                
            }
        }
        catch(SQLException err){
            JOptionPane.showMessageDialog(null,err.getMessage());
        }
        return food;
    }
    
    public Drink searchDrink(String searchID) throws ClassNotFoundException{
        try{
                        
            String SQL = "SELECT * FROM Drink where DRINKID=?";
            
            PreparedStatement ps = db.openConnection().prepareStatement(SQL);
            
            ps.setString(1,searchID);
            
            ResultSet rs = ps.executeQuery();
            
            if(rs.next()){
                
                food.setItemID(rs.getString("DRINKID"));
                food.setItemName(rs.getString("NAME"));
                food.setItemType(rs.getString("TYPE"));
                food.setArrivalDate(rs.getString("ARRIVALDATE"));
                food.setArrivalDate(rs.getString("EXPIREDDATE"));
                food.setItemPrice(rs.getDouble("PRICE"));
                food.setItemQuantity(rs.getInt("QUANTITY"));
                
            }
        }
        catch(SQLException err){
            JOptionPane.showMessageDialog(null,err.getMessage());
        }
        return drink;
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
                    if ("Food".equals(list.get(i).getItemType()) && (list.get(i).getExpireDate() == null ? search == null : list.get(i).getExpireDate().equals(search)))
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
}
