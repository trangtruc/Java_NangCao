package shopping;

import java.io.Serializable;


public class Sanpham  implements Serializable{
    private String msSP;
    private String tenSP;
    private float dongia;
 
    public Sanpham(String msSP, String tenSP, float dongia) {
        this.msSP = msSP;
        this.tenSP = tenSP;
        this.dongia = dongia;
    }
 
    public float getDongia() {
        return dongia;
    }
 
    public void setDongia(float dongia) {
        this.dongia = dongia;
    }
 
    public String getMsSP() {
        return msSP;
    }
 
    public void setMsSP(String msSP) {
        this.msSP = msSP;
    }
 
    public String getTenSP() {
        return tenSP;
    }
 
    public void setTenSP(String tenSP) {
        this.tenSP = tenSP;
    }
 
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Sanpham other = (Sanpham) obj;
        if ((this.msSP == null) ? (other.msSP != null) : !this.msSP.equals(other.msSP)) {
            return false;
        }
        return true;
    }
 
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 19 * hash + (this.msSP != null ? this.msSP.hashCode() : 0);
        return hash;
    }
 
    @Override
    public String toString() {
        return msSP+";"+ tenSP;
    }    
}
