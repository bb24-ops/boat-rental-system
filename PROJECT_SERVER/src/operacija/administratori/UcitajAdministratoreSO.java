/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacija.administratori;

import domen.Administrator;
import java.util.List;
import operacija.ApstraktnaGenerickaOperacija;

/**
 *
 * @author boris
 */
public class UcitajAdministratoreSO extends ApstraktnaGenerickaOperacija{
    List<Administrator> admini;
    @Override
    protected void preduslovi(Object objekat) throws Exception {
        //ucitavanje nema preduslova
    }

    @Override
    protected void izvrsiOperaciju(Object param, String kljuc) throws Exception {
        admini = broker.getAll(new Administrator(),"");
    }

    public List<Administrator> getAdmini() {
        return admini;
    }
    
}
