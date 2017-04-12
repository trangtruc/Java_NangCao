package servlet;

import java.io.Serializable;
import java.util.ArrayList;

public class Monhang implements Serializable{
    private Sanpham sanpham;
    private int soluong;
    private ArrayList<Sanpham> sanpham1;
 
    public Monhang(Sanpham sanpham, int soluong) {
        this.sanpham = sanpham;
        this.soluong = soluong;
    }
    
    public Monhang(ArrayList<Sanpham> sanpham1, int soluong) {
        this.sanpham1 = sanpham1;
        this.soluong = soluong;
    }
    
    public Sanpham getSanpham() {
        return sanpham;
    }
    
 
 
    public void setSanpham(Sanpham sanpham) {
        this.sanpham = sanpham;
    }
 
    public int getSoluong() {
        return soluong;
    }
 
    public void setSoluong(int soluong) {
        this.soluong = soluong;
    }
 
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Monhang other = (Monhang) obj;
        if (this.sanpham != other.sanpham && (this.sanpham == null || !this.sanpham.equals(other.sanpham))) {
            return false;
        }
        return true;
    }
 
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + (this.sanpham != null ? this.sanpham.hashCode() : 0);
        return hash;
    }
}


