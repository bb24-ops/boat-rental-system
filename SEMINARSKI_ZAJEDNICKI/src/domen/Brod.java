/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domen;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author boris
 */
public class Brod implements ApstraktniDomenskiObjekat{
    private int idBrod;
    private String naziv;
    private TipBroda tip;
    private KategorijaBroda kategorija;
    private double cenaPoDanu;

    public Brod() {
    }

    public Brod(int idBrod, String naziv, TipBroda tip, KategorijaBroda kategorija, double cenaPoDanu) {
        this.idBrod = idBrod;
        this.naziv = naziv;
        this.tip = tip;
        this.kategorija = kategorija;
        this.cenaPoDanu = cenaPoDanu;
    }

    public int getIdBrod() {
        return idBrod;
    }

    public void setIdBrod(int idBrod) {
        this.idBrod = idBrod;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public TipBroda getTip() {
        return tip;
    }

    public void setTip(TipBroda tip) {
        this.tip = tip;
    }

    public KategorijaBroda getKategorija() {
        return kategorija;
    }

    public void setKategorija(KategorijaBroda kategorija) {
        this.kategorija = kategorija;
    }

    public double getCenaPoDanu() {
        return cenaPoDanu;
    }

    public void setCenaPoDanu(double cenaPoDanu) {
        this.cenaPoDanu = cenaPoDanu;
    }

    @Override
    public String toString() {
        return naziv;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Brod other = (Brod) obj;
        if (!Objects.equals(this.naziv, other.naziv)) {
            return false;
        }
        if (this.tip != other.tip) {
            return false;
        }
        return this.kategorija == other.kategorija;
    }

    @Override
    public String vratiNazivTabele() {
        return "brod";
    }

    @Override
    public List<ApstraktniDomenskiObjekat> vratiListu(ResultSet rs) throws Exception {
        List<ApstraktniDomenskiObjekat> lista = new ArrayList<>();
        while(rs.next()){
            //OBAVENZNO ID!
            int idBrod = rs.getInt("brod.idBrod");
            
            String naziv = rs.getString("brod.naziv");
            //Zvanje z = Zvanje.valueOf(rs.getString("zvanje"));
            TipBroda t = TipBroda.valueOf(rs.getString("brod.tip"));
            KategorijaBroda kb = KategorijaBroda.valueOf(rs.getString("brod.kategorija"));
            double cenaPoDanu = rs.getDouble("brod.cenaPoDanu");
         
            
            Brod b = new Brod(idBrod, naziv, t, kb, cenaPoDanu);
            
            lista.add(b);
        }
        
        
        
        return lista;
    }

    @Override
    public String vratiKoloneZaUbacivanje() {
        return "naziv,tip,kategorija,cenaPoDanu";
    }

    @Override
    public String vratiVrednostiZaUbacivanje() {
        //ENUM! DA LI MORA .toString da ide????!
        return "'"+naziv+"','"+tip.toString()+"','"+kategorija.toString()+"',"+cenaPoDanu;
    }

    @Override
    public String vratiPrimarniKljuc() {
        return "brod.idBrod="+idBrod;
    }

    @Override
    public ApstraktniDomenskiObjekat vratiObjekatIzRs(ResultSet rs) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String vratiVrednostiZaIzmenu() {
        return "naziv='"+naziv+"', tip='"+tip.toString()+"', kategorija='"+kategorija.toString()+"', cenaPoDanu=" + cenaPoDanu;
    }
    
    
    
}
