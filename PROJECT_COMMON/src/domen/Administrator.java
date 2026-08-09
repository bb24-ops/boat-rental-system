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
public class Administrator implements ApstraktniDomenskiObjekat{
    private int idAdministrator;
    private String ime;
    private String prezime;
    private String brojTelefona;
    private String username;
    private String password;

    public Administrator() {
    }

    public Administrator(int idAdministrator, String ime, String prezime, String brojTelefona, String username, String password) {
        this.idAdministrator = idAdministrator;
        this.ime = ime;
        this.prezime = prezime;
        this.brojTelefona = brojTelefona;
        this.username = username;
        this.password = password;
    }

    public int getIdAdministrator() {
        return idAdministrator;
    }

    public void setIdAdministrator(int idAdministrator) {
        this.idAdministrator = idAdministrator;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public String getBrojTelefona() {
        return brojTelefona;
    }

    public void setBrojTelefona(String brojTelefona) {
        this.brojTelefona = brojTelefona;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return ime + " " + prezime;
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
        final Administrator other = (Administrator) obj;
        if (!Objects.equals(this.username, other.username)) {
            return false;
        }
        return Objects.equals(this.password, other.password);
    }

    

    @Override
    public String vratiNazivTabele() {
        return "administrator";
    }

    @Override
    public List<ApstraktniDomenskiObjekat> vratiListu(ResultSet rs) throws Exception {
        List<ApstraktniDomenskiObjekat> lista = new ArrayList<>();
        while(rs.next()){
            //OBAVENZNO ID!
            int idAdministrator = rs.getInt("administrator.idAdministrator");
            
            String ime = rs.getString("administrator.ime");
            String prezime = rs.getString("administrator.prezime");
            String brojTelefona = rs.getString("administrator.brojTelefona");
            String username = rs.getString("administrator.username");
            String password = rs.getString("administrator.password");
            
            Administrator a = new Administrator(idAdministrator, ime, prezime, brojTelefona, username, password);
            
            lista.add(a);
        }
        
        
        
        return lista;
    }

    @Override
    public String vratiKoloneZaUbacivanje() {
        return "ime,prezime,brojTelefona,username,password";
    }

    @Override
    public String vratiVrednostiZaUbacivanje() {
        return "'"+ime+"','"+prezime+"','"+brojTelefona+"','"+username+"','"+password+"'";
    }

    @Override
    public String vratiPrimarniKljuc() {
        return "administrator.idAdministrator="+idAdministrator;
    }

    @Override
    public ApstraktniDomenskiObjekat vratiObjekatIzRs(ResultSet rs) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String vratiVrednostiZaIzmenu() {
        return "ime='"+ime+"', prezime='"+prezime+"', brojTelefona='"+brojTelefona+"', username='"+username+"', password='"+password+"'";
    }
    
    
    
}
