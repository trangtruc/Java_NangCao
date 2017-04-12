package servlet;

import java.io.Serializable;
import java.util.ArrayList;

import servlet.Sanpham;


public class Sanpham  implements Serializable{
    private String title;
    private String author;
    private float price;
    private String id;
    private int qty;
    
    public Sanpham(String id,String title, String author, float price, int qty) {
    	this.id = id;
        this.title = title;
    	this.author = author;
        this.price = price;
        this.qty = qty;
    }
    
    
    public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

    public String getId() {
        return id;
    }
 
    public void setId(String id) {
        this.id = id;
    }
    
    public void setTitle(String title){
    	this.title = title;
    }
    
    public String getTitle(){
    	return title;
    }
    
    public void setAuthor(String author){
    	this.author = author;
    }
    
    public String getAuthor(){
    	return author;
    }
    
    public int getQty() {
        return qty;
    }
 
    public void setQty(int qty) {
        this.qty = qty;
    }
    
  
 
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        Sanpham other = (Sanpham) obj;
        if ((this.id == null) ? (other.id != null) : !this.id.equals(other.id)) {
            return false;
        }
        return true;
    }
 
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 19 * hash + (this.id != null ? this.id.hashCode() : 0);
        return hash;
    }
 
    @Override
    public String toString() {
        return id+";"+ title;
    }    
}
