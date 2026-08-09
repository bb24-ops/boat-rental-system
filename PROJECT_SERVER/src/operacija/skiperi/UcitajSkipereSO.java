/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacija.skiperi;

import domen.Skiper;
import java.util.List;
import operacija.ApstraktnaGenerickaOperacija;

/**
 *
 * @author boris
 */
public class UcitajSkipereSO extends ApstraktnaGenerickaOperacija {

    List<Skiper> skiperi;
    @Override
    protected void preduslovi(Object objekat) throws Exception {
        //nema preduslova!
    }

    @Override
    protected void izvrsiOperaciju(Object param, String kljuc) throws Exception {
        skiperi = broker.getAll(new Skiper(),"");
        //System.out.println("KLASA UcitajSkipereSO:" + skiperi);
    }

    public List<Skiper> getSkiperi() {
        return skiperi;
    }

    public void setSkiperi(List<Skiper> skiperi) {
        this.skiperi = skiperi;
    }
    
}
