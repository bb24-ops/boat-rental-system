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
public class UcitajIznajmljivanjaSO extends ApstraktnaGenerickaOperacija {

    List<Iznajmljivanje> iznajmljivanja;
    @Override
    protected void preduslovi(Object objekat) throws Exception {
        //ucitavanje nema preduslove!
    }

    @Override
    protected void izvrsiOperaciju(Object param, String kljuc) throws Exception {
        String uslov = " i JOIN Administrator a ON i.administrator = a.idAdministrator JOIN Korisnik k ON i.korisnik = k.idKorisnik ";
        iznajmljivanja = broker.getAll(new Iznajmljivanje(),uslov);
    }

    public List<Iznajmljivanje> getIznajmljivanja() {
        return iznajmljivanja;
    }
}