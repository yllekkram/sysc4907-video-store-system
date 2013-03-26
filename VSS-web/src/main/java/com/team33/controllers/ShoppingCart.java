/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.team33.controllers;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author Daywalker
 */
class ShoppingCart {
    
    static ShoppingCart fromString(String shoppingCartString){
        ShoppingCart cart = new ShoppingCart();
        if (shoppingCartString == null){
            return cart;
        }
        String videos[] = shoppingCartString.split(",");
        for(String video : videos){
            String vidInfo[] = video.split(":");
            if (vidInfo[0].equals("R")){
                cart.addToCart(Integer.parseInt(vidInfo[1]), true);
            }else{
                cart.addToCart(Integer.parseInt(vidInfo[1]), false);
            }
        }
        return cart;
    }
    
    private HashMap<Integer, Boolean> shoppingCartList;
    
    private ShoppingCart(){
        shoppingCartList = new HashMap<Integer, Boolean>();
    }
    
    public void addToCart(int videoId, boolean isRented){
        this.shoppingCartList.put(videoId, isRented);
    }
    
    public void removeFromCart(int videoId){
        this.shoppingCartList.remove(videoId);
    }
    
    public Set<Integer> getVideoList(){
        return this.shoppingCartList.keySet();
    }
    
    public Set<Integer> getPurchaseList(){
        Set<Integer> list = new HashSet<Integer>();
        for(Integer i : this.shoppingCartList.keySet()){
            if (!this.shoppingCartList.get(i).booleanValue()){
                list.add(i);
            }
        }
        return list;
    }
    
    public Set<Integer> getRentedList(){
        Set<Integer> list = new HashSet<Integer>();
        for(Integer i : this.shoppingCartList.keySet()){
            if (this.shoppingCartList.get(i).booleanValue()){
                list.add(i);
            }
        }
        return list;
    }
    
    @Override
    public String toString(){
        String s = "";
        
        for (Integer i : this.shoppingCartList.keySet()){
            if (this.shoppingCartList.get(i).booleanValue()){
                s += "R:" + i.intValue();
            }else{
                s += "P:" + i.intValue();
            }
            s += ",";
        }
        
        return s.substring(0, s.length() - 2);
    }
}
