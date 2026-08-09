/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacija.iznajmljivanja;

import domen.Iznajmljivanje;
import java.util.List;
import operacija.ApstraktnaGenerickaOperacija;

/**
 *
 * @author boris
 */
public class PretraziIznajmljivanjaSO extends ApstraktnaGenerickaOperacija{
    List<Iznajmljivanje> iznajmljivanja;
    @Override
    protected void preduslovi(Object objekat) throws Exception {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    protected void izvrsiOperaciju(Object param, String kljuc) throws Exception {
        Iznajmljivanje i = (Iznajmljivanje) param;
        String iznos = String.valueOf(i.getUkupanIznos());
        String uslov;
        if(i.getKorisnik().getIme().contains("/")){
            uslov = " i JOIN korisnik k ON i.korisnik = k.idKorisnik JOIN administrator a ON i.administrator = a.idAdministrator " + " WHERE i.ukupanIznos <= " + i.getUkupanIznos();
        }else if(i.getUkupanIznos() <= 0 || iznos.isEmpty()){
            uslov = " i JOIN korisnik k ON i.korisnik = k.idKorisnik JOIN administrator a ON i.administrator = a.idAdministrator " + " WHERE k.ime LIKE '%" + i.getKorisnik().getIme() + "%'";
        }else{
            uslov = " i JOIN korisnik k ON i.korisnik = k.idKorisnik JOIN administrator a ON i.administrator = a.idAdministrator " + " WHERE i.ukupanIznos <= " + i.getUkupanIznos() + " AND k.ime LIKE '%" + i.getKorisnik().getIme() + "%'";
        }
        System.out.println(uslov);
        iznajmljivanja = broker.getAll(new Iznajmljivanje(),uslov);
    }

    public List<Iznajmljivanje> getIznajmljivanja() {
        return iznajmljivanja;
    }
    
}
