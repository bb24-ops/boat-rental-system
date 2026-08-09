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
public class Skiper implements ApstraktniDomenskiObjekat{
    private int idSkiper;
    private String ime;
    private int brojTerminaUkupno;
    private String sertifikat;

    public Skiper() {
    }

    public Skiper(int idSkiper,String ime, int brojTerminaUkupno, String sertifikat) {
        this.idSkiper = idSkiper;
        this.ime = ime;
        this.brojTerminaUkupno = brojTerminaUkupno;
        this.sertifikat = sertifikat;
    }

    public int getIdSkiper() {
        return idSkiper;
    }

    public void setIdSkiper(int idSkiper) {
        this.idSkiper = idSkiper;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public int getBrojTerminaUkupno() {
        return brojTerminaUkupno;
    }

    public void setBrojTerminaUkupno(int brojTerminaUkupno) {
        this.brojTerminaUkupno = brojTerminaUkupno;
    }

    public String getSertifikat() {
        return sertifikat;
    }

    public void setSertifikat(String sertifikat) {
        this.sertifikat = sertifikat;
    }

    @Override
    public String toString() {
        return ime + ": " + brojTerminaUkupno + " termina";
    }

    @Override
    public int hashCode() {
        int hash = 3;
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
        final Skiper other = (Skiper) obj;
        if (this.brojTerminaUkupno != other.brojTerminaUkupno) {
            return false;
        }
        if (!Objects.equals(this.ime, other.ime)) {
            return false;
        }
        return Objects.equals(this.sertifikat, other.sertifikat);
    }

    

    @Override
    public String vratiNazivTabele() {
        return "skiper";
    }

    @Override
    public List<ApstraktniDomenskiObjekat> vratiListu(ResultSet rs) throws Exception {
        List<ApstraktniDomenskiObjekat> lista = new ArrayList<>();
        while(rs.next()){
            //OBAVENZNO ID!
            int idSkiper = rs.getInt("skiper.idSkiper");
            
            String ime = rs.getString("skiper.ime");
            int brojTerminaUkupno = rs.getInt("skiper.brojTerminaUkupno");
            String sertifikat = rs.getString("skiper.sertifikat");
            
            Skiper s = new Skiper(idSkiper, ime, brojTerminaUkupno, sertifikat);
            
            lista.add(s);
        }
        
        
        
        return lista;
    }

    @Override
    public String vratiKoloneZaUbacivanje() {
        return "ime,brojTerminaUkupno,sertifikat";
    }

    @Override
    public String vratiVrednostiZaUbacivanje() {
        return "'"+ime+"',"+brojTerminaUkupno+",'"+sertifikat+"'";
    }

    @Override
    public String vratiPrimarniKljuc() {
        return "skiper.idSkiper="+idSkiper;
    }

    @Override
    public ApstraktniDomenskiObjekat vratiObjekatIzRs(ResultSet rs) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String vratiVrednostiZaIzmenu() {
        return "ime='"+ime+"', brojTerminaUkupno="+brojTerminaUkupno+", sertifikat='"+sertifikat+"'";
    }
    
    
}
