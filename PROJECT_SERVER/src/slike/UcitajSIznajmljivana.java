/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package slike;

import domen.Iznajmljivanje;
import domen.StavkaIzn;
import java.util.List;
import operacija.ApstraktnaGenerickaOperacija;

/**
 *
 * @author boris
 */
public class UcitajSIznajmljivana extends ApstraktnaGenerickaOperacija{
    List<StavkaIzn> stavke;
    @Override
    protected void preduslovi(Object objekat) throws Exception {
        //nema preduslova!
    }

    @Override
    protected void izvrsiOperaciju(Object param, String kljuc) throws Exception {
        String uslov = " JOIN  brod on brod.idBrod = stavkaizn.brod WHERE iznajmljivanje=" + (int)param + " ";
        stavke = broker.getAll(new StavkaIzn(),uslov);
    }

    public List<StavkaIzn> getStavke() {
        return stavke;
    }

    

}
