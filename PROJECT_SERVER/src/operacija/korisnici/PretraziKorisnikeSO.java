/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacija.korisnici;

import domen.Korisnik;
import java.util.List;
import operacija.ApstraktnaGenerickaOperacija;

/**
 *
 * @author boris
 */
public class PretraziKorisnikeSO extends ApstraktnaGenerickaOperacija{
    List<Korisnik> listaKorisnika;
    @Override
    protected void preduslovi(Object objekat) throws Exception {
        
    }

    @Override
    protected void izvrsiOperaciju(Object param, String kljuc) throws Exception {
        Korisnik k = (Korisnik) param;
        String uslov;
        if(k.getIme().contains("/")){
            uslov = " k JOIN luka l ON k.luka = l.idLuka WHERE k.prezime LIKE '%"  + k.getPrezime()+ "%'";
        }else if(k.getPrezime().contains("/")){
            uslov = " k JOIN luka l ON k.luka = l.idLuka WHERE k.ime LIKE '%"  + k.getIme()+ "%'";
        }else{
            uslov = " k JOIN luka l ON k.luka = l.idLuka WHERE k.ime LIKE '%"  + k.getIme()+ "%' AND k.prezime LIKE '%" + k.getPrezime()+ "%'";
        }
        System.out.println(uslov);
        listaKorisnika = broker.getAll(new Korisnik(),uslov);
    }

    public List<Korisnik> getListaKorisnika() {
        return listaKorisnika;
    }
    
}
