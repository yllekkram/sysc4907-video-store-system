/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.team33.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 *
 * @author Daywalker
 */
class ShoppingCart {
    
    private HashMap<ShoppingCartKey, Integer> shoppingCartList;
    
    public ShoppingCart(){
        shoppingCartList = new HashMap<ShoppingCartKey, Integer>();
    }
    
    public void addToCart(int videoId, boolean isRented){
        ShoppingCartKey key = new ShoppingCartKey(videoId, isRented);
        if (this.shoppingCartList.containsKey(key)){
            this.shoppingCartList.put(key, this.shoppingCartList.get(key) + 1);
        }else{
            this.shoppingCartList.put(key, 1);
        }
        //this.shoppingCartList.put(new ShoppingCart, isRented);
    }
    
    public void removeFromCart(int videoId, boolean isRented){
        ShoppingCartKey key = new ShoppingCartKey(videoId, isRented);
        if (!this.shoppingCartList.containsKey(key)){
            return;
        }
        this.shoppingCartList.put(key, this.shoppingCartList.get(key) - 1);
        if (this.shoppingCartList.get(key) <= 0){
            this.shoppingCartList.remove(key);
        }
    }
    
    public List<Integer> getVideoList(){
        ArrayList<Integer> list = new ArrayList<Integer>();
        for(ShoppingCartKey i : this.shoppingCartList.keySet()){
            list.add(i.getVideoId());
        }
        return list;
    }
    
    public List<ShoppingCartKey> getShoppingCartInfo(){
        ArrayList<ShoppingCartKey> info = new ArrayList<ShoppingCartKey>();
        
        for (ShoppingCartKey key : this.shoppingCartList.keySet()){
            int copies = this.shoppingCartList.get(key);
            for (int i = 0; i < copies; i++){
                info.add(key);
            }
        }
        
        return info;
    }   
    
    public Set<Integer> getPurchaseList(){
        Set<Integer> list = new HashSet<Integer>();
        for(ShoppingCartKey i : this.shoppingCartList.keySet()){
            if (!i.getIsRented()){
                list.add(i.getVideoId());
            }
        }
        return list;
    }
    
    public Set<Integer> getRentedList(){
        Set<Integer> list = new HashSet<Integer>();
        for(ShoppingCartKey i : this.shoppingCartList.keySet()){
            if (i.getIsRented()){
                list.add(i.getVideoId());
            }
        }
        return list;
    }
    
    @Override
    public String toString(){
        String s = "";
        
        for (ShoppingCartKey i : this.shoppingCartList.keySet()){
            if (i.getIsRented()){
                s += "R:" + i.getVideoId();
            }else{
                s += "P:" + i.getVideoId();
            }
            s += ",";
        }
        
        return s.substring(0, s.length() - 1);
    }
    
    public class ShoppingCartKey{
        
        private int videoId;
        private boolean isRented;
        
        public ShoppingCartKey(int videoId, boolean isRented){
            this.isRented = isRented;
            this.videoId = videoId;
        }
        
        public ShoppingCartKey(ShoppingCartKey key){
            this.isRented = key.isRented;
            this.videoId = key.videoId;
        }

        public int getVideoId() {
            return videoId;
        }

        public boolean getIsRented() {
            return isRented;
        }

        @Override
        public int hashCode() {
            int hash = 3;
            hash = 17 * hash + this.videoId;
            hash = 17 * hash + (this.isRented ? 1 : 0);
            return hash;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == null) {
                return false;
            }
            if (getClass() != obj.getClass()) {
                return false;
            }
            final ShoppingCartKey other = (ShoppingCartKey) obj;
            if (this.videoId != other.videoId) {
                return false;
            }
            if (this.isRented != other.isRented) {
                return false;
            }
            return true;
        }
        
    }
}
